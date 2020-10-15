package ru.unclediga.saburov.city.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DirectConnectionBuilder implements ConnectionBuilder{
    @Override
    public Connection getConnection() throws SQLException {
        // Calling from Servlet result this error _         _
        //                                         \ (*.*)/
        // java.sql.SQLException: No suitable driver found for jdbc:mysql://localhost:3306/db_example
        // Overcome:
        //         Class.forName("com.mysql.jdbc.Driver");
        // see Constructor above
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/db_example","springuser","springuser");
    }
}
