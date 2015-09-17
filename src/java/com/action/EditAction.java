package com.action;

import com.db.DBConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditAction extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            int id = Integer.parseInt(request.getParameter("id"));

            String sql = "SELECT * FROM PBook WHERE id = ?";
            PreparedStatement ps = DBConnection.getPreparedStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            out.println("<html>"
                    + "<head>"
                    + "<title>:: Gopu AddressBook</title>"
                    + "<link rel=\"stylesheet\" href=\"styles/styleFramework.css\"/>"
                    + "<script type=\"text/javascript\" src=\"styles/action.js\"></script>"
                    + "</head>"
                    + "<body>");

            out.println("<div class=\"container\">"
                    + "<div class=\"header\">"
                    + "<img src=\"styles/Gopu.jpg\" alt=\"\" id=\"CalImage\" />"
                    + "<h1 id=\"headerTitle\">Gopu's Online AddressBook</h1>"
                    + "</div>"
                    + "<div class=\"section\">"
                    + "<div class=\"menu\">"
                    + "    <ul>"
                    + "        <li><a href=\"Intro.html\">Home</a></li>"
                    + "        <li><a href=\"Save.html\">New Contact</a></li>"
                    + "         <li><a href=\"Search.html\">Search Contact</a></li>"
                    + "        <li><a href=\"ListAction\">List Contacts</a></li>"
                    + "    </ul>"
                    + "</div>"
                    + "<div id=\"top\">");
            if (rs.next()) {
                out.println("<form method=\"POST\" action=\"UpdateAction\" enctype=\"multipart/form-data\">"
                        + "<table class=\"tblInput\" cellspacing=\"10\" cellpadding=\"5\">"
                        + "<tr><td width=80%>"
                        + "<table class=\"tblInput\" cellspacing=\"10\" cellpadding=\"5\">"
                        + "<tr bgcolor=\"lightgrey\">"
                        + "<th colspan=\"2\">Update Existing Contact</th>"
                        + "</tr>"
                        + "<tr>"
                        + "<td>Contact ID : </td>"
                        + "<td><input type=\"text\" name=\"id\" size=\"32\" readonly value='" + rs.getInt(1) + "' /></td>"
                        + "</tr>"
                        + "<tr>"
                        + "<td>Contact Name : </td>"
                        + "<td><input type=\"text\" name=\"name\" size=\"32\" value='" + rs.getString(2) + "' /></td>"
                        + "</tr>"
                        + "<tr>"
                        + "<td>Mobile No : </td>"
                        + "<td><input type=\"text\" name=\"phone\" size=\"32\" value='" + rs.getLong(3) + "' /></td>"
                        + "</tr>"
                        + "<td>Address : </td>"
                        + "<td><textarea name=\"address\" cols=\"25\" rows=\"5\">" + rs.getString(4) + "</textarea></td>"
                        + "</tr>"
                        + "<tr>"
                        + "<td>Photo : </td>"
                        + "<td><input type=\"file\"  name=\"photo\" size=\"15\" /></td>"
                        + "</tr>"
                        + "<tr>"
                        + "<td colspan=\"2\"><hr style=\"border-color: red;\" /></td>"
                        + "</tr>"
                        + "<tr>"
                        + "<td colspan=\"2\" align=\"center\"><input type=\"submit\" class=\"btn\" value=\"Update Contact\" /></td>"
                        + "</tr>"
                        + "</table>"
                        + "</td>"
                        + "<td><img style=\"border-radius: 200px;\" width='230' height='230' src=DisplayPhotoServlet?id=" + id + "></img></td>"
                        + "</tr></table>"
                        + "</form>");

            }
            out.println("</div>"
                    + "</div>");

            out.println("<div class=\"footer\">"
                    + "@copyright"
                    + "<br/>"
                    + "Developed By Gopal Pandey"
                    + "</div>");
            out.println("</div>");
            out.println("</body></html>");
        } catch (Exception e) {

        }
    }

}
