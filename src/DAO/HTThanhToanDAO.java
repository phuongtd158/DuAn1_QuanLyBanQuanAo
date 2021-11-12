/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entity.HinhThucTT;
import Ultil.JDBCHelper;
import java.util.ArrayList;
import java.util.List;
import java.sql.* ;

/**
 *
 * @author ASUS
 */
public class HTThanhToanDAO extends Main< HinhThucTT , String >{
    private String SelectAll = "select * from HINHTHUCTHANHTOAN " ;
    
    @Override
    public void insert(HinhThucTT entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(HinhThucTT entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<HinhThucTT> selectAll() {
        return selectBySQL(SelectAll );
    }

    @Override
    public HinhThucTT selectByID(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected List<HinhThucTT> selectBySQL(String sql, Object... args) {
        List<HinhThucTT> list = new ArrayList<>();
        
        try {
            ResultSet rs = JDBCHelper.query(sql, args);
            
            while( rs.next() ){
                HinhThucTT tt = new HinhThucTT();
                tt.setMaHTTT( rs.getInt("MaHTTT"));
                tt.setTenHTTT( rs.getString("TenHTTT"));
                tt.setTrangThai( rs.getBoolean("TrangThai"));
                list.add(tt);
            }
            rs.getStatement().getConnection().close();
            return list ;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
    
}
