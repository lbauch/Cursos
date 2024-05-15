/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package br.com.teste.telas;

import java.sql.*;
import br.com.teste.dal.ModuloConexao;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Usuário
 */
public class TelaOs extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    private String tipo;

    /**
     * Creates new form TelaOs
     */
    public TelaOs() {
        initComponents();
        conexao = ModuloConexao.conector();
    }

    private void pesquisar() {
        String sql = "select idCliente 'Cp', nomeCliente 'Nome' from clientes where nomeCliente like ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, "%" + tf_Pesquisa.getText() + "%");
            rs = pst.executeQuery();
            tb_Clientes.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void setarCampos() {
        int setar = tb_Clientes.getSelectedRow();
        tf_Id.setText(tb_Clientes.getModel().getValueAt(setar, 0).toString());
    }

    private void emitirOS() {
        String sql = "insert into os(tipo,situacao,equipamento,defeito,servico,tecnico,valor,id_Cliente) "
                + "values(?,?,?,?,?,?,?,?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, tipo);
            pst.setString(2, cb_Situacao.getSelectedItem().toString());
            pst.setString(3, tf_Equipamento.getText());
            pst.setString(4, tf_Defeito.getText());
            pst.setString(5, tf_Servico.getText());
            pst.setString(6, tf_Tecnico.getText());
            pst.setString(7, tf_Valor.getText().replace(",", "."));
            pst.setString(8, tf_Id.getText());
            if ((tf_Id.getText().isEmpty()) || tf_Equipamento.getText().isEmpty() || tf_Defeito.getText().isEmpty() || cb_Situacao.getSelectedItem().equals(" ")) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
            } else {
                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "OS emitida com sucesso");
                    bt_Add.setEnabled(false);
                    bt_Pesquisar.setEnabled(false);
                    bt_Imprimir.setEnabled(true);
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void pesquisarOS() {
        String num_OS = JOptionPane.showInputDialog("número da OS");
        String sql = "select idOS 'OS',date_format(data_os, '%d/%m/%y - %H:%I'), tipo, situacao,equipamento, defeito, servico, tecnico, valor, id_Cliente from os where os = " + num_OS;
        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                tf_NOs.setText(rs.getString(1));
                tf_Data.setText(rs.getString(2));
                String rbtTipo = rs.getString(3);
                if (rbtTipo.equals("OS")) {
                    rb_Os.setSelected(true);
                    tipo = "OS";
                } else {
                    rb_Orcamento.setSelected(true);
                    tipo = "Orçamento";
                }
                cb_Situacao.setSelectedItem(rs.getString(4));
                tf_Equipamento.setText(rs.getString(5));
                tf_Defeito.setText(rs.getString(6));
                tf_Servico.setText(rs.getString(7));
                tf_Tecnico.setText(rs.getString(8));
                tf_Valor.setText(rs.getString(9));
                tf_Id.setText(rs.getString(10));
                bt_Add.setEnabled(false);
                bt_Pesquisar.setEnabled(false);
                bt_Alterar.setEnabled(true);
                bt_Remover.setEnabled(true);
                bt_Imprimir.setEnabled(true);
                tf_Pesquisa.setEnabled(false);
                tb_Clientes.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null, "OS não cadastrada");
            }
        } catch (java.sql.SQLSyntaxErrorException e) {
            JOptionPane.showMessageDialog(null, "nº de OS Inválida");
        } catch (Exception e2) {
            JOptionPane.showMessageDialog(null, e2);
        }

    }

    private void alterarOs() {
        String sql = "update os set tipo =?, situacao = ?, equipamento = ?, defeito = ?, servico = ?, tecnico = ?, valor = ? where idOs = ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, tipo);
            pst.setString(2, cb_Situacao.getSelectedItem().toString());
            pst.setString(3, tf_Equipamento.getText());
            pst.setString(4, tf_Defeito.getText());
            pst.setString(5, tf_Servico.getText());
            pst.setString(6, tf_Tecnico.getText());
            pst.setString(7, tf_Valor.getText().replace(",", "."));
            pst.setString(8, tf_NOs.getText());
            if ((tf_Id.getText().isEmpty()) || tf_Equipamento.getText().isEmpty() || tf_Defeito.getText().isEmpty()|| cb_Situacao.getSelectedItem().equals(" ")) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
            } else {
                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "OS alterada com sucesso");
                    limpar();
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void excluirOS() {
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja exccluir a OS?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            String sql = "delete from os where idOs = ?";
            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, tf_NOs.getText());
                int apagado = pst.executeUpdate();
                if (apagado > 0) {
                    JOptionPane.showMessageDialog(null, "OS Excluída com sucesso");
                    limpar();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    private void limpar() {
        tf_NOs.setText(null);
        tf_Data.setText(null);
        tf_Pesquisa.setText(null);
        ((DefaultTableModel) tb_Clientes.getModel()).setRowCount(0);
        cb_Situacao.setSelectedItem(" ");
        tf_Id.setText(null);
        tf_Equipamento.setText(null);
        tf_Defeito.setText(null);
        tf_Servico.setText(null);
        tf_Tecnico.setText(null);
        tf_Valor.setText(null);
        bt_Add.setEnabled(true);
        bt_Pesquisar.setEnabled(true);
        tf_Pesquisa.setEnabled(true);
        tb_Clientes.setVisible(true);
        bt_Alterar.setEnabled(false);
        bt_Remover.setEnabled(false);
        bt_Imprimir.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        lb_NOs = new javax.swing.JLabel();
        lb_Data = new javax.swing.JLabel();
        tf_NOs = new javax.swing.JTextField();
        tf_Data = new javax.swing.JTextField();
        rb_Orcamento = new javax.swing.JRadioButton();
        rb_Os = new javax.swing.JRadioButton();
        lb_Situacao = new javax.swing.JLabel();
        cb_Situacao = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        tf_Pesquisa = new javax.swing.JTextField();
        lb_ImgPesquisa = new javax.swing.JLabel();
        lb_Id = new javax.swing.JLabel();
        tf_Id = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_Clientes = new javax.swing.JTable();
        lb_Equipamento = new javax.swing.JLabel();
        lb_Defeito = new javax.swing.JLabel();
        tf_Equipamento = new javax.swing.JTextField();
        tf_Defeito = new javax.swing.JTextField();
        tf_Servico = new javax.swing.JTextField();
        lb_Servico = new javax.swing.JLabel();
        lb_Tecnico = new javax.swing.JLabel();
        tf_Tecnico = new javax.swing.JTextField();
        lb_Valor = new javax.swing.JLabel();
        tf_Valor = new javax.swing.JTextField();
        bt_Add = new javax.swing.JButton();
        bt_Alterar = new javax.swing.JButton();
        bt_Pesquisar = new javax.swing.JButton();
        bt_Remover = new javax.swing.JButton();
        bt_Imprimir = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("OS");
        setPreferredSize(new java.awt.Dimension(520, 480));
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lb_NOs.setText("Nº OS");

        lb_Data.setText("Data");

        tf_NOs.setEditable(false);
        tf_NOs.setEnabled(false);

        tf_Data.setEditable(false);
        tf_Data.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        tf_Data.setEnabled(false);

        buttonGroup1.add(rb_Orcamento);
        rb_Orcamento.setText("Orçamento");
        rb_Orcamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb_OrcamentoActionPerformed(evt);
            }
        });

        buttonGroup1.add(rb_Os);
        rb_Os.setSelected(true);
        rb_Os.setText("OS");
        rb_Os.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb_OsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(rb_Orcamento)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rb_Os))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tf_NOs, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lb_NOs))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lb_Data)
                            .addComponent(tf_Data, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb_NOs)
                    .addComponent(lb_Data))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_NOs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_Data, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rb_Os)
                    .addComponent(rb_Orcamento))
                .addContainerGap(73, Short.MAX_VALUE))
        );

        lb_Situacao.setText("*Situação");

        cb_Situacao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Entrega OK", "Orçamento REPROVADO", "Aguardando Aprovação", "Aguardando Peças", "Abandonado pelo Cliente", "Na Bancada", "Retornou" }));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Cliente"));

        tf_Pesquisa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tf_PesquisaKeyReleased(evt);
            }
        });

        lb_ImgPesquisa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/teste/icones/pesquisar.png"))); // NOI18N

        lb_Id.setText("*ID");

        tb_Clientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "CP", "Nome"
            }
        ));
        tb_Clientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_ClientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tb_Clientes);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(tf_Pesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lb_ImgPesquisa))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lb_Id)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tf_Id, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 8, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lb_ImgPesquisa)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(tf_Pesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tf_Id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lb_Id))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );

        lb_Equipamento.setText("*Equipamento");

        lb_Defeito.setText("*Defeito");

        lb_Servico.setText("Serviço");

        lb_Tecnico.setText("Técnico");

        lb_Valor.setText("Valor Total");

        tf_Valor.setText("0");

        bt_Add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/teste/icones/create.png"))); // NOI18N
        bt_Add.setToolTipText("Adicionar");
        bt_Add.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bt_Add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_AddActionPerformed(evt);
            }
        });

        bt_Alterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/teste/icones/update.png"))); // NOI18N
        bt_Alterar.setToolTipText("Alterar");
        bt_Alterar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bt_Alterar.setEnabled(false);
        bt_Alterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_AlterarActionPerformed(evt);
            }
        });

        bt_Pesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/teste/icones/read.png"))); // NOI18N
        bt_Pesquisar.setToolTipText("Pesquisar");
        bt_Pesquisar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bt_Pesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_PesquisarActionPerformed(evt);
            }
        });

        bt_Remover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/teste/icones/delete.png"))); // NOI18N
        bt_Remover.setToolTipText("Remover");
        bt_Remover.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bt_Remover.setEnabled(false);
        bt_Remover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_RemoverActionPerformed(evt);
            }
        });

        bt_Imprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/teste/icones/print.png"))); // NOI18N
        bt_Imprimir.setToolTipText("Imprimir OS");
        bt_Imprimir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bt_Imprimir.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lb_Situacao)
                                .addGap(32, 32, 32)
                                .addComponent(cb_Situacao, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lb_Equipamento)
                            .addComponent(lb_Defeito)
                            .addComponent(lb_Servico)
                            .addComponent(lb_Tecnico))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(tf_Tecnico, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lb_Valor)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tf_Valor, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE))
                            .addComponent(tf_Servico, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tf_Defeito, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tf_Equipamento, javax.swing.GroupLayout.Alignment.LEADING)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bt_Add)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_Alterar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_Pesquisar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_Remover)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_Imprimir)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lb_Situacao)
                            .addComponent(cb_Situacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_Equipamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_Equipamento))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb_Defeito)
                    .addComponent(tf_Defeito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_Servico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_Servico))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb_Tecnico)
                    .addComponent(tf_Tecnico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_Valor)
                    .addComponent(tf_Valor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(bt_Add)
                    .addComponent(bt_Alterar)
                    .addComponent(bt_Pesquisar)
                    .addComponent(bt_Remover)
                    .addComponent(bt_Imprimir))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tf_PesquisaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_PesquisaKeyReleased
        pesquisar();
    }//GEN-LAST:event_tf_PesquisaKeyReleased

    private void tb_ClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_ClientesMouseClicked
        setarCampos();
    }//GEN-LAST:event_tb_ClientesMouseClicked

    private void rb_OrcamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb_OrcamentoActionPerformed
        tipo = "Orçamento";
    }//GEN-LAST:event_rb_OrcamentoActionPerformed

    private void rb_OsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb_OsActionPerformed
        tipo = "OS";
    }//GEN-LAST:event_rb_OsActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        rb_Orcamento.setSelected(true);
        tipo = "Orcamento";
    }//GEN-LAST:event_formInternalFrameOpened

    private void bt_AddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_AddActionPerformed
        emitirOS();
    }//GEN-LAST:event_bt_AddActionPerformed

    private void bt_PesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_PesquisarActionPerformed
        pesquisarOS();
    }//GEN-LAST:event_bt_PesquisarActionPerformed

    private void bt_AlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_AlterarActionPerformed
        alterarOs();
    }//GEN-LAST:event_bt_AlterarActionPerformed

    private void bt_RemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_RemoverActionPerformed
        excluirOS();
    }//GEN-LAST:event_bt_RemoverActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_Add;
    private javax.swing.JButton bt_Alterar;
    private javax.swing.JButton bt_Imprimir;
    private javax.swing.JButton bt_Pesquisar;
    private javax.swing.JButton bt_Remover;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cb_Situacao;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lb_Data;
    private javax.swing.JLabel lb_Defeito;
    private javax.swing.JLabel lb_Equipamento;
    private javax.swing.JLabel lb_Id;
    private javax.swing.JLabel lb_ImgPesquisa;
    private javax.swing.JLabel lb_NOs;
    private javax.swing.JLabel lb_Servico;
    private javax.swing.JLabel lb_Situacao;
    private javax.swing.JLabel lb_Tecnico;
    private javax.swing.JLabel lb_Valor;
    private javax.swing.JRadioButton rb_Orcamento;
    private javax.swing.JRadioButton rb_Os;
    private javax.swing.JTable tb_Clientes;
    private javax.swing.JTextField tf_Data;
    private javax.swing.JTextField tf_Defeito;
    private javax.swing.JTextField tf_Equipamento;
    private javax.swing.JTextField tf_Id;
    private javax.swing.JTextField tf_NOs;
    private javax.swing.JTextField tf_Pesquisa;
    private javax.swing.JTextField tf_Servico;
    private javax.swing.JTextField tf_Tecnico;
    private javax.swing.JTextField tf_Valor;
    // End of variables declaration//GEN-END:variables
}
