


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Payment Successful</h1>
        <h2>Thank you  for shoppping with us</h2>
        <h3>Your Order no is 1234</h3>
        <h3>Your Payment ID is <%= request.getParameter("razorpay_payment_id") %></h3>
        
    </body>
</html>
