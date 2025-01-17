<html>
<head>
     <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>

    <div class="form-container">
        <h1>Login</h1>
        <form action="Login" method="post">
            <input class="inputField" type="text" name="username" placeholder="Username..." required >
            <br>
            <input class="inputField" type="password" name="password" placeholder="Password..."  required>
            <br>
            <input class="btn" type="submit" name="submit">

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