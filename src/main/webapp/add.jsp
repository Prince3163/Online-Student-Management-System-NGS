<html>
<head>
    <title>
        Add-New-Student
    </title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>

<%
    if (session == null || session.getAttribute("data") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

   <div class="form-container">

    <h1>Add Student</h1>

    <form action="AddServlet" method="post">
        <input type="text" class="inputField" name="name" placeholder="Name..." required>
        <br>
        <br>
        <input type="email" class="inputField" name="email" placeholder="Email..." required>
        <br>
        <br>
        <input type="text" class="inputField" name="phone" placeholder="Phone..." required>
        <br>
        <br>
        <input type="text" class="inputField" name="course" placeholder="Course..." required>
        <br>
        <br>
        <input type="number" class="inputField" name="Year_of_Study" placeholder="Year-of-Study..." required>
        <br>
        <br>
        <input type="submit" class="btn" value="Add Student" style="background-color: green; color: white;">
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