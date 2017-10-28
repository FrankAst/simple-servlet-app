package DB;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class MySQLConnection {

   private String userName;
    private String password;
    private String url;
   private Connection conn = null;


        public MySQLConnection(String userName, String password, String url){
            this.userName=userName;
            this.password=password;
            this.url=url;


            try
            {
                Class.forName ("com.mysql.jdbc.Driver").newInstance ();
                conn = DriverManager.getConnection(url, userName, password);
                System.out.println ("Database connection established");
            }
            catch (Exception e)
            {
                System.err.println ("Cannot connect to database server");
                e.printStackTrace();
            }
        }

    public void insertNewUser(String email, String password){
            String query = "INSERT INTO `users`(`email`, `password`) VALUES (? , ?)";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, email);
            stmt.setString(2, password);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Map selectAll() {
        String query = "select * from users";
        PreparedStatement stmt = null;
        Map<String, String> result = new HashMap<String, String>();
        try {
            stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                result.put("email", rs.getString(2));
                result.put("password", rs.getString(3));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }


    public Map findOne(String email) {
        String query = "select * from users where users.email='" + email + "'";
        PreparedStatement stmt = null;
        Map<String, String> result = new HashMap<String, String>();
        try {
            stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                result.put("email", rs.getString(2));
                result.put("password", rs.getString(3));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }


}





