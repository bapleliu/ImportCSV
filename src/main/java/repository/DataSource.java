package repository;

import org.apache.commons.dbcp2.BasicDataSource;


/**
 * Created by Denis on 24.09.2015.
 */
public class DataSource {

    private static final String DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3308";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";
    private static final int CONN_POOL_SIZE = 5;

    private BasicDataSource basicDataSource = new BasicDataSource();

    private DataSource() {
        //Set database driver name
        basicDataSource.setDriverClassName(DRIVER_CLASS_NAME);
        //Set database url
        basicDataSource.setUrl(DB_URL);
        //Set database user
        basicDataSource.setUsername(DB_USER);
        //Set database password
        basicDataSource.setPassword(DB_PASSWORD);
        //Set the connection pool size
        basicDataSource.setInitialSize(CONN_POOL_SIZE);
    }

    private static class DataSourceHolder {
        private static final DataSource INSTANCE = new DataSource();
    }

    public static DataSource getInstance() {
        return DataSourceHolder.INSTANCE;
    }

    public BasicDataSource getBasicDataSource() {
        return basicDataSource;
    }

    public void setBasicDataSource(BasicDataSource basicDataSource) {
        this.basicDataSource = basicDataSource;
    }

}
