/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entity.HoaDon;
import Ultil.JDBCHelper;
import java.util.ArrayList;
import java.util.List;
import java.sql.* ;

/**
 *
 * @author ASUS
 */
public class HoaDonDAO extends Main< HoaDon, String >{
    private String selectAll = "select * from HOADON" ;
    private String Insert = "Insert into HOADON ( MaKH , MaNV , MaHTTT ) values ( ? , ? , ? ) " ;
    private String selectByID = "select * from HOADON where MaHD like ? " ; 
    String Update = "Update HOADON set TrangThai = 1 where MaHD = ? " ;
    
    @Override
    public void insert(HoaDon entity) {
        JDBCHelper.Update(Insert, entity.getMaKH() , entity.getMaHD() , entity.getMaHTTT() ) ;
    }

    @Override
    public void update(HoaDon entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void update1( String k ) {
       JDBCHelper.Update( Update, k ) ;
    }    

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<HoaDon> selectAll() {
        return selectBySQL(selectAll );
    }

    @Override
    public HoaDon selectByID(String id) {
        List<HoaDon> list = selectBySQL( selectByID, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<HoaDon> selectBySQL(String sql, Object... args) {
        List<HoaDon> list = new ArrayList<>();
        
        try {
            ResultSet rs = JDBCHelper.query(sql, args);
            
            while( rs.next() ){
                HoaDon hd = new HoaDon() ;
                hd.setMaHD( rs.getInt("MaHD") );
                hd.setMaKH( rs.getInt("MaKH") );
                hd.setMaHTTT( rs.getInt("MaHTTT") );
                hd.setMaNV( rs.getString("MaNV"));
                hd.setNgayTao( rs.getDate("NgayKhoiTao"));
                hd.setTrangThai( rs.getBoolean("TrangThai"));
                list.add(hd);
            }
            rs.getStatement().getConnection().close(); 
            return list ;
        } catch (Exception e) {
            throw new RuntimeException() ;
        }
    }
    
}
