package main.services;
import java.util.List;
import main.model.dao.StudentDao;
import main.model.dao.StudentDaoImpl;
import main.model.pojo.Student;
import main.model.pojo.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by admin on 19.04.2017.
 */
@Service
public class StudentService implements StudentServiceInterface {

    //ManagementSystem managementSystem = new ManagementSystem();


    private   StudentDao studentDao;// = new StudentDaoImpl(ManagementSystem.getCon());

    public StudentDao getStudentDao() {
        return studentDao;
    }

    @Autowired
    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public  List<Student> getAllStudents(){
        return studentDao.getAll();
    }

    public Student get(Integer id) {
        return studentDao.get(id);
    }

    public void delete(Integer id) {
        studentDao.deleteStudent(id);
    }

    public Student create() {
        return studentDao.create();
    }

    public void save(Integer id, String name, Integer age, Integer groupId) {
        Student student = new Student(id, name, age, groupId);
        studentDao.updateStudent(student);
    }

    public boolean addStudent(int id, String name, int age, int group_id) {
        Student student = new Student(id,name,age,group_id);
        return studentDao.insertStudents(student);
    }

}
