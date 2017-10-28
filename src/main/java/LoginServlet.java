import DB.MySQLConnection;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;


public class LoginServlet extends HttpServlet {

//    MySQLConnection sq = new MySQLConnection("root", "", "jdbc:mysql://localhost:3306/users");

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        ServletContext ctx = req.getServletContext();
        MySQLConnection sq = (MySQLConnection) ctx.getAttribute("DBConnection");

        Map user = sq.findOne(req.getParameter("email"));
            if(user.get("password").equals(req.getParameter("password"))){
                HttpSession httpSession = req.getSession(true);
                httpSession.setAttribute("email",req.getParameter("email").toString());
                httpSession.setMaxInactiveInterval(30);
                res.sendRedirect("profile.html");
            }
            else{
                res.getWriter().println("Invalid data");
            }
        }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        throw new ServletException("GET method is not supported.");
    }
}
