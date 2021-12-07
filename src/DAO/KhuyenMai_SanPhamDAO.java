/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entity.KhuyenMai_SanPham;
import Ultil.JDBCHelper;
import java.util.ArrayList;
import java.util.List;
import java.sql.* ;

/**
 *
 * @author ASUS
 */
public class KhuyenMai_SanPhamDAO extends Main< KhuyenMai_SanPham, String >{
    String Insert = "Insert into SANPHAM_KHUYENMAI ( MaCTSP , MaKM ) values ( ? , ? ) " ;
    String selectById = "select * from SANPHAM_KHUYENMAI where MaCTSP like ? " ;
    String delete = " delete from SANPHAM_KHUYENMAI where MaKM = ? " ;
    
    // Các phương thức insert 
    @Override
    public void insert(KhuyenMai_SanPham entity) {
        JDBCHelper.Update(Insert, entity.getMaCTSP() , entity.getMaKM() );
    }

    
    // Các phương thức update
    
    @Override
    public void update(KhuyenMai_SanPham entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(String id) {
        JDBCHelper.Update( delete , id );
    }

    @Override
    public List<KhuyenMai_SanPham> selectAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<KhuyenMai_SanPham> selectById( String id ){
        return selectBySQL( selectById , id );
    }
    
    @Override
    public KhuyenMai_SanPham selectByID(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected List<KhuyenMai_SanPham> selectBySQL(String sql, Object... args) {
        List<KhuyenMai_SanPham> list = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.query(sql, args);
            while (rs.next()) {
                KhuyenMai_SanPham km = new KhuyenMai_SanPham();
                km.setMaCTSP(rs.getInt("MaCTSP"));
                km.setMaKM(rs.getString("MaKM"));
                list.add(km);
            }
            rs.getStatement().getConnection().close();
            return list;

        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
    
}
