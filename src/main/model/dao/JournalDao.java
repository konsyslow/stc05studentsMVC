package main.model.dao;

import main.model.pojo.Journal;

import java.util.List;

/**
 * Created by admin on 19.04.2017.
 */
public interface JournalDao {
    void insertJournal(Journal journal);
    List<Journal> getAll();
    void updateJournal(Journal journal);
    void deleteJournal(Journal journal);
}
