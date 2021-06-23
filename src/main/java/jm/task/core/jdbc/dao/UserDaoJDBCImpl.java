//package jm.task.core.jdbc.dao;
//
//import jm.task.core.jdbc.model.User;
//import jm.task.core.jdbc.util.Util;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class UserDaoJDBCImpl implements UserDao {
//    public UserDaoJDBCImpl() {
//    }
//
//    public void createUsersTable() throws SQLException {
//        String CREATE_TABLE = "CREATE TABLE users(" + "id BIGINT AUTO_INCREMENT PRIMARY KEY," + "user_name VARCHAR(255) NOT NULL," + "user_lastName VARCHAR(255) NOT NULL," + "user_age INTEGER NOT NULL)";
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//        try {
//            connection = Util.getConnection();
//            connection.setAutoCommit(false);
//            preparedStatement = connection.prepareStatement(CREATE_TABLE);
//            preparedStatement.execute();
//            connection.commit();
//            connection.setAutoCommit(true);
//            System.out.println("Table successfully created...");
//        } catch (SQLException throwables) {
//            connection.rollback();
//            throwables.printStackTrace();
//        } finally {
//            if (preparedStatement != null) {
//                preparedStatement.close();
//            }
//            if (connection != null) {
//                connection.close();
//            }
//        }
//    }
//
//    public void dropUsersTable() throws SQLException {
//        String DROP_TABLE = "DROP TABLE IF EXISTS users";
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//        try {
//            connection = Util.getConnection();
//            connection.setAutoCommit(false);
//            preparedStatement = connection.prepareStatement(DROP_TABLE);
//            preparedStatement.execute();
//            connection.commit();
//            connection.setAutoCommit(true);
//            System.out.println("Table successfully dropped...");
//        } catch (SQLException throwables) {
//            connection.rollback();
//            throwables.printStackTrace();
//        } finally {
//            if (preparedStatement != null) {
//                preparedStatement.close();
//            }
//            if (connection != null) {
//                connection.close();
//            }
//        }
//    }
//
//    public void saveUser(String name, String lastName, byte age) throws SQLException {
//        String SAVE_USER = "INSERT INTO users(user_name, user_lastName, user_age) VALUES(?, ?, ?)";
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//        try {
//            connection = Util.getConnection();
//            connection.setAutoCommit(false);
//            preparedStatement = connection.prepareStatement(SAVE_USER);
//            preparedStatement.setString(1, name);
//            preparedStatement.setString(2, lastName);
//            preparedStatement.setInt(3, age);
//            preparedStatement.execute();
//            connection.commit();
//            connection.setAutoCommit(true);
//            System.out.println("User " + name + " " + lastName + " successfully created");
//        } catch (SQLException throwables) {
//            connection.rollback();
//            throwables.printStackTrace();
//        } finally {
//            if (preparedStatement != null) {
//                preparedStatement.close();
//            }
//            if (connection != null) {
//                connection.close();
//            }
//        }
//    }
//
//    public void removeUserById(long id) throws SQLException {
//        String REMOVE_USER = "DELETE FROM users WHERE id=" + id;
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//        try {
//            connection = Util.getConnection();
//            connection.setAutoCommit(false);
//            preparedStatement = connection.prepareStatement(REMOVE_USER);
//            preparedStatement.execute();
//            connection.commit();
//            connection.setAutoCommit(true);
//            System.out.println("User with id " + id + " successfully deleted");
//        } catch (SQLException throwables) {
//            connection.rollback();
//            throwables.printStackTrace();
//        } finally {
//            if (preparedStatement != null) {
//                preparedStatement.close();
//            }
//            if (connection != null) {
//                connection.close();
//            }
//        }
//    }
//
//    public List<User> getAllUsers() throws SQLException {
//        String GET_ALL_USERS = "SELECT * FROM users";
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//        List<User> list = new ArrayList<>();
//        try {
//            connection = Util.getConnection();
//            connection.setAutoCommit(false);
//            preparedStatement = connection.prepareStatement(GET_ALL_USERS);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//                list.add(new User(resultSet.getString("user_name"), resultSet.getString("user_lastName"), resultSet.getByte("user_age")));
//            }
//            connection.commit();
//            connection.setAutoCommit(true);
//        } catch (SQLException throwables) {
//            connection.rollback();
//            throwables.printStackTrace();
//        } finally {
//            if (preparedStatement != null) {
//                preparedStatement.close();
//            }
//            if (connection != null) {
//                connection.close();
//            }
//        }
//        return list;
//    }
//
//    public void cleanUsersTable() throws SQLException {
//        String CLEAN_TABLE = "DELETE FROM users";
//        Connection connection = null;
//        PreparedStatement preparedStatement = null;
//        try {
//            connection = Util.getConnection();
//            connection.setAutoCommit(false);
//            preparedStatement = connection.prepareStatement(CLEAN_TABLE);
//            preparedStatement.execute();
//            connection.commit();
//            connection.setAutoCommit(true);
//        } catch (SQLException throwables) {
//            connection.rollback();
//            throwables.printStackTrace();
//        } finally {
//            if (preparedStatement != null) {
//                preparedStatement.close();
//            }
//            if (connection != null) {
//                connection.close();
//            }
//        }
//    }
//}
package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Connection connection;

    public UserDaoJDBCImpl(Connection connection) {
        this.connection = connection;
    }

    public void createUsersTable() {
        try {
            connection.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS usersTable (id BIGINT PRIMARY KEY AUTO_INCREMENT, " +
                    "name VARCHAR(20) NOT NULL, lastname VARCHAR(20) NOT NULL, age TINYINT NOT NULL)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try {
            connection.createStatement().executeUpdate("DROP TABLE IF EXISTS usersTable");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO usersTable (name, lastname, age) VALUES (?, ?, ?)");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.printf("User с именем – %s добавлен в базу данных" + '\n', name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM usersTable WHERE id = ?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        ResultSet resultSet;
        try {
            resultSet = connection.createStatement().executeQuery("SELECT * FROM usersTable");
            while (resultSet.next()) {
                list.add(new User(resultSet.getString(2), resultSet.getString(3), resultSet.getByte(4)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void cleanUsersTable() {
        try {
            connection.createStatement().executeUpdate("TRUNCATE TABLE usersTable");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
