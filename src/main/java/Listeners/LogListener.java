package Listeners;

import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;


@WebListener
public class LogListener implements ServletRequestListener {

    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        ServletRequest req = servletRequestEvent.getServletRequest();
        String log = "Protocol: " + req.getProtocol()+ "\nContent-type: " + req.getContentType() + "\nServer name: "
                + req.getServerName() + "\nPort: " + req.getServerPort();
        System.out.println(log);
    }

    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
        ServletRequest servletRequest = servletRequestEvent.getServletRequest();
        System.out.println("ServletRequest destroyed");
    }
}
