package repository;

import org.apache.commons.dbcp2.BasicDataSource;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


/**
 * Created by Denis on 24.09.2015.
 */
public class DataSource {

    private Properties properties = new Properties();
    private BasicDataSource basicDataSource = new BasicDataSource();

    private DataSource() {
        try (FileInputStream fileInputStream = new FileInputStream("db.properties")) {
            properties.load(fileInputStream);
            //Set database driver name
            basicDataSource.setDriverClassName(properties.getProperty("DB_DRIVER_CLASS"));
            //Set database url
            basicDataSource.setUrl(properties.getProperty("DB_URL"));
            //Set database user
            basicDataSource.setUsername(properties.getProperty("DB_USERNAME"));
            //Set database password
            basicDataSource.setPassword("DB_PASSWORD");
            //Set the connection pool size
            basicDataSource.setInitialSize(Integer.parseInt("CONN_POOL_SIZE"));
        } catch (IOException e) {
            e.printStackTrace();
        }
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
