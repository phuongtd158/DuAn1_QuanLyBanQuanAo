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
    }

    public void DoVaoTableHoaDon() {
        listHD = (ArrayList<HoaDon>) hdDAO.selectAll_2(txtMaHD.getText(), txtTenNV.getText(),
                txtTenKhanhHang.getText(), txtNgayTao.getText(),txtTrangThai.getText());
        modeltbHD.setRowCount(1);
        int k = 0;
        for (int i = listHD.size() - 1; i >= 0; i--) {
            HoaDon hd = listHD.get(i);
            KhachHang kh = khDao.selectByID(String.valueOf(hd.getMaKH()));
            NhanVien nv = nvDAO.selectByID(String.valueOf(hd.getMaNV()));
            modeltbHD.addRow(new Object[]{
                k + 1, hd.getMaHD(), nv.getTenNV(), kh.getTenKH(), hd.getNgayTao(), hd.getTrangThai()
            });
            k++;
        }

    }
    
    public String HamDinhDang(String sk) {
        int a = 0, ac;
        String d = "";

        if (sk.charAt(0) == '-') {
            a = 1;
            sk = sk.substring(1);
        }

        ac = sk.indexOf(".");
        if (ac >= 0) {
            sk = sk.substring(0, sk.indexOf("."));
        }
        int k = sk.length();

        if (k >= 4 && k <= 6) {
            if (k == 6 || k == 5) {
                d = sk.substring(0, k / 2) + "," + sk.substring(k / 2, k);
            } else {
                d = sk.substring(0, k / 2 - 1) + "," + sk.substring(k / 2 - 1, k);
            }
        } else if (k == 7 || k == 8) {
            d = sk.substring(0, k / 2 - 2) + "," + sk.substring(k / 2 - 2, k / 2 + 1) + "," + sk.substring(k / 2 + 1, k);
        }

        if (a == 1) {
            return "-" + d + " VNĐ";
        }
        return d + " VNĐ";
    }
    
        // Hàm đổi lại định dang tiền 
    public String HamDinhDang2( String sk ){
        String ab = "";
        int ak = sk.indexOf(" ");

        if (ak >= 0) {
            sk = sk.substring(0, ak);
        }
        
        String a[] = sk.split(",");
        for (int i = 0; i < a.length; i++) {
            ab += a[i];
        }
        return ab ;
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
            String tt = x.getTrangThai() ? "Đã Bán" : "Bị Hủy";
            modeltbSP.addRow(new Object[]{
                1, x.getMaCTSP(), sp.getTenSP(), x.getSoLuong(), HamDinhDang( String.valueOf( x.getGia() )) , x.getGiamGia(), 
                HamDinhDang( String.valueOf( x.getThanhTien())) , tt,x.getGhiChu()
            });
        }
        SapXepDSSP();
        TinhTien();
        if (kh.getDiaChi().length() > 4) {
            if (tblDanhSachHoaDon.getValueAt(index, 5).toString().equals("Đã Hủy") ||
                    tblDanhSachHoaDon.getValueAt(index, 5).toString().equals("Đơn hàng âm")) {
                lbTenNV.setText(nv.getTenNV());
                lblTenKH.setText(kh.getTenKH());
                lblSDT.setText(kh.getSDT());
                lblDiaChi.setText(kh.getDiaChi());
                lbMaNV.setText(nv.getMaNV());
                lbNgayTao.setText(XDate.toString(hd.getNgayTao()));
                lbTrangThai.setText(tblDanhSachHoaDon.getValueAt(index, 5) + "");
                txtLyDo.setText(hd.getGhiChu());
            } else {
                lbTenNV.setText(nv.getTenNV());
                lblTenKH.setText(kh.getTenKH());
                lblSDT.setText(kh.getSDT());
                lblDiaChi.setText(kh.getDiaChi());
                lbMaNV.setText(nv.getMaNV());
                lbNgayTao.setText(XDate.toString(hd.getNgayTao()));
                lbTrangThai.setText(tblDanhSachHoaDon.getValueAt(index, 5) + "");
                txtLyDo.setText(hd.getGhiChu());
            }
        } else {
            if (tblDanhSachHoaDon.getValueAt(index, 5).toString().equals("Đã Hủy") ||
                    tblDanhSachHoaDon.getValueAt(index, 5).toString().equals("Đơn hàng âm")) {
                lbTenNV.setText(nv.getTenNV());
                lblTenKH.setText(kh.getTenKH());
                lblSDT.setText(kh.getSDT());
                lbMaNV.setText(nv.getMaNV());
                lbNgayTao.setText(XDate.toString(hd.getNgayTao()));
                lbTrangThai.setText(tblDanhSachHoaDon.getValueAt(index, 5) + "");
                lblDiaChi.setText("");
                txtLyDo.setText(hd.getGhiChu());
            } else {
                lbTenNV.setText(nv.getTenNV());
                lblTenKH.setText(kh.getTenKH());
                lblSDT.setText(kh.getSDT());
                lbMaNV.setText(nv.getMaNV());
                lbNgayTao.setText(XDate.toString(hd.getNgayTao()));
                lbTrangThai.setText(tblDanhSachHoaDon.getValueAt(index, 5) + "");
                lblDiaChi.setText("");
                txtLyDo.setText(hd.getGhiChu());
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

    public void TinhTien() {
        Double tien = 0.0;
        for (int i = 0; i < modeltbSP.getRowCount(); i++) {
            tien += Double.valueOf( HamDinhDang2(modeltbSP.getValueAt(i, 6).toString() ) );
        }
        lbTongTienHang.setText( HamDinhDang(String.valueOf(tien) ) );
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
        txtTrangThai = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDanhSachHoaDon = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        lbMaNV = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        lbTenNV = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel55 = new javax.swing.JLabel();
        lblTenKH = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel54 = new javax.swing.JLabel();
        lblSDT = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel56 = new javax.swing.JLabel();
        lblDiaChi = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lbNgayTao = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lbTrangThai = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        lbLydo1 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jLabel57 = new javax.swing.JLabel();
        lbTongTienHang = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtLyDo = new javax.swing.JTextArea();
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

        txtTrangThai.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTrangThaiKeyReleased(evt);
            }
        });
        jPanel5.add(txtTrangThai);

        jPanel2.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 860, 35));

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

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setLayout(new java.awt.GridLayout(1, 0));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Mã Nhân Viên:");
        jPanel6.add(jLabel4);

        lbMaNV.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbMaNV.setForeground(new java.awt.Color(0, 0, 0));
        jPanel6.add(lbMaNV);

        jPanel3.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 290, 40));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setLayout(new java.awt.GridLayout(1, 0));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Tên Nhân Viên:");
        jPanel7.add(jLabel3);

        lbTenNV.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbTenNV.setForeground(new java.awt.Color(0, 0, 0));
        jPanel7.add(lbTenNV);

        jPanel3.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 290, 40));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setLayout(new java.awt.GridLayout(1, 0));

        jLabel55.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel55.setForeground(new java.awt.Color(0, 0, 0));
        jLabel55.setText("Tên KH:");
        jPanel8.add(jLabel55);

        lblTenKH.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblTenKH.setForeground(new java.awt.Color(0, 0, 0));
        jPanel8.add(lblTenKH);

        jPanel3.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 290, 40));

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setLayout(new java.awt.GridLayout(1, 0));

        jLabel54.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel54.setForeground(new java.awt.Color(0, 0, 0));
        jLabel54.setText("Số Điện Thoại:");
        jPanel9.add(jLabel54);

        lblSDT.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblSDT.setForeground(new java.awt.Color(0, 0, 0));
        jPanel9.add(lblSDT);

        jPanel3.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 290, 40));

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setLayout(new java.awt.GridLayout(1, 0));

        jLabel56.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel56.setForeground(new java.awt.Color(0, 0, 0));
        jLabel56.setText("Địa chỉ:");
        jPanel10.add(jLabel56);

        lblDiaChi.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblDiaChi.setForeground(new java.awt.Color(0, 0, 0));
        jPanel10.add(lblDiaChi);

        jPanel3.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 290, 40));

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setLayout(new java.awt.GridLayout(1, 0));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Ngày Tạo:");
        jPanel12.add(jLabel2);

        lbNgayTao.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbNgayTao.setForeground(new java.awt.Color(0, 0, 0));
        jPanel12.add(lbNgayTao);

        jPanel3.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 290, 40));

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setLayout(new java.awt.GridLayout(1, 0));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Trạng Thái:");
        jPanel13.add(jLabel1);

        lbTrangThai.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbTrangThai.setForeground(new java.awt.Color(0, 0, 0));
        jPanel13.add(lbTrangThai);

        jPanel3.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 290, 40));

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setLayout(new java.awt.GridLayout(1, 0));

        lbLydo1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbLydo1.setForeground(new java.awt.Color(0, 0, 0));
        lbLydo1.setText("Lý Do Hủy:");
        jPanel14.add(lbLydo1);

        jPanel3.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, 290, 40));

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setLayout(new java.awt.GridLayout(1, 0));

        jLabel57.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(0, 0, 0));
        jLabel57.setText("Tổng tiền hàng:");
        jPanel15.add(jLabel57);

        lbTongTienHang.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbTongTienHang.setForeground(new java.awt.Color(0, 0, 0));
        lbTongTienHang.setText("0");
        jPanel15.add(lbTongTienHang);

        jPanel3.add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 290, 50));

        txtLyDo.setEditable(false);
        txtLyDo.setColumns(20);
        txtLyDo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtLyDo.setLineWrap(true);
        txtLyDo.setRows(5);
        txtLyDo.setWrapStyleWord(true);
        jScrollPane3.setViewportView(txtLyDo);

        jPanel3.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 390, 290, -1));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 20, 300, 730));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 0, 14), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã SP", "Tên SP", "Số lượng", "Đơn giá", "Giảm giá", "Thành tiền", "Trạng Thái", "Lý Do Hủy"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
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

    private void txtTrangThaiKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTrangThaiKeyReleased
        // TODO add your handling code here:
        DoVaoTableHoaDon();
    }//GEN-LAST:event_txtTrangThaiKeyReleased


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
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jTextField2;
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
    private javax.swing.JTextArea txtLyDo;
    private javax.swing.JTextField txtMaHD;
    private javax.swing.JTextField txtNgayTao;
    private javax.swing.JTextField txtTenKhanhHang;
    private javax.swing.JTextField txtTenNV;
    private javax.swing.JTextField txtTrangThai;
    // End of variables declaration//GEN-END:variables
}
