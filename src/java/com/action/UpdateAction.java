package com.action;

import com.db.DBConnection;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class UpdateAction extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
            FileItem cid = (FileItem) items.get(0);
            int id = Integer.parseInt(cid.getString());
            FileItem cname = (FileItem) items.get(1);
            String name = cname.getString();
            FileItem cphone = (FileItem) items.get(2);
            long phone = Long.parseLong(cphone.getString());
            FileItem caddress = (FileItem) items.get(3);
            String address = caddress.getString();
            FileItem photo = (FileItem) items.get(4);

//            int id = Integer.parseInt(request.getParameter("id"));
//            String name = request.getParameter("name");
//            long phone = Long.parseLong(request.getParameter("phone"));
//            String address = request.getParameter("address");

            String sql = "UPDATE PBook SET name = ?, phone = ?, address = ?, photo = ? WHERE id = ?";
            PreparedStatement ps = DBConnection.getPreparedStatement(sql);
            ps.setString(1, name);
            ps.setLong(2, phone);
            ps.setString(3, address);
            ps.setBinaryStream(4, photo.getInputStream(), (int) photo.getSize());
            ps.setInt(5, id);
            ps.executeUpdate();

            response.getWriter().println("<meta http-equiv=\"REFRESH\" content=\"0; URL=ListAction\">");

        } catch (Exception ex) {

        }
    }

}
