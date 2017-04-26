package main.model.dao;

import main.model.pojo.Lesson;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 18.04.2017.
 */
public class LessonDaoImpl implements LessonDao {

    private Connection connection;
    //private ConnectionPool connectionPool;

    public static final String SELECT_ALL_LESSONS = "SELECT * FROM LESSON";
    public static final String INSERT_LESSONS = "INSERT INTO lesson  " +
            "(id, study_group_id, lesson_date, room, description) VALUES (?,?, ?, ?,?)";
    public static final String UPDATE_LESSONS = "UPDATE lesson SET " +
            "study_group_id=?, lesson_date=?, room=? description=? WHERE id=?";
    public static final String DELETE_LESSON= "DELETE FROM lesson WHERE id=?";

    public LessonDaoImpl(Connection connection){//}, ConnectionPool connectionPool) {
        this.connection = connection;
        //this.connectionPool = connectionPool;
    }

    public void returnConnectionInPool() {
        //connectionPool.returnConnection(connection);
    }

    public PreparedStatement getPrepareStatement(String sql) {
        PreparedStatement ps = null;
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

    public  List<Lesson> getAll() {
        List<Lesson> list = new ArrayList<Lesson>();
        PreparedStatement preparedStatement = getPrepareStatement(SELECT_ALL_LESSONS);
        try {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Lesson lesson = new Lesson();
                lesson.setId(rs.getInt(1));
                lesson.setStudyGroupId(rs.getInt(2));
                lesson.setLessonDate(rs.getDate(3));
                lesson.setRoom(rs.getInt(4));
                lesson.setDescription(rs.getString(5));
                list.add(lesson);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closePrepareStatement(preparedStatement);
        }

        return list;
    }

    public void insertLesson(Lesson lesson) {
        PreparedStatement preparedStatement = getPrepareStatement(INSERT_LESSONS);
        try {
            preparedStatement.setInt(1, lesson.getId());
            preparedStatement.setInt(2, lesson.getStudyGroupId());
            preparedStatement.setDate(3, lesson.getLessonDate());
            preparedStatement.setInt(4, lesson.getRoom());
            preparedStatement.setString(4, lesson.getDescription());
            preparedStatement.executeUpdate();
            closePrepareStatement(preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateLesson(Lesson lesson){
        PreparedStatement preparedStatement = getPrepareStatement(UPDATE_LESSONS);
        try {
            preparedStatement.setInt(1, lesson.getStudyGroupId());
            preparedStatement.setDate(2, lesson.getLessonDate());
            preparedStatement.setInt(3, lesson.getRoom());
            preparedStatement.setString(4, lesson.getDescription());
            preparedStatement.setInt(5, lesson.getId());
            preparedStatement.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            closePrepareStatement(preparedStatement);
        }
    }

    public void deleteLesson(Lesson lesson) {
        PreparedStatement preparedStatement = getPrepareStatement(DELETE_LESSON);
        try {
            preparedStatement.setInt(1, lesson.getId());
            preparedStatement.execute();
        } catch (SQLException e){
            e.printStackTrace();
        }finally {
            closePrepareStatement(preparedStatement);
        }
    }
}
