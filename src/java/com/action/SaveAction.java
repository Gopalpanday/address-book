package com.action;

import com.db.DBConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class SaveAction extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            // Apache Commons-Fileupload library classes
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload sfu = new ServletFileUpload(factory);

            if (!ServletFileUpload.isMultipartContent(request)) {
                System.out.println("Sorry. No file uploaded");
                return;
            }

            // parse request
            List items = sfu.parseRequest(request);
            FileItem cname = (FileItem) items.get(0);
            String name = cname.getString();
            FileItem cphone = (FileItem) items.get(1);
            long phone = Long.parseLong(cphone.getString());
            FileItem caddress = (FileItem) items.get(2);
            String address = caddress.getString();
            FileItem photo = (FileItem) items.get(3);

//            String name = request.getParameter("name");
//            long phone = Long.parseLong(request.getParameter("phone"));
//            String address = request.getParameter("address");

            String sql = "INSERT INTO PBook(name, phone, address, photo) VALUES(?, ?, ?, ?)";
            PreparedStatement ps = DBConnection.getPreparedStatement(sql);
            ps.setString(1, name);
            ps.setLong(2, phone);
            ps.setString(3, address);
            ps.setBinaryStream(4, photo.getInputStream(), (int) photo.getSize());
            ps.executeUpdate();

            out.println("<meta http-equiv=\"REFRESH\" content=\"0; URL=ListAction\">");

        } catch (Exception ex) {

        }
    }

}
