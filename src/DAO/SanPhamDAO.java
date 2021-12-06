/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entity.SanPham;
import Ultil.JDBCHelper;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASUS
 */
public class SanPhamDAO extends Main< SanPham, String> {

    String Insert = " Insert into SANPHAM ( TenSP ) values ( ? ) ";
    String Insert_1 = "Insert into CHITIETSANPHAM ( MaSP , MaLoai , MaMauSac , MaKichThuoc , MaChatLieu , SoLuong , Gia )"
            + " values ( ? , ? ,? , ? ,? ,? , ? )  ";

    String Update = " Update SANPHAM set TenSp = ? where MaSP = ? "
            + " Update CHITIETSANPHAM set SoLuong = ?  , Gia = ? where MaCTSP = ? ";

    String Update_1 = " Update CHITIETSANPHAM set SoLuong = ? where MaCTSP = ?" ;
    
    String Update_2 = " Update CHITIETSANPHAM set TrangThai = 0 where MaCTSP = ? ";
    String Update_3 = " Update CHITIETSANPHAM set SoLuong = ?  where MaCTSP = ? "; 
    String Update_4 = " Update CHITIETSANPHAM set GiamGia = ?  where MaCTSP = ? ";
    
    String Update_HienThiSanPham = " UPDATE dbo.CHITIETSANPHAM SET TrangThai = 1, SoLuong = ? WHERE MaCTSP = ? ";

    String SelectID = "select * from SANPHAM where TenSp like ? ";
    String SelectID_1 = "select * from SANPHAM where MaCTSP = ? ";
    
    String SelectAlll = "SELECT * FROM dbo.CHITIETSANPHAM JOIN dbo.CHATLIEU ON CHATLIEU.MaChatLieu = CHITIETSANPHAM.MaChatLieu\n"
            + "		JOIN dbo.KICHTHUOC ON KICHTHUOC.MaKichThuoc = CHITIETSANPHAM.MaKichThuoc\n"
            + "		JOIN dbo.MAUSAC ON MAUSAC.MaMauSac = CHITIETSANPHAM.MaMauSac\n"
            + "		JOIN dbo.LOAISP ON LOAISP.MaLoai = CHITIETSANPHAM.MaLoai\n"
            + "		JOIN dbo.SANPHAM ON SANPHAM.MaSP = CHITIETSANPHAM.MaSP ";
    
    String SelectAll = "SELECT * FROM dbo.CHITIETSANPHAM JOIN dbo.CHATLIEU ON CHATLIEU.MaChatLieu = CHITIETSANPHAM.MaChatLieu\n"
            + "		JOIN dbo.KICHTHUOC ON KICHTHUOC.MaKichThuoc = CHITIETSANPHAM.MaKichThuoc\n"
            + "		JOIN dbo.MAUSAC ON MAUSAC.MaMauSac = CHITIETSANPHAM.MaMauSac\n"
            + "		JOIN dbo.LOAISP ON LOAISP.MaLoai = CHITIETSANPHAM.MaLoai\n"
            + "		JOIN dbo.SANPHAM ON SANPHAM.MaSP = CHITIETSANPHAM.MaSP "
            + " where TenSp like ? ";

    String SelectAll_2 = "SELECT * FROM dbo.CHITIETSANPHAM JOIN dbo.CHATLIEU ON CHATLIEU.MaChatLieu = CHITIETSANPHAM.MaChatLieu\n"
            + "		JOIN dbo.KICHTHUOC ON KICHTHUOC.MaKichThuoc = CHITIETSANPHAM.MaKichThuoc\n"
            + "		JOIN dbo.MAUSAC ON MAUSAC.MaMauSac = CHITIETSANPHAM.MaMauSac\n"
            + "		JOIN dbo.LOAISP ON LOAISP.MaLoai = CHITIETSANPHAM.MaLoai\n"
            + "		JOIN dbo.SANPHAM ON SANPHAM.MaSP = CHITIETSANPHAM.MaSP "
            + " where TenSp like ? and CHITIETSANPHAM.MaCTSP like ? and TenMauSac like ? and TenChatLieu like ? and KichThuoc like ? and TenLoai like ? ";

