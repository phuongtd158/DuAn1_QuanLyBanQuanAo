/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.NhanVien;
import Entity.SanPham;
import Ultil.JDBCHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class NhanVienDAO extends Main<NhanVien, String> {

    final String INSERT = "INSERT INTO dbo.NHANVIEN(MaNV,TenNV,DiaChi,GioiTinh,Email,SoDienThoai,NgaySinh,VaiTro,MatKhau,TrangThai)\n"
            + "VALUES(?,?,?,?,?,?,?,?,?,?)";
    final String UPDATE = "UPDATE dbo.NHANVIEN\n"
            + "SET TenNV = ?,DiaChi = ?,GioiTinh = ?,Email = ?,SoDienThoai = ?,NgaySinh = ?,VaiTro = ?,MatKhau = ?,TrangThai = ?\n"
            + "WHERE MaNV = ?";
    final String SELECT_BYTT = "SELECT*FROM dbo.NHANVIEN WHERE TrangThai = 0";
    final String SELECT_ALL = "SELECT*FROM dbo.NHANVIEN";
    final String SELECT_BYID = "SELECT*FROM dbo.NHANVIEN WHERE MaNV = ?";
    
        final String UPDATE_MK ="UPDATE NHANVIEN SET MatKhau=? where Email=? ";
        final String checkemaiString= "SELECT*FROM dbo.NHANVIEN where Email=? ";
    @Override
    public void insert(NhanVien entity) {
        JDBCHelper.Update(INSERT, entity.getMaNV(), entity.getTenNV(), entity.getDiaChi(), entity.getGioiTinh(), entity.getEmail(), entity.getSDT(), entity.getNgaySinh(), entity.getVaiTro(), entity.getMatKhau(), entity.getTrangThai());
    }

    @Override
    public void update(NhanVien entity) {
        JDBCHelper.Update(UPDATE, entity.getTenNV(), entity.getDiaChi(), entity.getGioiTinh(), entity.getEmail(), entity.getSDT(), entity.getNgaySinh(), entity.getVaiTro(), entity.getMatKhau(), entity.getTrangThai(), entity.getMaNV());
    }

    @Override
    public void delete(String id) {

    }
    public void updatemk(String id , String matkhau) {
        JDBCHelper.Update(UPDATE_MK,matkhau, id);
    }

    public void update_tt(String id) {
        JDBCHelper.Update("UPDATE NHANVIEN set TrangThai = 0 where MaNV = ?", id);
    }

    public void update_tt_1(String id) {
        JDBCHelper.Update("UPDATE NHANVIEN set TrangThai = 1 where MaNV = ?", id);
    }

    @Override
    public List<NhanVien> selectAll() {
        return selectBySQL(SELECT_ALL);
    }

    @Override
    public NhanVien selectByID(String id) {
        List<NhanVien> list = selectBySQL(SELECT_BYID, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
    public NhanVien checkemai(String email){
        List<NhanVien> list = selectBySQL(checkemaiString, email);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<NhanVien> selectBySQL(String sql, Object... args) {
        List<NhanVien> list = new ArrayList<>();

        try {
            ResultSet rs = JDBCHelper.query(sql, args);

            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setMaNV(rs.getString("MaNV"));
                nv.setTenNV(rs.getString("TenNV"));
                nv.setDiaChi(rs.getString("DiaChi"));
                nv.setGioiTinh(rs.getBoolean("GioiTinh"));
                nv.setEmail(rs.getString("Email"));
                nv.setSDT(rs.getString("SoDienThoai"));
                nv.setNgaySinh(rs.getDate("NgaySinh"));
                nv.setVaiTro(rs.getBoolean("VaiTro"));
                nv.setMatKhau(rs.getString("MatKhau"));
                nv.setTrangThai(rs.getBoolean("TrangThai"));
                list.add(nv);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public List<NhanVien> selectBytt() {
        return selectBySQL(SELECT_BYTT);
    }
}
