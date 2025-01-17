<html>
<head>
    <title>
        Update-Student-Details
    </title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>

<%
//Pending--> if null then make it null

String name = (String) request.getAttribute("name");
String email = (String) request.getAttribute("email");
String phone = (String) request.getAttribute("phone");
String course = (String) request.getAttribute("course");
String year_of_study =(String) request.getAttribute("year_of_study");
%>

   <div class="form-container">

    <h2>Update Student details</h2>

    <form action="UpdateServlet" method="post">
        <label class="label" for="name">Name</label>
        <input type="text" class="inputField" value=<%= name %> name="name"  required>
        <br>
        <br>
        <label class="label" for="email">Email</label>
        <input type="email"  class="inputField" value=<%= email %> name="email" required >
        <br>
        <br>
        <label class="label" for="phone">Phone</label>
        <input type="text" class="inputField" value=<%= phone %> name="phone" required>
        <br>
        <br>
        <label class="label" for="course">Course</label>
        <input type="text" class="inputField" value=<%= course %> name="course" required>
        <br>
        <br>
        <label class="label" for="year_of_study">Year Of Study</label>
        <input type="number" class="inputField" value=<%= year_of_study %> name="Year_of_Study" required>
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