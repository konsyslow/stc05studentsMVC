package main.model.dao;

import main.model.pojo.Users;
import main.services.ManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by admin on 20.04.2017.
 */
public class UserDAOImpl implements UserDAO {
    //private Connection connection;
    // private ConnectionPool connectionPool;

    public static final String SELECT_ALL_USERS = "SELECT * FROM USERS";
    public static final String INSERT_USERS = "INSERT INTO USERS  " +
            "(id, login, password) VALUES (?,?, ?, ?)";
    public static final String UPDATE_USERS = "UPDATE users SET " +
            "login=?, password=? WHERE id=?";
    public static final String DELETE_USER = "DELETE FROM users WHERE id=?";
    public static final String FIND_USER = "SELECT * FROM system.users WHERE login = ? AND password = ?";

//    public UserDAOImpl(Connection connection){//}, ConnectionPool connectionPool) {
//        this.connection = connection;
//        //this.connectionPool = connectionPool;
//    }
    public UserDAOImpl(){

    }


    public PreparedStatement getPrepareStatement(String sql) {
        PreparedStatement ps = null;
        Connection connection = ManagementSystem.getCon();
        try {
            ps = connection.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ps;
    }

    public void closePrepareStatement(PreparedStatement ps) {
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public void insertUser(Users user) {

    }

    public List<Users> getAll() {
        return null;
    }

    public void updateStudent(Users user) {

    }

    public void deleteStudent(Users user) {

    }

    public Users findUserByLoginAndPassword(String login, String password) {
        Users user = null;
        PreparedStatement preparedStatement = getPrepareStatement(FIND_USER);
        try {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = createEntity(resultSet);
            }

            //logger.debug("user " + user);
        } catch (SQLException e) {
            //logger.error(e);
            e.printStackTrace();
        }

        return user;
    }

    private Users createEntity(ResultSet resultSet) throws SQLException {
        return new Users(resultSet.getLong("id"),
                resultSet.getString("login"),
                resultSet.getString("password"),
                resultSet.getInt("isblocked"));
    }
}
