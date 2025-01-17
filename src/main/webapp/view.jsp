<%@ page import="java.util.ArrayList" %>
<html>
<head>
<style>
    .header {
        display: flex,
        justify-content: space-between,
        align-items: center
    }
    .logout {
        margin-left: auto
     }
</style>
</head>

<body>

<%
    if (session == null && session.getAttribute("data")==null ) {
        response.sendRedirect("login.jsp");
        return;
    }
    ArrayList<String[]> data = (ArrayList<String[]>) session.getAttribute("data");

%>


<div class="header">
    <h1>Dashboard</h1>
    <a class="logout" href="Logout">Logout</a>
</div>


<form action="add.jsp">
    <input type="submit" value="Add Student">
</form>


<h2>View Data</h2>

<br>
<br>
<table>
    <tr>
        <th>Name</th>
        <th>Email</th>
        <th>Phone No.</th>
        <th>Course</th>
        <th>Year_of_Study</th>
        <th>Actions</th>
    </tr>
    <% for (String[] row : data) { %>
    <tr>
        <% for (String value : row) { %>
        <td><%= value %></td>
        <% } %>
        <td>
            <form action="UpdateServlet" method="get" style="display:inline;">
                <input type="hidden" name="id" value="<%= row[1] %>">
                <input type="submit" style = "background-color:blue ; color:white" value="Update">
            </form>
            <form action="DeleteServlet" method="get" style="display:inline;">
                <input type="hidden" name="id" value="<%= row[1] %>">
                <input type="submit" style = "background-color:red ; color:white" value="Delete">
            </form>
        </td>
    </tr>
    <% } %>
</table>


</body>
</html>