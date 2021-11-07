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
    String Update = "Update KICHTHUOC set KichThuoc = ? , TrangThai = ? " ;
    String Select_all = "select * from KICHTHUOC" ;
    
    @Override
    public void insert(KichThuoc entity) {
        JDBCHelper.Update(Insert, entity.getTenKT()) ;
    }

    @Override
    public void update(KichThuoc entity) {
        JDBCHelper.Update(Update, entity.getTenKT() , entity.getTrangThai() );
    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<KichThuoc> selectAll() {
        return selectBySQL(Select_all );
    }

    @Override
    public KichThuoc selectByID(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
