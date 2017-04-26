package main.services;

import main.model.dao.UserDAO;
import main.model.pojo.Users;

/**
 * Created by admin on 20.04.2017.
 */
public interface UserServiceInterface {
    Users auth(String login, String password);
    UserDAO getUserDAO();
}
