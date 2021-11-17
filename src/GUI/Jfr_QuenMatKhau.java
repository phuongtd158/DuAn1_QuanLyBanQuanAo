/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DAO.NhanVienDAO;
import Entity.NhanVien;
import Ultil.Auth;
import Ultil.MsgBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author ADMIN
 */
public class Jfr_QuenMatKhau extends javax.swing.JFrame {

    int randomCode;

    /**
     * Creates new form Jfr_QuenMatKhau
     */

    public Jfr_QuenMatKhau() {
        initComponents();
        setLocationRelativeTo(null);
        initMoving(this);
        hide_();
    }

    private int x, y;

    public void hide_() {
        jLabel9.setVisible(false);
        txtMaXacNhan.setVisible(false);
        btnCode.setVisible(false);
        jSeparator4.setVisible(false);

        jLabel10.setVisible(false);
        txtMatKhau.setVisible(false);
        btnCode.setVisible(false);
        jSeparator5.setVisible(false);

        jLabel11.setVisible(false);
        txtXacNhanMatKhau.setVisible(false);
        btnCode.setVisible(false);
        jSeparator3.setVisible(false);

        btnXacNhan.setVisible(false);
    }

    public void initMoving(JFrame frame) {
        jPanel1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                x = e.getX();
                y = e.getY();
            }

        });
        jPanel1.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                frame.setLocation(e.getXOnScreen() - x, e.getYOnScreen() - y);
            }

        });

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        btnCode = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        txtMaXacNhan = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        btnEmail = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        txtXacNhanMatKhau = new javax.swing.JPasswordField();
        txtMatKhau = new javax.swing.JPasswordField();
        jButton3 = new javax.swing.JButton();
        btnXacNhan = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Be Vietnam Pro ExtraBold", 0, 18)); // NOI18N
        jLabel1.setText("Quên mật khẩu");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 20, 140, 30));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_email_open_20px_1.png"))); // NOI18N
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 20, 30));

        txtEmail.setForeground(new java.awt.Color(81, 81, 81));
        txtEmail.setText("  Email");
        txtEmail.setBorder(null);
        txtEmail.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtEmailFocusGained(evt);
            }
        });
        jPanel1.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 80, 260, 30));

        jSeparator2.setBackground(new java.awt.Color(81, 81, 81));
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 110, 260, 20));

        btnCode.setBackground(new java.awt.Color(255, 255, 255));
        btnCode.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_ok_20px.png"))); // NOI18N
        btnCode.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCodeMouseClicked(evt);
            }
        });
        btnCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCodeActionPerformed(evt);
            }
        });
        jPanel1.add(btnCode, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 130, 40, 30));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_pin_code_20px.png"))); // NOI18N
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 20, 30));

        txtMaXacNhan.setForeground(new java.awt.Color(81, 81, 81));
        txtMaXacNhan.setText(" Mã xác nhận");
        txtMaXacNhan.setBorder(null);
        txtMaXacNhan.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtMaXacNhanFocusGained(evt);
            }
        });
        jPanel1.add(txtMaXacNhan, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 130, 260, 30));

        jSeparator3.setBackground(new java.awt.Color(81, 81, 81));
        jPanel1.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 260, 260, 20));

        btnEmail.setBackground(new java.awt.Color(255, 255, 255));
        btnEmail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_email_send_20px.png"))); // NOI18N
        btnEmail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEmailMouseClicked(evt);
            }
        });
        btnEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEmailActionPerformed(evt);
            }
        });
        jPanel1.add(btnEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 80, 40, 30));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_password_20px.png"))); // NOI18N
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 20, 30));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_password_20px.png"))); // NOI18N
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, 20, 30));

        jSeparator4.setBackground(new java.awt.Color(81, 81, 81));
        jPanel1.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 160, 260, 20));

        jSeparator5.setBackground(new java.awt.Color(81, 81, 81));
        jPanel1.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 210, 260, 20));

        txtXacNhanMatKhau.setForeground(new java.awt.Color(81, 81, 81));
        txtXacNhanMatKhau.setText("jPasswordField1");
        txtXacNhanMatKhau.setBorder(null);
        txtXacNhanMatKhau.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtXacNhanMatKhauFocusGained(evt);
            }
        });
        jPanel1.add(txtXacNhanMatKhau, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 230, 260, 30));

        txtMatKhau.setForeground(new java.awt.Color(81, 81, 81));
        txtMatKhau.setText("jPasswordField1");
        txtMatKhau.setBorder(null);
        txtMatKhau.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtMatKhauFocusGained(evt);
            }
        });
        jPanel1.add(txtMatKhau, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 180, 260, 30));

        jButton3.setBackground(new java.awt.Color(255, 255, 255));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_multiply_20px.png"))); // NOI18N
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 10, 30, 30));

        btnXacNhan.setBackground(new java.awt.Color(255, 255, 255));
        btnXacNhan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnXacNhan.setText("Xác nhận");
        btnXacNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXacNhanActionPerformed(evt);
            }
        });
        jPanel1.add(btnXacNhan, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 280, -1, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCodeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCodeMouseClicked
        if (Integer.valueOf(txtMaXacNhan.getText()) == randomCode) {
            JOptionPane.showMessageDialog(this, "Mời bạn nhập mật khẩu mới");
            jLabel10.setVisible(true);
            txtMatKhau.setVisible(true);
            btnCode.setVisible(true);
            jSeparator5.setVisible(true);

            jLabel11.setVisible(true);
            txtXacNhanMatKhau.setVisible(true);
            btnCode.setVisible(true);
            jSeparator3.setVisible(true);

            btnXacNhan.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "code sai");
        }

    }//GEN-LAST:event_btnCodeMouseClicked

    private void btnEmailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEmailMouseClicked
        try {

            if (chektontai() == false) {
                this.sendcode();
            } else {
                MsgBox.alert(this, "email không tồn tại");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Lỗi, Vui lòng xem lại");
        }

    }//GEN-LAST:event_btnEmailMouseClicked

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked
        Jfr_DangNhap dn = new Jfr_DangNhap();
        dn.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton3MouseClicked

    private void txtEmailFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtEmailFocusGained
        txtEmail.setText("");
    }//GEN-LAST:event_txtEmailFocusGained

    private void txtMaXacNhanFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtMaXacNhanFocusGained
        txtMaXacNhan.setText("");
    }//GEN-LAST:event_txtMaXacNhanFocusGained

    private void txtMatKhauFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtMatKhauFocusGained
        txtMatKhau.setText("");
    }//GEN-LAST:event_txtMatKhauFocusGained

    private void txtXacNhanMatKhauFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtXacNhanMatKhauFocusGained
        txtXacNhanMatKhau.setText("");
    }//GEN-LAST:event_txtXacNhanMatKhauFocusGained

    private void btnCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCodeActionPerformed
