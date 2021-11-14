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
    String Insert = " Insert into HOADONCHITIET ( MaHD , MaCTSP , SoLuong , Gia , GiamGia , ThanhTien )"
            + " values ( ? , ? , ? , ? , ? , ? ) ";
    String selectByID = "select * from HOADONCHITIET where MaHD =  ?";
    String selectByID2 = "select * from HOADONCHITIET where MaCTSP like ? ";

    @Override
    public void insert(HoaDonCT entity) {
        JDBCHelper.Update(Insert, entity.getMaHD() , entity.getMaCTSP() , entity.getSoLuong() , entity.getGia() ,
                entity.getGiamGia() , entity.getThanhTien() );
    }

    @Override
    public void update(HoaDonCT entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); 
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
    public HoaDonCT selectByID(String id) {
        List<HoaDonCT> list = this.selectBySQL( selectByID2 , id );
        if( list.isEmpty() ){
            return null ;
        }
        return list.get(0) ;
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
                 hdct.setTrangThai( rs.getBoolean("ThanhTien"));
                 list.add(hdct);
             }
             rs.getStatement().getConnection().close();
             return list ;
        } catch (Exception e) {
            throw new RuntimeException();
        }
         
    }
    
}
