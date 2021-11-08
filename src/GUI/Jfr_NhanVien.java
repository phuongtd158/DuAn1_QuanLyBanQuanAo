/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DAO.NhanVienDAO;
import Entity.NhanVien;
import Ultil.MsgBox;
import java.awt.Color;
import java.util.List;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author ADMIN
 */
public class Jfr_NhanVien extends javax.swing.JInternalFrame {

    Color defaulColor, ClickColor;
    NhanVienDAO nvdao = new NhanVienDAO();
    int dong = 0;
    /**
     * Creates new form NewJInternalFrame
     */
    public Jfr_NhanVien() {
        initComponents();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);

//
//        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) jTable1.getTableHeader().getDefaultRenderer();
//        renderer.setHorizontalAlignment(0);
        hoverButton();
        defaulColor = new Color(255, 255, 255);
        ClickColor = new Color(221, 221, 221);
        fillTable();
    }

    public void hoverButton() {
        defaulColor = new Color(255, 255, 255);
        ClickColor = new Color(225, 225, 225);
    }
    public void fillTable(){
        DefaultTableModel model = (DefaultTableModel) tbNhanVien.getModel();
        model.setRowCount(0);
        try {
            List<NhanVien> list = nvdao.selectAll();
            for (NhanVien nv : list) {
                model.addRow(new Object[]{
                    nv.getMaNV(),nv.getTenNV(),nv.getGioiTinh()?"Nam":"Nữ",nv.getDiaChi(),nv.getNgaySinh()
                        ,nv.getSDT(),nv.getEmail(),nv.getTrangThai()?"Đang Làm":"Đã Nghỉ Làm",nv.getMatKhau(),nv.getVaiTro()?"Quản Lý":"Nhân Viên"
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            MsgBox.alert(this, "Lỗi fillTable");
        }
    }
    // ẩn các nhân viên trạng thái đã nghỉ làm
    public void anTrangThai(){
        DefaultTableModel model = (DefaultTableModel) tbNhanVien.getModel();
        model.setRowCount(0);
        try {
            List<NhanVien> list = nvdao.selectBytt();
            for (NhanVien nv : list) {
                model.addRow(new Object[]{
                    nv.getMaNV(),nv.getTenNV(),nv.getGioiTinh()?"Nam":"Nữ",nv.getDiaChi(),nv.getNgaySinh()
                        ,nv.getSDT(),nv.getEmail(),nv.getTrangThai()?"Đang Làm":"Đã Nghỉ Làm",nv.getMatKhau(),nv.getVaiTro()?"Quản Lý":"Nhân Viên"
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            MsgBox.alert(this, "Lỗi fillTable");
        }
    }
    public void edit(){
        try {
            String maNV = (String) tbNhanVien.getValueAt(dong, 0);
            NhanVien nv = nvdao.selectByID(maNV);
            if (nv != null) {
                setForm(nv);
            }
        } catch (Exception e) {
            e.printStackTrace();
            MsgBox.alert(this, "Lỗi edit");
        }
    }
    
    public void setForm(NhanVien nv){
        txtMaNV.setText(nv.getMaNV());
        txtTenNhanVien.setText(nv.getTenNV());
        if (nv.getGioiTinh() == true) {
            rdNam.isSelected();
        }else{
            rdNu.isSelected();
        }
        txtDiaChi.setText(nv.getDiaChi());
        txtNgaySinh.setDate(nv.getNgaySinh());
        txtSDT.setText(nv.getSDT());
        txtEmail.setText(nv.getEmail());
        cbbTrangThai.setSelectedItem(nv.getTrangThai()?"Đang Làm":"Đã Nghỉ Làm");
        if (nv.getVaiTro()== true) {
            rdQuanLy.isSelected();
        }else{
            rdNhanVien.isSelected();
        }
        txtMatKhau.setText(nv.getMatKhau());
    }
    public void clearFrom(){
        this.setForm(new NhanVien());
        dong = -1;
    }
    NhanVien getFrom(){
        NhanVien nv = new NhanVien();
        nv.setMaNV(txtMaNV.getText());
        nv.setTenNV(txtTenNhanVien.getText());
        nv.setGioiTinh(rdNam.isSelected());
        nv.setDiaChi(txtDiaChi.getText());
        nv.setNgaySinh(txtNgaySinh.getDate());
        nv.setSDT(txtSDT.getText());
        nv.setEmail(txtDiaChi.getText());
        nv.setVaiTro(rdQuanLy.isSelected());
        if (cbbTrangThai.getSelectedIndex() == 0) {
            nv.setTrangThai(true);
        }else{
            nv.setTrangThai(false);
        }
        nv.setMatKhau(txtMatKhau.getText());
        return nv;
    }
    void insert(){
        NhanVien nv = getFrom();
        try {
            nvdao.insert(nv);
            this.fillTable();
            this.clearFrom();
            MsgBox.alert(this, "Thêm Thành Công");
        } catch (Exception e) {
            e.printStackTrace();
            MsgBox.alert(this, "Lỗi insert");
        }
    }
    
    void update(){
        NhanVien nv = getFrom();
        try {
            nvdao.update(nv);
            this.fillTable();
            this.clearFrom();
            MsgBox.alert(this, "Sửa Thành Công");
        } catch (Exception e) {
            e.printStackTrace();
            MsgBox.alert(this, "Lỗi update");
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
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtMaNV = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtTenNhanVien = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        rdNam = new javax.swing.JRadioButton();
        rdNu = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        rdNhanVien = new javax.swing.JRadioButton();
        rdQuanLy = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        txtNgaySinh = new com.toedter.calendar.JDateChooser();
        jLabel16 = new javax.swing.JLabel();
        txtMatKhau = new javax.swing.JTextField();
        cbbTrangThai = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbNhanVien = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        btnThem = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        btnSua = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        btnAn = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        btnMoi = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1260, 760));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1260, 770));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Mã Nhân Viên");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, -1, 30));
        jPanel2.add(txtMaNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 50, 340, 34));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Tên Nhân Viên");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 50, -1, 30));
        jPanel2.add(txtTenNhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 50, 340, 34));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Giới Tính");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 110, -1, -1));

        rdNam.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rdNam);
        rdNam.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rdNam.setForeground(new java.awt.Color(0, 0, 0));
        rdNam.setSelected(true);
        rdNam.setText("Nam");
        jPanel2.add(rdNam, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, -1, -1));

        rdNu.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rdNu);
        rdNu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rdNu.setForeground(new java.awt.Color(0, 0, 0));
        rdNu.setText("Nữ");
        jPanel2.add(rdNu, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 110, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Địa Chỉ");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 110, -1, 30));
        jPanel2.add(txtDiaChi, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 110, 340, 34));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Ngày Sinh");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 160, -1, 30));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Số Điện Thoại");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 160, -1, 30));
        jPanel2.add(txtSDT, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 160, 340, 34));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Email");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(79, 220, 40, 30));
        jPanel2.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 224, 340, 30));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Trạng Thái");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 220, -1, 30));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Vai Trò");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 280, -1, -1));

        rdNhanVien.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup2.add(rdNhanVien);
        rdNhanVien.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rdNhanVien.setForeground(new java.awt.Color(0, 0, 0));
        rdNhanVien.setSelected(true);
        rdNhanVien.setText("Nhân Viên");
        jPanel2.add(rdNhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 280, -1, -1));

        rdQuanLy.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup2.add(rdQuanLy);
        rdQuanLy.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rdQuanLy.setForeground(new java.awt.Color(0, 0, 0));
        rdQuanLy.setText("Quản Lý");
        jPanel2.add(rdQuanLy, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 280, -1, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Quản Lý Nhân Viên");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        txtNgaySinh.setDateFormatString("dd-MM-yyyy");
        jPanel2.add(txtNgaySinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 160, 340, 30));

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Mật khẩu");
        jPanel2.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 270, -1, 30));
        jPanel2.add(txtMatKhau, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 270, 340, 34));

        cbbTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đang Làm", "Đã Nghỉ Làm" }));
        jPanel2.add(cbbTrangThai, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 220, 340, 30));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 9, 1190, 320));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbNhanVien.setForeground(new java.awt.Color(0, 0, 0));
        tbNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Nhân Viên", "Tên Nhân Viên", "Giới Tính", "Địa Chỉ", "Ngày Sinh", "Số Điện Thoại", "Email", "Trạng Thái", "Mật Khẩu", "Vai Trò"
            }
        ));
        tbNhanVien.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tbNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tbNhanVienMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tbNhanVien);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 50, 1160, 270));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Thông Tin Nhân Viên");
        jPanel3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 342, 1194, 330));

        btnThem.setBackground(new java.awt.Color(255, 255, 255));
        btnThem.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Thêm");
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel12MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout btnThemLayout = new javax.swing.GroupLayout(btnThem);
        btnThem.setLayout(btnThemLayout);
        btnThemLayout.setHorizontalGroup(
            btnThemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );
        btnThemLayout.setVerticalGroup(
            btnThemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
        );

        jPanel1.add(btnThem, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 700, -1, 30));

        btnSua.setBackground(new java.awt.Color(255, 255, 255));
        btnSua.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Sửa");
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout btnSuaLayout = new javax.swing.GroupLayout(btnSua);
        btnSua.setLayout(btnSuaLayout);
        btnSuaLayout.setHorizontalGroup(
            btnSuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );
        btnSuaLayout.setVerticalGroup(
            btnSuaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
        );

        jPanel1.add(btnSua, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 700, -1, 30));

        btnAn.setBackground(new java.awt.Color(255, 255, 255));
        btnAn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Ẩn");
        jLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel14MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout btnAnLayout = new javax.swing.GroupLayout(btnAn);
        btnAn.setLayout(btnAnLayout);
        btnAnLayout.setHorizontalGroup(
            btnAnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
        );
        btnAnLayout.setVerticalGroup(
            btnAnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
        );

        jPanel1.add(btnAn, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 700, -1, 30));

        btnMoi.setBackground(new java.awt.Color(255, 255, 255));
        btnMoi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Mới");
        jLabel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel15MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout btnMoiLayout = new javax.swing.GroupLayout(btnMoi);
        btnMoi.setLayout(btnMoiLayout);
        btnMoiLayout.setHorizontalGroup(
            btnMoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );
        btnMoiLayout.setVerticalGroup(
            btnMoiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
        );

        jPanel1.add(btnMoi, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 700, -1, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1248, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 751, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked
        btnThem.setBackground(ClickColor);
        btnSua.setBackground(defaulColor);
        btnAn.setBackground(defaulColor);
        btnMoi.setBackground(defaulColor);
        insert();
    }//GEN-LAST:event_jLabel12MouseClicked

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked
        btnThem.setBackground(defaulColor);
        btnSua.setBackground(ClickColor);
        btnAn.setBackground(defaulColor);
        btnMoi.setBackground(defaulColor);
        update();
    }//GEN-LAST:event_jLabel13MouseClicked

    private void jLabel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseClicked
        btnThem.setBackground(defaulColor);
        btnSua.setBackground(defaulColor);
        btnAn.setBackground(ClickColor);
        btnMoi.setBackground(defaulColor);
        anTrangThai();
    }//GEN-LAST:event_jLabel14MouseClicked

    private void jLabel15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseClicked
        btnThem.setBackground(defaulColor);
        btnSua.setBackground(defaulColor);
        btnAn.setBackground(defaulColor);
        btnMoi.setBackground(ClickColor);
        clearFrom();
    }//GEN-LAST:event_jLabel15MouseClicked

    private void tbNhanVienMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbNhanVienMousePressed
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            this.dong = tbNhanVien.rowAtPoint(evt.getPoint());
            edit();
        }
    }//GEN-LAST:event_tbNhanVienMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel btnAn;
    private javax.swing.JPanel btnMoi;
    private javax.swing.JPanel btnSua;
    private javax.swing.JPanel btnThem;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> cbbTrangThai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rdNam;
    private javax.swing.JRadioButton rdNhanVien;
    private javax.swing.JRadioButton rdNu;
    private javax.swing.JRadioButton rdQuanLy;
    private javax.swing.JTable tbNhanVien;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtMatKhau;
    private com.toedter.calendar.JDateChooser txtNgaySinh;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTenNhanVien;
    // End of variables declaration//GEN-END:variables
}
