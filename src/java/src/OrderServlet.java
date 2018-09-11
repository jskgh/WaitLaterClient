package src;

import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class OrderServlet extends HttpServlet {
  

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null)
            sb.append(line);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

        Order order = JSONConvertor.toOrder(sb.toString());
        OrderManager om = new OrderManager();
        om.addOrder(order);
        System.out.println("Order request CSV: "+ sb.toString());
        

        
        response.setStatus(HttpServletResponse.SC_OK);
        
        // Debugging
        response.getWriter().write("Order Confirmed");
        response.getWriter().flush();
        response.getWriter().close();
        
    }

}
