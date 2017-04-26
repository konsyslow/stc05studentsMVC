package main.model.dao;

import main.model.pojo.StudyGroup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 18.04.2017.
 */
public class StudyGroupDaoImpl implements StudyGroupDao{
    private Connection connection;
    //private ConnectionPool connectionPool;

    public static final String SELECT_ALL_STUDY_GROUPS = "SELECT * FROM study_group";
    public static final String INSERT_STUDY_GROUPS = "INSERT INTO study_group (id, name) VALUES (?,?)";
    public static final String UPDATE_STUDY_GROUPS = "UPDATE study_group SET name=? WHERE id=?";
    public static final String DELETE_STUDY_GROUP = "DELETE FROM study_group WHERE id=?";

    public StudyGroupDaoImpl(Connection connection){//}, ConnectionPool connectionPool) {
        this.connection = connection;
        //this.connectionPool = connectionPool;
    }

    public void returnConnectionInPool()
    {
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

    public List<StudyGroup> getAll() {
        List<StudyGroup> list = new ArrayList<StudyGroup>();
        PreparedStatement preparedStatement = getPrepareStatement(SELECT_ALL_STUDY_GROUPS);
        try {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                StudyGroup studyGroup = new StudyGroup();
                studyGroup.setId(rs.getInt(1));
                studyGroup.setName(rs.getString(2));
                list.add(studyGroup);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closePrepareStatement(preparedStatement);
        }

        return list;
    }

    public void insertStudyGroup(StudyGroup studyGroup) {
        PreparedStatement preparedStatement = getPrepareStatement(INSERT_STUDY_GROUPS);
        try {
            preparedStatement.setInt(1, studyGroup.getId());
            preparedStatement.setString(2, studyGroup.getName());
            preparedStatement.executeUpdate();
            closePrepareStatement(preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateStudyGroup(StudyGroup studyGroup){
        PreparedStatement preparedStatement = getPrepareStatement(UPDATE_STUDY_GROUPS);
        try {
            preparedStatement.setString(1, studyGroup.getName());
            preparedStatement.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            closePrepareStatement(preparedStatement);
        }
    }

    public void deleteStudyGroup(StudyGroup studyGroup) {
        PreparedStatement preparedStatement = getPrepareStatement(DELETE_STUDY_GROUP);
        try {
            preparedStatement.setInt(1, studyGroup.getId());
            preparedStatement.execute();
        } catch (SQLException e){
            e.printStackTrace();
        }finally {
            closePrepareStatement(preparedStatement);
        }
    }
}
