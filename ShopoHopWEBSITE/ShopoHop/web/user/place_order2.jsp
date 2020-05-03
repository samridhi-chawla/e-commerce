<%@page import="java.util.ArrayList"%>
<%@page import="vmm.cart_item"%>

<%
    ArrayList<cart_item> al_cart = (ArrayList<cart_item>) session.getAttribute("al_cart");
    if (al_cart == null || al_cart.size() == 0) {
        response.sendRedirect("view_cart.jsp");
    }
    String mode_of_payment = request.getParameter("mode");
    if (mode_of_payment.equals("cod")) {
        session.setAttribute("payment_mode", "cod");
        response.sendRedirect("before_final.jsp");
    } else {
        session.setAttribute("payment_mode", "digital");
        response.sendRedirect("payment.jsp");
    }


%>