<html>
<head>
    <title>
        Add Student
    </title>
</head>
<body>
    <h1>Add Student</h1>

    <form action="AddServlet" method="post">
        <input type="text" name="name" placeholder="Enter Name" required>
        <br>
        <br>
        <input type="email" name="email" placeholder="Enter Email" required>
        <br>
        <br>
        <input type="text" name="phone" placeholder="Enter Phone" required>
        <br>
        <br>
        <input type="text" name="course" placeholder="Enter Course" required>
        <br>
        <br>
        <input type="number"  name="Year_of_Study" placeholder="Enter Year-of-Study" required>
        <br>
        <br>
        <input type="submit" value="Add Student" style="background-color: green; color: white;">
    </form>

    <%
            String errorMessage = (String) request.getAttribute("errorMessage");
            if (errorMessage != null) {
            %>
                <p > <%=errorMessage %></p>
            <%
            }
    %>

</body>
</html>