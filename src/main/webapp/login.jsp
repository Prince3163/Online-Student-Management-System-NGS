<html>
<head>
    <style>
        body {
            font-family: Arial, sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .login-container {
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 4px;
            width: 300px;
            text-align: center;
        }
        .login-container h1 {
            margin-bottom: 20px;
        }
        .login-container input[type="text"],
        .login-container input[type="password"] {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        .login-container input[type="submit"] {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
            cursor: pointer;
        }
        .error-message {
            color: red;
            margin-top: 10px;
        }
    </style>
</head>
<body>


    <div class="login-container">
        <h1>Login Here</h1>
        <form action="Login" method="post">
            <input type="text" name="username" placeholder="Username..." required >
            <br>
            <input type="password" name="password" placeholder="Password..." required >
            <br>
            <input type="submit" name="submit">
        </form>
    </div>

    <%
        String errorMessage = (String) request.getAttribute("errorMessage");
        if (errorMessage != null) {
        %>
            <p class="error-message" > <%=errorMessage %></p>
        <%
        }
    %>

</body>
</html>