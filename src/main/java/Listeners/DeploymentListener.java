package Listeners;

import DB.MySQLConnection;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.SQLException;

@WebListener
public class DeploymentListener implements ServletContextListener{

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext ctx = servletContextEvent.getServletContext();
        MySQLConnection conn = new MySQLConnection("root", "", "jdbc:mysql://localhost:3306/users");
        ctx.setAttribute("DBConnection", conn);
        System.out.println("Context: Database connection is established.");
    }
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        ServletContext ctx = servletContextEvent.getServletContext();
        MySQLConnection conn = (MySQLConnection) ctx.getAttribute("DBConnection");
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Context: Database connection closed.");

    }

}

