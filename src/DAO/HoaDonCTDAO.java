/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entity.HoaDonCT;
import Ultil.JDBCHelper;
import java.util.ArrayList;
import java.util.List;
import java.sql.* ;

/**
 *
 * @author ASUS
 */
public class HoaDonCTDAO extends Main< HoaDonCT, String >{
    String Insert = " Insert into HOADONCHITIET ( MaHD , MaCTSP , SoLuong , Gia , GiamGia , ThanhTien , TrangThai , GhiChu )"
            + " values ( ? , ? , ? , ? , ? , ?  , ? , ? ) ";
    String selectByID = "select * from HOADONCHITIET where MaHD =  ? ";
    String selectByID2 = "select * from HOADONCHITIET where MaCTSP like ? and MaHD like ? ";
    String selectByID3 = "select * from HOADONCHITIET where MaHD = ? and MaCTSP = ? ";
    String Update = "Update HOADONCHITIET set SoLuong = ? , ThanhTien = ?  where MaHDCT = ?  " ;
    String Upadte_tt = "Update HOADONCHITIET set TrangThai = 0  where MaHD like ? " ;
    String delete  = "Delete from HOADONCHITIET where MaHD like ? and MaCTSP like ? " ;
    
    @Override
    public void insert(HoaDonCT entity) {
        JDBCHelper.Update(Insert, entity.getMaHD() , entity.getMaCTSP() , entity.getSoLuong() , entity.getGia() ,
                entity.getGiamGia() , entity.getThanhTien() , entity.getTrangThai() , entity.getGhiChu() );
    }

    @Override
    public void update(HoaDonCT entity) {
        JDBCHelper.Update(Update , entity.getSoLuong() , entity.getThanhTien() , entity.getMaHDCT() ) ;
    }

    public void update_tt( String a ) {
        JDBCHelper.Update(Upadte_tt , a ) ;
    }
    
    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    public void delete_1(String a , String b ) {
        JDBCHelper.Update(delete , a ,  b) ;
    }
    
    // Các câu select
    @Override
    public List<HoaDonCT> selectAll() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
    public List<HoaDonCT> selectAll_2( String k ) {
        return selectBySQL( selectByID, k ) ;
    }
    
    
    @Override
    public HoaDonCT selectByID(String id ) {
//        List<HoaDonCT> list = this.selectBySQL( selectByID2 , id );
//        if( list.isEmpty() ){
//            return null ;
//        }
//        return list.get(0) ;
         throw new RuntimeException() ;
    }
    
    
    public HoaDonCT selectByID1(String id, int k) {
        List<HoaDonCT> list = this.selectBySQL(selectByID2, id , k );
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
    
    public HoaDonCT selectByID3(String id , String id2 ) {
        List<HoaDonCT> list = this.selectBySQL(selectByID3, id , id2 );
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<HoaDonCT> selectBySQL(String sql, Object... args) {
         List<HoaDonCT> list = new ArrayList<>();
         
         try {
             ResultSet rs = JDBCHelper.query(sql, args);
             
             while( rs.next() ){
                 HoaDonCT hdct = new HoaDonCT();
                 hdct.setMaHDCT( rs.getInt("MaHDCT") );
                 hdct.setMaHD( rs.getInt("MaHD"));
                 hdct.setMaCTSP( rs.getInt("MaCTSP"));
                 hdct.setSoLuong( rs.getInt("SoLuong"));
                 hdct.setGia( rs.getDouble("Gia"));
                 hdct.setGiamGia( rs.getDouble("GiamGia"));
                 hdct.setThanhTien( rs.getDouble("ThanhTien"));
                 hdct.setTrangThai( rs.getBoolean("TrangThai"));
                 hdct.setGhiChu( rs.getString("GhiChu"));
                 list.add(hdct);
             }
             rs.getStatement().getConnection().close();
             return list ;
        } catch (Exception e) {
            
            throw new RuntimeException();
        }
         
    }
    
}
