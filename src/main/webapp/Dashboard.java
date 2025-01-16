package main.webapp;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

@WebServlet("/Dashboard")
public class Dashboard extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        if (session == null || session.getAttribute("login_id") == null) {
            resp.sendRedirect("login.jsp");
            return;
        }
        String login_id = session.getAttribute("login_id").toString();
        PrintWriter out= resp.getWriter();

        out.write("<html><head>");
        out.write("<style>");
        out.write(".header { display: flex; justify-content: space-between; align-items: center; }");
        out.write(".logout { margin-left: auto; }");
        out.write("</style>");
        out.write("</head><body>");
        out.write("<div class='header'>");
        out.write("<h2>Welcome, " + login_id + "!</h2>");
        out.write("<a class='logout' href='Logout'>Logout</a>");
        out.write("</div>");
        out.write("<br><br>");

        out.write("<form action='view.jsp' >");
        out.write("<input type='submit' value='View Student'>");
        out.write("</form>");

        out.write("<br>");
        out.write("<br>");

        out.write("<form action='add.jsp' >");
        out.write("<input type='submit' value='Add Student'>");
        out.write("</form>");

        out.write("</body></html>");


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
