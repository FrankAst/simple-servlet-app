package controllers;

import db.DBConnection;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Reserve extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        ServletContext ctx = req.getServletContext();
        DBConnection sq = (DBConnection) ctx.getAttribute("DBConnection");
        String currentEmail = "";
        HttpSession session = req.getSession(false);
        if (session == null) {
            res.getWriter().println("Please, login firstly!");
        } else {
            currentEmail = session.getAttribute("email").toString();
        }
        String query = "update books set user_id = (select id from users where email = '" + currentEmail + "') where title ='" + req.getParameter("title") + "' and author = '" + req.getParameter("author") + "';";
        sq.updateBook(query);
        req.getRequestDispatcher("manage.jsp").forward(req, res);

    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        ServletContext ctx = req.getServletContext();
        DBConnection sq = (DBConnection) ctx.getAttribute("DBConnection");

        HttpSession session = req.getSession(false);
        if(session==null){
            res.getWriter().println("Please, login firstly!");
        }
        else{
            if(session.getAttribute("role").equals("user")){
                String query = "select* from books join users on books.user_id = users.id where books.user_id is not null and users.email = '"+ session.getAttribute("email") + "';";
                List<Map> allReservedBooks = sq.selectAllReservedBooks(query);
                req.setAttribute("reservedBooks", allReservedBooks);
                req.getRequestDispatcher("profile.jsp").forward(req, res);

            } else if(session.getAttribute("role").equals("librarian")) {
                String query = "select* from books join users on books.user_id = users.id where books.user_id is not null";
                List<Map> allReservedBooks = sq.selectAllReservedBooks(query);
                req.setAttribute("reservedBooks", allReservedBooks);
                req.getRequestDispatcher("manage.jsp").forward(req, res);
            }
        }


    }


}