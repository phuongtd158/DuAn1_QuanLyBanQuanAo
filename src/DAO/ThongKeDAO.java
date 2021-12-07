/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Ultil.JDBCHelper;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.text.DecimalFormat;

/**
 *
 * @author ADMIN
 */
public class ThongKeDAO {

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

    public List<Object[]> getDoanhThu(Integer nam) {
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        List<Object[]> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                String sql = "{CALL SP_DOANHTHU(?)}";
                rs = JDBCHelper.query(sql, nam);
                while (rs.next()) {
                    list.add(new Object[]{
                        rs.getInt("Thang"), rs.getInt("SanPhamBan"), HamDinhDang(String.valueOf(rs.getFloat("TongGiaBan"))), rs.getFloat("GiamGia"), HamDinhDang(String.valueOf(rs.getFloat("DoanhThu")))
                    });
                }
                rs.getStatement().getConnection().close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Object[]> getSanPham(int min, int max) {
        List<Object[]> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                String sql = "{CALL SP_SANPHAM(?, ?)}";
                rs = JDBCHelper.query(sql, min, max);
                while (rs.next()) {
                    list.add(new Object[]{
                        rs.getInt("STT"), rs.getString("MaSP"), rs.getString("TenLoai"), rs.getString("TenSP"), rs.getString("ChatLieu"),
                        rs.getString("MauSac"), rs.getString("KichThuoc"), rs.getInt("SoLuong")
                    });
                }
                rs.getStatement().getConnection().close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
        public List<Object[]> getSanPham1() {
        List<Object[]> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                String sql = "{CALL SP_SANPHAM1}";
                rs = JDBCHelper.query(sql);
                while (rs.next()) {
                    list.add(new Object[]{
                        rs.getInt("STT"), rs.getString("MaSP"), rs.getString("TenLoai"), rs.getString("TenSP"), rs.getString("ChatLieu"),
                        rs.getString("MauSac"), rs.getString("KichThuoc"), rs.getInt("SoLuong")
                    });
                }
                rs.getStatement().getConnection().close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    //Thống kê tổng đơn hàng theo ngày tìm kiếm
    public int getTongDonHang(String ngayBatDau, String ngayKetThuc) {
        int tongDonHang = 0;
        String sql = "{CALL SP_TONGDONHANG(?, ?)}";
        ResultSet rs = null;
        try {
            rs = JDBCHelper.query(sql, ngayBatDau, ngayKetThuc);
            while (rs.next()) {
                tongDonHang = rs.getInt("TongDonHang");
            }
            rs.getStatement().getConnection().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tongDonHang;
    }

    public int getTongDonHang_huy(String ngayBatDau, String ngayKetThuc) {
        int tongDonHang = 0;
        String sql = "{CALL SP_TONGDONHANG_Huy(?, ?)}";
        ResultSet rs = null;
        try {
            rs = JDBCHelper.query(sql, ngayBatDau, ngayKetThuc);
            while (rs.next()) {
                tongDonHang = rs.getInt("TongDonHang");
            }
            rs.getStatement().getConnection().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tongDonHang;
    }

    //Tổng đơn hàng ngày hiện tại
    public int getTongDonHang_Ngay(String ngayBatDau) {
        int tongDonHang = 0;
        String sql = "{CALL SP_TONGDONHANG_Ngay(?)}";
        ResultSet rs = null;
        try {
            rs = JDBCHelper.query(sql, ngayBatDau);
            while (rs.next()) {
                tongDonHang = rs.getInt("TongDonHang");
            }
            rs.getStatement().getConnection().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tongDonHang;
    }

    //Tổng đơn hàng bị hủy ngày hiện tại
    public int getTongDonHang_Ngay_BiHuy(String ngayBatDau) {
        int tongDonHang = 0;
        String sql = "{CALL SP_TONGDONHANG_BiHuy_Ngay(?)}";
        ResultSet rs = null;
        try {
            rs = JDBCHelper.query(sql, ngayBatDau);
            while (rs.next()) {
                tongDonHang = rs.getInt("TongDonHang");
            }
            rs.getStatement().getConnection().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tongDonHang;
    }

    public int getTongDonHang_Thang(int thang) {
        int tongDonHang = 0;
        String sql = "{CALL SP_TONGDONHANG_Thang(?)}";
        ResultSet rs = null;
        try {
            rs = JDBCHelper.query(sql, thang);
            while (rs.next()) {
                tongDonHang = rs.getInt("TongDonHang");
            }
            rs.getStatement().getConnection().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tongDonHang;
    }

    public int getTongDonHang_Thang_BiHuy(int thang) {
        int tongDonHang = 0;
        String sql = "{CALL SP_TONGDONHANG_BiHuy_Thang(?)}";
        ResultSet rs = null;
        try {
            rs = JDBCHelper.query(sql, thang);
            while (rs.next()) {
                tongDonHang = rs.getInt("TongDonHangHuy");
            }
            rs.getStatement().getConnection().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tongDonHang;
    }

    public float getTongDoanhThu(String ngayBatDau, String ngayKetThuc) {
        float tongDoanhThu = 0;
        String sql = "{CALL SP_TONGDOANHTHU(?, ?)}";
        ResultSet rs = null;
        try {
            rs = JDBCHelper.query(sql, ngayBatDau, ngayKetThuc);
            while (rs.next()) {
                tongDoanhThu = rs.getInt("TongDoanhThu");
            }
            rs.getStatement().getConnection().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tongDoanhThu;
    }

    public float getTongDoanhThu_ngay(String ngayBatDau) {
        float tongDoanhThu = 0;
        String sql = "{CALL SP_TONGDOANHTHU_Ngay(?)}";
        ResultSet rs = null;
        try {
            rs = JDBCHelper.query(sql, ngayBatDau);
            while (rs.next()) {
                tongDoanhThu = rs.getInt("TongDoanhThu");
            }
            rs.getStatement().getConnection().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tongDoanhThu;
    }

    public float getTongDoanhThuThang(int thang) {
        float tongDoanhThu = 0;
        String sql = "{CALL SP_DOANHTHUTHANG(?)}";
        ResultSet rs = null;
        try {
            rs = JDBCHelper.query(sql, thang);
            while (rs.next()) {
                tongDoanhThu = rs.getInt("DoanhThuThang");
            }
            rs.getStatement().getConnection().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tongDoanhThu;
    }

    public float getTongDoanhThuNam(int nam) {
        float tongDoanhThu = 0;
        String sql = "{CALL SP_DOANHTHUNAM(?)}";
        ResultSet rs = null;
        try {
            rs = JDBCHelper.query(sql, nam);
            while (rs.next()) {
                tongDoanhThu = rs.getInt("DoanhThuNam");
            }
            rs.getStatement().getConnection().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tongDoanhThu;
    }

}
