package repository;

import model.Contact;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Denis on 24.10.2015.
 */
public class DAOContact implements DAOInterface<Contact> {
    private BasicDataSource basicDataSource;
    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;

    public DAOContact() {
        this.basicDataSource = DataSource.getInstance().getBasicDataSource();
    }

    public void add(Contact contact) {
        if (null == connection) {
                basicDataSource = DataSource.getInstance().getBasicDataSource();
        }
        try {
            connection = basicDataSource.getConnection();
            preparedStatement
                    = connection.prepareStatement("INSERT INTO " + contact.getClass().getSimpleName() + " VALUES(?,?,?,?,?,?,?);");
            preparedStatement.setInt(1, contact.getId());
            preparedStatement.setString(2, contact.getName());
            preparedStatement.setString(3, contact.getSurname());
            preparedStatement.setString(4, contact.getLogin());
            preparedStatement.setString(5, contact.getEmail());
            preparedStatement.setString(6, contact.getPhoneNumber());
            preparedStatement.setInt(7, contact.getStatusDel());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != preparedStatement) {
                    preparedStatement.close();
                }
                if (null != connection) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public void addAll(List<Contact> contacts) {
        if (null == connection) {
            basicDataSource = DataSource.getInstance().getBasicDataSource();
        }
        String insertTableSQL = "INSERT INTO contact VALUES(?,?,?,?,?,?,?);";
        try {
            connection = basicDataSource.getConnection();
            preparedStatement = connection.prepareStatement(insertTableSQL);
            for (Contact contact : contacts) {
                connection.setAutoCommit(false);
                preparedStatement.setInt(1, contact.getId());
                preparedStatement.setString(2, contact.getName());
                preparedStatement.setString(3, contact.getSurname());
                preparedStatement.setString(4, contact.getLogin());
                preparedStatement.setString(5, contact.getEmail());
                preparedStatement.setString(6, contact.getPhoneNumber());
                preparedStatement.setInt(7, contact.getStatusDel());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != preparedStatement) {
                    preparedStatement.close();
                }
                if (null != connection) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public void update(Contact contact) {
        if (null == connection) {
            basicDataSource = DataSource.getInstance().getBasicDataSource();
        }
        try {
            connection = basicDataSource.getConnection();
            PreparedStatement preparedStatement
                    = connection.prepareStatement("UPDATE " + contact.getClass().getSimpleName()
                    + " SET name=?,SET surname=?,SET login=?,SET email=?,SET phoneNumber=?,SET statusDel=? " +
                    "WHERE id=" + contact.getId() + ";");
            preparedStatement.setString(1, contact.getName());
            preparedStatement.setString(2, contact.getSurname());
            preparedStatement.setString(3, contact.getLogin());
            preparedStatement.setString(4, contact.getEmail());
            preparedStatement.setString(5, contact.getPhoneNumber());
            preparedStatement.setInt(6, contact.getStatusDel());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != connection) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public void delete(Contact contact) {
        if (null == connection) {
            basicDataSource = DataSource.getInstance().getBasicDataSource();
        }
        try {
            connection = basicDataSource.getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("UPDATE " + contact.getClass().getSimpleName() +
                    " SET statusDel=1 WHERE id=" + contact.getId() + ';');
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != statement) {
                    statement.close();
                }
                if (null != connection) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void recover(Contact contact) {
        if (null == connection) {
            basicDataSource = DataSource.getInstance().getBasicDataSource();
        }
        try {
            connection = basicDataSource.getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("UPDATE " + contact.getClass().getSimpleName() +
                    " SET statusDel=0 WHERE id=" + contact.getId() + ';');
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != statement) {
                    statement.close();
                }
                if (null != connection) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Contact> asList() {
        List<Contact> contacts = new ArrayList<>();
        if (null == connection) {
            basicDataSource = DataSource.getInstance().getBasicDataSource();
        }
        try {
            connection = basicDataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM contact;");
            while (resultSet.next()) {
                Contact contact = new Contact();
                contact.setId(resultSet.getInt("id"));
                contact.setName(resultSet.getString("name"));
                contact.setSurname(resultSet.getString("surname"));
                contact.setLogin(resultSet.getString("login"));
                contact.setEmail(resultSet.getString("email"));
                contact.setPhoneNumber(resultSet.getString("phoneNumber"));
                contacts.add(contact);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != statement) {
                    statement.close();
                }
                if (null != connection) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return contacts;
    }

    @Override
    public List<Contact> asList(int offset, int number) {
        List<Contact> contacts = new ArrayList<>();
        if (null == connection) {
            basicDataSource = DataSource.getInstance().getBasicDataSource();
        }
        try {
            connection = basicDataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT SQL_CALC_FOUND_ROWS * FROM contact LIMIT "
                    + offset + ", " + number + ';');
            while (resultSet.next()) {
                Contact contact = new Contact();
                contact.setId(resultSet.getInt("id"));
                contact.setName(resultSet.getString("name"));
                contact.setSurname(resultSet.getString("surname"));
                contact.setLogin(resultSet.getString("login"));
                contact.setEmail(resultSet.getString("email"));
                contact.setPhoneNumber(resultSet.getString("phoneNumber"));
                contacts.add(contact);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contacts;
    }

    public int getLastId() {
        int res = 0;
        if (null == connection) {
            basicDataSource = DataSource.getInstance().getBasicDataSource();
        }
        try {
            connection = basicDataSource.getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT MAX(id) FROM contact;");
            if (resultSet.next()) {
                res = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != statement) {
                    statement.close();
                }
                if (null != connection) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return res;
    }
}