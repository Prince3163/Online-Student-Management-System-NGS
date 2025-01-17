<html>
<head>
    <title>
        Update Student
    </title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>

<%
String name = (String) request.getAttribute("name");
String email = (String) request.getAttribute("email");
String phone = (String) request.getAttribute("phone");
String course = (String) request.getAttribute("course");
String year_of_study =(String) request.getAttribute("year_of_study");
%>




   <div class="form-container">

    <h1>Update Student</h1>

    <form action="UpdateServlet" method="post">
        <input type="text" class="inputField" value=<%= name %> name="name" placeholder="Name..." required>
        <br>
        <br>
        <input type="email"  class="inputField" value=<%= email %> name="email" placeholder="Email..." required readonly>
        <br>
        <br>
        <input type="text" class="inputField" value=<%= phone %> name="phone" placeholder="Phone..." required>
        <br>
        <br>
        <input type="text" class="inputField" value=<%= course %> name="course" placeholder="Course..." required>
        <br>
        <br>
        <input type="number" class="inputField" value=<%= year_of_study %> name="Year_of_Study" placeholder="Year-of-Study..." required>
        <br>
        <br>
        <input type="submit" class="btn" value="Update Student" style="background-color: blue; color: white;">
    </form>

    <%
            String errorMessage = (String) request.getAttribute("errorMessage");
            if (errorMessage != null) {
            %>
                <p class="error-message" > <%=errorMessage %></p>
            <%
            }
    %>

</div>
</body>
</html>