package controllers;


import db.DBConnection;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class Welcome extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext ctx = request.getServletContext();
        DBConnection sq = (DBConnection) ctx.getAttribute("DBConnection");
        HttpSession session = request.getSession(false);
        if(session==null || session.getAttribute("id") == null){

            JSONArray notes = new JSONArray();

            JSONObject item1 = new JSONObject();
            item1.put("title", "Title example");
            item1.put("content", "You have to login firstly");
            item1.put("date", "00:00 | 21.12.2012");
            item1.put("color", "#246703");

            JSONObject item2 = new JSONObject();
            item2.put("title", "Awesome title");
            item2.put("content", "For create your own notes, login firstly, please.");
            item2.put("date", "00:00 | 21.12.2012");
            item2.put("color", "#ffd700");


            notes.put(item1);
            notes.put(item2);

            request.setAttribute("notes", notes);
            request.getRequestDispatcher("profile.jsp").forward(request, response);
        }
        else{
            try {
                JSONArray allNotes = sq.selectAllNotes(Integer.parseInt(session.getAttribute("id").toString()));
                request.setAttribute("notes", allNotes);
                request.getRequestDispatcher("profile.jsp").forward(request, response);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
