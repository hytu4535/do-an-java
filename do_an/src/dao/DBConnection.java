package dao;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/quanlivanphongpham";
    private static final String JDBC_USERNAME = "root";
    private static final String JDBC_PASSWORD = "thinh2014"; // Thay bằng mật khẩu MySQL của bạn

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC Driver not found", e);
        }
    }
    
    // đóng kết nối
    public static void closeConnection(Connection c) {
        try {
            // nếu co kết nối tới csdl 
            if( c != null) c.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    // in thông tin trạng thái
    public static void printInfo(Connection c) {
        try {
            if (c != null) {
            DatabaseMetaData mtdt = c.getMetaData();
            System.out.println(mtdt.getDatabaseProductName());
            System.out.println(mtdt.getDatabaseProductVersion());
            } 
        } 
        catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
         }
    }
}