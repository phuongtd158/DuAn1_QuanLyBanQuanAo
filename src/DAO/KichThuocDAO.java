/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entity.KichThuoc;
import Entity.MauSac;
import Ultil.JDBCHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class KichThuocDAO extends Main< KichThuoc, String >{
    String Insert = "Insert into KICHTHUOC ( KichThuoc ) values ( ? )" ;
    String Update = "Update KICHTHUOC set KichThuoc = ?  where MaKichThuoc = ? " ;
    String Update_1 = "Update KICHTHUOC set TrangThai = 1  where KichThuoc = ? ";
    String Update_2 = "Update KICHTHUOC set TrangThai = 0  where KichThuoc = ? " ;
    String Select_all = "select * from KICHTHUOC where TrangThai = 1 " ;
    String Select_all_1 = "select * from KICHTHUOC " ;
    String selectById = " select * from KICHTHUOC where  KichThuoc like ? " ;
    
    @Override
    public void insert(KichThuoc entity) {
        JDBCHelper.Update(Insert, entity.getTenKT()) ;
    }
    
    
    // Update 
    @Override
    public void update(KichThuoc entity) {
        JDBCHelper.Update(Update, entity.getTenKT() , entity.getMaKT() );
    }
    
    public void update_1(KichThuoc entity) {
        JDBCHelper.Update(Update_1, entity.getTenKT() );
    }

    public void update_2( String k ) {
        JDBCHelper.Update(Update_2, k );
    }
    
    // Khác
    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<KichThuoc> selectAll() {
        return selectBySQL(Select_all );
    }
    
    public List<KichThuoc> selectAll_1() {
        return selectBySQL(Select_all_1 );
    }    

    @Override
    public KichThuoc selectByID(String id) {
        List<KichThuoc> list = selectBySQL( selectById , id );
        if(  list.isEmpty()  ){
            return null ;
        }
        return list.get(0) ;
    }

    @Override
    protected List<KichThuoc> selectBySQL(String sql, Object... args) {
        List<KichThuoc> list = new ArrayList<>();

        try {
            ResultSet rs = JDBCHelper.query(sql, args);

            while (rs.next()) {
                KichThuoc kt = new KichThuoc();
                kt.setMaKT(rs.getInt("MaKichThuoc"));
                kt.setTenKT(rs.getString("KichThuoc"));
                kt.setTrangThai(rs.getBoolean("TrangThai"));
                list.add(kt);
            }
            rs.getStatement().getConnection().close();
            return list;

        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
    
}
