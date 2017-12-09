package controllers;


import db.DBConnection;
import org.json.JSONObject;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;

public class Create extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext ctx = request.getServletContext();
        DBConnection sq = (DBConnection) ctx.getAttribute("DBConnection");
        HttpSession session = request.getSession(false);
        StringBuilder sb = new StringBuilder();
        BufferedReader br = request.getReader();
        String str;

        while( (str = br.readLine()) != null ){
            sb.append(str);
        }

        JSONObject newNote = new JSONObject(sb.toString());

        if(session==null){
            response.getWriter().println("Login firstly, please!");
        } else {
            int id = Integer.parseInt(session.getAttribute("id").toString());
            System.out.println(id);
            sq.insertNote(newNote.get("title").toString(), newNote.get("content").toString(), newNote.get("date").toString(), newNote.get("color").toString(), id);
            response.setContentType("application/json");
            response.getWriter().write(newNote.toString());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        throw new ServletException("GET is unsupported here!");
    }
}
