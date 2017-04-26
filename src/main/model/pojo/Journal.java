package main.model.pojo;

/**
 * Created by admin on 18.04.2017.
 */
public class Journal {
    private int id;
    private int lesson_id;
    private Lesson lesson;
    private int student_id;
    private Student student;

    public Journal(int id, int lesson_id, int student_id) {
        this.id = id;
        this.lesson_id = lesson_id;
        this.student_id = student_id;
    }

    public Journal(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLesson_id() {
        return lesson_id;
    }

    public void setLesson_id(int lesson_id) {
        this.lesson_id = lesson_id;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }
}
