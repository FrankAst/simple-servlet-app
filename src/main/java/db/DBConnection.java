package db;

import javax.print.DocFlavor;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

    public class DBConnection {

        private String userName;
        private String password;
        private String url;
        private Connection conn = null;


        public DBConnection(String userName, String password, String url){
            this.userName=userName;
            this.password=password;
            this.url=url;
            try
            {
                Class.forName ("org.postgresql.Driver").newInstance ();
                conn = DriverManager.getConnection(url, userName, password);
                conn.setAutoCommit(false);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

    public void insertUser(String email, String password, String query){
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, email);
            stmt.setString(2, password);
            stmt.setString(3, "user");
            stmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

        public void insertBook(String title, String author, String query){
            try {
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, title);
                stmt.setString(2, author);
                stmt.executeUpdate();
                conn.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    public List<Map> selectAllUsers() {
        String query = "select * from users";
        PreparedStatement stmt = null;
        List<Map> data = new ArrayList<Map>();
        try {
            stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Map<String, String> result = new HashMap<String, String>();
                result.put("email", rs.getString("email"));
                result.put("password", rs.getString("password"));
                data.add(result);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

        public List<Map> selectAllBooks() {
            String query = "select * from books";
            PreparedStatement stmt = null;
            List<Map> data = new ArrayList<Map>();
            try {
                stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery();
               data = new ArrayList<Map>();
                while (rs.next()) {
                    Map<String, String> result = new HashMap<String, String>();
                    result.put("title", rs.getString("title"));
                    result.put("author", rs.getString("author"));
                    data.add(result);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println(data);
            return data;
        }

        public List<Map> selectAllReservedBooks(String query) {
            PreparedStatement stmt = null;
            List<Map> data = new ArrayList<Map>();
            try {
                stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery();
                data = new ArrayList<Map>();
                while (rs.next()) {
                    Map<String, String> result = new HashMap<String, String>();
                    result.put("title", rs.getString("title"));
                    result.put("author", rs.getString("author"));
                    result.put("email", rs.getString("email"));
                    System.out.println(result);
                    data.add(result);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println(data);
            return data;
        }

        public void updateBook(String query) {
            PreparedStatement stmt = null;
            try {
                stmt = conn.prepareStatement(query);
                stmt.executeUpdate();
                conn.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public Map findOne(String query) {
        PreparedStatement stmt = null;
        Map<String, String> result = new HashMap<String, String>();
        try {
            stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                result.put("email", rs.getString("email"));
                result.put("password", rs.getString("password"));
                result.put("role", rs.getString("role"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

        public int getUserID(String query) {
            PreparedStatement stmt = null;
            int id=0;
            try {
                stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    id = rs.getInt("id");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return id;
        }

        public void insertComment(String query, String body, int user_id){
            try {
                Timestamp created_at = new Timestamp(System.currentTimeMillis());
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setInt(1, user_id);
                stmt.setTimestamp(2, created_at);
                stmt.setString(3, body);
                stmt.executeUpdate();
                conn.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public List<Map> selectAllComments() {
            String query = "select * from comments join users on comments.user_id = users.id";
            PreparedStatement stmt = null;
            List<Map> data = new ArrayList<Map>();
            try {
                stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    Map<String, Object> result = new HashMap<String, Object>();
                    result.put("body", rs.getString("body"));
                    result.put("created_at", rs.getDate("created_at"));
                    result.put("email", rs.getString("email"));
                    data.add(result);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

            return data;
        }


    public Boolean close() throws SQLException{
        this.conn.close();
        if(this.conn.isClosed()){
            return true;
        }
        return false;
    }


}





