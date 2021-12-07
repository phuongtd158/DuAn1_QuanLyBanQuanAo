/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DAO.KhuyenMaiDAO;
import DAO.NhanVienDAO;
import DAO.SanPhamDAO;
import Entity.KhuyenMai;
import Entity.NhanVien;
import Entity.SanPham;
import Ultil.Auth;
import Ultil.MsgBox;
import Ultil.XDate;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Date;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author ADMIN
 */
public class Jfr_DangNhap extends javax.swing.JFrame {

    /**
     * Creates new form Jfr_DangNhap
     */
    Color defualtColor,
            /**
             * Creates new form Jfr_DangNhap
             */
            ClickColor;

    Date vn = new Date() ;
    KhuyenMaiDAO daoKM = new KhuyenMaiDAO() ;
    SanPhamDAO daoSP = new SanPhamDAO() ;
    
    public Jfr_DangNhap() {
        initComponents();
        setLocationRelativeTo(null);
        hoverButton();
        initMoving(Jfr_DangNhap.this);
    }

    public void hoverButton() {
        defualtColor = new Color(255, 255, 255);
        ClickColor = new Color(225, 225, 225);
        //alo
    }

    private int x, y;

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
    
    
    
    // Hàm Update khuyến mãi 
    private void UpdateKhuyenMai(){
        List<KhuyenMai> listKM = daoKM.selectAll() ; 
        
        for( KhuyenMai x : listKM ){
            if( x.isTrangThai() && XDate.toDay(vn).equals(XDate.toDay(x.getNgayBD()))  && XDate.toMonth(vn).equals(XDate.toMonth(x.getNgayBD()) )   ){
                List<SanPham> listSP = daoSP.selectAll_6(x.getMaKM()) ;
                for( SanPham k : listSP ){
                    if( k.getGiamGia() == 0 || ( x.getGiamGia() > k.getGiamGia() ) ){                      
                        daoSP.Update_4( x.getGiamGia() , k.getMaCTSP() );
                    }
                }
            }
        }
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        txtTenDangNhap = new javax.swing.JTextField();
        txtMatKhau = new javax.swing.JPasswordField();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel2.setBackground(new java.awt.Color(41, 182, 246));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_shopping_cart_with_money_95px.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(101, 101, 101)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(113, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(110, 110, 110)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtTenDangNhap.setForeground(new java.awt.Color(81, 81, 81));
        txtTenDangNhap.setText("Tên đăng nhập");
        txtTenDangNhap.setBorder(null);
        txtTenDangNhap.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtTenDangNhapFocusGained(evt);
            }
        });
        txtTenDangNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenDangNhapActionPerformed(evt);
            }
        });
        jPanel3.add(txtTenDangNhap, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 110, 220, 30));

        txtMatKhau.setForeground(new java.awt.Color(81, 81, 81));
        txtMatKhau.setText("password");
        txtMatKhau.setBorder(null);
        txtMatKhau.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtMatKhauFocusGained(evt);
            }
        });
        jPanel3.add(txtMatKhau, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 170, 220, 30));

        jSeparator1.setBackground(new java.awt.Color(81, 81, 81));
        jPanel3.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 200, 210, 20));

        jSeparator2.setBackground(new java.awt.Color(81, 81, 81));
        jPanel3.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 140, 210, 20));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_password_20px.png"))); // NOI18N
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 20, -1));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(81, 81, 81)));
        jPanel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel4MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel4MousePressed(evt);
            }
        });

        jLabel4.setForeground(new java.awt.Color(81, 81, 81));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_login_25px_1.png"))); // NOI18N
        jLabel4.setText("Đăng nhập");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
        );

        jPanel3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 230, 100, 30));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(81, 81, 81)));
        jPanel5.setPreferredSize(new java.awt.Dimension(87, 30));
        jPanel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel5MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel5MousePressed(evt);
            }
        });

        jLabel3.setForeground(new java.awt.Color(81, 81, 81));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_cancel_25px_1.png"))); // NOI18N
        jLabel3.setText("Thoát");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
        );

        jPanel3.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 230, -1, 30));

        jLabel5.setForeground(new java.awt.Color(81, 81, 81));
        jLabel5.setText("Quên mật khẩu ?");
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 290, -1, -1));

        jLabel7.setBackground(new java.awt.Color(41, 182, 246));
        jLabel7.setFont(new java.awt.Font("Be Vietnam Pro ExtraBold", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(81, 81, 81));
        jLabel7.setText("Đăng nhập");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 30, 110, 40));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_user_20px.png"))); // NOI18N
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 20, -1));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtTenDangNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenDangNhapActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenDangNhapActionPerformed

    private void jPanel4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel4MousePressed
        jPanel4.setBackground(ClickColor);
        jPanel5.setBackground(defualtColor);
    }//GEN-LAST:event_jPanel4MousePressed

    private void jPanel5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel5MousePressed
        jPanel4.setBackground(defualtColor);
        jPanel5.setBackground(ClickColor);
    }//GEN-LAST:event_jPanel5MousePressed

    private void jPanel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel5MouseClicked
        int hoi = JOptionPane.showConfirmDialog(this, "Bạn có muốn kết thúc không ?", "Hỏi", JOptionPane.YES_NO_OPTION);
        if (hoi == JOptionPane.YES_OPTION) {
            System.exit(0);
        } else {
            MsgBox.alert(rootPane, "Bạn đã hủy thoát");
        }
    }//GEN-LAST:event_jPanel5MouseClicked

    private void txtTenDangNhapFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTenDangNhapFocusGained
        txtTenDangNhap.setText("");
    }//GEN-LAST:event_txtTenDangNhapFocusGained

    private void txtMatKhauFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtMatKhauFocusGained
        txtMatKhau.setText("");
    }//GEN-LAST:event_txtMatKhauFocusGained

    private void jPanel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel4MouseClicked
        // TODO add your handling code here:
        if (txtTenDangNhap.getText().equals("")) {
            MsgBox.alert(this, "Mời bạn nhập tên đăng nhập!");
            return;
        } else if (txtMatKhau.getText().equals("")) {
            MsgBox.alert(this, "Mời bạn nhập mật khẩu!");
            return;
        } else {
            dangnhap();
        }
    }//GEN-LAST:event_jPanel4MouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        Jfr_QuenMatKhau mk = new Jfr_QuenMatKhau();
        mk.setVisible(true);
        this.dispose();

    }//GEN-LAST:event_jLabel5MouseClicked

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
            java.util.logging.Logger.getLogger(Jfr_DangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Jfr_DangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Jfr_DangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Jfr_DangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Jfr_DangNhap().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPasswordField txtMatKhau;
    private javax.swing.JTextField txtTenDangNhap;
    // End of variables declaration//GEN-END:variables
    NhanVienDAO nvdao = new NhanVienDAO();

    void dangnhap() {
        String manv = txtTenDangNhap.getText();
        String matkhau = txtMatKhau.getText();
        NhanVien nv = nvdao.selectByID(manv);
        if (nv == null) {
            MsgBox.alert(this, "Sai tên đăng nhập!");
        } else if (!matkhau.equals(nv.getMatKhau())) {
            MsgBox.alert(this, "Sai mật khẩu đăng nhập!");
        } else {
            UpdateKhuyenMai();
            Auth.user = nv;
            MsgBox.alert(this, "Bạn đã đăng nhập thành công!");
            this.dispose();
            Jfr_CuaSoChinh cuaSoChinh = new Jfr_CuaSoChinh();
            cuaSoChinh.setVisible(true);
        }
    }
}
