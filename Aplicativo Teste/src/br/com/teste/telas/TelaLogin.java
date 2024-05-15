/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package br.com.teste.telas;

import java.sql.*;
import br.com.teste.dal.ModuloConexao;
import java.awt.Color;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuário
 */
public class TelaLogin extends javax.swing.JFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    /**
     * Creates new form TelaLogin
     */
    public void logar() {
        String senha = new String(pf_Senha.getPassword());
        String usuario = tf_Usuario.getText();
        if(senha.contains("'") || senha.contains("&") || senha.contains("|") || senha.contains("/") || senha.contains("(") || senha.contains(")") || usuario.contains("'") || usuario.contains("&") || usuario.contains("|") || usuario.contains("/") || usuario.contains("(") || usuario.contains(")")){
            JOptionPane.showMessageDialog(null, "Algo deu errado");
            System.exit(0);
        }
        String sql = "select * from usuarios where login=? and senha=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, tf_Usuario.getText());
            String captura = new String(pf_Senha.getPassword());
            pst.setString(2, captura);
            rs = pst.executeQuery();
            if (rs.next()) {
                String perfil = rs.getString(6);
                TelaPrincipal principal = new TelaPrincipal();
                principal.setVisible(true);
                TelaPrincipal.lb_Usuario.setText(rs.getString(2));
                if (perfil.equals("admin")) {
                    TelaPrincipal.mn_Cad_Us.setEnabled(true);
                    TelaPrincipal.mn_Rel.setEnabled(true);
                    TelaPrincipal.lb_Usuario.setForeground(Color.red);
                } else {
                    TelaPrincipal.lb_Usuario.setForeground(Color.blue);
                }
                this.dispose();
                conexao.close();
            } else {
                JOptionPane.showMessageDialog(null, "usuário e/ou senha inválido");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public TelaLogin() {
        initComponents();
        conexao = ModuloConexao.conector();
        //System.out.println(conexao);
        if (conexao != null) {
            lb_Status.setText("Conectado");
            lb_Status.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/teste/icones/dbok.png")));
        } else {
            lb_Status.setText("Não conectado");
            lb_Status.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/teste/icones/dberror.png")));
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lb_Usuario = new javax.swing.JLabel();
        lb_Senha = new javax.swing.JLabel();
        tf_Usuario = new javax.swing.JTextField();
        bt_Login = new javax.swing.JButton();
        pf_Senha = new javax.swing.JPasswordField();
        lb_Status = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login Teste");
        setResizable(false);

        lb_Usuario.setText("Usuário");

        lb_Senha.setText("Senha");

        tf_Usuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tf_UsuarioKeyPressed(evt);
            }
        });

        bt_Login.setText("Login");
        bt_Login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_LoginActionPerformed(evt);
            }
        });
        bt_Login.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                bt_LoginKeyPressed(evt);
            }
        });

        pf_Senha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                pf_SenhaKeyPressed(evt);
            }
        });

        lb_Status.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/teste/icones/dberror.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lb_Usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lb_Senha, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tf_Usuario)
                            .addComponent(pf_Senha, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lb_Status, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_Login)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb_Usuario)
                    .addComponent(tf_Usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lb_Senha)
                    .addComponent(pf_Senha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_Login)
                    .addComponent(lb_Status))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void bt_LoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_LoginActionPerformed
        logar();
    }//GEN-LAST:event_bt_LoginActionPerformed

    private void pf_SenhaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pf_SenhaKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            logar();
        }
    }//GEN-LAST:event_pf_SenhaKeyPressed

    private void bt_LoginKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_bt_LoginKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            logar();
        }
    }//GEN-LAST:event_bt_LoginKeyPressed

    private void tf_UsuarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_UsuarioKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            logar();
        }
    }//GEN-LAST:event_tf_UsuarioKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_Login;
    private javax.swing.JLabel lb_Senha;
    private javax.swing.JLabel lb_Status;
    private javax.swing.JLabel lb_Usuario;
    private javax.swing.JPasswordField pf_Senha;
    private javax.swing.JTextField tf_Usuario;
    // End of variables declaration//GEN-END:variables
}