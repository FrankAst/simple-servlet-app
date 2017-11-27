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

public class Book extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        ServletContext ctx = req.getServletContext();
        DBConnection sq = (DBConnection) ctx.getAttribute("DBConnection");
        List<Map> allBooks = sq.selectAllBooks();
        Boolean isExist = false;

        for (int i = 0; i < allBooks.size(); i++) {
            if(allBooks.get(i).containsValue(req.getParameter("title")) && allBooks.get(i).containsValue(req.getParameter("author"))){
                isExist = true;
            }
        }

        if(!isExist){
            String query = "insert into books (title, author) VALUES (? , ?)";
            sq.insertBook(req.getParameter("title"), req.getParameter("author"),query);
            HttpSession session = req.getSession(false);
            if(session==null){
                res.getWriter().println("Please, login firstly!");
            }
            else{
                if(session.getAttribute("role").equals("user")){
                    req.getRequestDispatcher("profile.jsp").forward(req, res);
                } else if(session.getAttribute("role").equals("librarian")) {
                    req.getRequestDispatcher("manage.jsp").forward(req, res);
                }
            }
        } else {
            res.getWriter().println("Sorry, this book already exist!");
        }


    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext ctx = request.getServletContext();
        DBConnection sq = (DBConnection) ctx.getAttribute("DBConnection");
        List<Map> allBooks = sq.selectAllBooks();
        request.setAttribute("books", allBooks);
        HttpSession session = request.getSession(false);
        if(session==null){
            response.getWriter().println("Please, login firstly!");
        }
        else{
            if(session.getAttribute("role").equals("user")){
                request.getRequestDispatcher("profile.jsp").forward(request, response);
            } else if(session.getAttribute("role").equals("librarian")) {
                request.getRequestDispatcher("manage.jsp").forward(request, response);
            }
        }
    }
}
