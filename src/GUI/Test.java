/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Ultil.JDBCHelper;
import java.sql.SQLException;
import java.sql.*;

/**
 *
 * @author ADMIN
 */
public class Test {

    public static void main(String[] args) throws SQLException {
        String sql = "select * from NHANVIEN";

        ResultSet rs = JDBCHelper.query(sql);
        while (rs.next()) {
            System.out.println("" + rs.getString(3));
        }

    }
}
