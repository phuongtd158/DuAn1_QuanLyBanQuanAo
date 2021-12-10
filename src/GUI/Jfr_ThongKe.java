/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DAO.HoaDonDAO;
import DAO.SanPhamDAO;
import DAO.ThongKeDAO;
import Entity.HoaDon;
import Entity.SanPham;
import Ultil.Auth;
import Ultil.Check;
import Ultil.MsgBox;
import Ultil.XDate;
import java.awt.Color;
import java.awt.Component;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author ADMIN
 */
public class Jfr_ThongKe extends javax.swing.JInternalFrame {

    ChartPanel c;
    ThongKeDAO dao_tk = new ThongKeDAO();
    HoaDonDAO dao_hd = new HoaDonDAO();
    SanPhamDAO dao = new SanPhamDAO();
    DefaultTableModel model;
    DefaultComboBoxModel<Object> model_cbbNam;
    DefaultComboBoxModel<Object> model_cbbNam_sp;
    public static Jfr_ThongKe tk;

    /**
     * Creates new form Jfr_ThongKe
     */
    public Jfr_ThongKe() {
        initComponents();

        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);
        doVaoCbbNam();
        doVaoCbbTG();
        if (Auth.user.getVaiTro() == false) {
            jTabbedPane1.remove(0);

            lbTongDoanhThuNam.setText("0" + " VND");
            cbbLoaiThoiGian.removeItemAt(1);
        } else {
            jButton2.setVisible(false);
        }
        doVaoSanPham1();
        tk = this;

//        Calendar c = Calendar.getInstance();
//        int ngayCuoiThang = c.getMaximum(Calendar.DATE);
//        System.out.println("" + ngayCuoiThang);
    }

    //Gửi email
    public void sendEmail() {
        try {
            //Thống kê hóa đơn
            List<HoaDon> list = dao_hd.selectAll();
            List<Object> obj = new ArrayList<>();
            List<Object> obj2 = new ArrayList<>();
            List<Object> obj3 = new ArrayList<>();

            long millis = System.currentTimeMillis();
            java.sql.Date date = new java.sql.Date(millis);
            String ngay = XDate.toString(date);
            for (HoaDon x : list) {
                if (x.getTrangThai().equalsIgnoreCase("Đơn hàng âm") && x.getNgayTao().equals(XDate.toDate(ngay))) {

                    obj.add("\n" + "+Mã hóa đơn: " + x.getMaHD() + "   " + "Lý do: " + x.getGhiChu());

                }
            }
            System.out.println(obj);

            for (HoaDon x : list) {
                if (x.getTrangThai().equalsIgnoreCase("Đã Hủy") && x.getNgayTao().equals(XDate.toDate(ngay))) {
                    obj2.add("\n" + "+Mã hóa đơn: " + x.getMaHD() + "   " + "Lý do: " + x.getGhiChu());

                }
            }
            System.out.println(obj2);

            //Thống kê sản phẩm gần hết hàng
            List<SanPham> listsp = dao.selectAll();
            String s = "";
            for (SanPham x : listsp) {
                if (x.getSoLuong() < 20 && x.isTrangThai() == true) {

                    obj3.add("\n" + "+Mã sản phẩm: " + x.getMaCTSP() + "   Tên sản phẩm: " + x.getTenSP() + "   Số lượng còn lại: " + x.getSoLuong());
                }
            }

            if (obj3.isEmpty()) {
                s = "Không có sản phẩm nào gần hết hàng";
            }
            System.out.println("" + s + obj3);

            Calendar c1 = Calendar.getInstance();
            c1.get(Calendar.DATE);
            String host = "smtp.gmail.com";
            String user = "quanlybanquanaopoly@gmail.com";
            String pass = "Poly123456";
            String to = "phuongtdph13747@fpt.edu.vn";
            String subjectString = "Báo cáo doanh thu ngày" + "(" + java.time.LocalDate.now() + ")";
            String message
                    = "Tổng doanh thu của ngày hôm nay: " + lbTongDoanhThuNgay.getText() + "\n"
                    + "Tổng đơn hàng của ngày hôm nay: " + lbTong.getText() + "\n"
                    + "\n-" + lbTongDonHang.getText() + " đơn thành công" + "\n"
                    + "-" + lbTongDonHangHuy.getText() + " đơn bị hủy" + "\nHủy" + obj + "\nĐổi trả:" + obj2 + "\n"
                    + "\nSản phẩm gần hết hàng: " + s + obj3 + "\n"
                    + "Nhân viên bán hàng: " + Auth.user.getTenNV();
            boolean sessionDebug = false;
            Properties pros = System.getProperties();
            pros.put("mail.smtp.ssl.trust", "*");
            pros.put("mail.smtp.starttls.enable", "true");
            pros.put("mail.smtp.host", "host");
            pros.put("mail.smtp.port", "896");
            pros.put("mail.smtp.auth", "true");
            pros.put("mail.smtp.starttls.required", "true");
//            pros.put("mail.smtp.ssl.trust", "*");

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
            MsgBox.alert(this, "Báo cáo doanh thu thành công");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    //Gửi email báo cáo cuối tháng
    public void sendEmail_Thang() {
        try {

            List<HoaDon> list = dao_hd.selectAll();
            List<Object> obj = new ArrayList<>();
            List<Object> obj2 = new ArrayList<>();
            List<Object> obj3 = new ArrayList<>();
            Calendar c1 = Calendar.getInstance();
            int thang = c1.get(Calendar.MONTH) + 1;
            int tongDonHangThang = Integer.parseInt(lbTongDonThang.getText()) + Integer.parseInt(lbTongHuyThang.getText());

            //Thống kê hóa đơn theo tháng
            for (HoaDon x : list) {
                if (x.getTrangThai().equalsIgnoreCase("Đơn hàng âm") && XDate.toMonth(x.getNgayTao()).equals(String.valueOf(thang))) {

                    obj.add("\n" + "+Mã hóa đơn: " + x.getMaHD() + "   " + "Lý do: " + x.getGhiChu());

                }
            }
            System.out.println(obj);

            for (HoaDon x : list) {
                if (x.getTrangThai().equalsIgnoreCase("Đã Hủy") && XDate.toMonth(x.getNgayTao()).equals(String.valueOf(thang))) {
                    obj2.add("\n" + "+Mã hóa đơn: " + x.getMaHD() + "   " + "Lý do: " + x.getGhiChu());

                }
            }
            System.out.println(obj2);

            //Thống kê sản phẩm gần hết hàng
            List<SanPham> listsp = dao.selectAll();
            String s = "";
            for (SanPham x : listsp) {
                if (x.getSoLuong() < 20 && x.isTrangThai() == true) {

                    obj3.add("\n" + "+Mã sản phẩm: " + x.getMaCTSP() + "   Tên sản phẩm: " + x.getTenSP() + "   Số lượng còn lại: " + x.getSoLuong());
                }
            }

            if (obj3.isEmpty()) {
                s = "Không có sản phẩm nào gần hết hàng";
            }
            System.out.println("" + s + obj3);

            c1.get(Calendar.DATE);
            String host = "smtp.gmail.com";
            String user = "quanlybanquanaopoly@gmail.com";
            String pass = "Poly123456";
            String to = "phuongtdph13747@fpt.edu.vn";
            String subjectString = "Báo cáo doanh thu tháng " + thang;
            String message
                    = "Tổng doanh thu của tháng: " + lbTongDoanhThuThang.getText() + "\n"
                    + "Tổng đơn hàng của tháng này: " + tongDonHangThang + "\n"
                    + "\n-" + lbTongDonThang.getText() + " đơn thành công" + "\n"
                    + "-" + lbTongHuyThang.getText() + " đơn bị hủy" + "\nHủy" + obj + "\nĐổi trả:" + obj2 + "\n"
                    + "\nSản phẩm gần hết hàng: " + s + obj3 + "\n"
                    + "Nhân viên bán hàng: " + Auth.user.getTenNV();
            boolean sessionDebug = false;
            Properties pros = System.getProperties();
            pros.put("mail.smtp.ssl.trust", "*");
            pros.put("mail.smtp.starttls.enable", "true");
            pros.put("mail.smtp.host", "host");
            pros.put("mail.smtp.port", "896");
            pros.put("mail.smtp.auth", "true");
            pros.put("mail.smtp.starttls.required", "true");
//            pros.put("mail.smtp.ssl.trust", "*");

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
            MsgBox.alert(this, "Báo cáo thành công");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

//Đổ vào cbb năm
    private void doVaoCbbNam() {
        try {
            model_cbbNam = (DefaultComboBoxModel) cbbNam_DoanhThu.getModel();

            model_cbbNam.removeAllElements();

            List<Integer> list = dao_hd.getYear();

            for (Integer x : list) {
                model_cbbNam.addElement(x);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Đổ vào cbb loại thời gian
    private void doVaoCbbTG() {
        try {
            cbbLoaiThoiGian.removeAllItems();
            cbbLoaiThoiGian.addItem("Hôm nay");
            cbbLoaiThoiGian.addItem("Theo ngày");
        } catch (Exception e) {
        }
    }

    //Đổ vào table doanh thu
    private void doVaoDoanhThu() {
        try {
            model = (DefaultTableModel) tblDoanhThu.getModel();
            model.setRowCount(0);

            changeColor();
            if (cbbNam_DoanhThu.getSelectedItem() != null) {
                int nam = (Integer) cbbNam_DoanhThu.getSelectedItem();
                List<Object[]> list = dao_tk.getDoanhThu(nam);
                for (Object[] x : list) {
                    model.addRow(x);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Đổi màu table theo số lượng sản phẩm
    public void changeColor() {
        tblSanPham.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                Color c = Color.WHITE;
                int soLuong = Integer.parseInt(table.getValueAt(row, 7).toString());
                if (soLuong < 10) {
                    c = Color.RED;
                    label.setBackground(c);
                    tblSanPham.setSelectionForeground(Color.BLACK);
                    return label;
                } else if (soLuong < 20) {
                    c = Color.YELLOW;
                    label.setBackground(c);
                    tblSanPham.setSelectionForeground(Color.BLACK);
                    return label;
                } else {
                    label.setBackground(c);
                    tblSanPham.setSelectionForeground(Color.BLACK);
                    return label;
                }

            }

        });
    }

    //Đổ vào table sản phẩm
    private void doVaoSanPham() {
        try {
            model = (DefaultTableModel) tblSanPham.getModel();
            model.setRowCount(0);
            List<Object[]> list = dao_tk.getSanPham(Integer.parseInt(txtTu.getText()), Integer.parseInt(txtDen.getText()));

            for (Object[] x : list) {
                model.addRow(x);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void doVaoSanPham1() {
        try {
            model = (DefaultTableModel) tblSanPham.getModel();
            model.setRowCount(0);
            List<Object[]> list = dao_tk.getSanPham1();

            for (Object[] x : list) {
                model.addRow(x);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Check ngày bắt đầu nhỏ hơn ngày kết thúc
    public boolean checkNgay() {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(txtNgayBatDau.getDate());
        c2.setTime(txtNgayKetThuc.getDate());
        long a = (c1.getTime().getTime()) / (24 * 3600 * 1000);
        long b = (c2.getTime().getTime()) / (24 * 3600 * 1000);
        if (a < b) {
            return true;
        } else {
            MsgBox.alert(this, "Ngày bắt đầu phải nhỏ hơn ngày kết thúc");
            return false;
        }
    }

    //Hiển thị sô lượng lên các label thống kê
    private void thongKe() {
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        try {

            //Lấy ngày tháng từ textfield
            String ngayBatDau = ((JTextField) txtNgayBatDau.getDateEditor().getUiComponent()).getText();
            String ngayKetThuc = ((JTextField) txtNgayKetThuc.getDateEditor().getUiComponent()).getText();

            //Lấy tháng, năm hiện tại ở máy tính
            Calendar c1 = Calendar.getInstance();
            int thang = c1.get(Calendar.MONTH) + 1;
            int nam = c1.get(Calendar.YEAR);

            float tongDoanhThuThang = dao_tk.getTongDoanhThuThang(thang);
            lbTongDoanhThuThang.setText(String.valueOf(formatter.format(tongDoanhThuThang)) + " VND");

            float tongDoanhThuNam = dao_tk.getTongDoanhThuNam(nam);
            lbTongDoanhThuNam.setText(String.valueOf(formatter.format(tongDoanhThuNam)) + " VND");

            int tongDonThang = dao_tk.getTongDonHang_Thang(thang);
            lbTongDonThang.setText(String.valueOf(tongDonThang));

            int tongDonThangBiHuy = dao_tk.getTongDonHang_Thang_BiHuy(thang);
            lbTongHuyThang.setText(String.valueOf(tongDonThangBiHuy));

            if (cbbLoaiThoiGian.getSelectedItem() != null) {
                if (cbbLoaiThoiGian.getSelectedItem().toString().equals("Hôm nay")) {
                    hide_();
                    if (Auth.user.getVaiTro() == false) {

                        int tongDonHang = dao_tk.getTongDonHang_Ngay(LocalDate.now().toString());
                        lbTongDonHang.setText(String.valueOf(tongDonHang));
                        lbTongDonHang1.setText(String.valueOf(tongDonHang));

                        int tongDonHuy = dao_tk.getTongDonHang_Ngay_BiHuy(LocalDate.now().toString());
                        lbTongDonHangHuy.setText(tongDonHuy + "");
                        lbTongDonHangHuy1.setText(tongDonHuy + "");

                        lbTong.setText(tongDonHang + tongDonHuy + " đơn hàng");

                        float tongDoanhThu = dao_tk.getTongDoanhThu_ngay(LocalDate.now().toString());
                        lbTongDoanhThuNgay.setText(String.valueOf(formatter.format(tongDoanhThu)) + " VND");
                        lbTongDoanhThuNam.setText("0" + " VND");

                    } else {
                        int tongDonHang = dao_tk.getTongDonHang_Ngay(LocalDate.now().toString());
                        lbTongDonHang.setText(String.valueOf(tongDonHang));
                        lbTongDonHang1.setText(String.valueOf(tongDonHang));

                        int tongDonHuy = dao_tk.getTongDonHang_Ngay_BiHuy(LocalDate.now().toString());
                        lbTongDonHangHuy.setText(tongDonHuy + "");
                        lbTongDonHangHuy1.setText(tongDonHuy + "");

                        lbTong.setText(tongDonHang + tongDonHuy + " đơn hàng");

                        float tongDoanhThu = dao_tk.getTongDoanhThu_ngay(LocalDate.now().toString());
                        lbTongDoanhThuNgay.setText(String.valueOf(formatter.format(tongDoanhThu)) + " VND");
                    }

                } else { //Tìm kiếm
                    show_();
                    //thành công
                    int tongDonHang = dao_tk.getTongDonHang(ngayBatDau, ngayKetThuc);
                    lbTongDonHang.setText(String.valueOf(tongDonHang));
                    lbTongDonHang1.setText(String.valueOf(tongDonHang));
                    //hủy
                    int tongDonHuy_tk = dao_tk.getTongDonHang_huy(ngayBatDau, ngayKetThuc);
                    lbTongDonHangHuy.setText(String.valueOf(tongDonHuy_tk));
                    lbTongDonHangHuy1.setText(String.valueOf(tongDonHuy_tk));

                    lbTong.setText(tongDonHang + tongDonHuy_tk + " đơn hàng");

                    //doanh thu
                    float tongDoanhThu = dao_tk.getTongDoanhThu(ngayBatDau, ngayKetThuc);
                    lbTongDoanhThuNgay.setText(String.valueOf(formatter.format(tongDoanhThu)) + " VND");

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Hiển thị ô nhập ngày tháng
    private void show_() {
        txtNgayBatDau.setVisible(true);
        txtNgayKetThuc.setVisible(true);
        jLabel16.setVisible(true);
        jLabel18.setVisible(true);
        jButton1.setVisible(true);
    }

    //Ẩn ô nhập ngày tháng
    private void hide_() {
        txtNgayBatDau.setVisible(false);
        txtNgayKetThuc.setVisible(false);
        jLabel16.setVisible(false);
        jLabel18.setVisible(false);
        jButton1.setVisible(false);
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
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDoanhThu = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cbbNam_DoanhThu = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        jLabel21 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        txtTu = new javax.swing.JTextField();
        txtDen = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lbBiHuy = new javax.swing.JLabel();
        lbTongDonHangHuy = new javax.swing.JLabel();
        lbTongDonHang = new javax.swing.JLabel();
        lbTongDonHang2 = new javax.swing.JLabel();
        lbTong = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lbTongDoanhThuNgay = new javax.swing.JLabel();
        lbTongDonHang3 = new javax.swing.JLabel();
        lbTongDonHang4 = new javax.swing.JLabel();
        lbTongDonHang1 = new javax.swing.JLabel();
        lbTongDonHangHuy1 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        lbTongDoanhThuNam = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        lbTongDoanhThuThang = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lbTongHuyThang = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lbTongDonThang = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        cbbLoaiThoiGian = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        txtNgayBatDau = new com.toedter.calendar.JDateChooser();
        txtNgayKetThuc = new com.toedter.calendar.JDateChooser();
        jLabel18 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel6.setLayout(new javax.swing.BoxLayout(jPanel6, javax.swing.BoxLayout.LINE_AXIS));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        tblDoanhThu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tháng", "Sản phẩm bán", "Tổng giá bán", "Tổng giá giảm", "Doanh thu"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDoanhThu.setRowHeight(25);
        jScrollPane1.setViewportView(tblDoanhThu);
        if (tblDoanhThu.getColumnModel().getColumnCount() > 0) {
            tblDoanhThu.getColumnModel().getColumn(0).setPreferredWidth(1);
        }

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Chi tiết doanh thu");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 993, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(416, 416, 416)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
        );

        jPanel6.add(jPanel8);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setLayout(new javax.swing.BoxLayout(jPanel7, javax.swing.BoxLayout.LINE_AXIS));
        jPanel6.add(jPanel7);

        jPanel5.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1010, 510));

        jPanel2.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 0, 1010, 720));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Lựa chọn hiển thị");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, -1));

        cbbNam_DoanhThu.setBackground(new java.awt.Color(255, 255, 255));
        cbbNam_DoanhThu.setForeground(new java.awt.Color(0, 0, 0));
        cbbNam_DoanhThu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2021" }));
        cbbNam_DoanhThu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbNam_DoanhThuActionPerformed(evt);
            }
        });
        jPanel2.add(cbbNam_DoanhThu, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 210, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Năm");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setForeground(new java.awt.Color(0, 0, 0));
        jRadioButton1.setText("Biểu đồ");
        jRadioButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadioButton1MouseClicked(evt);
            }
        });
        jPanel2.add(jRadioButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 150, -1, -1));

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setForeground(new java.awt.Color(0, 0, 0));
        jRadioButton2.setSelected(true);
        jRadioButton2.setText("Bảng");
        jRadioButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadioButton2MouseClicked(evt);
            }
        });
        jPanel2.add(jRadioButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, -1, -1));

        jTabbedPane1.addTab("Doanh thu", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setForeground(new java.awt.Color(0, 0, 0));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel10.setLayout(new javax.swing.BoxLayout(jPanel10, javax.swing.BoxLayout.LINE_AXIS));

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã SP", "Số lượng bán", "Tên SP", "Chất liệu", "Màu sắc", "Kích thước", "Số lượng"
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
        jScrollPane2.setViewportView(tblSanPham);
        if (tblSanPham.getColumnModel().getColumnCount() > 0) {
            tblSanPham.getColumnModel().getColumn(0).setPreferredWidth(1);
        }

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setText("Thông tin chi tiết sản phẩm");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 993, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(379, 379, 379)
                        .addComponent(jLabel21)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
        );

        jPanel10.add(jPanel11);

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setLayout(new javax.swing.BoxLayout(jPanel12, javax.swing.BoxLayout.LINE_AXIS));
        jPanel10.add(jPanel12);

        jPanel9.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1010, 510));

        jPanel3.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 0, 1010, 720));

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 0, 0));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_stop_graph_report_25px.png"))); // NOI18N
        jButton2.setText("Báo cáo doanh thu");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        jPanel3.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 210, 50));

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Tìm kiếm theo số lượng:");
        jPanel3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, 20));

        txtTu.setText("0");
        txtTu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTuKeyReleased(evt);
            }
        });
        jPanel3.add(txtTu, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, 70, -1));

        txtDen.setText("0");
        txtDen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDenKeyReleased(evt);
            }
        });
        jPanel3.add(txtDen, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 140, 70, -1));

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Đến");
        jPanel3.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 140, -1, 20));

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Từ");
        jPanel3.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, -1, 20));

        jTabbedPane1.addTab("Sản phẩm", jPanel3);

        jPanel1.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 1250, 510));

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel16.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel17.setBackground(new java.awt.Color(41, 182, 246));
        jPanel17.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_add_shopping_cart_70px.png"))); // NOI18N
        jPanel17.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 30, -1, 50));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Tổng đơn hàng");
        jPanel17.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        lbBiHuy.setForeground(new java.awt.Color(255, 255, 255));
        lbBiHuy.setText("Bị hủy:");
        jPanel17.add(lbBiHuy, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 50, 20));

        lbTongDonHangHuy.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbTongDonHangHuy.setForeground(new java.awt.Color(255, 255, 255));
        lbTongDonHangHuy.setText("0");
        jPanel17.add(lbTongDonHangHuy, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 100, 110, 20));

        lbTongDonHang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbTongDonHang.setForeground(new java.awt.Color(255, 255, 255));
        lbTongDonHang.setText("0");
        jPanel17.add(lbTongDonHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 80, 110, -1));

        lbTongDonHang2.setForeground(new java.awt.Color(255, 255, 255));
        lbTongDonHang2.setText("Thành công:");
        jPanel17.add(lbTongDonHang2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 70, 20));

        lbTong.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbTong.setForeground(new java.awt.Color(255, 255, 255));
        lbTong.setText("0");
        jPanel17.add(lbTong, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 130, 30));

        jPanel16.add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 260, 140));

        jPanel18.setBackground(new java.awt.Color(239, 83, 80));
        jPanel18.setPreferredSize(new java.awt.Dimension(230, 90));
        jPanel18.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_money_70px.png"))); // NOI18N
        jPanel18.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 40, -1, 50));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Tổng doanh thu ngày");
        jPanel18.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        lbTongDoanhThuNgay.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbTongDoanhThuNgay.setForeground(new java.awt.Color(255, 255, 255));
        lbTongDoanhThuNgay.setText("0");
        jPanel18.add(lbTongDoanhThuNgay, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 180, 30));

        lbTongDonHang3.setForeground(new java.awt.Color(255, 255, 255));
        lbTongDonHang3.setText("Bị hủy:");
        jPanel18.add(lbTongDonHang3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 40, 20));

        lbTongDonHang4.setForeground(new java.awt.Color(255, 255, 255));
        lbTongDonHang4.setText("Thành công:");
        jPanel18.add(lbTongDonHang4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 70, 20));

        lbTongDonHang1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbTongDonHang1.setForeground(new java.awt.Color(255, 255, 255));
        lbTongDonHang1.setText("0");
        jPanel18.add(lbTongDonHang1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 80, 100, -1));

        lbTongDonHangHuy1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbTongDonHangHuy1.setForeground(new java.awt.Color(255, 255, 255));
        lbTongDonHangHuy1.setText("0");
        jPanel18.add(lbTongDonHangHuy1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 100, 90, 20));

        jPanel16.add(jPanel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 20, 270, 140));

        jPanel20.setBackground(new java.awt.Color(255, 153, 153));
        jPanel20.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_minus_1_year_70px.png"))); // NOI18N
        jPanel20.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 40, -1, 50));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Tổng doanh thu năm");
        jPanel20.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        lbTongDoanhThuNam.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbTongDoanhThuNam.setForeground(new java.awt.Color(255, 255, 255));
        lbTongDoanhThuNam.setText("0");
        jPanel20.add(lbTongDoanhThuNam, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 170, 30));

        jPanel16.add(jPanel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 20, 260, 140));

        jPanel19.setBackground(new java.awt.Color(255, 143, 0));
        jPanel19.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_minus_1_month_70px.png"))); // NOI18N
        jPanel19.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 40, -1, 50));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Tổng doanh thu tháng");
        jPanel19.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        lbTongDoanhThuThang.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbTongDoanhThuThang.setForeground(new java.awt.Color(255, 255, 255));
        lbTongDoanhThuThang.setText("0");
        jPanel19.add(lbTongDoanhThuThang, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 170, 30));

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Bị hủy");
        jPanel19.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 60, 20));

        lbTongHuyThang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbTongHuyThang.setForeground(new java.awt.Color(255, 255, 255));
        lbTongHuyThang.setText("0");
        jPanel19.add(lbTongHuyThang, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 100, 100, -1));

        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Thành công:");
        jPanel19.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 70, 20));

        lbTongDonThang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbTongDonThang.setForeground(new java.awt.Color(255, 255, 255));
        lbTongDonThang.setText("0");
        jPanel19.add(lbTongDonThang, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 80, 90, -1));

        jPanel16.add(jPanel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 20, 270, 140));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Ngày bắt đầu");
        jPanel16.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 190, -1, 30));

        cbbLoaiThoiGian.setBackground(new java.awt.Color(255, 255, 255));
        cbbLoaiThoiGian.setForeground(new java.awt.Color(0, 0, 0));
        cbbLoaiThoiGian.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbLoaiThoiGian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbLoaiThoiGianActionPerformed(evt);
            }
        });
        jPanel16.add(cbbLoaiThoiGian, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 190, 150, 30));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Loại thời gian");
        jPanel16.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, -1, 30));

        txtNgayBatDau.setDateFormatString("yyyy-MM-dd");
        jPanel16.add(txtNgayBatDau, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 190, 160, 30));

        txtNgayKetThuc.setDateFormatString("yyyy-MM-dd");
        txtNgayKetThuc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtNgayKetThucMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtNgayKetThucMousePressed(evt);
            }
        });
        txtNgayKetThuc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNgayKetThucKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNgayKetThucKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNgayKetThucKeyTyped(evt);
            }
        });
        jPanel16.add(txtNgayKetThuc, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 190, 160, 30));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Ngày kết thúc");
        jPanel16.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 190, -1, 30));

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setForeground(new java.awt.Color(0, 0, 0));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8_search_25px.png"))); // NOI18N
        jButton1.setText("Tìm kiếm");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jPanel16.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 190, -1, 30));

        jPanel1.add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1250, 240));

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

    //Radio show table thông kê daonh thu
    private void jRadioButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButton2MouseClicked
        jPanel7.setVisible(false);
        jPanel8.setVisible(true);
    }//GEN-LAST:event_jRadioButton2MouseClicked
    public String HamDinhDang2(String sk) {
        String ab = "";
        int ak = sk.indexOf(" ");

        if (ak >= 0) {
            sk = sk.substring(0, ak);
        }

        String a[] = sk.split(",");
        for (int i = 0; i < a.length; i++) {
            ab += a[i];
        }
        return ab;
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

    //Radio show biểu đồ thống kê doanh thu
    private void jRadioButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButton1MouseClicked
        jPanel8.setVisible(false);
        jPanel7.setVisible(true);
        DefaultCategoryDataset set = new DefaultCategoryDataset();
        for (int i = 0; i < tblDoanhThu.getRowCount(); i++) {
            set.setValue(Float.parseFloat(HamDinhDang2(tblDoanhThu.getValueAt(i, 4).toString())), "Doanh thu", String.valueOf(tblDoanhThu.getValueAt(i, 0)));
        }

        JFreeChart chart = ChartFactory.createBarChart("Doanh thu", "Tháng", "Tổng doanh thu", set, PlotOrientation.VERTICAL, true, true, true);
        CategoryPlot plot = chart.getCategoryPlot();
        plot.setRangeGridlinePaint(Color.ORANGE);
        c = new ChartPanel(chart);

        jPanel7.removeAll();
        jPanel7.add(c);
        tblDoanhThu.updateUI();
        jPanel7.updateUI();
    }//GEN-LAST:event_jRadioButton1MouseClicked


    private void cbbNam_DoanhThuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbNam_DoanhThuActionPerformed
        doVaoDoanhThu();
    }//GEN-LAST:event_cbbNam_DoanhThuActionPerformed

    private void txtNgayKetThucKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNgayKetThucKeyPressed

    }//GEN-LAST:event_txtNgayKetThucKeyPressed

    private void txtNgayKetThucKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNgayKetThucKeyTyped

    }//GEN-LAST:event_txtNgayKetThucKeyTyped

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        if (Check.checkTrongJdate(txtNgayBatDau) && Check.checkTrongJdate(txtNgayKetThuc)) {
            if (checkNgay()) {
                if (Auth.user.getVaiTro() == false) {

                    lbTongDoanhThuNam.setText("0" + " VND");
                } else {
                    thongKe();
                }
            }
        }
    }//GEN-LAST:event_jButton1MouseClicked

    private void txtNgayKetThucKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNgayKetThucKeyReleased

    }//GEN-LAST:event_txtNgayKetThucKeyReleased

    private void txtNgayKetThucMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtNgayKetThucMouseClicked

    }//GEN-LAST:event_txtNgayKetThucMouseClicked

    private void txtNgayKetThucMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtNgayKetThucMousePressed

    }//GEN-LAST:event_txtNgayKetThucMousePressed

    private void cbbLoaiThoiGianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbLoaiThoiGianActionPerformed
        thongKe();
    }//GEN-LAST:event_cbbLoaiThoiGianActionPerformed

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked

        sendEmail();

    }//GEN-LAST:event_jButton2MouseClicked

    private void txtTuKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTuKeyReleased
        doVaoSanPham();
        if (txtDen.getText().trim().equalsIgnoreCase("")) {
            doVaoSanPham1();
        }
    }//GEN-LAST:event_txtTuKeyReleased

    private void txtDenKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDenKeyReleased
        doVaoSanPham();
        if (txtDen.getText().trim().equalsIgnoreCase("")) {
            doVaoSanPham1();
        }
    }//GEN-LAST:event_txtDenKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> cbbLoaiThoiGian;
    private javax.swing.JComboBox<String> cbbNam_DoanhThu;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lbBiHuy;
    private javax.swing.JLabel lbTong;
    private javax.swing.JLabel lbTongDoanhThuNam;
    private javax.swing.JLabel lbTongDoanhThuNgay;
    private javax.swing.JLabel lbTongDoanhThuThang;
    private javax.swing.JLabel lbTongDonHang;
    private javax.swing.JLabel lbTongDonHang1;
    private javax.swing.JLabel lbTongDonHang2;
    private javax.swing.JLabel lbTongDonHang3;
    private javax.swing.JLabel lbTongDonHang4;
    private javax.swing.JLabel lbTongDonHangHuy;
    private javax.swing.JLabel lbTongDonHangHuy1;
    private javax.swing.JLabel lbTongDonThang;
    private javax.swing.JLabel lbTongHuyThang;
    private javax.swing.JTable tblDoanhThu;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTextField txtDen;
    private com.toedter.calendar.JDateChooser txtNgayBatDau;
    private com.toedter.calendar.JDateChooser txtNgayKetThuc;
    private javax.swing.JTextField txtTu;
    // End of variables declaration//GEN-END:variables
}
