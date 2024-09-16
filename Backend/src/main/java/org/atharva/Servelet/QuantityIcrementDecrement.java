package org.atharva.Servelet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.atharva.Model.Cart;


public class QuantityIcrementDecrement extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public QuantityIcrementDecrement() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try(PrintWriter out = response.getWriter()) {
			
			String action=request.getParameter("action");
			int id = Integer.parseInt(request.getParameter("id"));
			ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
			if(action!=null && id>=0) {
				if(action.equals("inc")) {
					for(Cart item:cart_list) {
						if(item.getId()==id) {
							int quantity= item.getQuantity();
							quantity++;
							item.setQuantity(quantity);
							response.sendRedirect("cart.jsp");
						}
					}
				}
				if(action!=null && id>=0) {
					if(action.equals("dec")) {
						for(Cart item:cart_list) {
							if(item.getId()==id && item.getQuantity()>0) {
								int quantity= item.getQuantity();
								quantity--;
								item.setQuantity(quantity);
								break;
							}
						}
						response.sendRedirect("cart.jsp");
					}
				}
			}else {
				response.sendRedirect("cart.jsp");
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}

}
