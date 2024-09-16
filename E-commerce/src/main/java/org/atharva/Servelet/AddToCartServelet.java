package org.atharva.Servelet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.atharva.Model.Cart;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;


public class AddToCartServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	response.setContentType("text/html;charset=UTF-8");
	try(PrintWriter out = response.getWriter()){
		ArrayList<Cart> cartList = new ArrayList<>();
		int id= Integer.parseInt(request.getParameter("id"));
		Cart cart = new Cart();
		cart.setId(id);
		cart.setQuantity(1);
		
		
		HttpSession session = request.getSession();
		
		@SuppressWarnings("unchecked")
		ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
		if(cart_list==null) {
			cartList.add(cart);
			session.setAttribute("cart-list", cartList);
			response.sendRedirect("index.jsp");
		}
		else {
			cartList =cart_list;
			Boolean exist = false;
					for(Cart c : cartList) {
						if(c.getId()==id) {
							exist=true;
							out.println("<h3 style='color:crimson; text-align: center'>Item Already in Cart. <a href='cart.jsp'>GO to Cart Page</a></h3>");
		
						}
						}
					if(!exist) {
						cartList.add(cart);
						response.sendRedirect("index.jsp");
					}
		}
		
	}catch (NumberFormatException e) {
        // Handle the exception if the id is not a valid integer
        response.getWriter().print("Invalid product ID");
    }
	}

}
