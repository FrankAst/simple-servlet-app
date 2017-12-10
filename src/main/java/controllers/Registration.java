package controllers;

import db.DBConnection;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;


public class Registration extends HttpServlet {


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

        JSONArray allUsers = sq.selectAllUsers();

        Boolean isExist = false;

        for (int i = 0; i < allUsers.length(); i++) {
            if(allUsers.getJSONObject(i).get("email").equals(reqData.getString("email"))){
                isExist = true;
            }
        }

        if(!isExist){
            sq.insertUser(reqData.getString("email"), reqData.getString("password"), reqData.getString("name"));
            String query = "select * from users where users.email='" + reqData.getString("email") + "'";
            JSONObject user = sq.findOne(query);
            HttpSession httpSession = req.getSession(true);
            httpSession.setAttribute("email", user.get("email"));
            httpSession.setAttribute("name", user.get("name"));
            httpSession.setAttribute("id", user.get("id"));
            httpSession.setMaxInactiveInterval(30);
            req.setAttribute("title", user.get("name"));
            req.getRequestDispatcher("profile.jsp").forward(req, res);
        } else {
            res.getWriter().println("Sorry, user with this email already exists, try again!");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        throw new ServletException("GET method is not supported.");
    }
}