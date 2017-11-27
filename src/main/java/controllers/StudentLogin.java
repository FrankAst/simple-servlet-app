package controllers;

import db.DBConnection;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;


public class StudentLogin extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        ServletContext ctx = req.getServletContext();
        DBConnection sq = (DBConnection) ctx.getAttribute("DBConnection");
        String query = "select * from users where users.email='" + req.getParameter("email") + "'";
        Map user = sq.findOne(query);
        if(user != null && user.get("password") != null && req.getParameter("email")!= null && req.getParameter("password")!= null) {
            if (user.get("password").equals(req.getParameter("password"))) {
                HttpSession httpSession = req.getSession(true);
                httpSession.setAttribute("email", req.getParameter("email").toString());
                httpSession.setAttribute("role", "student");
                httpSession.setMaxInactiveInterval(30);
                res.sendRedirect("profile.jsp");
            } else {
                res.getWriter().println("Invalid data");
            }
        }else{
            res.getWriter().println("There is no user with this parameters!");
        }
        }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("student.html").forward(request, response);
    }
}
