<!--doubt err label for invalid email or pass cant be set-->
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
            if (email != null && password != null) {
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/myproject", "root", "system");
                Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet rs = stmt.executeQuery("select * from merchant where email='" + email + "' and password ='" + password + "'");
               
                if (rs.next()) {
                    session.setAttribute("email", email);
                    response.sendRedirect("dashboard.jsp");
                } else {
        %>
        <script>
            alert("invalid email and password combination");
        </script>
        <%
                }
            }
        %>


        <h1 class='text-center'>Merchant Login</h1>


        <div class="frame">
            <form class="form-vertical" action ='#' method="post">

                <div class="form-group">
                    <label id="err"></label>
                    <label for='email'>Enter the Email</label>
                    <input type="email" class="form-control" id='email' name="email"/>
                </div>
                <div class="form-group">
                    <label for='password'>Enter the password</label>
                    <input type="password" class="form-control" id='password' name="password"/>
                </div>
                <input type="submit" class="btn btn-primary btn-lg" value="Login"/><br>
                <label>Don't have a merchant account.<a href='sign_up.jsp'>Click here</a></label>
            </form>
        </div>
    </body>
</html>
