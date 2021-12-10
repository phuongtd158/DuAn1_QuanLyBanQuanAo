/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DAO.KhuyenMaiDAO;
import DAO.SanPhamDAO;
import Entity.KhuyenMai;
import Entity.SanPham;
import Ultil.Auth;
import Ultil.MsgBox;
import Ultil.XDate;
import com.github.sarxos.webcam.Webcam;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author ADMIN
 */
public class Jfr_CuaSoChinh extends javax.swing.JFrame {

    Color defualtColor, ClickColor;
    private int x, y;

    Date vn = new Date();
    KhuyenMaiDAO daoKM = new KhuyenMaiDAO();
    SanPhamDAO daoSP = new SanPhamDAO();

    public Jfr_CuaSoChinh() {
        initComponents();
        setLocationRelativeTo(null);

        Jfr_ThongKe tk = new Jfr_ThongKe();
        jDesktopPane1.add(tk).setVisible(true);
        defualtColor = new Color(41, 182, 246);
        ClickColor = new Color(144, 202, 249);
        initMoving(Jfr_CuaSoChinh.this);

//        if (Auth.user.getVaiTro() == false) {
//            if (XDate.toDay(date).equals(String.valueOf(25))) {
//                new Jfr_ThongKe().setVisible(true);
//                MsgBox.alert(this, "Đang báo cáo doanh thu tháng");
//                Jfr_ThongKe.tk.sendEmail_Thang();
//            } else {
//                System.out.println("Hôm nay là ngày " + XDate.toDay(date));
//            }
//        }
    }

    // hàm update time 
    private void UpdateKhuyenMai() {
        List<KhuyenMai> listKM = daoKM.selectAll();

        for (KhuyenMai x : listKM) {
            if (x.isTrangThai() && XDate.toDay(vn).equals(XDate.toDay(x.getNgayKT())) && XDate.toMonth(vn).equals(XDate.toMonth(x.getNgayKT()))) {
                List<SanPham> listSP = daoSP.selectAll_6(x.getMaKM());
                for (SanPham k : listSP) {
                    if (k.getGiamGia() == x.getGiamGia()) {
                        daoSP.Update_4(0, k.getMaCTSP());
                    }
                }
                daoKM.update(x);
            }
        }
    }

