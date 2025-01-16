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

@WebServlet("")
public class AddServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String course = req.getParameter("course");
        String year_of_study = req.getParameter("Year_of_Study");


        if(name !="" &&  email!="" && phone !="" && course != "" && year_of_study!=""){
            try{
                ServletContext ctx = getServletContext();

                String url = ctx.getInitParameter("url");
                String username = ctx.getInitParameter("username");
                String password = ctx.getInitParameter("password");

                Class.forName("org.postgresql.Driver");

                try (Connection con = DriverManager.getConnection(url,username,password) ){
                    PreparedStatement pstm = con.prepareStatement("INSERT INTO students ('name','email','phone','course','year_of_study' ) values(?,?,?,?,?)");
                    pstm.setString(1,);
//                    if(rs.next()){
//                        HttpSession session = req.getSession();
//                        session.setAttribute("login_id",login_id);
//                        session.setMaxInactiveInterval(300);
//                        resp.sendRedirect("Dashboard");
//                    }
//                    else{
//                        req.setAttribute("errorMessage","Invalid Credentials :( ");
//                        req.getRequestDispatcher("login.jsp").forward(req,resp);
//                    }
//                }
//                catch (SQLException e){
//                    //Make this Better
//                    System.out.println(e.getMessage());
//                }
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
