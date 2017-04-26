package main.controllers;

import main.model.pojo.Student;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import main.services.StudentService;
import main.services.StudentServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 19.04.2017.
 */
public class ListController extends HttpServlet {
    private   StudentServiceInterface service;// = new StudentService();
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
    }

    public  StudentServiceInterface getService() {
        return service;
    }

    @Autowired
    public  void setService(StudentServiceInterface service) {
        this.service = service;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("value", "Hello,student");

        List<Student> students = service.getAllStudents();
//        Student student1 = new Student(1,"Vasya", 12,1);
//        Student student2 = new Student(2,"Kolya", 22,1);
//        students.add(student1);
//        students.add(student2);
        //List<Student> list = service.getAllStudents();
        req.setAttribute("students", students);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/listStudents.jsp");
        dispatcher.forward(req, resp);
    }

    @Override

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
