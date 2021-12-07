/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entity.KhuyenMai;
import Ultil.JDBCHelper;
import java.util.ArrayList;
import java.util.List;
import java.sql.* ;

/**
 *
 * @author ASUS
 */
public class KhuyenMaiDAO extends Main< KhuyenMai , String >{
    private String Insert = "Insert into KHUYENMAI ( MaKM , TenKM , NgayBatDau , NgayKetThuc , GiamGia , TrangThai ) values ( ? , ? , ? , ? , ? , ? )";
    private String SelectByID = "Select * from KHUYENMAI where MaKM like ? ";
    private String SelectAll = "select * from KHUYENMAI " ;
    private String Update = " Update KHUYENMAI set TrangThai = 0 where MaKM = ? ";
    private String SelectById_2 = "Select * from KHUYENMAI where TenKM like ? ";
    private String Update2 = " Update  KHUYENMAI set TenKM = ?  , NgayBatDau = ? , NgayKetThuc = ?, GiamGia = ? , TrangThai = ? where MaKM like ? ";
    private String delete = " delete from KHUYENMAI where MaKM like ? " ;
    
    @Override
    public void insert(KhuyenMai entity) {
        JDBCHelper.Update(Insert, entity.getMaKM() , entity.getTenKM() , entity.getNgayBD() , entity.getNgayKT() , entity.getGiamGia() , entity.isTrangThai() ) ;
    }

    // Các câu update
    
    @Override
    public void update(KhuyenMai entity) {
        JDBCHelper.Update( Update , entity.getMaKM() );
    }

    // 
    public void Update_2 ( KhuyenMai entity ){
        JDBCHelper.Update( Update2 , entity.getTenKM() , entity.getNgayBD() , entity.getNgayKT() , entity.getGiamGia()  , entity.isTrangThai() , entity.getMaKM() );
    }
    
    
    @Override
    public void delete(String id) {
        JDBCHelper.Update( delete , id );
    }

    @Override
    public List<KhuyenMai> selectAll() {
        return selectBySQL( SelectAll ) ;
    }

    @Override
    public KhuyenMai selectByID(String id) {
        List<KhuyenMai> list = selectBySQL( SelectByID , id ) ;
        if( list.isEmpty() ){
            return null ;
        }
        return list.get(0) ;
    }

    public List<KhuyenMai> selectAll_1(String k) {
        return selectBySQL(SelectById_2 , "%" + k + "%" );
    }
 
    
    @Override
    protected List<KhuyenMai> selectBySQL(String sql, Object... args) {
        List<KhuyenMai> list = new ArrayList<>() ;
        
        try {
             ResultSet rs =JDBCHelper.query(sql, args) ;
             while( rs.next() ){
                 KhuyenMai kh = new KhuyenMai();
                 kh.setMaKM( rs.getString("MaKM") );
                 kh.setTenKM( rs.getString("TenKM") );
                 kh.setNgayBD( rs.getDate("NgayBatDau") );
                 kh.setNgayKT( rs.getDate("NgayKetThuc") );
                 kh.setGiamGia( rs.getDouble("GiamGia") );
                 kh.setTrangThai( rs.getBoolean("TrangThai") );
                 list.add(kh);
             }
             rs.getStatement().getConnection().close();
             return list ;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException() ;
        }
    }
    
}
