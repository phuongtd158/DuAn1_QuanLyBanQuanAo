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
public class ChatLieuDAO extends Main<ChatLieu, Integer> {

    final String INSERT = "INSERT INTO dbo.CHATLIEU(TenChatLieu,TrangThai) VALUES(?, ?)";
    final String UPDATE = "UPDATE dbo.CHATLIEU SET TenChatLieu = ?, TrangThai = ? WHERE MaChatLieu = ?";
    final String SELECT_ALL = "SELECT * FROM dbo.CHATLIEU where TrangThai = 1";
    final String SELECT_BY_ID = "SELECT * FROM dbo.CHATLIEU where ";

    @Override
    public void insert(ChatLieu entity) {
        JDBCHelper.Update(INSERT, entity.getTenChatLieu(), entity.isTrangThai());
    }

    @Override
    public void update(ChatLieu entity) {
        JDBCHelper.Update(UPDATE, entity.getTenChatLieu(), entity.isTrangThai(), entity.getMaChatLieu());
    }

    @Override
    public void delete(Integer id) {
        throw new RuntimeException();
    }

    @Override
    public List<ChatLieu> selectAll() {
        return selectBySQL(SELECT_ALL);
    }

    @Override
    public ChatLieu selectByID(Integer id) {
        throw new RuntimeException();
    }

    @Override
    protected List<ChatLieu> selectBySQL(String sql, Object... args) {
        List<ChatLieu> list = new ArrayList<>();

        try {
            ResultSet rs = JDBCHelper.query(sql, args);
            while (rs.next()) {
                ChatLieu chatLieu = new ChatLieu();
                chatLieu.setMaChatLieu(rs.getInt(1));
                chatLieu.setTenChatLieu(rs.getString(2));
                chatLieu.setTrangThai(rs.getBoolean(3));

                list.add(chatLieu);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

}
