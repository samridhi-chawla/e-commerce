
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="css/css.css" rel="stylesheet" type="text/css"/>
        <script src="js/bootstrap.min.js" type="text/javascript"></script>
        <title>JSP Page</title>
    </head>
    <body class='container'>
        <div class="frame">
            <form action="check_admin_login.jsp" class="form-vertical">
                <h1>Admin Login</h1>
                <%
                    String msg = request.getParameter("msg");
                    if (msg != null) {
                %>
                <label style="color: red"><%= msg%></label>
                <%
                    }

                %>
                <div class="form-group">
                    <label for ="username"> Enter Username</label>
                <input  type="text" name="username" class="form-control" required /><br>
                </div>
                <div class="form-group">
                    <label for="password">
                        Enter Password</label>
                <input type="password" class="form-control" name="pass" required /><br>
                </div>
                <input class="btn btn-primary btn-lg text-center" type="submit" value="Login" /><br>
                
                
                <a href="recover_admin_password.jsp" target="_blank">Recover password</a><br>


            </form>    

        </div>

    </body>
</html>
