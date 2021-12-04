/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entity.ChatLieu;
import Ultil.JDBCHelper;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

/**
 *
 * @author ADMIN
 */
public class ChatLieuDAO extends Main< ChatLieu , String > {

    final String INSERT = "INSERT INTO dbo.CHATLIEU(TenChatLieu ) VALUES(  ?)";
    final String UPDATE = "UPDATE dbo.CHATLIEU SET TenChatLieu = ?  WHERE MaChatLieu = ?";
    String Update_1 = "Update CHATLIEU set TrangThai = 1  where TenChatLieu = ? ";
    String Update_2 = "Update CHATLIEU set TrangThai = 0  where TenChatLieu = ? " ;
    final String SELECT_ALL = "SELECT * FROM dbo.CHATLIEU where TrangThai = 1";
    String SELECT_ALL_1 = "SELECT * FROM dbo.CHATLIEU" ;
    String selectById = " select * from CHATLIEU where TenChatLieu like ?  " ;

    @Override
    public void insert(ChatLieu entity) {
        JDBCHelper.Update(INSERT, entity.getTenChatLieu() );
    }

  
    // Update 
    @Override
    public void update(ChatLieu entity) {
        JDBCHelper.Update(UPDATE, entity.getTenChatLieu()  , entity.getMaCL() );
    }
    
    public void update_1( ChatLieu entity) {
        JDBCHelper.Update(Update_1 , entity.getTenChatLieu()  );
    }    
    
    public void update_2(String k) {
        JDBCHelper.Update(Update_2, k);
    }    
    
    // Khác 
    @Override
    public List<ChatLieu> selectAll() {
        return selectBySQL(SELECT_ALL);
    }

    public List<ChatLieu> selectAll_1() {
        return selectBySQL(SELECT_ALL_1);
    }    
    
    @Override
    protected List<ChatLieu> selectBySQL(String sql, Object... args) {
        List<ChatLieu> list = new ArrayList<>();

        try {
            ResultSet rs = JDBCHelper.query(sql, args);
            while (rs.next()) {
                ChatLieu chatLieu = new ChatLieu();
                chatLieu.setMaCL(rs.getInt("MaChatLieu"));
                chatLieu.setTenChatLieu(rs.getString("TenChatLieu"));
                chatLieu.setTrangThai(rs.getBoolean("TrangThai"));

                list.add(chatLieu);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ChatLieu selectByID(String id) {
        List<ChatLieu> list = selectBySQL( selectById , id ) ;
        if( list.isEmpty() ){
            return null ;
        }
        return list.get(0) ;
    }

}
