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

/**
 *
 * @author ADMIN
 */
public class ThongKeDAO {

    public List<Object[]> getDoanhThu(Integer nam) {
        List<Object[]> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                String sql = "{CALL SP_DOANHTHU(?)}";
                rs = JDBCHelper.query(sql, nam);
                while (rs.next()) {
                    list.add(new Object[]{
                        rs.getInt("Thang"), rs.getInt("SanPhamBan"), rs.getFloat("TongGiaBan"), rs.getFloat("GiamGia"), rs.getFloat("DoanhThu")
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

    public List<Object[]> getSanPham(Integer nam) {
        List<Object[]> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                String sql = "{CALL SP_SANPHAM(?)}";
                rs = JDBCHelper.query(sql, nam);
                while (rs.next()) {
                    list.add(new Object[]{
                        rs.getInt("STT"), rs.getString("TenSP"), rs.getInt("SoLuongBan")
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
