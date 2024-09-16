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


public class RemoveFromCartServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public RemoveFromCartServelet() {
       
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try(PrintWriter out = response.getWriter()){
			String id = request.getParameter("id");
			if(id!=null) {
				ArrayList<Cart> cartlist = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
				if(cartlist != null) {
					for(Cart item : cartlist) {
						if(item.getId()==Integer.parseInt(id)) {
							cartlist.remove(cartlist.indexOf(item));
							break;
						}
					}
					response.sendRedirect("cart.jsp");
				}
			}else {
				response.sendRedirect("cart.jsp");
			}
			
		}
	}

}