//         TODO add your handling code here:

    }//GEN-LAST:event_btnCodeActionPerformed

    private void btnEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEmailActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_btnEmailActionPerformed

    private void btnXacNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXacNhanActionPerformed
        // TODO add your handling code here:

        doiMK();

    }//GEN-LAST:event_btnXacNhanActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(Jfr_QuenMatKhau.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Jfr_QuenMatKhau.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Jfr_QuenMatKhau.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Jfr_QuenMatKhau.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Jfr_QuenMatKhau().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCode;
    private javax.swing.JButton btnEmail;
    private javax.swing.JButton btnXacNhan;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtMaXacNhan;
    private javax.swing.JPasswordField txtMatKhau;
    private javax.swing.JPasswordField txtXacNhanMatKhau;
    // End of variables declaration//GEN-END:variables

    NhanVienDAO dao = new NhanVienDAO();

    private void doiMK() {
        NhanVien nv = new NhanVien();
        String emai = new String(txtEmail.getText());
        String mkMoi = new String(txtMatKhau.getPassword());
        String mkMoi2 = new String(txtXacNhanMatKhau.getPassword());

        if (!mkMoi.equals(mkMoi2)) {
            MsgBox.alert(this, "Xác nhận mật khẩu không khớp");
        } else {
            nv.setMatKhau(mkMoi2);
            dao.updatemk(emai, mkMoi2);
            MsgBox.alert(this, "Đổi mật khẩu thành công");
            Jfr_DangNhap dn = new Jfr_DangNhap();
            dn.setVisible(true);
            this.dispose();
        }
    }

    public boolean chektontai() {
        if (dao.checkemai(txtEmail.getText()) != null) {
            return false;
        }
        return true;
    }

    private void sendcode() {
        try {
            Random rand = new Random();
            randomCode = rand.nextInt(99999);
            String host = "smtp.gmail.com";
            String user = "quanlybanquanaopoly@gmail.com";
            String pass = "Poly123456";
            String to = txtEmail.getText();
            String subjectString = "Resteting Code";
            String message = "Code của mày đây " + randomCode;
            boolean sessionDebug = false;
            Properties pros = System.getProperties();
            pros.put("mail.smtp.starttls.enable", "true");
            pros.put("mail.smtp.host", "host");
            pros.put("mail.smtp.port", "587");
            pros.put("mail.smtp.auth", "true");
            pros.put("mail.smtp.starttls.required", "true");

            Session mailSession = Session.getDefaultInstance(pros, null);
            mailSession.setDebug(sessionDebug);
            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(user));
            InternetAddress[] address = {new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subjectString);
            msg.setText(message);
            Transport transport = mailSession.getTransport("smtps");
            transport.connect(host, user, pass);
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
            JOptionPane.showMessageDialog(this, "mã đã dc gửi đến email");
            jLabel9.setVisible(true);
            txtMaXacNhan.setVisible(true);
            btnCode.setVisible(true);
            jSeparator4.setVisible(true);
        } catch (Exception ex) {
//            JOptionPane.showMessageDialog(rootPane, ex);
            ex.printStackTrace();
        }
    }
}
