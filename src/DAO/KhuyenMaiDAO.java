/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entity.KhuyenMai;
import Ultil.JDBCHelper;
import java.util.ArrayList;
import java.util.List;
import java.sql.* ;

/**
 *
 * @author ASUS
 */
public class KhuyenMaiDAO extends Main< KhuyenMai , String >{
    private String Insert = "Insert into KHUYENMAI ( MaKM , TenKH , NgayBatDau , NgayKetThuc , GiamGia ) values ( ? , ? , ? , ? , ? )";
    private String SelectByID = "Select * from KHUYENMAI where MaKM like ? ";

    @Override
    public void insert(KhuyenMai entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(KhuyenMai entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<KhuyenMai> selectAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public KhuyenMai selectByID(String id) {
        List<KhuyenMai> list = selectBySQL( SelectByID , id ) ;
        if( list.isEmpty() ){
            return null ;
        }
        return list.get(0) ;
    }

    @Override
    protected List<KhuyenMai> selectBySQL(String sql, Object... args) {
        List<KhuyenMai> list = new ArrayList<>() ;
        
        try {
             ResultSet rs =JDBCHelper.query(sql, args) ;
             while( rs.next() ){
                 KhuyenMai kh = new KhuyenMai();
                 kh.setMaKM( rs.getString("MaKH") );
                 kh.setTenKM( rs.getString("TenKM") );
                 kh.setNgayBD( rs.getDate("NgayBatDau") );
                 kh.setNgayKT( rs.getDate("NgayKetThuc") );
                 kh.setGiamGia( rs.getDouble("GiamGia") );
                 kh.setTrangThai( rs.getBoolean("TrangThai") );
                 list.add(kh);
             }
             rs.getStatement().getConnection().close();
             return list ;
        } catch (Exception e) {
            throw new RuntimeException() ;
        }
    }
    
}
