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
public class TelaCliente extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    /**
     * Creates new form TelaCliente
     */
    public TelaCliente() {
        initComponents();
        conexao = ModuloConexao.conector();
    }

    private void adicionar() {
        String sql = "insert into clientes(nomeCliente,endereco,foneCliente,emailCliente)"
                + "values(?,?,?,?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, tf_Nome.getText());
            pst.setString(2, tf_Endereco.getText());
            pst.setString(3, tf_Fone.getText());
            pst.setString(4, tf_Email.getText());

            if (tf_Nome.getText().isEmpty() || tf_Endereco.getText().isEmpty()
                    || tf_Fone.getText().isEmpty() || tf_Email.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
            } else {
                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Cliente adicionado com sucesso");
                    limpar();
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    private void pesquisar() {
        String sql = "select idCliente 'Cp', nomeCliente 'Nome', endereco 'Endereco', foneCliente 'Telefone', emailCliente 'Email' from clientes where nomeCliente like ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, "%" + tf_Pesquisa.getText() + "%");
            rs = pst.executeQuery();
            tb_Clientes.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void setar_campos() {
        int setar = tb_Clientes.getSelectedRow();
        tf_idCliente.setText(tb_Clientes.getModel().getValueAt(setar, 0).toString());
        tf_Nome.setText(tb_Clientes.getModel().getValueAt(setar, 1).toString());
        tf_Endereco.setText(tb_Clientes.getModel().getValueAt(setar, 2).toString());
        tf_Fone.setText(tb_Clientes.getModel().getValueAt(setar, 3).toString());
        tf_Email.setText(tb_Clientes.getModel().getValueAt(setar, 4).toString());
        bt_Add.setEnabled(false);

    }

    private void alterar() {
        String sql = "update clientes set nomeCliente=?, endereco = ?, foneCliente = ?, emailCliente = ?"
                + " where idCliente = ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, tf_Nome.getText());
            pst.setString(2, tf_Endereco.getText());
            pst.setString(3, tf_Fone.getText());
            pst.setString(4, tf_Email.getText());
            pst.setString(5, tf_idCliente.getText());
            if (tf_Nome.getText().isEmpty() || tf_Fone.getText().isEmpty() || tf_Endereco.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
            } else {
                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Dados do cliente alterados com sucesso");
                    limpar();
                    bt_Add.setEnabled(true);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void remover() {
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover este cliente?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            String sql = "delete from clientes where idCliente = ?";
            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, tf_idCliente.getText());
                int apagado = pst.executeUpdate();
                if (apagado > 0) {
                    JOptionPane.showMessageDialog(null, "Cliente removido com sucesso!");
                    limpar();
                    bt_Add.setEnabled(true);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    private void limpar() {
        tf_Pesquisa.setText(null);
        tf_idCliente.setText(null);
        tf_Nome.setText(null);
        tf_Endereco.setText(null);
        tf_Fone.setText(null);
        tf_Email.setText(null);
        ((DefaultTableModel) tb_Clientes.getModel()).setRowCount(0);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lb_Nome = new javax.swing.JLabel();
        lb_Endereco = new javax.swing.JLabel();
        lb_Fone = new javax.swing.JLabel();
        lb_Email = new javax.swing.JLabel();
        tf_Nome = new javax.swing.JTextField();
        tf_Endereco = new javax.swing.JTextField();
        tf_Fone = new javax.swing.JTextField();
        tf_Email = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        bt_Add = new javax.swing.JButton();
        bt_Remove = new javax.swing.JButton();
        bt_Alterar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        tf_Pesquisa = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_Clientes = new javax.swing.JTable();
        lb_idCliente = new javax.swing.JLabel();
        tf_idCliente = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Cliente");
        setMaximumSize(new java.awt.Dimension(5555555, 555555));
        setPreferredSize(new java.awt.Dimension(520, 480));

        lb_Nome.setText("*Nome");

        lb_Endereco.setText("*Endereço");

        lb_Fone.setText("*Telefone");

        lb_Email.setText("Email");

        jLabel1.setText("*Campos Obrigatórios");

        bt_Add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/teste/icones/create.png"))); // NOI18N
        bt_Add.setToolTipText("Adicionar");
        bt_Add.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bt_Add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_AddActionPerformed(evt);
            }
        });

        bt_Remove.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/teste/icones/delete.png"))); // NOI18N
        bt_Remove.setToolTipText("Remover");
        bt_Remove.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bt_Remove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_RemoveActionPerformed(evt);
            }
        });

        bt_Alterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/teste/icones/update.png"))); // NOI18N
        bt_Alterar.setToolTipText("Remover");
        bt_Alterar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bt_Alterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_AlterarActionPerformed(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/teste/icones/pesquisar.png"))); // NOI18N

        tf_Pesquisa.setToolTipText("");
        tf_Pesquisa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tf_PesquisaKeyReleased(evt);
            }
        });

        tb_Clientes = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        tb_Clientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "CP", "Nome", "Endereço", "Fone", "Email"
            }
        ));
        tb_Clientes.setFocusable(false);
        tb_Clientes.getTableHeader().setResizingAllowed(false);
        tb_Clientes.getTableHeader().setReorderingAllowed(false);
        tb_Clientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_ClientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tb_Clientes);

        lb_idCliente.setText("ID");

        tf_idCliente.setEnabled(false);
        tf_idCliente.setMinimumSize(new java.awt.Dimension(80, 22));
        tf_idCliente.setOpaque(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lb_Fone)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(tf_Pesquisa)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2)
                                .addGap(30, 30, 30)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lb_idCliente)
                                    .addComponent(lb_Nome))
                                .addGap(22, 22, 22)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tf_idCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tf_Nome)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(lb_Endereco)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tf_Fone)
                                    .addComponent(tf_Endereco)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(lb_Email)
                                .addGap(30, 30, 30)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(bt_Alterar)
                                        .addGap(73, 73, 73)
                                        .addComponent(bt_Add)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(bt_Remove))
                                    .addComponent(tf_Email))))
                        .addGap(22, 22, 22))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tf_Pesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb_idCliente)
                    .addComponent(tf_idCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb_Nome)
                    .addComponent(tf_Nome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb_Endereco)
                    .addComponent(tf_Endereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_Fone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_Fone))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb_Email)
                    .addComponent(tf_Email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bt_Alterar)
                    .addComponent(bt_Remove)
                    .addComponent(bt_Add))
                .addGap(0, 18, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bt_AddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_AddActionPerformed
        adicionar();
    }//GEN-LAST:event_bt_AddActionPerformed

    private void tf_PesquisaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_PesquisaKeyReleased
        pesquisar();
    }//GEN-LAST:event_tf_PesquisaKeyReleased

    private void tb_ClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_ClientesMouseClicked
        setar_campos();
    }//GEN-LAST:event_tb_ClientesMouseClicked

    private void bt_AlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_AlterarActionPerformed
        alterar();
    }//GEN-LAST:event_bt_AlterarActionPerformed

    private void bt_RemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_RemoveActionPerformed
        remover();
    }//GEN-LAST:event_bt_RemoveActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_Add;
    private javax.swing.JButton bt_Alterar;
    private javax.swing.JButton bt_Remove;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lb_Email;
    private javax.swing.JLabel lb_Endereco;
    private javax.swing.JLabel lb_Fone;
    private javax.swing.JLabel lb_Nome;
    private javax.swing.JLabel lb_idCliente;
    private javax.swing.JTable tb_Clientes;
    private javax.swing.JTextField tf_Email;
    private javax.swing.JTextField tf_Endereco;
    private javax.swing.JTextField tf_Fone;
    private javax.swing.JTextField tf_Nome;
    private javax.swing.JTextField tf_Pesquisa;
    private javax.swing.JTextField tf_idCliente;
    // End of variables declaration//GEN-END:variables
}
