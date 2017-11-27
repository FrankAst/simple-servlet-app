package controllers;

import db.DBConnection;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;


public class Forum extends HttpServlet {



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String currentEmail="";
        ServletContext ctx = req.getServletContext();
        DBConnection sq = (DBConnection) ctx.getAttribute("DBConnection");
        HttpSession session = req.getSession(false);
        if(session==null){
            res.getWriter().println("Please, login firstly!");
        } else {
            currentEmail = session.getAttribute("email").toString();
        }
            String query = "insert into comments (user_id, created_at, body) VALUES (?, ?, ?)";
            int id = sq.getUserID("select id from users where email = '" + currentEmail+ "';");
            sq.insertComment(query, req.getParameter("body"), id);
            List<Map> allComments = sq.selectAllComments();
            req.setAttribute("comments", allComments);
            req.getRequestDispatcher("comments.jsp").forward(req, res);
        }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        ServletContext ctx = req.getServletContext();
        DBConnection sq = (DBConnection) ctx.getAttribute("DBConnection");
        List<Map> allComments = sq.selectAllComments();
        req.setAttribute("comments", allComments);
        req.getRequestDispatcher("comments.jsp").forward(req, res);
    }
}
