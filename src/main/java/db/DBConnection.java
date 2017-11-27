package db;

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

    public void insert(String email, String password, String query){
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, email);
            stmt.setString(2, password);
            stmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Map> selectAllStudents() {
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

        public List<Map> selectAllLibrarians() {
            String query = "select * from librarians";
            PreparedStatement stmt = null;
            List<Map> data = new ArrayList<Map>();
            try {
                stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    Map<String, String> result = new HashMap<String, String>();
                    result.put("email", rs.getString(4));
                    result.put("password", rs.getString(5));
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
                    result.put("title", rs.getString(2));
                    result.put("author", rs.getString(3));
                    data.add(result);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println(data);
            return data;
        }


    public Map findOne(String query) {
        PreparedStatement stmt = null;
        Map<String, String> result = new HashMap<String, String>();
        try {
            stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                result.put("email", rs.getString(3));
                result.put("password", rs.getString(4));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


    public Boolean close() throws SQLException{
        this.conn.close();
        if(this.conn.isClosed()){
            return true;
        }
        return false;
    }


}





