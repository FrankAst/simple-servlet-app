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


public class LibrarianRegistration extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        ServletContext ctx = req.getServletContext();
        DBConnection sq = (DBConnection) ctx.getAttribute("DBConnection");
        List<Map> allLibrarians = sq.selectAllLibrarians();
        Boolean isExist = false;

        for (int i = 0; i < allLibrarians.size(); i++) {

            if(allLibrarians.get(i).containsValue(req.getParameter("email"))){
                isExist = true;
            }
        }

        if(!isExist){
            String query = "insert into librarians (email, password) VALUES (? , ?)";
            sq.insert(req.getParameter("email"), req.getParameter("password"),query);
            res.getWriter().println(req.getParameter("email"));
        } else {
            res.getWriter().println("Sorry, librarian with this email already exists, try again!");
        }
    }



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        throw new ServletException("GET method is not supported.");
    }
}