package main.model.dao;

import main.model.pojo.Student;

import java.util.List;

/**
 * Created by admin on 18.04.2017.
 */
public interface StudentDao {
//    public Connection connection;
//    private ConnectionPool connectionPool;
    boolean insertStudents(Student student);
    List<Student> getAll();
    void updateStudent(Student student);
    void deleteStudent(Integer id);
    public Student get(Integer id);
    public Student create();
}
