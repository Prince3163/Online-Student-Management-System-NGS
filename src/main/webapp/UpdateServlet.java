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

@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {


    private String emailid; //We get this from click on specific raw, this is used to perform updation with database.

    //Followings are all attributes of form or column names.
    private String name;
    private String email;
    private String phone;
    private String course;
    private String year_of_study;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        emailid = req.getParameter("id");
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
            String query=String.format("Select * from students WHERE email = '"+emailid+"' ");

            Class.forName("org.postgresql.Driver");

            try (Connection con = DriverManager.getConnection(url,username,password) ){
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                if(rs.next()){
//                    req.setAttribute("name",rs.getString(2));
//                    req.setAttribute("email",rs.getString(3));
//                    req.setAttribute("phone",rs.getString(4));
//                    req.setAttribute("course",rs.getString(5));
//                    req.setAttribute("year_of_study",String.valueOf(rs.getInt(6))) ;

                    name=rs.getString("name");
                    email=rs.getString("email");
                    phone=rs.getString("phone");
                    course=rs.getString("course");
                    year_of_study=rs.getString("year_of_study");

                    goToUpdateJSP(req,resp);

//                    req.getRequestDispatcher("update.jsp").forward(req,resp);
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
        name = req.getParameter("name");
        email = req.getParameter("email");
        phone = req.getParameter("phone");
        course = req.getParameter("course");
        year_of_study = req.getParameter("Year_of_Study");

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
                    ResultSet rs = stmt.executeQuery("SELECT email FROM students WHERE email ='"+email+"' AND email != '"+emailid+"' ");
                    if( rs.next() ) {
                        req.setAttribute("errorMessage", "Email alredy exists :(");
                        goToUpdateJSP(req,resp);
//                        req.setAttribute("name", name);
//                        req.setAttribute("email", email);
//                        req.setAttribute("phone", phone);
//                        req.setAttribute("course", course);
//                        req.setAttribute("year_of_study", year_of_study);
//
//                        req.getRequestDispatcher("update.jsp").forward(req, resp);

                        return;
                    }

                    //Actual Insertion is here
                    PreparedStatement pstm = con.prepareStatement("UPDATE students set name = ?,email = ? , phone = ? , course = ? , year_of_study = ? WHERE email= ? ");

                    pstm.setString(1, name);
                    pstm.setString(2,email);
                    pstm.setString(3, phone);
                    pstm.setString(4, course);
                    pstm.setInt(5, Integer.parseInt(year_of_study));
                    pstm.setString(6,emailid);

                    int updatedRaw = pstm.executeUpdate();
                    if (updatedRaw > 0) {
                        resp.sendRedirect("Dashboard");
                    } else {

//                        req.setAttribute("name", name);
//                        req.setAttribute("email", email);
//                        req.setAttribute("phone", phone);
//                        req.setAttribute("course", course);
//                        req.setAttribute("year_of_study", year_of_study);
//

                        req.setAttribute("errorMessage", "Something Went Wrong, Try Again! ");
                        goToUpdateJSP(req,resp);
                        //req.getRequestDispatcher("update.jsp").forward(req, resp);
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
            goToUpdateJSP(req,resp);
//            req.setAttribute("name", name);
//            req.setAttribute("email", email);
//            req.setAttribute("phone", phone);
//            req.setAttribute("course", course);
//            req.setAttribute("year_of_study", year_of_study);
//
//            req.getRequestDispatcher("update.jsp").forward(req,resp);
        }
    }

    void goToUpdateJSP(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        req.setAttribute("name", name);
        req.setAttribute("email", email);
        req.setAttribute("phone", phone);
        req.setAttribute("course", course);

        req.setAttribute("year_of_study", year_of_study);
        req.setAttribute("errorMessage", (String) req.getAttribute("errorMessage"));

        req.getRequestDispatcher("update.jsp").forward(req,resp);
    }


}