    String SelectAll_3 = "SELECT * FROM dbo.CHITIETSANPHAM JOIN dbo.CHATLIEU ON CHATLIEU.MaChatLieu = CHITIETSANPHAM.MaChatLieu\n"
            + "		JOIN dbo.KICHTHUOC ON KICHTHUOC.MaKichThuoc = CHITIETSANPHAM.MaKichThuoc\n"
            + "		JOIN dbo.MAUSAC ON MAUSAC.MaMauSac = CHITIETSANPHAM.MaMauSac\n"
            + "		JOIN dbo.LOAISP ON LOAISP.MaLoai = CHITIETSANPHAM.MaLoai\n"
            + "		JOIN dbo.SANPHAM ON SANPHAM.MaSP = CHITIETSANPHAM.MaSP "
            + " where MaCTSP like ? ";

    String SelectAll_4 = "SELECT * FROM dbo.CHITIETSANPHAM JOIN dbo.CHATLIEU ON CHATLIEU.MaChatLieu = CHITIETSANPHAM.MaChatLieu\n"
            + "		JOIN dbo.KICHTHUOC ON KICHTHUOC.MaKichThuoc = CHITIETSANPHAM.MaKichThuoc\n"
            + "		JOIN dbo.MAUSAC ON MAUSAC.MaMauSac = CHITIETSANPHAM.MaMauSac\n"
            + "		JOIN dbo.LOAISP ON LOAISP.MaLoai = CHITIETSANPHAM.MaLoai\n"
            + "		JOIN dbo.SANPHAM ON SANPHAM.MaSP = CHITIETSANPHAM.MaSP "
            + " where TenLoai like ?  ";
    
    String SelectAll_5 = "SELECT * FROM dbo.CHITIETSANPHAM JOIN dbo.CHATLIEU ON CHATLIEU.MaChatLieu = CHITIETSANPHAM.MaChatLieu\n"
            + "		JOIN dbo.KICHTHUOC ON KICHTHUOC.MaKichThuoc = CHITIETSANPHAM.MaKichThuoc\n"
            + "		JOIN dbo.MAUSAC ON MAUSAC.MaMauSac = CHITIETSANPHAM.MaMauSac\n"
            + "		JOIN dbo.LOAISP ON LOAISP.MaLoai = CHITIETSANPHAM.MaLoai\n"
            + "		JOIN dbo.SANPHAM ON SANPHAM.MaSP = CHITIETSANPHAM.MaSP "
            + " where TenLoai like ? and Gia between ? and ? ";

    String selectAll_6 =  "SELECT * FROM dbo.CHITIETSANPHAM JOIN dbo.CHATLIEU ON CHATLIEU.MaChatLieu = CHITIETSANPHAM.MaChatLieu\n"
            + "		JOIN dbo.KICHTHUOC ON KICHTHUOC.MaKichThuoc = CHITIETSANPHAM.MaKichThuoc\n"
            + "		JOIN dbo.MAUSAC ON MAUSAC.MaMauSac = CHITIETSANPHAM.MaMauSac\n"
            + "		JOIN dbo.LOAISP ON LOAISP.MaLoai = CHITIETSANPHAM.MaLoai\n"
            + "		JOIN dbo.SANPHAM ON SANPHAM.MaSP = CHITIETSANPHAM.MaSP "
            + "         join SANPHAM_KHUYENMAI on CHITIETSANPHAM.MaCTSP = SANPHAM_KHUYENMAI.MaCTSP where MaKM like ? " ;
// Insert

    String SelectAll_7 = "SELECT * FROM dbo.CHITIETSANPHAM JOIN dbo.CHATLIEU ON CHATLIEU.MaChatLieu = CHITIETSANPHAM.MaChatLieu\n"
            + "		JOIN dbo.KICHTHUOC ON KICHTHUOC.MaKichThuoc = CHITIETSANPHAM.MaKichThuoc\n"
            + "		JOIN dbo.MAUSAC ON MAUSAC.MaMauSac = CHITIETSANPHAM.MaMauSac\n"
            + "		JOIN dbo.LOAISP ON LOAISP.MaLoai = CHITIETSANPHAM.MaLoai\n"
            + "		JOIN dbo.SANPHAM ON SANPHAM.MaSP = CHITIETSANPHAM.MaSP "
            + " where  Gia between ? and ? ";
    
