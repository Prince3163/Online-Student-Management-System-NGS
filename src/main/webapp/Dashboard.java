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
import java.util.ArrayList;

@WebServlet("/Dashboard")
public class Dashboard extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        if (session == null || session.getAttribute("login_id") == null) {
            resp.sendRedirect("login.jsp");
            return;
        }

        try{
            ServletContext ctx = getServletContext();

            String url = ctx.getInitParameter("url");
            String username = ctx.getInitParameter("username");
            String password = ctx.getInitParameter("password");
            String query=String.format("Select * from students ");

            Class.forName("org.postgresql.Driver");

            try (Connection con = DriverManager.getConnection(url,username,password) ){
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                ArrayList<String[]> data = new ArrayList<>();

                while(rs.next()){
                    String[] raw = new String[]{
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getString(5),
                            String.valueOf(rs.getInt(6))
                    };
                    data.add(raw);
                }

                session.setAttribute("data",data);

                req.getRequestDispatcher("view.jsp").forward(req,resp);

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
