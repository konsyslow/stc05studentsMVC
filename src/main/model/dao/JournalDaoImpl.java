package main.model.dao;

import main.model.pojo.Journal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 19.04.2017.
 */
public class JournalDaoImpl implements JournalDao {

    private Connection connection;
    //private ConnectionPool connectionPool;

    public static final String SELECT_ALL_JOURNALS = "SELECT * FROM JOURNAL";
    public static final String INSERT_JOURNALS = "INSERT INTO JOURNALS (id, lesson_id, student_id) VALUES (?,?,?)";
    public static final String UPDATE_JOURNALS = "UPDATE JOURNALS SET lesson_id=? student_id=?  WHERE id=?";
    public static final String DELETE_JOURNAL = "DELETE FROM JOURNALS WHERE id=?";

    public JournalDaoImpl(Connection connection){//, ConnectionPool connectionPool) {
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

    public List<Journal> getAll() {
        List<Journal> list = new ArrayList<Journal>();
        PreparedStatement preparedStatement = getPrepareStatement(SELECT_ALL_JOURNALS);
        try {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Journal journal = new Journal();
                journal.setId(rs.getInt(1));
                journal.setLesson_id(rs.getInt(2));
                journal.setStudent_id(rs.getInt(3));
                list.add(journal);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closePrepareStatement(preparedStatement);
        }

        return list;
    }

    public void insertJournal(Journal journal) {
        PreparedStatement preparedStatement = getPrepareStatement(INSERT_JOURNALS);
        try {
            preparedStatement.setInt(1, journal.getId());
            preparedStatement.setInt(2, journal.getLesson_id());
            preparedStatement.setInt(3, journal.getStudent_id());
            preparedStatement.executeUpdate();
            closePrepareStatement(preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateJournal(Journal journal){
        PreparedStatement preparedStatement = getPrepareStatement(UPDATE_JOURNALS);
        try {
            preparedStatement.setInt(1, journal.getLesson_id());
            preparedStatement.setInt(2, journal.getStudent_id());
            preparedStatement.setInt(3, journal.getId());
            preparedStatement.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            closePrepareStatement(preparedStatement);
        }
    }

    public void deleteJournal(Journal journal) {
        PreparedStatement preparedStatement = getPrepareStatement(DELETE_JOURNAL);
        try {
            preparedStatement.setInt(1, journal.getId());
            preparedStatement.execute();
        } catch (SQLException e){
            e.printStackTrace();
        }finally {
            closePrepareStatement(preparedStatement);
        }
    }
}
