
<%@page import="vmm.cart_item"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="../css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="../css/css.css" rel="stylesheet" type="text/css"/>
        <script src="../js/jquery-3.2.0.min.js" type="text/javascript"></script>
        <script src="../js/bootstrap.min.js" type="text/javascript"></script>

    </head>
    <body>
        <jsp:include page= "../includes/header.jsp" />
        <div class="container" style="margin-top: 3%">
            <h1>Thank you for Shopping with us</h1>
            <a href="home.jsp"><div class="btn btn-lg btn-primary">Continue Shopping</div></a>

        </div>

    </body>
</html>
