package com.action;

import com.db.DBConnection;
import java.io.IOException;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteAction extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String ids[] = request.getParameterValues("rdel");

            for (int i = 0; i < ids.length; i++) {
                int id = Integer.parseInt(ids[i]);
                String sql = "DELETE FROM PBook WHERE id = ?";
                PreparedStatement ps = DBConnection.getPreparedStatement(sql);
                ps.setInt(1, id);
                ps.executeUpdate();
            }
            response.getWriter().println("<meta http-equiv=\"REFRESH\" content=\"0; URL=ListAction\">");
        } catch (Exception ex) {

        }
    }

}
