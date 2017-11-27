package controllers;

import db.DBConnection;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;


public class Registration extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        ServletContext ctx = req.getServletContext();
        DBConnection sq = (DBConnection) ctx.getAttribute("DBConnection");
        List<Map> allUsers = sq.selectAllUsers();
        Boolean isExist = false;

        for (int i = 0; i < allUsers.size(); i++) {
            if(allUsers.get(i).containsValue(req.getParameter("email"))){
                isExist = true;
            }
        }
        if(!isExist){
            String query = "insert into users (email, password, role) VALUES (?, ?, ?)";
            sq.insertUser(req.getParameter("email"), req.getParameter("password"), query);
            res.getWriter().println(req.getParameter("email"));
        } else {
            res.getWriter().println("Sorry, user with this email already exists, try again!");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        throw new ServletException("GET method is not supported.");
    }
}