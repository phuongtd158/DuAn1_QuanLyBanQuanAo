/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

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
    String Update = "Update MAUSAC set TenMauSac = ? , TrangThai = ? " ;
    String Select_all = "select * from MAUSAC" ;
    
    @Override
    public void insert(MauSac entity) {
        JDBCHelper.Update(Insert, entity.getTenMau() );
    }

    @Override
    public void update(MauSac entity) {
        JDBCHelper.Update( Update, entity.getTenMau() , entity.getTrangThai() );
    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<MauSac> selectAll() {
        return selectBySQL( Select_all );
    }

    @Override
    public MauSac selectByID(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    
}
