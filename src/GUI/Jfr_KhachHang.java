/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DAO.KhachHangDAO;
import Entity.KhachHang;
import Ultil.MsgBox;
import java.awt.Color;
import java.util.List;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class Jfr_KhachHang extends javax.swing.JInternalFrame {

    Color defaulColor, ClickColor;
    DefaultTableModel model;
    KhachHangDAO dao = new KhachHangDAO();
    int row = 0;
    /**
     * Creates new form Jfr_KhachHang
     */
    public Jfr_KhachHang() {
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
    //ĐỔ VÀO BẢNG
    public void fillToTable() {
        model = (DefaultTableModel) tblKH.getModel();
        model.setRowCount(0);    //xóa tất cả các hàng trên JTable
        try {
            List<KhachHang> list = dao.selectAll();// đọc dữ liệu từ CSDL
            for (KhachHang x : list) {
                model.addRow(new Object[]{
                    x.getMaKH(), x.getTenKH(), x.isGioiTinh() ? "Nam" : "Nữ", x.getDiaChi(), x.getSDT(), x.getNgaySinh()
                });
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Truy vấn thất bại");
            e.printStackTrace();
        }
    }
      public void setForm(KhachHang model) {//hiển thị khách hàng có sẵn lên from 
        row = tblKH.getSelectedRow();
        txtMaKH.setText(model.getMaKH() + "");
        txtTenKH.setText(model.getTenKH());
        if (model.getGioiTinh() == true) {
            rdoNam.setSelected(true);
        } else {
            rdoNu.setSelected(true);
        }
        txtDiaChi.setText(model.getDiaChi());
        txtDST.setText(model.getSDT());
        jDateNgaySinh.setDate(model.getNgaySinh());
    }
       KhachHang getForm() {// tạo khách hàng từ from / nhận dc 1 khách hàng
        KhachHang kh = new KhachHang();
//        kh.getMaKH(Integer.valueOf(txtMaKH.getText()));
//        kh.getMaKH(txtMaKH.getText()+"");
        kh.setMaKH((int) tblKH.getValueAt(row, 0));
        kh.setTenKH(txtTenKH.getText());
        kh.setGioiTinh(rdoNam.isSelected() ? true : false);
        kh.setDiaChi(txtDiaChi.getText());
        kh.setSDT(txtDST.getText());
        kh.setNgaySinh(jDateNgaySinh.getDate());
        return kh;
    }
        //THÊM KHÁCH HÀNG
    public void insert() {
        KhachHang model = getForm();// lấy khách hàng từ from
        try {
            dao.insert(model);
            this.fillToTable();
//            this.clear();
            MsgBox.alert(this, "Thêm mới thành công");
        } catch (Exception e) {
            e.printStackTrace();
            MsgBox.alert(this, "Thêm mới thất bại");
        }
    }
    //Hiển thị thông tin nhân viên lên các ô text
    public void edit() {
        String MaKH = tblKH.getValueAt(row, 0).toString();//Lấy dữ liệu ở cột Mã NV
        try {
            KhachHang model = dao.selectByID(MaKH);//Tìm kiếm theo mã nhan viên
            if (model != null) {//Check điều kiện nếu nhân viên != null
                setForm(model);  //Set text cho form
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //CẬP NHẬT KHÁCH HÀNG
    public void update() {
        try {
            KhachHang khachHangh = getForm();
            dao.update(khachHangh);
            fillToTable();
//            this.clear();
            MsgBox.alert(this, "Cập nhật thành công");
        } catch (Exception e) {
            e.printStackTrace();
            MsgBox.alert(this, "Cập nhật thất bại");
        }

    }

    public void delete() {
        if (MsgBox.comfirm(this, "bạn có thực sự muốn xóa nhân viên này không?")) {
            String maNV = txtMaKH.getText();
            try {
                dao.delete(maNV);//lấy mã
                this.fillToTable();
                MsgBox.alert(this, "Xóa thành công!");
            } catch (Exception a) {
                MsgBox.alert(this, "Xóa Thất bại!");
            }
        }
    }
//LÀM MỚI FOM
    public void clear() {
        txtTenKH.setText("");
        txtDST.setText("");
        txtDiaChi.setText("");
        ((JTextField) jDateNgaySinh.getDateEditor().getUiComponent()).setText("");
        txtMaKH.setText("");
        rdoNam.setSelected(true);
    }

    public boolean check() {
        String sdt = "0[3,9](\\d){8}";
        if (txtTenKH.getText().trim().equals("") || txtDiaChi.getText().trim().equals("") || txtDST.getText().trim().equals("")) {
            MsgBox.alert(this, "Không được để trống thông tin khi thêm mới");
            return false;
        } else if (txtDST.getText().length() < 1) {
            MsgBox.alert(this, "Số điện thoại phải lớn hơn 1 ký tự");
            return false;
        }else if (!txtDST.getText().matches(sdt)) {
            MsgBox.alert(this, "Số điện thoại phải đúng định dạng");
            return false;
        }else if (((JTextField) jDateNgaySinh.getDateEditor().getUiComponent()).getText().equals("")) {
            MsgBox.alert(this, "Không được để trống ngày sinh");
            return false;
        }
        return true;
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
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtMaKH = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtTenKH = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtDST = new javax.swing.JTextField();
        jDateNgaySinh = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblKH = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        btnThem = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        btnSua = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        btnXoa = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        btnMoi = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1267, 788));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel2.setText("Mã Khách Hàng");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, -1, -1));
        jPanel2.add(txtMaKH, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 50, 340, 34));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel3.setText("Tên Khách Hàng");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 60, -1, -1));

        txtTenKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenKHActionPerformed(evt);
            }
        });
        jPanel2.add(txtTenKH, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 50, 370, 34));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel4.setText("Giới Tính");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 120, -1, -1));
        jPanel2.add(txtDiaChi, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 110, 370, 34));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel5.setText("Địa Chỉ");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 120, -1, -1));

        rdoNam.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rdoNam);
        rdoNam.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rdoNam.setSelected(true);
        rdoNam.setText("Nam");
        jPanel2.add(rdoNam, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 120, -1, -1));

        rdoNu.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rdoNu);
        rdoNu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rdoNu.setText("Nữ");
        jPanel2.add(rdoNu, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 120, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel6.setText("Ngày Sinh");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 180, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel7.setText("Số Điện Thoại");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 180, -1, -1));
        jPanel2.add(txtDST, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 170, 370, 34));

        jDateNgaySinh.setDateFormatString("dd-MM-yyyy");
        jPanel2.add(jDateNgaySinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 170, 340, 34));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel1.setText("Quản Lý Khách Hàng");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(31, 21, 1195, 240));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblKH.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblKH.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Khách Hàng", "Tên Khách Hàng", "Giới Tính", "Địa Chỉ", "Số Điện Thoại", "Ngày Sinh"
            }
        ));
        tblKH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKHMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblKH);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 44, 1150, 340));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel8.setText("Thông Tin Khách Hàng");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(31, 279, 1195, 402));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnThem.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnThem.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnThem.setText("Thêm");
        btnThem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThemMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 700, 110, 30));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnSua.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnSua.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnSua.setText("Sửa");
        btnSua.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSuaMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnSua, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnSua, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 700, -1, 30));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnXoa.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnXoa.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnXoa.setText("Ẩn");
        btnXoa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnXoaMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnXoa, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnXoa, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 700, -1, 30));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnMoi.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnMoi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnMoi.setText("Mới");
        btnMoi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnMoiMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnMoi, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnMoi, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 700, -1, 30));

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

    private void btnThemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemMouseClicked
        jPanel4.setBackground(ClickColor);
        jPanel5.setBackground(defaulColor);
        jPanel6.setBackground(defaulColor);
        jPanel7.setBackground(defaulColor);
         try {

            if (check()) {
                this.insert();
            }
        } catch (Exception e) {
            e.printStackTrace();
            MsgBox.alert(rootPane, "Lỗi, Vui lòng xem lại");
        }
    }//GEN-LAST:event_btnThemMouseClicked

    private void btnSuaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSuaMouseClicked
        jPanel4.setBackground(defaulColor);
        jPanel5.setBackground(ClickColor);
        jPanel6.setBackground(defaulColor);
        jPanel7.setBackground(defaulColor);
        try {
            if (check()) {
                this.update();
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnSuaMouseClicked

    private void btnXoaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoaMouseClicked
        jPanel4.setBackground(defaulColor);
        jPanel5.setBackground(defaulColor);
        jPanel6.setBackground(ClickColor);
        jPanel7.setBackground(defaulColor);
        delete();
    }//GEN-LAST:event_btnXoaMouseClicked

    private void btnMoiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMoiMouseClicked
        jPanel4.setBackground(defaulColor);
        jPanel5.setBackground(defaulColor);
        jPanel6.setBackground(defaulColor);
        jPanel7.setBackground(ClickColor);
         clear();
    }//GEN-LAST:event_btnMoiMouseClicked

    private void txtTenKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenKHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenKHActionPerformed

    private void tblKHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKHMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 1) {
            row = tblKH.rowAtPoint(evt.getPoint());
            edit();
        }
    }//GEN-LAST:event_tblKHMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnMoi;
    private javax.swing.JLabel btnSua;
    private javax.swing.JLabel btnThem;
    private javax.swing.JLabel btnXoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private com.toedter.calendar.JDateChooser jDateNgaySinh;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JTable tblKH;
    private javax.swing.JTextField txtDST;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtMaKH;
    private javax.swing.JTextField txtTenKH;
    // End of variables declaration//GEN-END:variables
}
