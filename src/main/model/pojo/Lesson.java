package main.model.pojo;
import java.sql.Date;
import main.model.pojo.StudyGroup;
/**
 * Created by admin on 18.04.2017.
 */
public class Lesson {
    private int id;
    private int studyGroupId;
    private StudyGroup studyGroup;
    private Date lessonDate;
    private int room;
    private String description;

    public Lesson(int id, int studyGroupId, Date lessonDate, int room, String description) {
        this.id = id;
        this.studyGroupId = studyGroupId;
        this.lessonDate = lessonDate;
        this.room = room;
        this.description = description;
    }

    public Lesson(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudyGroupId() {
        return studyGroupId;
    }

    public void setStudyGroupId(int studyGroupId) {
        this.studyGroupId = studyGroupId;
    }

    public Date getLessonDate() {
        return lessonDate;
    }

    public void setLessonDate(Date lessonDate) {
        this.lessonDate = lessonDate;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
