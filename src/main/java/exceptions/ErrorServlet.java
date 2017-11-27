package exceptions;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ErrorServlet")
public class ErrorServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("status", request.getAttribute("javax.servlet.error.status_code").toString());
        request.setAttribute("uri", request.getAttribute("javax.servlet.error.request_uri").toString());
        request.getRequestDispatcher("Error.jsp").forward(request, response);
    }

}