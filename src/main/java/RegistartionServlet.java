
import DB.MySQLConnection;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.Map;


public class RegistartionServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        ServletContext ctx = req.getServletContext();
        MySQLConnection sq = (MySQLConnection) ctx.getAttribute("DBConnection");

        Map allUsers = sq.selectAll();

        if(allUsers.containsValue(req.getParameter("email"))){
            res.getWriter().println("Sorry, user with this email already exists, try again!");
        }
        else{
            sq.insertNewUser(req.getParameter("email"), req.getParameter("password"));
            res.getWriter().println(req.getParameter("email"));
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        throw new ServletException("GET method is not supported.");
    }
}