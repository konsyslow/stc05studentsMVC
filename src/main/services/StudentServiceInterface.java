package main.services;
import java.util.List;
import main.model.pojo.Student;

/**
 * Created by admin on 19.04.2017.
 */
public interface StudentServiceInterface {
    //public static main.model.dao.StudentDao studentDao;
    public List<Student> getAllStudents();
    public Student get(Integer id);
    public void delete(Integer id);
    public Student create();
    public void save(Integer id, String name, Integer age, Integer groupId);
    boolean addStudent(int id, String name, int age, int group_id);
}
