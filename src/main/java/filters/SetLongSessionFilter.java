package filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SetLongSessionFilter implements Filter{
    public SetLongSessionFilter() {
    }

    public void init(FilterConfig fConfig) throws ServletException {
        System.out.println("SetLongSessionFilter init successfuly!");
    }

    public void destroy() {
        System.out.println("SetLongSessionFilter destroy!");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession(false);
        if(session!=null){
            session.setMaxInactiveInterval(30);
            System.out.println("SetLongSessionFilter: session has 30 sec inactive time now.");
        } else {
            System.out.println("SetLongSessionFilter: there is not any session!");
        }
        chain.doFilter(request, response);
    }



}
