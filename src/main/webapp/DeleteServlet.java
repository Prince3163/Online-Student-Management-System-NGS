package main.webapp;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.*;

@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        if (session == null || session.getAttribute("login_id") == null) {
            resp.sendRedirect("login.jsp");
            return;
        }

        String email = req.getParameter("id");


        try{
            ServletContext ctx = getServletContext();

            String url = ctx.getInitParameter("url");
            String username = ctx.getInitParameter("username");
            String password = ctx.getInitParameter("password");
            String query=String.format("DELETE from students where email= ? ");

            Class.forName("org.postgresql.Driver");

            try (Connection con = DriverManager.getConnection(url,username,password) ){
                PreparedStatement pstm = con.prepareStatement(query);
                pstm.setString(1, email );

                int rowsAffected = pstm.executeUpdate();
                if (rowsAffected > 0) {
                    resp.sendRedirect("Dashboard");
                } else {
                    resp.getWriter().write("No record found to delete.");
                }

            }
            catch (SQLException e){
                //Make this Better
                System.out.println(e.getMessage());
            }
        }
        catch(ClassNotFoundException e){
            //Make this Better
            System.out.println(e.getMessage());
        }
    }
}