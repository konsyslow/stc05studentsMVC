package main.controllers;

import main.model.pojo.Users;
import main.services.UserService;
import main.services.UserServiceInterface;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by admin on 19.04.2017.
 */
public class LoginServlet extends HttpServlet {
//    static{
//        PropertyConfigurator.configure("lo4j.properties");
//    }
    private  UserServiceInterface userService;// = new UserService();
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
    }

    public UserServiceInterface getUserService() {
        return userService;
    }

    @Autowired
    public void setUserService(UserServiceInterface userService) {
        this.userService = userService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/login.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (userService.auth(login, password) != null) {
            req.getSession().setAttribute("userLogin", login);
            //logger.debug("user: " + login + " logged" );
            resp.sendRedirect(req.getContextPath() + "/listStudents");
           // resp.sendRedirect(req.getContextPath() + "/students/");
        }else{
            resp.sendRedirect(req.getContextPath() + "/");
        }
    }
}
