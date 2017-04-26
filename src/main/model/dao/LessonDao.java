package main.model.dao;

import main.model.pojo.Lesson;

import java.util.List;

/**
 * Created by admin on 18.04.2017.
 */
public interface LessonDao {
    void insertLesson(Lesson lesson);
    List<Lesson> getAll();
    void updateLesson(Lesson lesson);
    void deleteLesson(Lesson lesson);
}
