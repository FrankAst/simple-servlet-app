package controllers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class Welcome extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if(session==null){
                request.getRequestDispatcher("login.html").forward(request, response);
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
