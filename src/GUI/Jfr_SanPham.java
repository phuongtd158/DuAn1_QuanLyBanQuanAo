/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DAO.ChatLieuDAO;
import DAO.KichThuocDAO;
import DAO.LoaiSanPhamDAO;
import DAO.MauSacDAO;
import DAO.SanPhamDAO;
import Entity.ChatLieu;
import Entity.KichThuoc;
import Entity.LoaiSP;
import Entity.MauSac;
import Entity.SanPham;
import Ultil.Check;
import Ultil.MsgBox;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.awt.Color;
import java.awt.List;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class Jfr_SanPham extends javax.swing.JInternalFrame {

    Color defualtColor, ClickColor;

    MauSacDAO daoMS = new MauSacDAO();
    ChatLieuDAO daoCL = new ChatLieuDAO();
    KichThuocDAO daoKT = new KichThuocDAO();
    LoaiSanPhamDAO daoLSP = new LoaiSanPhamDAO();
    SanPhamDAO daoSP = new SanPhamDAO();

    ArrayList<MauSac> listMS = new ArrayList<>();
    ArrayList<ChatLieu> listCL = new ArrayList<>();
    ArrayList<KichThuoc> listKT = new ArrayList<>();
    ArrayList<LoaiSP> listLSP = new ArrayList<>();
    ArrayList<SanPham> listSP;

    DefaultTableModel modelThuocTinh;
    DefaultTableModel modelSP;
    DefaultComboBoxModel<Object> cbbBoxModel;
    int Index = -1;
    public static Jfr_SanPham sp;

    public Jfr_SanPham() {
        initComponents();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);
        sp = this;
        modelThuocTinh = (DefaultTableModel) table_ThuocTinh.getModel();
        modelSP = (DefaultTableModel) tblSanPham.getModel();

        DoVaoTableChiTiet();
        defualtColor = new Color(255, 255, 255);
        ClickColor = new Color(221, 221, 221);

        doVaoChatLieu();
        doVaoMauSac();
        doVaoLoaiSP();
        doVaoKichThuoc();

    }

    //Đổ dữ liệu từ thuộc tính vào combobox màu sắc
    public void doVaoMauSac() {
        cbbBoxModel = (DefaultComboBoxModel) cbbMauSac.getModel();
        cbbBoxModel.removeAllElements();
        listMS = (ArrayList<MauSac>) daoMS.selectAll();
        for (MauSac mauSac : listMS) {
            if (mauSac.getTrangThai() == true) {
                cbbBoxModel.addElement(mauSac);
            }
        }

    }

    public void set(String s) {
        if (s.equals("a")) {
            DoVaoTableChiTiet();
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

    //Đổ dữ liệu từ thuộc tính vào combobox chất liệu
    public void doVaoChatLieu() {
        cbbBoxModel = (DefaultComboBoxModel) cbbChatLieu.getModel();
        cbbBoxModel.removeAllElements();
        listCL = (ArrayList<ChatLieu>) daoCL.selectAll();

        for (ChatLieu chatLieu : listCL) {
            if (chatLieu.isTrangThai() == true) {
                cbbBoxModel.addElement(chatLieu);
            }
        }
    }

    //Đổ dữ liệu từ thuộc tính vào combobox kích thước
    public void doVaoKichThuoc() {
        cbbBoxModel = (DefaultComboBoxModel) cbbKichThuoc.getModel();
        cbbBoxModel.removeAllElements();
        listKT = (ArrayList<KichThuoc>) daoKT.selectAll();

        for (KichThuoc kichThuoc : listKT) {
            if (kichThuoc.getTrangThai() == true) {
                cbbBoxModel.addElement(kichThuoc);
            }
        }
    }

    //Đổ dữ liệu từ thuộc tính vào combobox loại sản phẩm
    public void doVaoLoaiSP() {
        cbbBoxModel = (DefaultComboBoxModel) cbbLoaiSanPham.getModel();
        cbbBoxModel.removeAllElements();
        listLSP = (ArrayList<LoaiSP>) daoLSP.selectAll();

        for (LoaiSP loaiSP : listLSP) {
            if (loaiSP.isTrangThai() == true) {
                cbbBoxModel.addElement(loaiSP);
            }
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        tabs = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        txtMaSP = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtDonGia = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        cbbChatLieu = new javax.swing.JComboBox<>();
        cbbLoaiSanPham = new javax.swing.JComboBox<>();
        cbbKichThuoc = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        cbbMauSac = new javax.swing.JComboBox<>();
        txtTenSP = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        txtTimKiem = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        btnSua = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        btnXoa = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        btnMoi = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        btnThem = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        btnHienThi = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtTenThuocTinh = new javax.swing.JTextField();
        rbMauSac = new javax.swing.JRadioButton();
        rbKichThuoc = new javax.swing.JRadioButton();
        rbChatLieu = new javax.swing.JRadioButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        rbLoaiSP = new javax.swing.JRadioButton();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_ThuocTinh = new javax.swing.JTable();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1260, 780));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabs.setBackground(new java.awt.Color(255, 255, 255));
        tabs.setForeground(new java.awt.Color(0, 0, 0));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Quản lý sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 1, 16), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtMaSP.setEditable(false);
        jPanel5.add(txtMaSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 50, 430, 30));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Màu sắc");
        jPanel5.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 50, -1, 30));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Kích thước");
        jPanel5.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 100, -1, 30));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Đơn giá");
        jPanel5.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, -1, 30));
        jPanel5.add(txtDonGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 200, 430, 30));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Số lượng");
        jPanel5.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, -1, 30));
        jPanel5.add(txtSoLuong, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 250, 430, 30));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Chất liệu");
        jPanel5.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 150, -1, 30));

        cbbChatLieu.setBackground(new java.awt.Color(255, 255, 255));
        cbbChatLieu.setForeground(new java.awt.Color(0, 0, 0));
        jPanel5.add(cbbChatLieu, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 150, 330, 30));

        cbbLoaiSanPham.setBackground(new java.awt.Color(255, 255, 255));
        cbbLoaiSanPham.setForeground(new java.awt.Color(0, 0, 0));
        jPanel5.add(cbbLoaiSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 150, 430, 30));

        cbbKichThuoc.setBackground(new java.awt.Color(255, 255, 255));
        cbbKichThuoc.setForeground(new java.awt.Color(0, 0, 0));
        jPanel5.add(cbbKichThuoc, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 100, 330, 30));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Tên sản phẩm");
        jPanel5.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, -1, 30));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Loại sản phẩm");
        jPanel5.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, -1, 30));

        cbbMauSac.setBackground(new java.awt.Color(255, 255, 255));
        cbbMauSac.setForeground(new java.awt.Color(0, 0, 0));
        jPanel5.add(cbbMauSac, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 50, 330, 30));
        jPanel5.add(txtTenSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 100, 430, 30));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Mã sản phẩm");
        jPanel5.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, 30));

        jPanel2.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 1200, 290));

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 1, 16), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Loại sản phẩm", "Kích thước", "Màu sắc", "Chất liệu", "Đơn giá", "Số lượng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSanPham.setRowHeight(25);
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblSanPham);

        jPanel12.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 1180, 260));

        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
        });
        jPanel12.add(txtTimKiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 20, 350, 30));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Tìm kiếm sản phẩm");
        jPanel12.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 20, -1, 30));

        jPanel2.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 1200, 330));

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnSua.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnSua.setForeground(new java.awt.Color(0, 0, 0));
        btnSua.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_refresh_25px.png"))); // NOI18N
        btnSua.setText("Sửa");
        btnSua.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSuaMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnSua, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnSua, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 660, 90, -1));

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel14MouseClicked(evt);
            }
        });

        btnXoa.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnXoa.setForeground(new java.awt.Color(0, 0, 0));
        btnXoa.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_delete_trash_25px.png"))); // NOI18N
        btnXoa.setText("Ẩn");
        btnXoa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnXoaMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnXoaMousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnXoa, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnXoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 660, 90, -1));

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnMoi.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnMoi.setForeground(new java.awt.Color(0, 0, 0));
        btnMoi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_broom_25px.png"))); // NOI18N
        btnMoi.setText("Mới");
        btnMoi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnMoiMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnMoi, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnMoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 660, -1, -1));

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel16MouseClicked(evt);
            }
        });

        btnThem.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnThem.setForeground(new java.awt.Color(0, 0, 0));
        btnThem.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_product_25px.png"))); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThemMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnThem, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 660, -1, -1));

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));
        jPanel17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnHienThi.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnHienThi.setForeground(new java.awt.Color(0, 0, 0));
        btnHienThi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnHienThi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_hide_25px.png"))); // NOI18N
        btnHienThi.setText("Hiển thị các sản phẩm đã ẩn");
        btnHienThi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHienThiMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnHienThi, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnHienThi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 660, 230, -1));

        tabs.addTab("Thông tin chi tiết", jPanel2);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thuộc tính sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 1, 16), new java.awt.Color(0, 0, 0))); // NOI18N

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Tên thuộc tính");

        buttonGroup1.add(rbMauSac);
        rbMauSac.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        rbMauSac.setForeground(new java.awt.Color(0, 0, 0));
        rbMauSac.setText("Màu sắc");
        rbMauSac.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbMauSacMouseClicked(evt);
            }
        });

        buttonGroup1.add(rbKichThuoc);
        rbKichThuoc.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        rbKichThuoc.setForeground(new java.awt.Color(0, 0, 0));
        rbKichThuoc.setText("Kích thước");
        rbKichThuoc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbKichThuocMouseClicked(evt);
            }
        });

        buttonGroup1.add(rbChatLieu);
        rbChatLieu.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        rbChatLieu.setForeground(new java.awt.Color(0, 0, 0));
        rbChatLieu.setText("Chất liệu");
        rbChatLieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbChatLieuMouseClicked(evt);
            }
        });

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_product_25px.png"))); // NOI18N
        jLabel8.setText("Thêm");
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
        );

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_refresh_25px.png"))); // NOI18N
        jLabel9.setText("Sửa");
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_delete_trash_25px.png"))); // NOI18N
        jLabel10.setText("Ẩn");
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        buttonGroup1.add(rbLoaiSP);
        rbLoaiSP.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        rbLoaiSP.setForeground(new java.awt.Color(0, 0, 0));
        rbLoaiSP.setText("Tên loại sản phẩm");
        rbLoaiSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbLoaiSPMouseClicked(evt);
            }
        });
        rbLoaiSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbLoaiSPActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(txtTenThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(59, 59, 59)
                        .addComponent(rbMauSac)
                        .addGap(52, 52, 52)
                        .addComponent(rbKichThuoc)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                        .addComponent(rbChatLieu)
                        .addGap(38, 38, 38)
                        .addComponent(rbLoaiSP)
                        .addGap(19, 19, 19))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rbMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rbKichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rbChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rbLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(27, 27, 27))
        );

        jPanel4.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 1200, 170));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin thuộc tính", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 1, 16), new java.awt.Color(0, 0, 0))); // NOI18N

        table_ThuocTinh.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "STT", "Loại thuộc tính", "Tên thuộc tinh"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_ThuocTinh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_ThuocTinhMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table_ThuocTinh);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel4.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 1200, 460));

        tabs.addTab("Thuộc tính sản phẩm", jPanel4);

        jPanel1.add(tabs, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 1230, 720));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1248, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 744, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rbLoaiSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbLoaiSPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbLoaiSPActionPerformed

    public SanPham GetForm() {
        ChatLieu cl = (ChatLieu) cbbChatLieu.getSelectedItem();
        KichThuoc kt = (KichThuoc) cbbKichThuoc.getSelectedItem();
        LoaiSP lsp = (LoaiSP) cbbLoaiSanPham.getSelectedItem();
        MauSac ms = (MauSac) cbbMauSac.getSelectedItem();
        SanPham sp = new SanPham();

        sp.setTenSP(txtTenSP.getText());
        sp.setTenKichThuoc(kt.getTenKT());
        sp.setTenChatLieu(cl.getTenChatLieu());
        sp.setTenLoai(lsp.getTenLoaiSP());
        sp.setTenMauSac(ms.getTenMau());
        sp.setGia( Double.valueOf( HamDinhDang2( txtDonGia.getText())  ));
        sp.setSoLuong(Integer.valueOf(txtSoLuong.getText()));
        sp.setMaSP(daoSP.SelectByIDSp(txtTenSP.getText()));
        System.out.println("" + daoSP.SelectByIDSp(txtTenSP.getText()));
        if (!txtMaSP.getText().equals("")) {
            sp.setMaCTSP(Integer.valueOf(txtMaSP.getText()));
        }
//        sp.setMaCTSP(Integer.valueOf(txtMaSP.getText()));

        return sp;
    }

    // Thêm SP ;
    private void ThemSP() {

        if (Check.checkTrongText(txtTenSP) == false) {
            return;
        }

        if (Check.checkTrongText(txtDonGia) == false) {
            return;
        }

        if (Check.checkTrongText(txtSoLuong) == false) {
            return;
        }

        if (Check.checkSoDuong(txtSoLuong) == false) {
            return;
        }
//        if ( ) {
//            return;
//        }

        ChatLieu cl = (ChatLieu) cbbChatLieu.getSelectedItem();
        KichThuoc kt = (KichThuoc) cbbKichThuoc.getSelectedItem();
        LoaiSP lsp = (LoaiSP) cbbLoaiSanPham.getSelectedItem();
        MauSac ms = (MauSac) cbbMauSac.getSelectedItem();
        SanPham sp = GetForm();

        try {
            if (daoSP.SelectByIDSp(txtTenSP.getText()) < 1) {
                daoSP.Insert_SP(txtTenSP.getText());
                sp.setMaSP(daoSP.SelectByIDSp(txtTenSP.getText()));
                daoSP.Insert_SPCT(sp, lsp.getMaLoaiSP(), ms.getMaMau(), kt.getMaKT(), cl.getMaCL());
                DoVaoTableChiTiet();
                MsgBox.alert(this, "Thêm thành công");
            } else {
                for (SanPham x : listSP) {
                    if (x.getTenSP().equalsIgnoreCase(sp.getTenSP()) && x.getTenChatLieu().equalsIgnoreCase(sp.getTenChatLieu())
                            && x.getTenKichThuoc().equalsIgnoreCase(x.getTenKichThuoc()) && x.getTenMauSac().equalsIgnoreCase(sp.getTenMauSac())
                            && x.getTenLoai().equalsIgnoreCase(sp.getTenLoai())) {
                        MsgBox.alert(this, "Sản phẩm đã tồn tại");
                        return;
                    }
                }

                daoSP.Insert_SPCT(sp, lsp.getMaLoaiSP(), ms.getMaMau(), kt.getMaKT(), cl.getMaCL());
                DoVaoTableChiTiet();
                MsgBox.alert(this, "Thêm thành công");
            }

        } catch (Exception e) {
            MsgBox.alert(this, "Thêm thất bại");
        }
    }

    private void btnThemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemMouseClicked
        jPanel16.setBackground(ClickColor);
        jPanel13.setBackground(defualtColor);
        jPanel14.setBackground(defualtColor);
        jPanel15.setBackground(defualtColor);
        jPanel17.setBackground(defualtColor);
        if( MsgBox.comfirm(this , "Bạn có muốn thêm sản phẩm không") ){
            ThemSP();
        }
    }//GEN-LAST:event_btnThemMouseClicked

    private void jPanel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel14MouseClicked

    }//GEN-LAST:event_jPanel14MouseClicked

    private void btnXoaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoaMousePressed

    }//GEN-LAST:event_btnXoaMousePressed

    // Xóa 
    private void XoaSP() {
        Index = tblSanPham.getSelectedRow();
        SanPham sp = GetForm();

        if (Index >= 0) {
            daoSP.Update_2(sp);
            DoVaoTableChiTiet();
            MsgBox.alert(this, "Ẩn thành công");
        }else{
            MsgBox.alert( this , "Vui lòng chọn sản phẩm muốn ẩn");
        }

    }

    private void btnXoaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoaMouseClicked
        jPanel16.setBackground(defualtColor);
        jPanel13.setBackground(defualtColor);
        jPanel14.setBackground(ClickColor);
        jPanel15.setBackground(defualtColor);
        jPanel17.setBackground(defualtColor);
        if( MsgBox.comfirm( this , "Bạn có muốn ẩn sản phẩm không") ){
            XoaSP();
        }        
    }//GEN-LAST:event_btnXoaMouseClicked

    // Hàm Sửa
    private void Update() {

        if (Check.checkTrongText(txtTenSP) == false) {
            return;
        }

        if (Check.checkTrongText(txtDonGia) == false) {
            return;
        }

        if (Check.checkTrongText(txtSoLuong) == false) {
            return;
        }

        if (Check.checkSoDuong(txtSoLuong) == false) {
            return;
        }
        if ( Double.valueOf( HamDinhDang2( txtDonGia.getText())) <= 0 ) {
           
            return;
        }
        SanPham sp = GetForm();
        try {

            System.out.println(sp.getMaCTSP() + " " + sp.getSoLuong() + " " + sp.getGia() + " " + sp.getMaSP() + " " + sp.getTenSP());
            daoSP.Update_1(sp);
            DoVaoTableChiTiet();
            MsgBox.alert(this, "Cập nhập thành công");
        } catch (Exception e) {
            e.printStackTrace();
            MsgBox.alert(this, "Cập nhật thất bại");
        }
    }

    private void btnSuaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSuaMouseClicked
        jPanel16.setBackground(defualtColor);
        jPanel13.setBackground(ClickColor);
        jPanel14.setBackground(defualtColor);
        jPanel15.setBackground(defualtColor);
        jPanel17.setBackground(defualtColor);
        if( MsgBox.comfirm( this , "Bạn có muốn cập nhập không!") ){
            Update();
        }      
    }//GEN-LAST:event_btnSuaMouseClicked

    private void btnHienThiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHienThiMouseClicked
        jPanel16.setBackground(defualtColor);
        jPanel13.setBackground(defualtColor);
        jPanel14.setBackground(defualtColor);
        jPanel15.setBackground(defualtColor);
        jPanel17.setBackground(ClickColor);
        Jfr_SanPhamAn sp = new Jfr_SanPhamAn();
        sp.setVisible(true);
    }//GEN-LAST:event_btnHienThiMouseClicked

    private void jPanel16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel16MouseClicked

    }//GEN-LAST:event_jPanel16MouseClicked

    // Hàm làm mới form 
    private void LamMoiForm() {
        txtDonGia.setText("");
        txtMaSP.setText("");
        txtSoLuong.setText("");
        txtTenSP.setText("");
        txtTenThuocTinh.setText("");
        cbbChatLieu.setSelectedIndex(0);
        cbbKichThuoc.setSelectedIndex(0);
        cbbLoaiSanPham.setSelectedIndex(0);
        cbbMauSac.setSelectedIndex(0);
        txtTimKiem.setText("");
        Index = -1;
    }

    private void btnMoiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMoiMouseClicked
        jPanel16.setBackground(defualtColor);
        jPanel13.setBackground(defualtColor);
        jPanel14.setBackground(defualtColor);
        jPanel15.setBackground(ClickColor);
        jPanel17.setBackground(defualtColor);
        LamMoiForm();
    }//GEN-LAST:event_btnMoiMouseClicked

    // Đổ vào table của thuộc tính
    // Insert Mau 
    private void ThemMauSac() {
        listMS = (ArrayList<MauSac>) daoMS.selectAll_1();

        for (MauSac x : listMS) {
            if (x.getTenMau().equalsIgnoreCase(txtTenThuocTinh.getText()) && x.getTrangThai() == true) {
                MsgBox.alert(this, "Màu sắc đã tồn tại");
                return;
            }

            if (x.getTenMau().equalsIgnoreCase(txtTenThuocTinh.getText()) && x.getTrangThai() == false) {
                daoMS.update_1(x);
                DoVaotableThuocTinh1();
                MsgBox.alert(this, "Thêm thành công");
                return;
            }
        }

        MauSac ms = new MauSac();
        ms.setTenMau(txtTenThuocTinh.getText());
        daoMS.insert(ms);
        DoVaotableThuocTinh1();
    }

    // Insert Chất Liệu
    private void ThemChatLieu() {
        listCL = (ArrayList<ChatLieu>) daoCL.selectAll_1();

        for (ChatLieu x : listCL) {
            if (x.getTenChatLieu().equalsIgnoreCase(txtTenThuocTinh.getText()) && x.isTrangThai() == true) {
                MsgBox.alert(this, "Chất liệu đã tồn tại");
                return;
            }

            if (x.getTenChatLieu().equalsIgnoreCase(txtTenThuocTinh.getText()) && x.isTrangThai() == false) {
                daoCL.update_1(x);
                DoVaotableThuocTinh3();
                MsgBox.alert(this, "Thêm thành công");
                return ;
            }
        }

        ChatLieu ms = new ChatLieu();
        ms.setTenChatLieu(txtTenThuocTinh.getText()) ;
        daoCL.insert(ms);
        DoVaotableThuocTinh3();
    }

    // Insert Kích Thước
    private void ThemKichThuoc() {
        listKT = (ArrayList<KichThuoc>) daoKT.selectAll_1();

        for (KichThuoc x : listKT) {
            if (x.getTenKT().equalsIgnoreCase(txtTenThuocTinh.getText()) && x.getTrangThai() == true) {
                MsgBox.alert(this, "Kích thước đã tồn tại");
                return;
            }

            if (x.getTenKT().equalsIgnoreCase(txtTenThuocTinh.getText()) && x.getTrangThai() == false) {
                daoKT.update_1(x);
                DoVaotableThuocTinh2();
                MsgBox.alert(this, "Thêm thành công");
                return;
            }
        }

        KichThuoc ms = new KichThuoc();
        ms.setTenKT(txtTenThuocTinh.getText());
        daoKT.insert(ms);
        DoVaotableThuocTinh2();
    }

    // Insert Loai SP 
    private void ThemLoaiSP() {
        listLSP = (ArrayList<LoaiSP>) daoLSP.selectAll_1();

        for (LoaiSP x : listLSP) {
            if (x.getTenLoaiSP().equalsIgnoreCase(txtTenThuocTinh.getText()) && x.isTrangThai() == true) {
                MsgBox.alert(this, "Loại sản phẩm đã tồn tại");
                return;
            }

            if (x.getTenLoaiSP().equalsIgnoreCase(txtTenThuocTinh.getText()) && x.isTrangThai() == false) {
                daoLSP.update_1(x);
                DoVaotableThuocTinh4();
                MsgBox.alert(this, "Thêm thành công");
                return;
            }
        }

        LoaiSP ms = new LoaiSP();
        ms.setTenLoaiSP(txtTenThuocTinh.getText());
        daoLSP.insert(ms);
        DoVaotableThuocTinh4();
    }

    // Nút insert ở bảng thuộc tính
    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        jPanel8.setBackground(ClickColor);
        jPanel9.setBackground(defualtColor);
        jPanel10.setBackground(defualtColor);

        if (Check.checkTrongText(txtTenThuocTinh) == false) {
            return;
        }

        if (rbChatLieu.isSelected()) {
            ThemChatLieu();
            doVaoChatLieu();
        } else if (rbKichThuoc.isSelected()) {
            ThemKichThuoc();
            doVaoKichThuoc();
        } else if (rbLoaiSP.isSelected()) {
            ThemLoaiSP();
            doVaoLoaiSP();
        } else {
            ThemMauSac();
            doVaoMauSac();
        }
        txtTenThuocTinh.setText("");
    }//GEN-LAST:event_jLabel8MouseClicked

    // Đổ vào bảng thuộc tính với dánh sách là Màu sắc
    private void DoVaotableThuocTinh1() {
        modelThuocTinh.setRowCount(0);
        listMS = (ArrayList<MauSac>) daoMS.selectAll();

        for (int i = 0; i < listMS.size(); i++) {
            MauSac ms = listMS.get(i);
            modelThuocTinh.addRow(new Object[]{i + 1, "Màu Sắc", ms.getTenMau()});
        }
    }

    private void rbMauSacMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbMauSacMouseClicked
        txtTenThuocTinh.setText("");
        DoVaotableThuocTinh1();
    }//GEN-LAST:event_rbMauSacMouseClicked

    // Đổ vào bảng thuộc tính với Kích Thước
    private void DoVaotableThuocTinh2() {
        modelThuocTinh.setRowCount(0);
        listKT = (ArrayList<KichThuoc>) daoKT.selectAll();

        for (int i = 0; i < listKT.size(); i++) {
            KichThuoc ms = listKT.get(i);
            modelThuocTinh.addRow(new Object[]{i + 1, "Kích Thước", ms.getTenKT()});
        }
    }

    private void rbKichThuocMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbKichThuocMouseClicked
        txtTenThuocTinh.setText("");
        DoVaotableThuocTinh2();
    }//GEN-LAST:event_rbKichThuocMouseClicked

    // Đổ vào bảng thuộc tính với chất liệu
    private void DoVaotableThuocTinh3() {
        modelThuocTinh.setRowCount(0);
        listCL = (ArrayList<ChatLieu>) daoCL.selectAll();

        for (int i = 0; i < listCL.size(); i++) {
            ChatLieu ms = listCL.get(i);
            modelThuocTinh.addRow(new Object[]{i + 1, "Chất Liệu", ms.getTenChatLieu()});
        }
    }

    private void rbChatLieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbChatLieuMouseClicked
        txtTenThuocTinh.setText("");
        DoVaotableThuocTinh3();
    }//GEN-LAST:event_rbChatLieuMouseClicked

    // Đổ vào bảng thuộc tính với loại sản phẩm
    private void DoVaotableThuocTinh4() {
        modelThuocTinh.setRowCount(0);
        listLSP = (ArrayList<LoaiSP>) daoLSP.selectAll();

        for (int i = 0; i < listLSP.size(); i++) {
            LoaiSP ms = listLSP.get(i);
            modelThuocTinh.addRow(new Object[]{i + 1, "Loại Sản Phẩm", ms.getTenLoaiSP()});
        }
    }

    private void rbLoaiSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbLoaiSPMouseClicked
        txtTenThuocTinh.setText("");
        DoVaotableThuocTinh4();
    }//GEN-LAST:event_rbLoaiSPMouseClicked

    // Nút ẩn
    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseClicked
        jPanel8.setBackground(defualtColor);
        jPanel9.setBackground(defualtColor);
        jPanel10.setBackground(ClickColor);

        Index = table_ThuocTinh.getSelectedRow();

        if (Index < 0) {
            MsgBox.alert(this, "yêu cầu bạn chọn dòng trên table");
            return;
        }

        if (rbChatLieu.isSelected()) {
            daoCL.update_2(txtTenThuocTinh.getText());
            DoVaotableThuocTinh3();
        } else if (rbKichThuoc.isSelected()) {
            daoKT.update_2(txtTenThuocTinh.getText());
            DoVaotableThuocTinh2();
        } else if (rbLoaiSP.isSelected()) {
            daoLSP.update_2(txtTenThuocTinh.getText());
            DoVaotableThuocTinh4();
        } else {
            daoMS.update_2(txtTenThuocTinh.getText());
            DoVaotableThuocTinh1();
        }

        doVaoChatLieu();
        doVaoKichThuoc();
        doVaoLoaiSP();
        doVaoMauSac();
        txtTenThuocTinh.setText("");
    }//GEN-LAST:event_jLabel10MouseClicked

    // Hiển thị nên jTextField ở thuộc tính
    private void table_ThuocTinhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_ThuocTinhMouseClicked
        Index = table_ThuocTinh.getSelectedRow();

        if (Index < 0) {
            MsgBox.alert(this, "yêu cầu bạn chọn dòng trên table");
            return;
        }

        txtTenThuocTinh.setText(table_ThuocTinh.getValueAt(Index, 2).toString());
    }//GEN-LAST:event_table_ThuocTinhMouseClicked
    
    // Sửa thuộc tính
    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        jPanel8.setBackground(defualtColor);
        jPanel9.setBackground(ClickColor);
        jPanel10.setBackground(defualtColor);

        Index = table_ThuocTinh.getSelectedRow();

        if (Index < 0) {
            MsgBox.alert(this, "yêu cầu bạn chọn dòng trên table");
            return;
        }

        try {
            if (rbChatLieu.isSelected()) {
                if (daoCL.selectByID(txtTenThuocTinh.getText()) == null) {
                    ChatLieu cl = listCL.get(Index);
                    cl.setTenChatLieu(txtTenThuocTinh.getText());
                    daoCL.update(cl);            
                }else{
                    MsgBox.alert( this , "Chất liệu đã tồn tại");
                }
                DoVaotableThuocTinh3();
            } else if (rbKichThuoc.isSelected()) {
                if (daoKT.selectByID(txtTenThuocTinh.getText()) == null) {
                    KichThuoc kt = listKT.get(Index);
                    kt.setTenKT(txtTenThuocTinh.getText());
                    daoKT.update(kt);
                }else{
                    MsgBox.alert( this , "Kích thước đã tồn tại");
                }
                DoVaotableThuocTinh2();
            } else if (rbLoaiSP.isSelected()) {
                if ( daoLSP.selectByID( txtTenThuocTinh.getText() ) == null ) {
                    LoaiSP sp = listLSP.get(Index);
                    sp.setTenLoaiSP(txtTenThuocTinh.getText());
                    daoLSP.update(sp);
                }else{
                    MsgBox.alert( this , "Loại sản phẩm đã tồn tại");
                }
                DoVaotableThuocTinh4();
            } else {
                if (daoMS.selectByID(txtTenThuocTinh.getText()) == null) {
                    MauSac ms = listMS.get(Index);
                    ms.setTenMau(txtTenThuocTinh.getText());
                    daoMS.update(ms);
                }else{
                    MsgBox.alert( this , "Màu sắc đã tồn tại");
                }
                DoVaotableThuocTinh1();
            }

            MsgBox.alert(this, "Sửa thành công");
        } catch (Exception e) {
            MsgBox.alert(this, "Sửa thất bại");
        }

    }//GEN-LAST:event_jLabel9MouseClicked

    // Dổ vào bảng sản Phẩm 
    public void DoVaoTableChiTiet() {
        listSP = (ArrayList<SanPham>) daoSP.selectAll_2(txtTimKiem.getText());
        modelSP.setRowCount(0);

        for (SanPham x : listSP) {
            if (x.getSoLuong() == 0) {
                daoSP.Update_2(x);
            }

            if (x.isTrangThai() == true && x.getSoLuong() > 0) {
                modelSP.addRow(new Object[]{x.getMaCTSP(), x.getTenSP(), x.getTenLoai(), x.getTenKichThuoc(), x.getTenMauSac(),
                    x.getTenChatLieu(), HamDinhDang( String.valueOf( x.getGia())) , x.getSoLuong()});
            }
        }
    }

    // Hiển thị ngược nên 
    private void DoNguocNenForm() {
        Index = tblSanPham.getSelectedRow();

        if (Index < 0) {
            MsgBox.alert(this, "Vui lòng chọn thông tin ở bảng");
            return;
        }

        SanPham sp = null;
        for (int i = 0; i < listSP.size(); i++) {
            if (listSP.get(i).getMaCTSP() == Integer.valueOf(tblSanPham.getValueAt(Index, 0).toString())) {
                sp = listSP.get(i);
            }
        }

        txtMaSP.setText(String.valueOf(sp.getMaCTSP()));
        txtTenSP.setText(sp.getTenSP());
        txtSoLuong.setText(  String.valueOf(sp.getSoLuong()));
        txtDonGia.setText( HamDinhDang(String.valueOf(sp.getGia()) ) );
        
        setSelectedComboboxMauSac(tblSanPham.getValueAt(Index, 4).toString(), cbbMauSac);
        setSelectedComboboxTenLoai(tblSanPham.getValueAt(Index, 2).toString(), cbbLoaiSanPham);
        setSelectedComboboxKT(tblSanPham.getValueAt(Index, 3).toString(), cbbKichThuoc);
        setSelectedComboboxCL(tblSanPham.getValueAt(Index, 5).toString(), cbbChatLieu);

    }

    public void setSelectedComboboxMauSac(String cbbselected, JComboBox cbb) {
        for (int i = 0; i < cbb.getItemCount(); i++) {
            MauSac obj = (MauSac) cbb.getItemAt(i);
            if (obj != null) {
                if (cbbselected.trim().equals(obj.getTenMau())) {
                    cbb.setSelectedItem(obj);
                }
            }
        }
    }

    public void setSelectedComboboxKT(String cbbselected, JComboBox cbb) {
        for (int i = 0; i < cbb.getItemCount(); i++) {
            KichThuoc m = (KichThuoc) cbb.getItemAt(i);
            if (m != null) {
                if (cbbselected.trim().equals(m.getTenKT())) {
                    cbb.setSelectedItem(m);
                }
            }
        }
    }

    public void setSelectedComboboxCL(String cbbselected, JComboBox cbb) {
        for (int i = 0; i < cbb.getItemCount(); i++) {
            ChatLieu obj = (ChatLieu) cbb.getItemAt(i);
            if (obj != null) {
                if (cbbselected.trim().equals(obj.getTenChatLieu())) {
                    cbb.setSelectedItem(obj);
                }
            }
        }
    }

    public void setSelectedComboboxTenLoai(String cbbselected, JComboBox cbb) {
        for (int i = 0; i < cbb.getItemCount(); i++) {
            LoaiSP obj = (LoaiSP) cbb.getItemAt(i);
            if (obj != null) {
                if (cbbselected.trim().equals(obj.getTenLoaiSP())) {
                    cbb.setSelectedItem(obj);
                }
            }
        }
    }

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        DoNguocNenForm();
    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        DoVaoTableChiTiet();
    }//GEN-LAST:event_txtTimKiemKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnHienThi;
    private javax.swing.JLabel btnMoi;
    private javax.swing.JLabel btnSua;
    private javax.swing.JLabel btnThem;
    private javax.swing.JLabel btnXoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbbChatLieu;
    private javax.swing.JComboBox<String> cbbKichThuoc;
    private javax.swing.JComboBox<String> cbbLoaiSanPham;
    private javax.swing.JComboBox<String> cbbMauSac;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JRadioButton rbChatLieu;
    private javax.swing.JRadioButton rbKichThuoc;
    private javax.swing.JRadioButton rbLoaiSP;
    private javax.swing.JRadioButton rbMauSac;
    private javax.swing.JTable table_ThuocTinh;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTextField txtDonGia;
    private javax.swing.JTextField txtMaSP;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTenSP;
    private javax.swing.JTextField txtTenThuocTinh;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
