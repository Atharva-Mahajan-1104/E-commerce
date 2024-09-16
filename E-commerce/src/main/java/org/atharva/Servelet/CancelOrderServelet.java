package org.atharva.Servelet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import org.atharva.Model.User;
import org.atharva.connection.DbCon;
import org.atharva.dao.OrderDao;


public class CancelOrderServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public CancelOrderServelet() {
       
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
			try(PrintWriter out = response.getWriter()){
				String id = request.getParameter("id");
				if(id!=null) {
					OrderDao orderDao = new OrderDao(DbCon.getConneciton());
					orderDao.cancelOrder(Integer.parseInt(id));
				}
					response.sendRedirect("order.jsp");
				
				
				
				
			}catch (Exception e) {
				e.printStackTrace();
			}
		}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