    //Di chuyển form khi giữ chuột
    public void initMoving(JFrame frame) {
        jPanel3.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                x = e.getX();
                y = e.getY();
            }

        });
        jPanel3.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                frame.setLocation(e.getXOnScreen() - x, e.getYOnScreen() - y);
            }

        });

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menu = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnTrangChu = new javax.swing.JPanel();
        lb = new javax.swing.JLabel();
        btnSanPham = new javax.swing.JPanel();
        lb1 = new javax.swing.JLabel();
        btnHoaDon = new javax.swing.JPanel();
        lb3 = new javax.swing.JLabel();
        btnNhanVien = new javax.swing.JPanel();
        lb2 = new javax.swing.JLabel();
        btnKhachHang = new javax.swing.JPanel();
        lb4 = new javax.swing.JLabel();
        btnLichSu = new javax.swing.JPanel();
        lb5 = new javax.swing.JLabel();
        btnThongKe = new javax.swing.JPanel();
        lb6 = new javax.swing.JLabel();
        btnDoiMatKhau = new javax.swing.JPanel();
        lb7 = new javax.swing.JLabel();
        btnDangXuat = new javax.swing.JPanel();
        lb8 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        pnTrangChu = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        menu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(41, 182, 246));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnTrangChu.setBackground(new java.awt.Color(41, 182, 246));
        btnTrangChu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTrangChuMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnTrangChuMousePressed(evt);
            }
        });

        lb.setBackground(new java.awt.Color(41, 182, 246));
        lb.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lb.setForeground(new java.awt.Color(255, 255, 255));
        lb.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_play_graph_report_30px.png"))); // NOI18N
        lb.setText("    Thống kê");

        javax.swing.GroupLayout btnTrangChuLayout = new javax.swing.GroupLayout(btnTrangChu);
        btnTrangChu.setLayout(btnTrangChuLayout);
        btnTrangChuLayout.setHorizontalGroup(
            btnTrangChuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnTrangChuLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(lb, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );
        btnTrangChuLayout.setVerticalGroup(
            btnTrangChuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lb, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        jPanel2.add(btnTrangChu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 220, 50));

        btnSanPham.setBackground(new java.awt.Color(41, 182, 246));
        btnSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSanPhamMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnSanPhamMousePressed(evt);
            }
        });

        lb1.setBackground(new java.awt.Color(41, 182, 246));
        lb1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lb1.setForeground(new java.awt.Color(255, 255, 255));
        lb1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_product_30px.png"))); // NOI18N
        lb1.setText("    Sản phẩm");

        javax.swing.GroupLayout btnSanPhamLayout = new javax.swing.GroupLayout(btnSanPham);
        btnSanPham.setLayout(btnSanPhamLayout);
        btnSanPhamLayout.setHorizontalGroup(
            btnSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnSanPhamLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(lb1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );
        btnSanPhamLayout.setVerticalGroup(
            btnSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lb1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        jPanel2.add(btnSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, -1, -1));

        btnHoaDon.setBackground(new java.awt.Color(41, 182, 246));
        btnHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHoaDonMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnHoaDonMousePressed(evt);
            }
        });

        lb3.setBackground(new java.awt.Color(41, 182, 246));
        lb3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lb3.setForeground(new java.awt.Color(255, 255, 255));
        lb3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_more_details_30px.png"))); // NOI18N
        lb3.setText("    Bán Hàng");

        javax.swing.GroupLayout btnHoaDonLayout = new javax.swing.GroupLayout(btnHoaDon);
        btnHoaDon.setLayout(btnHoaDonLayout);
        btnHoaDonLayout.setHorizontalGroup(
            btnHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnHoaDonLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(lb3, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );
        btnHoaDonLayout.setVerticalGroup(
            btnHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lb3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        jPanel2.add(btnHoaDon, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, -1, 50));

        btnNhanVien.setBackground(new java.awt.Color(41, 182, 246));
        btnNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnNhanVienMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnNhanVienMousePressed(evt);
            }
        });

        lb2.setBackground(new java.awt.Color(41, 182, 246));
        lb2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lb2.setForeground(new java.awt.Color(255, 255, 255));
        lb2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_staff_30px.png"))); // NOI18N
        lb2.setText("    Nhân viên");

        javax.swing.GroupLayout btnNhanVienLayout = new javax.swing.GroupLayout(btnNhanVien);
        btnNhanVien.setLayout(btnNhanVienLayout);
        btnNhanVienLayout.setHorizontalGroup(
            btnNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnNhanVienLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(lb2, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );
        btnNhanVienLayout.setVerticalGroup(
            btnNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lb2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        jPanel2.add(btnNhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, -1, -1));

        btnKhachHang.setBackground(new java.awt.Color(41, 182, 246));
        btnKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnKhachHangMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnKhachHangMousePressed(evt);
            }
        });

        lb4.setBackground(new java.awt.Color(41, 182, 246));
        lb4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lb4.setForeground(new java.awt.Color(255, 255, 255));
        lb4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_customer_30px.png"))); // NOI18N
        lb4.setText("    Khách hàng");

        javax.swing.GroupLayout btnKhachHangLayout = new javax.swing.GroupLayout(btnKhachHang);
        btnKhachHang.setLayout(btnKhachHangLayout);
        btnKhachHangLayout.setHorizontalGroup(
            btnKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnKhachHangLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(lb4, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );
        btnKhachHangLayout.setVerticalGroup(
            btnKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lb4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        jPanel2.add(btnKhachHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 230, -1, -1));

        btnLichSu.setBackground(new java.awt.Color(41, 182, 246));
        btnLichSu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLichSuMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnLichSuMousePressed(evt);
            }
        });

        lb5.setBackground(new java.awt.Color(41, 182, 246));
        lb5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lb5.setForeground(new java.awt.Color(255, 255, 255));
        lb5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_order_history_30px_2.png"))); // NOI18N
        lb5.setText("    Lịch sử");

        javax.swing.GroupLayout btnLichSuLayout = new javax.swing.GroupLayout(btnLichSu);
        btnLichSu.setLayout(btnLichSuLayout);
        btnLichSuLayout.setHorizontalGroup(
            btnLichSuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnLichSuLayout.createSequentialGroup()
                .addContainerGap(42, Short.MAX_VALUE)
                .addComponent(lb5, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49))
        );
        btnLichSuLayout.setVerticalGroup(
            btnLichSuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lb5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        jPanel2.add(btnLichSu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 280, -1, -1));

        btnThongKe.setBackground(new java.awt.Color(41, 182, 246));
        btnThongKe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThongKeMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnThongKeMousePressed(evt);
            }
        });

        lb6.setBackground(new java.awt.Color(41, 182, 246));
        lb6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lb6.setForeground(new java.awt.Color(255, 255, 255));
        lb6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_sale_30px.png"))); // NOI18N
        lb6.setText("    Khuyến mại");

        javax.swing.GroupLayout btnThongKeLayout = new javax.swing.GroupLayout(btnThongKe);
        btnThongKe.setLayout(btnThongKeLayout);
        btnThongKeLayout.setHorizontalGroup(
            btnThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnThongKeLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(lb6, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );
        btnThongKeLayout.setVerticalGroup(
            btnThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lb6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        jPanel2.add(btnThongKe, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 330, -1, -1));

        btnDoiMatKhau.setBackground(new java.awt.Color(41, 182, 246));
        btnDoiMatKhau.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDoiMatKhauMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnDoiMatKhauMousePressed(evt);
            }
        });

        lb7.setBackground(new java.awt.Color(41, 182, 246));
        lb7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lb7.setForeground(new java.awt.Color(255, 255, 255));
        lb7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_password_reset_30px.png"))); // NOI18N
        lb7.setText("    Đổi mật khẩu");

        javax.swing.GroupLayout btnDoiMatKhauLayout = new javax.swing.GroupLayout(btnDoiMatKhau);
        btnDoiMatKhau.setLayout(btnDoiMatKhauLayout);
        btnDoiMatKhauLayout.setHorizontalGroup(
            btnDoiMatKhauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnDoiMatKhauLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(lb7)
                .addContainerGap(39, Short.MAX_VALUE))
        );
        btnDoiMatKhauLayout.setVerticalGroup(
            btnDoiMatKhauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lb7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        jPanel2.add(btnDoiMatKhau, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 380, -1, -1));

        btnDangXuat.setBackground(new java.awt.Color(41, 182, 246));
        btnDangXuat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDangXuatMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnDangXuatMousePressed(evt);
            }
        });

        lb8.setBackground(new java.awt.Color(41, 182, 246));
        lb8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lb8.setForeground(new java.awt.Color(255, 255, 255));
        lb8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_logout_rounded_left_30px.png"))); // NOI18N
        lb8.setText("    Đăng xuất");

        javax.swing.GroupLayout btnDangXuatLayout = new javax.swing.GroupLayout(btnDangXuat);
        btnDangXuat.setLayout(btnDangXuatLayout);
        btnDangXuatLayout.setHorizontalGroup(
            btnDangXuatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnDangXuatLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(lb8, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );
        btnDangXuatLayout.setVerticalGroup(
            btnDangXuatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lb8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        jPanel2.add(btnDangXuat, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 430, 220, -1));

        menu.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 73, 221, 770));

        jPanel3.setBackground(new java.awt.Color(41, 182, 246));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_close_window_35px.png"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1440, 10, -1, 30));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_shopping_cart_70px.png"))); // NOI18N
        jLabel2.setText("TÊN SHOP");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 210, 60));

        menu.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -4, 1480, 80));

        pnTrangChu.setBackground(new java.awt.Color(255, 255, 255));
        pnTrangChu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jDesktopPane1.setLayer(pnTrangChu, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnTrangChu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnTrangChu, javax.swing.GroupLayout.DEFAULT_SIZE, 764, Short.MAX_VALUE))
        );

        menu.add(jDesktopPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 70, 1260, 770));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTrangChuMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTrangChuMousePressed
        btnTrangChu.setBackground(ClickColor);
        btnSanPham.setBackground(defualtColor);
        btnNhanVien.setBackground(defualtColor);
        btnHoaDon.setBackground(defualtColor);
        btnKhachHang.setBackground(defualtColor);
        btnLichSu.setBackground(defualtColor);
        btnThongKe.setBackground(defualtColor);
        btnDoiMatKhau.setBackground(defualtColor);
        btnDangXuat.setBackground(defualtColor);

    }//GEN-LAST:event_btnTrangChuMousePressed

    private void btnSanPhamMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSanPhamMousePressed
        btnTrangChu.setBackground(defualtColor);
        btnSanPham.setBackground(ClickColor);
        btnNhanVien.setBackground(defualtColor);
        btnHoaDon.setBackground(defualtColor);
        btnKhachHang.setBackground(defualtColor);
        btnLichSu.setBackground(defualtColor);
        btnThongKe.setBackground(defualtColor);
        btnDoiMatKhau.setBackground(defualtColor);
        btnDangXuat.setBackground(defualtColor);

    }//GEN-LAST:event_btnSanPhamMousePressed

    private void btnNhanVienMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNhanVienMousePressed
        btnTrangChu.setBackground(defualtColor);
        btnSanPham.setBackground(defualtColor);
        btnNhanVien.setBackground(ClickColor);
        btnHoaDon.setBackground(defualtColor);
        btnKhachHang.setBackground(defualtColor);
        btnLichSu.setBackground(defualtColor);
        btnThongKe.setBackground(defualtColor);
        btnDoiMatKhau.setBackground(defualtColor);
        btnDangXuat.setBackground(defualtColor);

    }//GEN-LAST:event_btnNhanVienMousePressed

    private void btnHoaDonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHoaDonMousePressed
        btnTrangChu.setBackground(defualtColor);
        btnSanPham.setBackground(defualtColor);
        btnNhanVien.setBackground(defualtColor);
        btnHoaDon.setBackground(ClickColor);
        btnKhachHang.setBackground(defualtColor);
        btnLichSu.setBackground(defualtColor);
        btnThongKe.setBackground(defualtColor);
        btnDoiMatKhau.setBackground(defualtColor);
        btnDangXuat.setBackground(defualtColor);
    }//GEN-LAST:event_btnHoaDonMousePressed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked

        if (vn.getHours() > 20) {
            UpdateKhuyenMai();
        }
        if (Auth.user.getVaiTro() == false) {
            try {
                long millis = System.currentTimeMillis();
                java.sql.Date date = new java.sql.Date(millis);
                Calendar c = Calendar.getInstance();

                int ngayCuoiThang = c.getMaximum(Calendar.DATE);
                if (Auth.user.getVaiTro() == false) {
                    if (XDate.toDay(date).equals(String.valueOf(ngayCuoiThang)) && XDate.toT(XDate.toTime(c.getTime())).after(XDate.toT("08:59 PM"))) {
                        Jfr_ThongKe.tk.sendEmail_Thang();
                        System.exit(0);

                        System.out.println("ok");
                    } else {
                        if (MsgBox.comfirm(this, "Bạn có muốn báo cáo doanh thu ngày không ?")) {

                            Jfr_ThongKe.tk.sendEmail();

                            System.exit(0);
                        } else {
                            if (MsgBox.comfirm(this, "Bạn chưa báo cáo doanh thu. Bạn chắc chắn muốn thoát ?")) {
                                System.exit(0);
                            }
                        }
                    }
                }

            } catch (Exception e) {
                MsgBox.alert(this, "Kiểm tra doanh thu trước khi báo cáo");
            }
        } else {
            System.exit(0);
        }

    }//GEN-LAST:event_jLabel1MouseClicked

    private void btnTrangChuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTrangChuMouseClicked
        Jfr_ThongKe tk = new Jfr_ThongKe();
        jDesktopPane1.add(tk).setVisible(true);
        if (Jfr_HoaDon.webcam != null) {
            Jfr_HoaDon.webcam.close();
        }
    }//GEN-LAST:event_btnTrangChuMouseClicked

    private void btnSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSanPhamMouseClicked
        Jfr_SanPham menu2 = new Jfr_SanPham();
        jDesktopPane1.add(menu2).setVisible(true);
        if (Jfr_HoaDon.webcam != null) {
            Jfr_HoaDon.webcam.close();
        }
    }//GEN-LAST:event_btnSanPhamMouseClicked

    private void btnNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNhanVienMouseClicked
        if (Auth.user.getVaiTro() == true) {
            Jfr_NhanVien menu3 = new Jfr_NhanVien();
            jDesktopPane1.add(menu3).setVisible(true);
            if (Jfr_HoaDon.webcam != null) {
                Jfr_HoaDon.webcam.close();
            }
        } else {
            MsgBox.alert(this, "Bạn không có quyền truy cập vào chức năng này");
        }
    }//GEN-LAST:event_btnNhanVienMouseClicked

    private void btnKhachHangMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnKhachHangMousePressed
        btnTrangChu.setBackground(defualtColor);
        btnSanPham.setBackground(defualtColor);
        btnNhanVien.setBackground(defualtColor);
        btnHoaDon.setBackground(defualtColor);
        btnKhachHang.setBackground(ClickColor);
        btnLichSu.setBackground(defualtColor);
        btnThongKe.setBackground(defualtColor);
        btnDoiMatKhau.setBackground(defualtColor);
        btnDangXuat.setBackground(defualtColor);

    }//GEN-LAST:event_btnKhachHangMousePressed

    private void btnLichSuMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLichSuMousePressed
        btnTrangChu.setBackground(defualtColor);
        btnSanPham.setBackground(defualtColor);
        btnNhanVien.setBackground(defualtColor);
        btnHoaDon.setBackground(defualtColor);
        btnKhachHang.setBackground(defualtColor);
        btnLichSu.setBackground(ClickColor);
        btnThongKe.setBackground(defualtColor);
        btnDoiMatKhau.setBackground(defualtColor);
        btnDangXuat.setBackground(defualtColor);

    }//GEN-LAST:event_btnLichSuMousePressed

    private void btnThongKeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThongKeMousePressed
        btnTrangChu.setBackground(defualtColor);
        btnSanPham.setBackground(defualtColor);
        btnNhanVien.setBackground(defualtColor);
        btnHoaDon.setBackground(defualtColor);
        btnKhachHang.setBackground(defualtColor);
        btnLichSu.setBackground(defualtColor);
        btnThongKe.setBackground(ClickColor);
        btnDoiMatKhau.setBackground(defualtColor);
        btnDangXuat.setBackground(defualtColor);

    }//GEN-LAST:event_btnThongKeMousePressed

    private void btnDoiMatKhauMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDoiMatKhauMousePressed
        btnTrangChu.setBackground(defualtColor);
        btnSanPham.setBackground(defualtColor);
        btnNhanVien.setBackground(defualtColor);
        btnHoaDon.setBackground(defualtColor);
        btnKhachHang.setBackground(defualtColor);
        btnLichSu.setBackground(defualtColor);
        btnThongKe.setBackground(defualtColor);
        btnDoiMatKhau.setBackground(ClickColor);
        btnDangXuat.setBackground(defualtColor);

    }//GEN-LAST:event_btnDoiMatKhauMousePressed

    private void btnDangXuatMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDangXuatMousePressed
        btnTrangChu.setBackground(defualtColor);
        btnSanPham.setBackground(defualtColor);
        btnNhanVien.setBackground(defualtColor);
        btnHoaDon.setBackground(defualtColor);
        btnKhachHang.setBackground(defualtColor);
        btnLichSu.setBackground(defualtColor);
        btnThongKe.setBackground(defualtColor);
        btnDoiMatKhau.setBackground(defualtColor);
        btnDangXuat.setBackground(ClickColor);

    }//GEN-LAST:event_btnDangXuatMousePressed

    private void btnHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHoaDonMouseClicked
        Jfr_HoaDon menu4 = new Jfr_HoaDon();
        jDesktopPane1.add(menu4).setVisible(true);

    }//GEN-LAST:event_btnHoaDonMouseClicked

    private void btnKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnKhachHangMouseClicked
        Jfr_KhachHang menu5 = new Jfr_KhachHang();
        jDesktopPane1.add(menu5).setVisible(true);
        if (Jfr_HoaDon.webcam != null) {
            Jfr_HoaDon.webcam.close();
        }

    }//GEN-LAST:event_btnKhachHangMouseClicked

    private void btnLichSuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLichSuMouseClicked
        Jfr_LichSuGiaoDich menu6 = new Jfr_LichSuGiaoDich();
        jDesktopPane1.add(menu6).setVisible(true);
        if (Jfr_HoaDon.webcam != null) {
            Jfr_HoaDon.webcam.close();
        }
    }//GEN-LAST:event_btnLichSuMouseClicked

    private void btnDoiMatKhauMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDoiMatKhauMouseClicked
        Jfr_DoiMatKhau menu7 = new Jfr_DoiMatKhau();
        jDesktopPane1.add(menu7).setVisible(true);
        if (Jfr_HoaDon.webcam != null) {
            Jfr_HoaDon.webcam.close();
        }
    }//GEN-LAST:event_btnDoiMatKhauMouseClicked

    private void btnDangXuatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDangXuatMouseClicked
        if (Auth.user.getVaiTro() == false) {
            try {
                long millis = System.currentTimeMillis();
                java.sql.Date date = new java.sql.Date(millis);
                Calendar c = Calendar.getInstance();

                int ngayCuoiThang = c.getMaximum(Calendar.DATE);
                if (Auth.user.getVaiTro() == false) {
                    if (XDate.toDay(date).equals(String.valueOf(ngayCuoiThang)) && XDate.toT(XDate.toTime(c.getTime())).after(XDate.toT("08:59 PM"))) {
                        if (MsgBox.comfirm(this, "Bạn có muốn báo cáo doanh thu ngày và tháng không ?")) {

                            Jfr_ThongKe.tk.sendEmail();
                            Jfr_ThongKe.tk.sendEmail_Thang();

                            System.exit(0);
                        } else {
                            if (MsgBox.comfirm(this, "Bạn chưa báo cáo doanh thu. Bạn chắc chắn muốn thoát ?")) {
                                System.exit(0);
                            }
                        }
                        System.out.println("ok");
                    } else {
                        if (MsgBox.comfirm(this, "Bạn có muốn báo cáo doanh thu ngày không ?")) {

                            Jfr_ThongKe.tk.sendEmail();

                            System.exit(0);
                        } else {
                            if (MsgBox.comfirm(this, "Bạn chưa báo cáo doanh thu. Bạn chắc chắn muốn thoát ?")) {
                                System.exit(0);
                            }
                        }
                    }
                }

            } catch (Exception e) {
                MsgBox.alert(this, "Kiểm tra doanh thu trước khi báo cáo");
            }
        } else {
            this.dispose();
            Jfr_DangNhap menu8 = new Jfr_DangNhap();
            menu8.setVisible(true);
        }
    }//GEN-LAST:event_btnDangXuatMouseClicked

    private void btnThongKeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThongKeMouseClicked
        if (Auth.isManager()) {
            Jfr_KhuyenMai menu9 = new Jfr_KhuyenMai();
            jDesktopPane1.add(menu9).setVisible(true);
            if (Jfr_HoaDon.webcam != null) {
                Jfr_HoaDon.webcam.close();
            }
        } else {
            MsgBox.alert(this, "Bạn không có quyền truy cập chức năng này");
        }

    }//GEN-LAST:event_btnThongKeMouseClicked

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
            java.util.logging.Logger.getLogger(Jfr_CuaSoChinh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Jfr_CuaSoChinh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Jfr_CuaSoChinh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Jfr_CuaSoChinh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Jfr_CuaSoChinh().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel btnDangXuat;
    private javax.swing.JPanel btnDoiMatKhau;
    private javax.swing.JPanel btnHoaDon;
    private javax.swing.JPanel btnKhachHang;
    private javax.swing.JPanel btnLichSu;
    private javax.swing.JPanel btnNhanVien;
    private javax.swing.JPanel btnSanPham;
    private javax.swing.JPanel btnThongKe;
    private javax.swing.JPanel btnTrangChu;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lb;
    private javax.swing.JLabel lb1;
    private javax.swing.JLabel lb2;
    private javax.swing.JLabel lb3;
    private javax.swing.JLabel lb4;
    private javax.swing.JLabel lb5;
    private javax.swing.JLabel lb6;
    private javax.swing.JLabel lb7;
    private javax.swing.JLabel lb8;
    private javax.swing.JPanel menu;
    private javax.swing.JPanel pnTrangChu;
    // End of variables declaration//GEN-END:variables

}
