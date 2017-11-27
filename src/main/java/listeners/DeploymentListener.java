package listeners;

import db.DBConnection;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.SQLException;

@WebListener
public class DeploymentListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext ctx = servletContextEvent.getServletContext();
        DBConnection conn = new DBConnection("valerijborodaev", "v1v2v3b4", "jdbc:postgresql://localhost:5432/java");
        ctx.setAttribute("DBConnection", conn);
        System.out.println("Context: Database connection is established.");
    }
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        ServletContext ctx = servletContextEvent.getServletContext();
        DBConnection conn = (DBConnection) ctx.getAttribute("DBConnection");
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Context: Database connection closed.");

    }

}