package filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class SessionFilter implements Filter {
    public SessionFilter() {
    }

    public void init(FilterConfig fConfig) throws ServletException {
        System.out.println("SessionFilter init successfuly!");
    }

    public void destroy() {
        System.out.println("SessionFilter destroy!");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession(false);
        if(session!=null){
            System.out.println("SessionFilter: Session has validated successfuly!!!");
        }
        else{
            System.out.println("SessionFilter: No session, please login again");
        }
        chain.doFilter(request, response);
    }
}
