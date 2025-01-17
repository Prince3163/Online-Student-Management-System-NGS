<%@ page import="java.util.ArrayList" %>
<html>
    <title>
        Dashboard
    </title>
<head>
<style>
    body {
        font-family: Arial, sans-serif;
        display: flex;
        flex-direction: column;
        align-items: center;
        margin: 0;
        padding: 20px;
        background-color: #f4f4f4;
    }
    .header {
        width: 100%;
        display: flex;
        justify-content: space-between;
        align-items: center;
        background-color: #333;
        color: white;
        padding: 10px 20px;
    }
    .header h1 {
        margin: 0;
    }
    .header a {
        color: white;
        text-decoration: none;
    }
    .container {
        width: 100%;
        max-width: 800px;
        background-color: white;
        padding: 20px;
        border-radius: 8px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        margin-top: 20px;
    }

    table {
        width: 100%;
        border-collapse: collapse;
        margin-bottom: 20px;
    }
    table, th, td {
        border: 1px solid #ddd;
    }
    th, td {
        padding: 12px;
        text-align: left;
    }
    th {
        background-color: #f2f2f2;
    }
    .btn {
        display: inline-block;
        padding: 10px 20px;
        margin: 5px 0;
        border: none;
        border-radius: 4px;
        cursor: pointer;
        text-align: center;
        text-decoration: none;
        color: white;
    }
    .btn-blue {
        background-color: blue;
    }
    .btn-red {
        background-color: red;
    }
    .btn-green {
        background-color: green;
    }
</style>
</head>

<body>
    <div class="header">
        <h1>Dashboard</h1>
        <a href="Logout" class="btn btn-red">Logout</a>
    </div>

<%
    if (session == null || session.getAttribute("data") == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    ArrayList<String[]> data = (ArrayList<String[]>) session.getAttribute("data");
%>

<div class="container">
    <h2>View Data</h2>
    <table>
        <tr>
            <th>Name</th>
            <th>Email</th>
            <th>Phone No.</th>
            <th>Course</th>
            <th>Year of Study</th>
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
                    <input type="submit" class="btn btn-blue" value="Update">
                </form>
                <form action="DeleteServlet" method="get" style="display:inline;">
                    <input type="hidden" name="id" value="<%= row[1] %>">
                    <input type="submit" class="btn btn-red" value="Delete">
                </form>
            </td>
        </tr>
        <% } %>
    </table>

    <form action="add.jsp" >
        <input type="submit" class="btn btn-green" value="Add Student">
    </form>
</div>

</body>
</html>