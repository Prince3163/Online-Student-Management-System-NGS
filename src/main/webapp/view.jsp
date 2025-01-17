<%@ page import="java.util.ArrayList" %>
<html>

<body>

<%
    if (session == null && session.getAttribute("data")==null ) {
        response.sendRedirect("login.jsp");
        return;
    }
    ArrayList<String[]> data = (ArrayList<String[]>) session.getAttribute("data");

%>

<h1>View Data</h1>
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
            <form action="UpdateServlet" method="post" style="display:inline;">
                <input type="hidden" name="id" value="<%= row[1] %>">
                <input type="submit" style = "background-color:blue ; color:white" value="Update">
            </form>
            <form action="DeleteServlet" method="post" style="display:inline;">
                <input type="hidden" name="id" value="<%= row[1] %>">
                <input type="submit" style = "background-color:red ; color:white" value="Delete">
            </form>
        </td>
    </tr>
    <% } %>
</table>


</body>
</html>