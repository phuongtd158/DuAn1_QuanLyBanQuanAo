/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class ConnectionsDB {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        String user = "sa";
        String pass = "123";
        String url = "jdbc:sqlserver://localhost:1433;databaseName=Edusys1";
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection cn = DriverManager.getConnection(url, user, pass);
        if (cn != null) {
            System.out.println("oke");
        } else {
            System.out.println("No");
        }

        String sql = "select * from chuyende";
        PreparedStatement stm = cn.prepareStatement(sql);
  
         ResultSet rs =stm.executeQuery();
         String ma;
         List<String> list = new ArrayList<>();
         while (rs.next()) {            
            ma = rs.getString(1);
            list.add(ma);
        }
         int i = 0;
         for (String x : list) {
             i++;
             System.out.println(i+": " +""+x);
        }
         

    }

}
