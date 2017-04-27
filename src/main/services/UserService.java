package main.services;

import main.model.dao.UserDAO;
import main.model.dao.UserDAOImpl;
import main.model.pojo.Users;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by admin on 20.04.2017.
 */
@Service
public class UserService implements UserServiceInterface {
    private    UserDAO userDAO;// = new UserDAOImpl(ManagementSystem.getCon());

    @Benchmark
    public Users auth(String login, String password) {
        Users user = userDAO.findUserByLoginAndPassword(login, password);
        //Users user = new Users(1,"login2","login2", 0);
        //logger.debug("user: " + user);

        if (user != null && user.isBlocked()==1) {
            return null;
        }
       // logger.debug("user not blocked");

        return user;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
}
