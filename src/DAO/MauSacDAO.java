/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entity.ChatLieu;
import Entity.KichThuoc;
import Entity.MauSac;
import Ultil.JDBCHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.* ;

/**
 *
 * @author ASUS
 */
public class MauSacDAO extends Main< MauSac, String>{
    String Insert = "Insert into MAUSAC ( TenMauSac ) values ( ? )" ;
    String Update = "Update MAUSAC set TenMauSac = ?  where MaMauSac = ? " ;
    String Update_1 = "Update MAUSAC set TrangThai = 1  where TenMauSac = ? ";
    String Update_2 = "Update MAUSAC set TrangThai = 0  where TenMauSac = ? " ;
    String Select_all = "select * from MAUSAC where TrangThai = 1 " ;
    String Select_all_1 = "select * from MAUSAC " ;
    String selectById = " select * from MAUSAC where TenMauSac like ? " ;
    
    @Override
    public void insert(MauSac entity) {
        JDBCHelper.Update(Insert, entity.getTenMau() );
    }

    
    // Update
    @Override
    public void update(MauSac entity) {
        JDBCHelper.Update( Update, entity.getTenMau()  , entity.getMaMau() );
    }
    
    public void update_1(MauSac entity) {
        JDBCHelper.Update(Update_1, entity.getTenMau() );
    }    

    public void update_2(String k) {
        JDBCHelper.Update(Update_2, k);
    }
    
    
    // Khác
    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<MauSac> selectAll() {
        return selectBySQL( Select_all );
    }
    
    public List<MauSac> selectAll_1() {
        return selectBySQL( Select_all_1 );
    }    

    @Override
    public MauSac selectByID(String id) {
        List<MauSac> list = selectBySQL( selectById,  id );
        if( list.isEmpty() ){
            return null ;
        }
        return list.get(0) ;
    }

    @Override
    protected List<MauSac> selectBySQL(String sql, Object... args) {
        List<MauSac> list = new ArrayList<>() ;
        
        try {
             ResultSet rs = JDBCHelper.query(sql, args);
             
             while( rs.next() ){
                 MauSac ms = new MauSac() ;
                 ms.setMaMau( rs.getInt("MaMauSac"));
                 ms.setTenMau(rs.getString("TenMauSac"));
                 ms.setTrangThai(rs.getBoolean("TrangThai"));
                 list.add(ms);
             }
             rs.getStatement().getConnection().close();
             return list ;
             
        } catch (Exception e ) {
            throw new RuntimeException();
        }
    }

    public void update_1(ChatLieu x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
