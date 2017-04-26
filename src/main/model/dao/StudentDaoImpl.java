package main.model.dao;

import main.model.pojo.Student;
import main.services.ManagementSystem;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 18.04.2017.
 */
@Repository
public class StudentDaoImpl implements StudentDao {

   // private Connection connection;
   // private ConnectionPool connectionPool;

    public static final String SELECT_ALL_STUDENTS = "SELECT * FROM STUDENT";
    public static final String INSERT_STUDENTS = "INSERT INTO student  " +
            "(id, firstname, secondname, lastname) VALUES (?,?, ?, ?)";
    public static final String UPDATE_STUDENTS = "UPDATE student SET " +
            "name=?, age=?, group_id=? WHERE id=?";
    public static final String DELETE_STUDENT = "DELETE FROM student WHERE id=?";

//    public StudentDaoImpl(Connection connection){//}, ConnectionPool connectionPool) {
//        this.connection = connection;
//        //this.connectionPool = connectionPool;
//    }
    public StudentDaoImpl(){

    }

    public void returnConnectionInPool() {

        //connectionPool.returnConnection(connection);
    }

    // Получение экземпляра PrepareStatement
    public PreparedStatement getPrepareStatement(String sql) {
        Connection connection = ManagementSystem.getCon();
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ps;
    }

    // Закрытие PrepareStatement
    public void closePrepareStatement(PreparedStatement ps) {
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Student> getAll() {
        List<Student> list = new ArrayList<Student>();
        PreparedStatement preparedStatement = getPrepareStatement(SELECT_ALL_STUDENTS);
        try {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt(1));
                student.setName(rs.getString(2));
                student.setAge(rs.getInt(3));
                student.setGroupId(rs.getInt(4));
                list.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closePrepareStatement(preparedStatement);
        }

        return list;
    }

    public boolean insertStudents(Student student) {
        PreparedStatement preparedStatement = getPrepareStatement(INSERT_STUDENTS);
        try {
            preparedStatement.setInt(1, student.getId());
            preparedStatement.setString(2, student.getName());
            preparedStatement.setInt(3, student.getAge());
            preparedStatement.setInt(4, student.getGroupId());
            preparedStatement.executeUpdate();
            closePrepareStatement(preparedStatement);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void updateStudent(Student student){
        PreparedStatement preparedStatement = getPrepareStatement(UPDATE_STUDENTS);
        try {
            preparedStatement.setString(1, student.getName());
            preparedStatement.setInt(2, student.getAge());
            preparedStatement.setInt(3, student.getGroupId());
            preparedStatement.setInt(4, student.getId());
            preparedStatement.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            closePrepareStatement(preparedStatement);
        }
    }

    public void deleteStudent(Integer id) {
        PreparedStatement preparedStatement = getPrepareStatement(DELETE_STUDENT);
        try {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e){
            e.printStackTrace();
        }finally {
            closePrepareStatement(preparedStatement);
        }
    }

    public Student get(Integer id) {
        Connection connection = ManagementSystem.getCon();
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM student WHERE id = " + id.toString());
            while (result.next()) {
                Student m = new Student(result.getInt("id"), result.getString("name"),
                         result.getInt("age"),result.getInt("group_id"));
                return m;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            //IProLogger.LOGGER.error(e.getClass().getSimpleName() + ": " + e.getMessage());
        }
        return null;
    }

    public Student create() {
        return new Student(0, "", 0, 0);
    }
}

