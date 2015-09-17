package com.action;

import com.db.DBConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListAction extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        try {
            String sql = "SELECT * FROM PBook ORDER BY ID ASC";
            Statement st = DBConnection.getStatement();
            ResultSet rs = st.executeQuery(sql);

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

            out.println("<form name=\"fom\" method=\"POST\">"
                    + "<table class=\"tbl\" cellspacing=\"6\" cellpadding=\"5\">"
                    + "<tr bgcolor=\"lightgrey\">"
                    + "<th></th>"
                    + "<th>ID</th>"
                    + "<th>Contact Name</th>"
                    + "<th>Mobile No</th>"
                    + "<th>Address</th>"
                    + "<th>Photo</th>"
                    + "<th>Action</th>"
                    + "</tr>");
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                long phone = rs.getLong(3);
                String address = rs.getString(4);

                out.println("<tr>"
                        + "<td align=\"center\">"
                        + "<input type=\"checkbox\" class=\"chk\" name=\"rdel\" value='" + id + "' />"
                        + "</td>"
                        + "<td align=\"center\">" + id + "</td>"
                        + "<td>" + name + "</td>"
                        + "<td align=\"center\">" + phone + "</td>"
                        + "<td>" + address + "</td>"
                        + "<td>" + "<img style=\"border-radius: 50px;\" width='80' height='80' src=DisplayPhotoServlet?id=" + id + "></img> <p/>" + "</td>"
                        + "<td align=\"center\">"
                        + "<a href=\"javascript:edit('" + id + "')\">Update</a>"
                        + "<img src=\"styles/edit.bmp\" width=\"20\" height=\"20\" alet=\"\" />"
                        + "</td>"
                        + "</tr>");
                out.println("<tr><td colspan=\"7\"><hr /></td></tr>");
            }

            out.println("<tr><td colspan=\"7\"><hr style=\"border-color: red;\" /></td></tr>");
            out.println("<tr>"
                    + "<td colspan=\"6\" align=\"center\">"
                    + "<input type=\"button\" class=\"btn\" value=\"Delete Selected Contacts\" onclick=\"deleteContacts();\" />"
                    + "</td>"
                    + "</tr>"
                    + "</table>"
                    + "</form>");

            out.println("</div>"
                    + "</div>");

            out.println("<div class=\"footer\">"
                    + "@copyright"
                    + "<br/>"
                    + "Developed By Gopal Pandey"
                    + "</div>");
            out.println("</div>");

            out.println("</body></html>");
        } catch (Exception ex) {

        }
    }

}
