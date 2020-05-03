import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import vmm.cart_item;
public class alter_qty_cart extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            int pos=Integer.parseInt(request.getParameter("pos"));
            String qty=request.getParameter("qty");
            HttpSession session=request.getSession();
            ArrayList<cart_item> al_cart=(ArrayList<cart_item>)session.getAttribute("al_cart");
            al_cart.get(pos).qty=qty;
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
