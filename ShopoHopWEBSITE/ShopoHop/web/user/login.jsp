<%@page import="java.sql.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="../css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="../css/css.css" rel="stylesheet" type="text/css"/>
        <script src="../js/bootstrap.min.js" type="text/javascript"></script>
    </head>
    <body class="container">
        <%
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            if (email != null && password != null) 
            {
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/myproject", "root", "system");
                Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet rs = stmt.executeQuery("select * from users where email='" + email + "' and password='"+password+"'");
                if (rs.next())
                {
                    session.setAttribute("email", email);
                    response.sendRedirect("home.jsp");
                } 
                else 
                {
        %>
        <script>
            document.getElementById("login_error").innerHTML = "Invalid email or Password.";
        </script>    
        <%
                }
            }
        %>

       
            <h1 class='text-center'>User Login</h1>
      

        <div class="frame">
            <form class="form-vertical" action ='#' method="post">
                <label id='login_error' style="color: red; size: 1em; font-style: italic"></label>
                <div class="form-group">
                    <label for='email'>Enter Email</label>
                    <input type="text" class="form-control" id='email' name="email"/>
                </div>
                <div class="form-group">
                    <label for='password'>Enter the username</label>
                    <input type="password" class="form-control" id='password' name="password"/>
                </div>
                <input type="submit" class="btn btn-primary btn-lg" value="Login"/>
            </form>
            <label>Don't have a user account? <a href='sign_up.jsp'>Click here.</a></label>
        </div>
    </body>
</html>
