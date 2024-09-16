package org.atharva.Servelet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.atharva.Model.Cart;
import org.atharva.Model.Order;
import org.atharva.Model.User;
import org.atharva.connection.DbCon;
import org.atharva.dao.OrderDao;


public class CheckOutServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public CheckOutServelet() {
      
       
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try(PrintWriter out = response.getWriter()){
			 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	            java.util.Date date = new java.util.Date();
	            
	            User auth = (User) request.getSession().getAttribute("auth");
	            
	            ArrayList<Cart> cartlist = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
	            
	             
	            if(cartlist != null && auth != null) {
	            	
	            	for(Cart item :cartlist) {
	            		Order order = new Order();
	            		order.setId(item.getId());
	            		order.setUid(auth.getId());
	            		order.setQuantity(item.getQuantity());
	            		order.setDate(formatter.format(date));
	            	
	            	OrderDao oDao = new OrderDao(DbCon.getConneciton());
	            	   Boolean result=  oDao.insertorder(order);
	            	  if(!result) break;
	            	
	            	
	            	}
	            	
	            	cartlist.clear();
	            	response.sendRedirect("order.jsp");
	            	
	            	
	            }else {
	            	if(auth==null) {
	            		response.sendRedirect("login.jsp");
	            		
	            	}
	            	response.sendRedirect("cart.jsp");
	            	
	            }
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
