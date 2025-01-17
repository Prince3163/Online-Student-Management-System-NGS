package main.webapp;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.*;

@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {

    private String id;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        id = req.getParameter("id");

        try{
            ServletContext ctx = getServletContext();

            String url = ctx.getInitParameter("url");
            String username = ctx.getInitParameter("username");
            String password = ctx.getInitParameter("password");
            String query=String.format("Select * from students WHERE email = '"+id+"' ");

            Class.forName("org.postgresql.Driver");

            try (Connection con = DriverManager.getConnection(url,username,password) ){
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                if(rs.next()){
                    req.setAttribute("name",rs.getString(2));
                    req.setAttribute("email",rs.getString(3));
                    req.setAttribute("phone",rs.getString(4));
                    req.setAttribute("course",rs.getString(5));
                    req.setAttribute("year_of_study",String.valueOf(rs.getInt(6))) ;

                    req.getRequestDispatcher("update.jsp").forward(req,resp);
                }
                else{
                    resp.getWriter().write("No record found to update.");
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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(id);

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
                    if(!rs.next()) {
                        req.setAttribute("errorMessage", "Student not exists :(");
                        req.getRequestDispatcher("update.jsp").forward(req, resp);
                        return;
                    }

                    //Actual Insertion is here
                    PreparedStatement pstm = con.prepareStatement("UPDATE students set name = ? , phone = ? , course = ? , year_of_study = ? WHERE email=? ");

                    pstm.setString(1, name);
                    pstm.setString(2, phone);
                    pstm.setString(3, course);
                    pstm.setInt(4, Integer.parseInt(year_of_study));
                    pstm.setString(5,email);

                    int updatedRaw = pstm.executeUpdate();
                    if (updatedRaw > 0) {
//                        req.setAttribute("errorMessage", "Data Updated :) ");
//                        req.getRequestDispatcher("update.jsp").forward(req, resp);

                        resp.sendRedirect("Dashboard");

                    } else {
                        req.setAttribute("errorMessage", "Something Went Wrong, Try Again! ");
                        req.getRequestDispatcher("Update.jsp").forward(req, resp);
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
            req.getRequestDispatcher("Update.jsp").forward(req,resp);
        }
    }
}
