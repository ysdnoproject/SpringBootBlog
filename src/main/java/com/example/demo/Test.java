package com.example.demo;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class Test {
    public static void main(String[] args) throws Exception {
        Connection con = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:4309/spring_blog?useSSL=false&useUnicode=true&characterEncoding=utf8&nullNamePatternMatchesAll=true",
                "root",
                "root");
        DatabaseMetaData metaData = con.getMetaData();
        ResultSet rs = metaData.getTables(null, "spring_blog", null, new String[]{"TABLE"});
        while (rs.next()) {
            System.out.println("CAT: " + rs.getString("TABLE_CAT"));
            System.out.println("SCHEM: " + rs.getString("TABLE_SCHEM"));
            System.out.println("NAME: " + rs.getString("TABLE_NAME"));
        }
        System.out.println("done");
    }
}
