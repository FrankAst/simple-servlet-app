package controllers;

import db.DBConnection;
import org.json.JSONObject;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

public class Delete extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServletContext ctx = request.getServletContext();
        DBConnection sq = (DBConnection) ctx.getAttribute("DBConnection");

        StringBuilder sb = new StringBuilder();
        BufferedReader br = request.getReader();
        String str;

        while( (str = br.readLine()) != null ){
            sb.append(str);
        }

        JSONObject reqData = new JSONObject(sb.toString());
        int id = Integer.parseInt(reqData.get("id").toString());
        sq.deleteNote(id);
    }
}
