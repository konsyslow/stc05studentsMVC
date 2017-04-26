package main.model.dao;

import main.model.pojo.StudyGroup;

import java.util.List;

/**
 * Created by admin on 18.04.2017.
 */
public interface StudyGroupDao {
    void insertStudyGroup(StudyGroup studyGroup);
    List<StudyGroup> getAll();
    void updateStudyGroup(StudyGroup studyGroup);
    void deleteStudyGroup(StudyGroup studyGroup);
}
