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

    final String INSERT = "INSERT INTO dbo.LOAISP(TenLoai,TrangThai) VALUES(?, ?)";
    final String UPDATE = "UPDATE dbo.LOAISP SET TenLoai = ?, TrangThai = ? WHERE MaLoai = ?";
    final String SELECT_ALL = "SELECT * FROM dbo.LOAISP where TrangThai = 1";

    @Override
    public void insert(LoaiSP entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(LoaiSP entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LoaiSP> selectAll() {
        return selectBySQL(SELECT_ALL);
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
                loaiSP.setMaLoaiSP(rs.getInt(1));
                loaiSP.setTenLoaiSP(rs.getString(2));
                loaiSP.setTrangThai(rs.getBoolean(3));

                list.add(loaiSP);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

}
