<html>
<body>
    <h1>Login Here</h1>

    <form action="Login" method="post">
        <input type="text" name="username" placeholder="Username..." required >
        <br>
        <input type="password" name="password" placeholder="Password..." required >
        <br>
        <input type="submit" name="submit">
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