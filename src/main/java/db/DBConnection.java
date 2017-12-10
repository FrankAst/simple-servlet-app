package db;

import org.joda.time.DateTime;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.print.DocFlavor;
import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneId;
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

    public void insertUser(String email, String password, String name){
        String query = "insert into users (email, password, name) VALUES (?, ?, ?)";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, email);
            stmt.setString(2, password);
            stmt.setString(3, name);
            stmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public JSONArray selectAllUsers() {
        String query = "select * from users";
        JSONArray allUsers = new JSONArray();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                JSONObject user = new JSONObject();
                user.put("email", rs.getString("email"));
                user.put("password", rs.getString("password"));
                user.put("id", rs.getInt("id"));
                allUsers.put(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allUsers;
    }


        public JSONObject findOne(String query) {
        PreparedStatement stmt = null;
        JSONObject user = new JSONObject();
        try {
            stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                user.put("email", rs.getString("email"));
                user.put("password", rs.getString("password"));
                user.put("id", Integer.toString(rs.getInt("id")));
                user.put("name", rs.getString("name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

        public void insertNote(String title, String content, String date, String color, int user_id){
        String query = "insert into notes (title, content, date, user_id, color) values (?, ?, ?, ?, ?)";
            try {

                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, title);
                stmt.setString(2, content);
                stmt.setString(3, date);
                stmt.setInt(4, user_id);
                stmt.setString(5, color);
                stmt.executeUpdate();
                conn.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public JSONArray selectAllNotes(int id) {
            String query = "select * from notes join users on notes.user_id = users.id where users.id = ?";
            PreparedStatement stmt = null;
            JSONArray allNotes = new JSONArray();
            try {
                stmt = conn.prepareStatement(query);
                stmt.setInt(1,id);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    JSONObject note = new JSONObject();
                    note.put("title", rs.getString("title"));
                    note.put("content", rs.getString("content"));
                    note.put("date", rs.getString("date"));
                    note.put("color", rs.getString("color"));
                    note.put("id", Integer.toString(rs.getInt("id")));
                    allNotes.put(note);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

            return allNotes;
        }

        public void deleteNote(int id){
            String query = "delete from notes where id = ?";
            try {
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setInt(1, id);
                stmt.executeUpdate();
                conn.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    public Boolean close() throws SQLException{
        this.conn.close();
        if(this.conn.isClosed()){
            return true;
        }
        return false;
    }


}





