//Not Completed
package main.webapp;

import com.sun.net.httpserver.HttpContext;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.*;

@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login_id = req.getParameter("username");
        String login_pwd = req.getParameter("password");

        if(login_id !=" " && !login_id.isEmpty() && !login_pwd.isEmpty() && login_pwd!=" "){
            try{
                ServletContext ctx = getServletContext();

                String url = ctx.getInitParameter("url");
                String username = ctx.getInitParameter("username");
                String password = ctx.getInitParameter("password");
                String query=String.format("Select * from admin WHERE username='%s' AND password='%s'",login_id,login_pwd);

                Class.forName("org.postgresql.Driver");

                try (Connection con = DriverManager.getConnection(url,username,password) ){
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(query);

                    if(rs.next()){
                        HttpSession session = req.getSession();
                        session.setAttribute("login_id",login_id);
                        session.setMaxInactiveInterval(300);
                        resp.sendRedirect("Dashboard");
                    }
                    else{
                        req.setAttribute("errorMessage","Invalid Credentials :( ");
                        req.getRequestDispatcher("login.jsp").forward(req,resp);
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
        else {
            req.setAttribute("errorMessage","All Fields Required!! ");
            req.getRequestDispatcher("login.jsp").forward(req,resp);
        }
    }
}