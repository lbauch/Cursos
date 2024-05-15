/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package br.com.teste.telas;

import java.sql.*;
import br.com.teste.dal.ModuloConexao;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuário
 */
public class TelaUsuario extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    /**
     * Creates new form TelaUsuario
     */
    public TelaUsuario() {
        initComponents();
        conexao = ModuloConexao.conector();
        cb_Perfil.setSelectedItem(null);
    }

    private void consultar() {
        String sql = "select * from usuarios where iduser = ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, tf_Id.getText());
            rs = pst.executeQuery();
            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "Usuário Cadastrado, dados inseridos");
                tf_Nome.setText(rs.getString(2));
                tf_Fone.setText(rs.getString(3));
                tf_Login.setText(rs.getString(4));
                tf_Senha.setText(rs.getString(5));
                cb_Perfil.setSelectedItem(rs.getString((6)));
            } else {
                JOptionPane.showMessageDialog(null, "Usuário não Cadastrado");
                limpar();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void adicionar() {
        String sql = "insert into usuarios(idUser,usuario,fone,login,senha,perfil)"
                + "values(?,?,?,?,?,?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, tf_Id.getText());
            pst.setString(2, tf_Nome.getText());
            pst.setString(3, tf_Fone.getText());
            pst.setString(4, tf_Login.getText());
            pst.setString(5, tf_Senha.getText());
            pst.setString(6, cb_Perfil.getSelectedItem().toString());
            if (tf_Id.getText().isEmpty() || tf_Nome.getText().isEmpty() || tf_Login.getText().isEmpty()
                    || tf_Senha.getText().isEmpty() || cb_Perfil.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
            } else {
                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso");
                    limpar();
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    private void alterar() {
        String sql = "update usuarios set usuario=?, fone = ?, login = ?, senha = ?, perfil = ? where idUser = ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, tf_Nome.getText());
            pst.setString(2, tf_Fone.getText());
            pst.setString(3, tf_Login.getText());
            pst.setString(4, tf_Senha.getText());
            pst.setString(5, cb_Perfil.getSelectedItem().toString());
            pst.setString(6, tf_Id.getText());
            if (tf_Id.getText().isEmpty() || tf_Nome.getText().isEmpty() || tf_Login.getText().isEmpty()
                    || tf_Senha.getText().isEmpty() || cb_Perfil.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
            } else {
                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Dados alterados com sucesso");
                    limpar();
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void remover() {
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover o usuário?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            String sql = "delete from usuarios where idUser = ?";
            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, tf_Id.getText());
                int apagado = pst.executeUpdate();
                if (apagado > 0) {
                    JOptionPane.showMessageDialog(null, "Usuário removido com sucesso!");
                    limpar();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    private void limpar() {
        tf_Id.setText(null);
        tf_Nome.setText(null);
        tf_Fone.setText(null);
        tf_Login.setText(null);
        tf_Senha.setText(null);
        cb_Perfil.setSelectedItem(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lb_Id = new javax.swing.JLabel();
        lb_Nome = new javax.swing.JLabel();
        lb_Login = new javax.swing.JLabel();
        lb_Senha = new javax.swing.JLabel();
        lb_Pefil = new javax.swing.JLabel();
        tf_Id = new javax.swing.JTextField();
        tf_Nome = new javax.swing.JTextField();
        tf_Login = new javax.swing.JTextField();
        tf_Senha = new javax.swing.JTextField();
        lb_Fone = new javax.swing.JLabel();
        tf_Fone = new javax.swing.JTextField();
        cb_Perfil = new javax.swing.JComboBox<>();
        bt_Adicionar = new javax.swing.JButton();
        bt_Remover = new javax.swing.JButton();
        bt_Editar = new javax.swing.JButton();
        bt_Pesquisar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Usuários");
        setPreferredSize(new java.awt.Dimension(520, 480));

        lb_Id.setText("*ID");

        lb_Nome.setText("*Nome");

        lb_Login.setText("*Login");

        lb_Senha.setText("*Senha");

        lb_Pefil.setText("*Perfil");

        tf_Id.setMinimumSize(new java.awt.Dimension(80, 22));

        lb_Fone.setText("Fone");

        cb_Perfil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "admin", "user" }));

        bt_Adicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/teste/icones/create.png"))); // NOI18N
        bt_Adicionar.setToolTipText("Adicionar");
        bt_Adicionar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bt_Adicionar.setPreferredSize(new java.awt.Dimension(80, 80));
        bt_Adicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_AdicionarActionPerformed(evt);
            }
        });

        bt_Remover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/teste/icones/delete.png"))); // NOI18N
        bt_Remover.setToolTipText("Remover");
        bt_Remover.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bt_Remover.setPreferredSize(new java.awt.Dimension(80, 80));
        bt_Remover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_RemoverActionPerformed(evt);
            }
        });

        bt_Editar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/teste/icones/update.png"))); // NOI18N
        bt_Editar.setToolTipText("Alterar");
        bt_Editar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bt_Editar.setPreferredSize(new java.awt.Dimension(80, 80));
        bt_Editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_EditarActionPerformed(evt);
            }
        });

        bt_Pesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/teste/icones/read.png"))); // NOI18N
        bt_Pesquisar.setToolTipText("Pesquisar");
        bt_Pesquisar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bt_Pesquisar.setPreferredSize(new java.awt.Dimension(80, 80));
        bt_Pesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_PesquisarActionPerformed(evt);
            }
        });

        jLabel1.setText("*Campos Obrigatórios");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lb_Id)
                    .addComponent(lb_Fone)
                    .addComponent(lb_Login)
                    .addComponent(lb_Nome))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tf_Id, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(tf_Login, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(tf_Fone)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(bt_Adicionar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(bt_Remover, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lb_Senha)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tf_Senha, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(lb_Pefil, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(bt_Editar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(bt_Pesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(cb_Perfil, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addComponent(tf_Nome))
                .addGap(214, 214, 214))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb_Id)
                    .addComponent(tf_Id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tf_Nome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_Nome))
                .addGap(57, 57, 57)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb_Login)
                    .addComponent(tf_Login, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_Senha)
                    .addComponent(tf_Senha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb_Pefil)
                    .addComponent(cb_Perfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lb_Fone)
                    .addComponent(tf_Fone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(64, 64, 64)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_Adicionar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_Remover, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_Editar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_Pesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        setBounds(0, 0, 520, 480);
    }// </editor-fold>//GEN-END:initComponents

    private void bt_PesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_PesquisarActionPerformed
        consultar();
    }//GEN-LAST:event_bt_PesquisarActionPerformed

    private void bt_AdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_AdicionarActionPerformed
        adicionar();
    }//GEN-LAST:event_bt_AdicionarActionPerformed

    private void bt_EditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_EditarActionPerformed
        alterar();
    }//GEN-LAST:event_bt_EditarActionPerformed

    private void bt_RemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_RemoverActionPerformed
        remover();
    }//GEN-LAST:event_bt_RemoverActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_Adicionar;
    private javax.swing.JButton bt_Editar;
    private javax.swing.JButton bt_Pesquisar;
    private javax.swing.JButton bt_Remover;
    private javax.swing.JComboBox<String> cb_Perfil;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lb_Fone;
    private javax.swing.JLabel lb_Id;
    private javax.swing.JLabel lb_Login;
    private javax.swing.JLabel lb_Nome;
    private javax.swing.JLabel lb_Pefil;
    private javax.swing.JLabel lb_Senha;
    private javax.swing.JTextField tf_Fone;
    private javax.swing.JTextField tf_Id;
    private javax.swing.JTextField tf_Login;
    private javax.swing.JTextField tf_Nome;
    private javax.swing.JTextField tf_Senha;
    // End of variables declaration//GEN-END:variables
}
