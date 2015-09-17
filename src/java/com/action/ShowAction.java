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

public class ShowAction extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            String name = request.getParameter("name");

            String sql = "SELECT * FROM PBook WHERE name = ?";
            PreparedStatement ps = DBConnection.getPreparedStatement(sql);
            ps.setString(1, name);
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
                int id = rs.getInt(1);

                out.println("<table class=\"tblInput\" cellspacing=\"10\" cellpadding=\"5\">"
                        + "<tr><td width=80%>"
                        + "<table class=\"tblInput\" cellspacing=\"10\" cellpadding=\"5\">"
                        + "<tr bgcolor=\"lightgrey\">"
                        + "<th colspan=\"2\">Searched Contact</th>"
                        + "</tr>"
                        + "<tr>"
                        + "<td>Contact ID : </td>"
                        + "<td><label class=\"lbl\">" + rs.getInt(1) + "</label></td>"
                        + "</tr>"
                        + "<tr>"
                        + "<td>Contact Name : </td>"
                        + "<td><label class=\"lbl\">" + rs.getString(2) + "</label></td>"
                        + "</tr>"
                        + "<tr>"
                        + "<td>Mobile No : </td>"
                        + "<td><label class=\"lbl\">" + rs.getLong(3) + "</label></td>"
                        + "</tr>"
                        + "<td>Address : </td>"
                        + "<td><label class=\"lbl\">" + rs.getString(4) + "</label></td>"
                        + "</tr>"
                        + "<tr>"
                        + "<td colspan=\"2\"><hr style=\"border-color: red;\" /></td>"
                        + "</tr>"
                        + "</table>"
                        + "</td>"
                        + "<td><img style=\"border-radius: 200px;\" width='230' height='230' src=DisplayPhotoServlet?id=" + id + "></img></td>"
                        + "</tr></table>");

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
