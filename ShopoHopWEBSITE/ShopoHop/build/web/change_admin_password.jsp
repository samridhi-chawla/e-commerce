<%
    String username = (String) session.getAttribute("username");
    if (username == null) {
        response.sendRedirect("admin_login.jsp?msg=Please Login TO perform This Action");
    } else {

%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <script src="js/bootstrap.min.js" type="text/javascript"></script>
        <script type="text/javascript">
            function go()
            {
                var pass = document.getElementById("pass").value;
                var a = document.getElementById("t1").value;
                var b = document.getElementById("t2").value;
                if (a != b)
                {
                    alert("please confirm your new password");
                    return false;
                } else
                {
                    return true;
                }

            }

        </script>
    </head>
    <body>
        <jsp:include page="admin_nav.jsp"/>
        <div class="container">
            <div class="jumbotron">
                <h1>Change Password</h1>
            </div>
            <form style="width: 70%;" class="form-vertical" action="change_admin_password2.jsp" method="get">
                <div class="form-group">
                    <label>Enter old password</label>
                    <input class="form-control" type="password" id="pass" name="old" required /><br> 
                </div>
                <div class="form-group">
                    <label>Enter new password</label>
                    <input class="form-control" type="password" id="t1" name="new" required /><br>
                </div>
                <div class="form-group"> 
                    <label>confirm password</label>
                    <input class="form-control" type="password" id="t2" required /><br>
                </div>
                <input class="btn btn-lg btn-primary" type="submit" value="submit" onclick="return go()" /><br>
                <%                   String msg = request.getParameter("msg");
                    if (msg != null) {
                %>
                <label style="color: red"><%= msg%></label>
                <%
                    }

                %>

            </form>
        </div>
    </body>
</html>
<%}%>