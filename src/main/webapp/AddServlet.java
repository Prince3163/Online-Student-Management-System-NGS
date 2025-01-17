package main.webapp;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.*;

@WebServlet("/AddServlet")
public class AddServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String course = req.getParameter("course");
        String year_of_study = req.getParameter("Year_of_Study");

        if(name !=null && !name.isEmpty() &&  email!=null && !email.isEmpty() && phone !=null && !phone.isEmpty() && course != null && !course.isEmpty() && year_of_study!=null && !year_of_study.isEmpty()){
            try{
                ServletContext ctx = getServletContext();

                String url = ctx.getInitParameter("url");
                String username = ctx.getInitParameter("username");
                String password = ctx.getInitParameter("password");

                Class.forName("org.postgresql.Driver");

                try (Connection con = DriverManager.getConnection(url,username,password) ){

                    //Checking for user is alredy there or not
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery("select email from students where email ='"+email+"' ");
                    if(rs.next()) {
                        req.setAttribute("errorMessage", "Email already exists :(");
                        req.getRequestDispatcher("add.jsp").forward(req, resp);
                        return;
                    }

                    //Actual Insertion is here
                    PreparedStatement pstm = con.prepareStatement("INSERT INTO students (name,email,phone,course,year_of_study ) values(?,?,?,?,?)");
                    pstm.setString(1, name);
                    pstm.setString(2, email);
                    pstm.setString(3, phone);
                    pstm.setString(4, course);
                    pstm.setInt(5, Integer.parseInt(year_of_study));

                    int insertedRaw = pstm.executeUpdate();
                    if (insertedRaw > 0) {
//                        req.setAttribute("errorMessage", "Data Inserted :) ");
//                        req.getRequestDispatcher("add.jsp").forward(req, resp);
                        resp.sendRedirect("Dashboard");
                    } else {
                        req.setAttribute("errorMessage", "Something Went Wrong, Try Again! ");
                        req.getRequestDispatcher("add.jsp").forward(req, resp);
                    }

                }
                catch (SQLException e){
                    System.out.println(e.getMessage());
                }
            }
            catch(ClassNotFoundException e){
                System.out.println(e.getMessage());
            }
        }
        else {
            req.setAttribute("errorMessage","All Fields Required!! ");
            req.getRequestDispatcher("add.jsp").forward(req,resp);
        }
    }
}
