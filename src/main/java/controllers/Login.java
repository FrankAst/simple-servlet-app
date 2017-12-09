package controllers;

import db.DBConnection;
import org.json.JSONObject;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;


public class Login extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        ServletContext ctx = req.getServletContext();
        DBConnection sq = (DBConnection) ctx.getAttribute("DBConnection");

        StringBuilder sb = new StringBuilder();
        BufferedReader br = req.getReader();
        String str;

        while( (str = br.readLine()) != null ){
            sb.append(str);
        }

        JSONObject reqData = new JSONObject(sb.toString());

        String query = "select * from users where users.email='" + reqData.get("email") + "'";
        JSONObject user = sq.findOne(query);

        if(user != null && user.get("password") != null && reqData.get("email")!= null && reqData.get("password")!= null) {

            if (user.get("password").equals(reqData.get("password"))) {

                    HttpSession httpSession = req.getSession(true);
                    httpSession.setAttribute("email", user.get("email"));
                    System.out.println(user.get("id"));
                    httpSession.setAttribute("id", user.get("id"));
                    httpSession.setMaxInactiveInterval(30);
                    res.sendRedirect("profile.jsp");

            } else {
                res.getWriter().println("Invalid data");
            }

        } else {
            res.getWriter().println("There is no user with this parameters!");
        }
     }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("login.html").forward(request, response);
    }
}
