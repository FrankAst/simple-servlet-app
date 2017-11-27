package exceptions;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ExceptionServlet")
public class ExceptionServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("exception", request.getAttribute("javax.servlet.error.exception").toString());
        request.setAttribute("status", request.getAttribute("javax.servlet.error.status_code").toString());
        request.setAttribute("servlet", request.getAttribute("javax.servlet.error.servlet_name").toString());
        request.setAttribute("uri", request.getAttribute("javax.servlet.error.request_uri").toString());
        request.getRequestDispatcher("Exception.jsp").forward(request, response);
    }
}


