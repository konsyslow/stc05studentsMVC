package main.model.pojo;

/**
 * Created by admin on 18.04.2017.
 */
public class StudyGroup {
    private int id;
    private String name;

    public StudyGroup(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public StudyGroup(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
