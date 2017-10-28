package filters;

import javax.servlet.*;

import java.io.*;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogFilter implements Filter {


    public LogFilter() {
    }

    public void init(FilterConfig fConfig) throws ServletException {
        System.out.println("LogFilter init successfuly!");
    }

    public void destroy() {
        System.out.println("LogFilter destroy!");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String ck="";
        int time=0;
        HttpServletRequest req = (HttpServletRequest) request;
        String servletPath = req.getServletPath();
        String url = req.getRequestURL().toString();
        String method = req.getMethod();
        HttpSession cookies = req.getSession(false);
        if(cookies!=null){
            ck = cookies.getId();
            time = cookies.getMaxInactiveInterval();
        } else {
            ck="no cookies";
            time=0;
        }

        System.out.println(method + " | " + url + " | " + servletPath + " | " + new Date() + " | Cookies: " + ck + "Time of cookie: " + time + " sec." );
        chain.doFilter(request, response);
    }


}
