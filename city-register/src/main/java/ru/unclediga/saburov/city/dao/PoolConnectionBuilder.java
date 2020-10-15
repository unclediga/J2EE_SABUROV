package ru.unclediga.saburov.city.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class PoolConnectionBuilder implements ConnectionBuilder {

    private DataSource dataSource;
    private Logger logger = LoggerFactory.getLogger(PoolConnectionBuilder.class);

    public PoolConnectionBuilder() {
        try {
            final InitialContext context = new InitialContext();
            dataSource = (DataSource) context.lookup("java:comp/env/jdbc/cityRegister");
        } catch (NamingException e) {
            logger.error("", e);
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
