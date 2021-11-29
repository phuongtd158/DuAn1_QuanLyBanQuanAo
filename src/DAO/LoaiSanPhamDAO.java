/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.LoaiSP;
import Ultil.JDBCHelper;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

/**
 *
 * @author ADMIN
 */
public class LoaiSanPhamDAO extends Main<LoaiSP, Integer> {

    private String INSERT = "INSERT INTO dbo.LOAISP(TenLoai ) VALUES(? )";
    private String UPDATE = "UPDATE dbo.LOAISP SET TenLoai = ?  WHERE MaLoai = ?";
    private String Update_1 = "Update LOAISP set TrangThai = 1  where TenLoai = ? ";
        String Update_2 = "Update LOAISP set TrangThai = 0  where TenLoai = ? " ;
    private String SELECT_ALL = "SELECT * FROM dbo.LOAISP where TrangThai = 1";
    String SELECT_ALL_1 = "SELECT * FROM dbo.LOAISP ";

    @Override
    public void insert(LoaiSP entity) {
        JDBCHelper.Update(INSERT, entity.getTenLoaiSP() );
    }

    
    // Update
    @Override
    public void update(LoaiSP entity) {
        JDBCHelper.Update(UPDATE, entity.getTenLoaiSP()  , entity.getMaLoaiSP() );
    }
    
    public void update_1(LoaiSP entity) {
        JDBCHelper.Update(Update_1, entity.getTenLoaiSP()  );
    }    

    public void update_2(String k) {
        JDBCHelper.Update(Update_2, k);
    }
    
    
    // Khác 
    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LoaiSP> selectAll() {
        return selectBySQL(SELECT_ALL);
    }
    
    public List<LoaiSP> selectAll_1() {
        return selectBySQL(SELECT_ALL_1);
    }    

    @Override
    public LoaiSP selectByID(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected List<LoaiSP> selectBySQL(String sql, Object... args) {
        List<LoaiSP> list = new ArrayList<>();

        try {
            ResultSet rs = JDBCHelper.query(sql, args);
            while (rs.next()) {
                LoaiSP loaiSP = new LoaiSP();
                loaiSP.setMaLoaiSP(rs.getInt("MaLoai"));
                loaiSP.setTenLoaiSP(rs.getString("TenLoai"));
                loaiSP.setTrangThai(rs.getBoolean("TrangThai"));

                list.add(loaiSP);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

}
