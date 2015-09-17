package com.db;

import java.sql.*;

public class DBConnection {

    private static Connection con;
    private static Statement stmt;
    private static PreparedStatement pstmt;
    private static ResultSet rs;

    public static Connection getConnection() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost/ASP", "root", "");
        return con;
    }

    public static Statement getStatement() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost/ASP", "root", "");
        stmt = con.createStatement();
        return stmt;
    }

    public static PreparedStatement getPreparedStatement(String sql) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost/ASP", "root", "");
        pstmt = con.prepareCall(sql);
        return pstmt;
    }

}
