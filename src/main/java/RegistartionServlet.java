
import DB.MySQLConnection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;


public class RegistartionServlet extends HttpServlet {

    MySQLConnection sq = new MySQLConnection("root", "", "jdbc:mysql://localhost:3306/users");

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        Map allUsers = sq.selectAll();
        if(allUsers.containsValue(req.getParameter("email"))){
            res.getWriter().println("Sorry, user with this email already exists, try again!");
        }
        else{
            sq.insertNewUser(req.getParameter("email"), req.getParameter("password"));
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        throw new ServletException("GET method is not supported.");
    }
}