    @Override
    public void insert(SanPham entity) {
        JDBCHelper.Update( Update_3, entity.getSoLuong() , entity.getMaCTSP() ) ;
    }

    public void Insert_SP(String k) {
        JDBCHelper.Update(Insert, k);
    }

    public void Insert_SPCT(SanPham entity, int a, int b, int c, int d) {
        JDBCHelper.Update(Insert_1, entity.getMaSP(), a, b, c,
                d, entity.getSoLuong(), entity.getGia());
    }

    // Update
    @Override
    public void update(SanPham entity) {
        JDBCHelper.Update( Update_3, entity.getSoLuong() , entity.getMaCTSP() );
    }

    public void Update_1(SanPham entity) {
        try {
            JDBCHelper.Update(Update, entity.getTenSP(), entity.getMaSP(), entity.getSoLuong(), entity.getGia(), entity.getMaCTSP());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void Update_1_1(SanPham entity) {
        try {
            JDBCHelper.Update(Update_1, entity.getSoLuong() , entity.getMaCTSP());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void Update_2(SanPham entity) {
        JDBCHelper.Update(Update_2, entity.getMaCTSP());
    }
    
    // Update giảm giá của khuyến mãi
    public void Update_4 ( double a , int b  ){
        JDBCHelper.Update( Update_4, a , b  ) ;
    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    // Các câu select
    @Override
    public List<SanPham> selectAll() {
        return selectBySQL(SelectAlll);
    }

    public List<SanPham> selectAll_2(String k) {
        return selectBySQL(SelectAll, "%" + k + "%");
    }

    public List<SanPham> selectAll_3(String... k) {
        return selectBySQL(SelectAll_2, "%" + k[0] + "%", "%" + k[1] + "%", "%" + k[2] + "%",
                "%" + k[3] + "%", "%" + k[4] + "%", "%" + k[5] + "%");
    }

    public List<SanPham> selectAll_4( String k  ) {
        return selectBySQL(SelectAll_4 ,  k  );
    }
    
    public List<SanPham> selectAll_5( String k ,double a , double b   ) {
        return selectBySQL(SelectAll_5 ,k ,  a , b  );
    }    
    
   // Câu truy vấn list sp đc khuyến mãi
    public List<SanPham> selectAll_6 ( String k ){
        return selectBySQL( selectAll_6, k );
    }
    
    public List<SanPham> selectAll_7(double a , double b ) {
        return selectBySQL(SelectAll_7, a , b );
    }
    
    public void hienThiSanPham(int id, int soLuong) {
        if (soLuong > 0) {
            JDBCHelper.Update(Update_HienThiSanPham, soLuong, id);
        } else {
            JDBCHelper.Update(Update_HienThiSanPham, 1, id);
        }
    }

    public int SelectByIDSp(String k) {
        int sk = 0;

        try {
            ResultSet rs = JDBCHelper.query(SelectID, "%" + k + "%");
            if (rs.next()) {
                sk = rs.getInt("MaSP");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return sk;
    }

    @Override
    public SanPham selectByID(String id) {
        try {
            List<SanPham> list = this.selectBySQL(SelectID_1, id);
            if (list.isEmpty()) {
                return null;
            }
            return list.get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public SanPham selectByID2(String id) {
        try {
            List<SanPham> list = this.selectBySQL( SelectAll_3, id);
            if (list.isEmpty()) {
                return null;
            }
            return list.get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected List<SanPham> selectBySQL(String sql, Object... args) {
        List<SanPham> list = new ArrayList<>();

        try {
            ResultSet rs = JDBCHelper.query(sql, args);

            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setMaCTSP(rs.getInt("MaCTSP"));
                sp.setMaSP(rs.getInt("MaSP"));
                sp.setTenSP(rs.getString("TenSp"));
                sp.setTenLoai(rs.getString("TenLoai"));
                sp.setTenMauSac(rs.getString("TenMauSac"));
                sp.setTenKichThuoc(rs.getString("KichThuoc"));
                sp.setTenChatLieu(rs.getString("TenChatLieu"));
                sp.setSoLuong(rs.getInt("SoLuong"));
                sp.setGia(rs.getDouble("Gia"));
                sp.setGiamGia(rs.getDouble("GiamGia"));
                sp.setTrangThai(rs.getBoolean("TrangThai"));
                list.add(sp);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

}
