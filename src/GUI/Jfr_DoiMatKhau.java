/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DAO.NhanVienDAO;
import Ultil.Auth;
import Ultil.MsgBox;
import java.awt.Color;
import javax.swing.plaf.basic.BasicInternalFrameUI;

/**
 *
 * @author ADMIN
 */
public class Jfr_DoiMatKhau extends javax.swing.JInternalFrame {

    Color defaulColor, ClickColor;

    public Jfr_DoiMatKhau() {
        initComponents();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);
        hoverButton();

        defaulColor = new Color(255, 255, 255);
        ClickColor = new Color(221, 221, 221);
    }

    public void hoverButton() {
        defaulColor = new Color(255, 255, 255);
        ClickColor = new Color(225, 225, 225);
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
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtTaiKhoan = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtMKMoi = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        txtMKCu = new javax.swing.JPasswordField();
        jLabel5 = new javax.swing.JLabel();
        txtXNMKhau = new javax.swing.JPasswordField();
        jPanel2 = new javax.swing.JPanel();
        btnLuuThayDoi = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btnThoat = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));
        jPanel1.setMinimumSize(new java.awt.Dimension(1226, 730));
        jPanel1.setPreferredSize(new java.awt.Dimension(1226, 730));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel2.setText("Tài khoản");
        jPanel4.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 111, -1));
        jPanel4.add(txtTaiKhoan, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, 340, 34));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel3.setText("Mật khẩu mới");
        jPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 20, 111, -1));

        txtMKMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMKMoiActionPerformed(evt);
            }
        });
        jPanel4.add(txtMKMoi, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 10, 339, 34));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel4.setText("Mật khẩu cũ");
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 111, -1));

        txtMKCu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMKCuActionPerformed(evt);
            }
        });
        jPanel4.add(txtMKCu, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 90, 339, 34));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel5.setText("Xác nhận mật khẩu");
        jPanel4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 100, -1, -1));

        txtXNMKhau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtXNMKhauActionPerformed(evt);
            }
        });
        jPanel4.add(txtXNMKhau, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 90, 339, 34));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnLuuThayDoi.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnLuuThayDoi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnLuuThayDoi.setText("Lưu thay đổi");
        btnLuuThayDoi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLuuThayDoiMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(btnLuuThayDoi, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 27, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnLuuThayDoi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
        );

        jPanel4.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 200, -1, -1));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnThoat.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnThoat.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnThoat.setText("Thoát");
        btnThoat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThoatMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(btnThoat, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 44, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnThoat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel4.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 200, -1, 30));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 1220, 730));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 750, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLuuThayDoiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLuuThayDoiMouseClicked
        jPanel2.setBackground(ClickColor);
        jPanel3.setBackground(defaulColor);
        
            doiMK();
        

    }//GEN-LAST:event_btnLuuThayDoiMouseClicked

    private void btnThoatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThoatMouseClicked
        jPanel2.setBackground(defaulColor);
        jPanel3.setBackground(ClickColor);
        this.dispose();
    }//GEN-LAST:event_btnThoatMouseClicked

    private void txtMKMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMKMoiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMKMoiActionPerformed

    private void txtXNMKhauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtXNMKhauActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtXNMKhauActionPerformed

    private void txtMKCuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMKCuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMKCuActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnLuuThayDoi;
    private javax.swing.JLabel btnThoat;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPasswordField txtMKCu;
    private javax.swing.JPasswordField txtMKMoi;
    private javax.swing.JTextField txtTaiKhoan;
    private javax.swing.JPasswordField txtXNMKhau;
    // End of variables declaration//GEN-END:variables
    NhanVienDAO dao = new NhanVienDAO();

    void doiMK() {
        String manv = txtTaiKhoan.getText();
        String mk = new String(txtMKCu.getPassword());
        String mkMoi = new String(txtMKMoi.getPassword());
        String mkMoi2 = new String(txtXNMKhau.getPassword());
        if (txtTaiKhoan.getText().trim().equals("")) {
            MsgBox.alert(this, "Tên đăng nhập không để trống");
        } else if (txtMKCu.getText().trim().equals("")) {
            MsgBox.alert(this, "Mật khẩu hiện tại không để trống");
        } else if (txtMKMoi.getText().trim().equals("")) {
            MsgBox.alert(this, "Mật khẩu mới không để trống");
        } else if (txtXNMKhau.getText().trim().equals("")) {
            MsgBox.alert(this, "Xác nhận mật khẩu không để trống");
        } else if (!manv.equalsIgnoreCase(Auth.user.getMaNV())) {
            MsgBox.alert(this, "Sai tên đăng nhập");
        } else if (!mk.equalsIgnoreCase(Auth.user.getMatKhau())) {
            MsgBox.alert(this, "Sai mật khẩu");
        } else if (!mkMoi.equals(mkMoi2)) {
            MsgBox.alert(this, "Xác nhận mật khẩu không khớp");
        } else {
            Auth.user.setMatKhau(mkMoi);
            dao.update(Auth.user);
            MsgBox.alert(this, "Đổi mật khẩu thành công");
        }
    }}

    //check trống
//    boolean checkTrong() {
//        if (txtTaiKhoan.getText().equals("")) {
//            MsgBox.alert(this, "Tên đăng nhập không để trống");
//            return true;
//        }
//        if (txtMKCu.getPassword().equals("")) {
//            MsgBox.alert(this, "Mật khẩu hiện tại không để trống");
//            return true;
//        } else if (txtMKMoi.getPassword().equals("")) {
//            MsgBox.alert(this, "Mật khẩu mới không để trống");
//            return true;
//        } else if (txtXNMKhau.getPassword().equals("")) {
//            MsgBox.alert(this, "Xác nhận mật khẩu không để trống");
//            return true;
//        }
//        return false;
//    }
//}
