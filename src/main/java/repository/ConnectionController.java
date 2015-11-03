package repository;


import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * Created by Denis on 24.09.2015.
 */
public class ConnectionController implements AutoCloseable {
    private Connection connection = null;
    private Statement statement = null;

    public ConnectionController() {

        try (BasicDataSource basicDataSource = DataSource.getInstance().getBasicDataSource()) {
            connection = basicDataSource.getConnection();
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void update(String sql) throws SQLException {
        statement.executeUpdate(sql);
    }

    public ResultSet query(String sql) throws SQLException {
        return statement.executeQuery(sql);

    }

    @Override
    public void close() {
        connection = null;
        statement = null;
    }
}
