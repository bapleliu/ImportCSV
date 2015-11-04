package repository;

import org.apache.commons.dbcp2.BasicDataSource;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


/**
 * Created by Denis on 24.09.2015.
 */
public class DataSource {

    private static final String DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/mysql";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";
    private static final int CONN_POOL_SIZE = 5;
    private Properties properties = new Properties();

    private BasicDataSource basicDataSource = new BasicDataSource();

    private DataSource() {
//        try (FileInputStream fileInputStream = new FileInputStream("db.properties")) {
//            properties.load(fileInputStream);
//            basicDataSource.setDriverClassName(properties.getProperty("DB_DRIVER_CLASS"));
//            basicDataSource.setUrl(properties.getProperty("DB_URL"));
//            basicDataSource.setUsername(properties.getProperty("DB_USERNAME"));
//            basicDataSource.setPassword(properties.getProperty("DB_PASSWORD"));
//            basicDataSource.setInitialSize(Integer.parseInt(properties.getProperty("CONN_POOL_SIZE")));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        basicDataSource.setDriverClassName(DRIVER_CLASS_NAME);
        basicDataSource.setUrl(DB_URL);
        basicDataSource.setUsername(DB_USER);
        basicDataSource.setPassword(DB_PASSWORD);
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

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}
