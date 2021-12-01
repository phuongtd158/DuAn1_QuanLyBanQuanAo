/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entity.KhachHang;
import Entity.SanPham;
import Ultil.JDBCHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class KhachHangDAO extends Main< KhachHang, String> {

    final String INSERT_SQL = " insert into KHACHHANG(TenKH, SoDienThoai, DiaChi ) values( ?, ?, ? )";
    final String UPDATE_SQL = "update KHACHHANG set TenKH = ?, NgaySinh = ?, GioiTinh = ?, SoDienThoai = ?, DiaChi = ?, TrangThai = ? where MaKH = ?";
    final String DELETE_SQL_0 = "update KHACHHANG set TrangThai = 0 where MaKH = ?";
    final String DELETE_SQL_1 = "update KHACHHANG set TrangThai = 1 where MaKH = ?";
    String Update_DC  = "update KHACHHANG set DiaChi = ? where SoDienThoai like ? ";
    String Update1 = "update KHACHHANG set TenKH =  ? ,  SoDienThoai = ?  where MaKH like ? ";
    final String SELECT_ALL_SQL = "select * from KHACHHANG";
    final String SELECY_BY_ID_SQL = "select * from KHACHHANG where MaKH = ?";
    String SelectByID = "select * from KHACHHANG where SoDienThoai = ? " ;

    @Override
    public void insert(KhachHang entity) {
         JDBCHelper.Update(INSERT_SQL, entity.getTenKH() , entity.getSDT() , entity.getDiaChi() );
    }

    @Override
    public void update(KhachHang entity) {
        JDBCHelper.Update(UPDATE_SQL, entity.getTenKH(), entity.getNgaySinh(), entity.getGioiTinh(), entity.getSDT(), entity.getDiaChi(), entity.getTrangThai(), entity.getMaKH());
    }

    
    public void update_1( String a , String b , int c ) {
        JDBCHelper.Update(Update1, a, b , c  );
    } 
    
    @Override
    public void delete(String id) {
        throw new RuntimeException();
    }

    public void delete_0(int id) {
        JDBCHelper.Update(DELETE_SQL_0, id);
    }

    
    // Câu Update Địa Chỉ
    public void update_dc( String a , String b ) {
        JDBCHelper.Update(Update_DC, a , b );
    }
    
    public void delete_1(int id) {
        JDBCHelper.Update(DELETE_SQL_1, id);
    }

    @Override
    public List<KhachHang> selectAll() {
        return selectBySQL(SELECT_ALL_SQL);
    }

    @Override
    public KhachHang selectByID(String id) {
        List<KhachHang> list = selectBySQL(SELECY_BY_ID_SQL, id );
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
    
    public int selectByID_2( String id ){
        int k = 0 ;
        try {
            ResultSet rs = JDBCHelper.query( SelectByID , id );
            
            if( rs.next() ){
                k = rs.getInt("MaKH") ;
            }
        } catch (Exception e) {
        }
        return k ;
    }

    @Override
    protected List<KhachHang> selectBySQL(String sql, Object... args) {
        List<KhachHang> list = new ArrayList<>();

        try {
            ResultSet rs = JDBCHelper.query(sql, args);

            while (rs.next()) {
                KhachHang kh = new KhachHang();
                kh.setMaKH(rs.getInt("MaKH"));
                kh.setTenKH(rs.getString("TenKH"));
                kh.setNgaySinh(rs.getDate("NgaySinh"));
                kh.setGioiTinh(rs.getBoolean("GioiTinh"));
                kh.setSDT(rs.getString("SoDienThoai"));
                kh.setDiaChi(rs.getString("DiaChi"));
                kh.setTrangThai(rs.getBoolean("TrangThai"));

                list.add(kh);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

}
