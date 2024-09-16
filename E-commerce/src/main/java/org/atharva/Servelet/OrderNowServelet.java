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
import org.atharva.Model.User; // Assuming you have a User model class
import org.atharva.connection.DbCon;
import org.atharva.dao.OrderDao;

public class OrderNowServelet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public OrderNowServelet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (PrintWriter out = response.getWriter()) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date date = new java.util.Date();

            User auth = (User) request.getSession().getAttribute("auth");

            if (auth != null) {
                String productId = request.getParameter("id");
                int productQuantity = Integer.parseInt(request.getParameter("quantity"));
                if (productQuantity <= 0) {
                    productQuantity = 1;
                }
                Order orderModel = new Order();
                orderModel.setId(Integer.parseInt(productId)); // Assuming you have a setProductId method
                orderModel.setUid(auth.getId()); // Assuming your User model has an getId() method
                orderModel.setDate(formatter.format(date));
                orderModel.setQuantity(productQuantity);
                OrderDao orderDao = new OrderDao(DbCon.getConneciton());
                boolean inserted = orderDao.insertorder(orderModel); // Assuming you have an insertOrder method
                if (inserted) {
                	ArrayList<Cart> cartlist = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
    				if(cartlist != null) {
    					for(Cart item : cartlist) {
    						if(item.getId()==Integer.parseInt(productId)) {
    							cartlist.remove(cartlist.indexOf(item));
    							break;
    						}
    					}
    					
    				}
                	
                	response.sendRedirect("order.jsp");
                } else {
                    out.print("Order failed");
                }
            } else {
                response.sendRedirect("login.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
