/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DAO.HoaDonCTDAO;
import DAO.HoaDonDAO;
import DAO.KhachHangDAO;
import DAO.NhanVienDAO;
import DAO.SanPhamDAO;
import Entity.HoaDon;
import Entity.HoaDonCT;
import Entity.KhachHang;
import Entity.NhanVien;
import Entity.SanPham;
import Ultil.XDate;
import java.util.ArrayList;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class Jfr_LichSuGiaoDich extends javax.swing.JInternalFrame {

    HoaDonDAO hdDAO = new HoaDonDAO();
    HoaDonCTDAO hdctdao = new HoaDonCTDAO();
    NhanVienDAO nvDAO = new NhanVienDAO();
    KhachHangDAO khDao = new KhachHangDAO();
    SanPhamDAO spdao = new SanPhamDAO();
    ArrayList<HoaDon> listHD = new ArrayList<>();
    ArrayList<HoaDonCT> listHDCT = new ArrayList<>();
    DefaultTableModel modeltbHD;
    DefaultTableModel modeltbSP;
    int index = -1;

    /**
     * Creates new form Jfr_LichSuGiaoDich
     */
    public Jfr_LichSuGiaoDich() {
        initComponents();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);
        modeltbHD = (DefaultTableModel) tblDanhSachHoaDon.getModel();
        modeltbSP = (DefaultTableModel) tbSanPham.getModel();
        DoVaoTableHoaDon();
        lbLyDo.setVisible(false);
        lbLydo1.setVisible(false);
    }

    public void DoVaoTableHoaDon() {
        listHD = (ArrayList<HoaDon>) hdDAO.selectAll_2(txtMaHD.getText(), txtTenNV.getText(),
                txtTenKhanhHang.getText(), txtNgayTao.getText());
        modeltbHD.setRowCount(1);
        int k = 0;
        for (int i = listHD.size() - 1; i >= 0; i--) {
            HoaDon hd = listHD.get(i);
            KhachHang kh = khDao.selectByID(String.valueOf(hd.getMaKH()));
            NhanVien nv = nvDAO.selectByID(String.valueOf(hd.getMaNV()));
            String tt = "";
            if (kh.getDiaChi().length() > 4) {
                tt = hd.getTrangThai() ? "Đã Giao Hàng" : "Đang Giao Hàng";
            } else if (hd.getGhiChu().length() > 4) {
                tt = "Đơn Đã Hủy";
            } else {
                tt = hd.getTrangThai() ? "Đã Thanh Toán" : "Chưa Thanh Toán";
            }
            modeltbHD.addRow(new Object[]{
                k + 1, hd.getMaHD(), nv.getTenNV(), kh.getTenKH(), hd.getNgayTao(), tt
            });
            k++;
        }

    }

    public void DoVaoTableSP() {
        index = tblDanhSachHoaDon.getSelectedRow();
        String MaHD = tblDanhSachHoaDon.getValueAt(index, 1).toString();
        HoaDon hd = hdDAO.selectByID(MaHD);
        listHDCT = (ArrayList<HoaDonCT>) hdctdao.selectAll_2(MaHD);
        KhachHang kh = khDao.selectByID(String.valueOf(hd.getMaKH()));
        NhanVien nv = nvDAO.selectByID(String.valueOf(hd.getMaNV()));
        modeltbSP.setRowCount(0);
        for (HoaDonCT x : listHDCT) {
            SanPham sp = spdao.selectByID2(String.valueOf(x.getMaCTSP()));
            modeltbSP.addRow(new Object[]{
                1, x.getMaCTSP(), sp.getTenSP(), x.getSoLuong(), x.getGia(), x.getGiamGia(), x.getThanhTien()
            });
        }
        SapXepDSSP();
        TingTien();
        if (kh.getDiaChi().length() > 4) {
            if (tblDanhSachHoaDon.getValueAt(index, 5).toString().equals("Đơn Đã Hủy")) {
                lbLyDo.setVisible(true);
                lbLydo1.setVisible(true);
                lbLyDo.setText(hd.getGhiChu());
                lbTenNV.setText(nv.getTenNV());
                lblTenKH.setText(kh.getTenKH());
                lblSDT.setText(kh.getSDT());
                lblDiaChi.setText(kh.getDiaChi());
                lbMaNV.setText(nv.getMaNV());
                lbNgayTao.setText(XDate.toString(hd.getNgayTao()));
                lbTrangThai.setText(tblDanhSachHoaDon.getValueAt(index, 5) + "");
            } else {
                lbLyDo.setVisible(false);
                lbLydo1.setVisible(false);
                lbTenNV.setText(nv.getTenNV());
                lblTenKH.setText(kh.getTenKH());
                lblSDT.setText(kh.getSDT());
                lblDiaChi.setText(kh.getDiaChi());
                lbMaNV.setText(nv.getMaNV());
                lbNgayTao.setText(XDate.toString(hd.getNgayTao()));
                lbTrangThai.setText(tblDanhSachHoaDon.getValueAt(index, 5) + "");
            }
        } else {
            if (tblDanhSachHoaDon.getValueAt(index, 5).toString().equals("Đơn Đã Hủy")) {
                lbLyDo.setVisible(true);
                lbLydo1.setVisible(true);
                lbLyDo.setText(hd.getGhiChu());
                lbTenNV.setText(nv.getTenNV());
                lblTenKH.setText(kh.getTenKH());
                lblSDT.setText(kh.getSDT());
                lbMaNV.setText(nv.getMaNV());
                lbNgayTao.setText(XDate.toString(hd.getNgayTao()));
                lbTrangThai.setText(tblDanhSachHoaDon.getValueAt(index, 5) + "");
            } else {
                lbLyDo.setVisible(false);
                lbLydo1.setVisible(false);
                lbTenNV.setText(nv.getTenNV());
                lblTenKH.setText(kh.getTenKH());
                lblSDT.setText(kh.getSDT());
                lbMaNV.setText(nv.getMaNV());
                lbNgayTao.setText(XDate.toString(hd.getNgayTao()));
                lbTrangThai.setText(tblDanhSachHoaDon.getValueAt(index, 5) + "");
            }
        }
    }

    public void SapXepDSSP() {
        if (tbSanPham.getRowCount() > 0) {
            for (int i = 0; i < tbSanPham.getRowCount(); i++) {
                modeltbSP.setValueAt(i + 1, i, 0);
            }
        }
    }

    public void TingTien() {
        Double tien = 0.0;
        for (int i = 0; i < modeltbSP.getRowCount(); i++) {
            tien += Double.valueOf(modeltbSP.getValueAt(i, 6).toString());
        }
        lbTongTienHang.setText(String.valueOf(tien));
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
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jTextField2 = new javax.swing.JTextField();
        txtMaHD = new javax.swing.JTextField();
        txtTenNV = new javax.swing.JTextField();
        txtTenKhanhHang = new javax.swing.JTextField();
        txtNgayTao = new javax.swing.JTextField();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDanhSachHoaDon = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel55 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        lbTongTienHang = new javax.swing.JLabel();
        lblTenKH = new javax.swing.JLabel();
        lblSDT = new javax.swing.JLabel();
        lblDiaChi = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lbTrangThai = new javax.swing.JLabel();
        lbLydo1 = new javax.swing.JLabel();
        lbLyDo = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lbTenNV = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lbNgayTao = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lbMaNV = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbSanPham = new javax.swing.JTable();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 0, 14), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setLayout(new java.awt.GridLayout(1, 0));

        jTextField2.setEditable(false);
        jPanel5.add(jTextField2);

        txtMaHD.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMaHDKeyReleased(evt);
            }
        });
        jPanel5.add(txtMaHD);

        txtTenNV.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTenNVKeyReleased(evt);
            }
        });
        jPanel5.add(txtTenNV);

        txtTenKhanhHang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTenKhanhHangKeyReleased(evt);
            }
        });
        jPanel5.add(txtTenKhanhHang);

        txtNgayTao.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNgayTaoKeyReleased(evt);
            }
        });
        jPanel5.add(txtNgayTao);

        jTextField1.setEditable(false);
        jPanel5.add(jTextField1);

        jPanel2.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 870, 35));

        tblDanhSachHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã HĐ", "Tên nhân viên", "Tên khách hàng", "Ngày tạo", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDanhSachHoaDon.setRowHeight(30);
        tblDanhSachHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDanhSachHoaDonMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblDanhSachHoaDon);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 870, 340));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 910, 390));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel55.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel55.setForeground(new java.awt.Color(0, 0, 0));
        jLabel55.setText("Tên KH:");
        jPanel3.add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, -1));

        jLabel54.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel54.setForeground(new java.awt.Color(0, 0, 0));
        jLabel54.setText("Số Điện Thoại:");
        jPanel3.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, -1, -1));

        jLabel56.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel56.setForeground(new java.awt.Color(0, 0, 0));
        jLabel56.setText("Địa chỉ:");
        jPanel3.add(jLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, -1, -1));

        jLabel57.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(0, 0, 0));
        jLabel57.setText("Tổng tiền hàng:");
        jPanel3.add(jLabel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, -1, -1));

        lbTongTienHang.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbTongTienHang.setForeground(new java.awt.Color(0, 0, 0));
        lbTongTienHang.setText("0");
        jPanel3.add(lbTongTienHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 190, 140, -1));

        lblTenKH.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jPanel3.add(lblTenKH, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 80, 210, 30));

        lblSDT.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jPanel3.add(lblSDT, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 120, 180, 30));

        lblDiaChi.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jPanel3.add(lblDiaChi, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 150, 210, 30));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel1.setText("Trạng Thái:");
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, 90, -1));

        lbTrangThai.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jPanel3.add(lbTrangThai, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 270, 180, 30));

        lbLydo1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbLydo1.setText("Lý Do Hủy:");
        jPanel3.add(lbLydo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, -1, -1));

        lbLyDo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jPanel3.add(lbLyDo, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 310, 190, 30));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel3.setText("Tên Nhân Viên:");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        lbTenNV.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jPanel3.add(lbTenNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 50, 170, 30));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel2.setText("Ngày Tạo:");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, -1, -1));

        lbNgayTao.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jPanel3.add(lbNgayTao, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 230, 170, 30));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel4.setText("Mã Nhân Viên:");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        lbMaNV.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jPanel3.add(lbMaNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 20, 170, 30));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 20, 300, 730));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 0, 14), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã SP", "Tên SP", "Số lượng", "Đơn giá", "Giảm giá", "Thành tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbSanPham.setRowHeight(30);
        jScrollPane2.setViewportView(tbSanPham);

        jPanel4.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 870, 280));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 420, 910, 330));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1260, 770));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtMaHDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMaHDKeyReleased
        // TODO add your handling code here:
        DoVaoTableHoaDon();
    }//GEN-LAST:event_txtMaHDKeyReleased

    private void txtTenKhanhHangKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTenKhanhHangKeyReleased
        // TODO add your handling code here:
        DoVaoTableHoaDon();
    }//GEN-LAST:event_txtTenKhanhHangKeyReleased

    private void txtTenNVKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTenNVKeyReleased
        // TODO add your handling code here:
        DoVaoTableHoaDon();
    }//GEN-LAST:event_txtTenNVKeyReleased

    private void txtNgayTaoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNgayTaoKeyReleased
        // TODO add your handling code here:
        DoVaoTableHoaDon();
    }//GEN-LAST:event_txtNgayTaoKeyReleased

    private void tblDanhSachHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDanhSachHoaDonMouseClicked
        // TODO add your handling code here:
        DoVaoTableSP();
    }//GEN-LAST:event_tblDanhSachHoaDonMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JLabel lbLyDo;
    private javax.swing.JLabel lbLydo1;
    private javax.swing.JLabel lbMaNV;
    private javax.swing.JLabel lbNgayTao;
    private javax.swing.JLabel lbTenNV;
    private javax.swing.JLabel lbTongTienHang;
    private javax.swing.JLabel lbTrangThai;
    private javax.swing.JLabel lblDiaChi;
    private javax.swing.JLabel lblSDT;
    private javax.swing.JLabel lblTenKH;
    private javax.swing.JTable tbSanPham;
    private javax.swing.JTable tblDanhSachHoaDon;
    private javax.swing.JTextField txtMaHD;
    private javax.swing.JTextField txtNgayTao;
    private javax.swing.JTextField txtTenKhanhHang;
    private javax.swing.JTextField txtTenNV;
    // End of variables declaration//GEN-END:variables
}
