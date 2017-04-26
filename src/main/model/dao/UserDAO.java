package main.model.dao;

import main.model.pojo.Users;

import java.util.List;

/**
 * Created by admin on 20.04.2017.
 */
public interface UserDAO {

    Users findUserByLoginAndPassword(String login, String password);
}
