<%@page import="org.atharva.connection.DbCon"%>
<%@page import="org.atharva.Model.*"%>
<%@page import="java.util.*"%>
<%@page import="org.atharva.dao.OrderDao"%>
<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
DecimalFormat dcf = new DecimalFormat("#.##");
request.setAttribute("dcf", dcf);
User auth = (User) request.getSession().getAttribute("auth");
List<Order> orders = null;
if (auth != null) {
    request.setAttribute("person", auth);
    OrderDao orderDao  = new OrderDao(DbCon.getConneciton());
    orders = orderDao.userOrders(auth.getId());
}else{
    response.sendRedirect("login.jsp");
}
ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
if (cart_list != null) {
    request.setAttribute("cart_list", cart_list);
}
%>
<!DOCTYPE html>
<html>
<head>
<%@include file="includes/header.jsp"%>
<title>User Orders</title>
<style>
    body {
        background-color: #121212; /* Dark background */
        color: #e0e0e0; /* Light grey text */
    }
    .card-header, .table {
        background-color: #1e1e1e; /* Slightly lighter dark shade for cards and tables */
        color: #ffffff; /* White text for better readability */
    }
    a {
        color: #bb86fc; /* A soft purple for links */
    }
    a:hover {
        color: #3700B3; /* A deeper purple when hovering over links */
    }
    .btn {
        background-color: #556272; /* Subdued blue for buttons */
        color: #ffffff; /* White text */
        border: none;
    }
    .btn:hover {
        background-color: #6b7a8f; /* Lighter blue for hover effect */
    }
    .btn-danger {
        background-color: #D32F2F; /* Red for danger buttons */
    }
    .btn-danger:hover {
        background-color: #F44336; /* Lighter red for hover effect */
    }
</style>
</head>
<body>
    <%@include file="/includes/navbar.jsp"%>
    <div class="container">
        <div class="card-header my-3">All Orders</div>
        <table class="table">
            <thead>
                <tr>
                    <th scope="col">Date</th>
                    <th scope="col">Name</th>
                    <th scope="col">Category</th>
                    <th scope="col">Quantity</th>
                    <th scope="col">Price</th>
                    <th scope="col">Cancel</th>
                </tr>
            </thead>
            <tbody>
            
             
            <%
            if(orders != null){
                for(Order o:orders){%>
                    <tr>
                        <td><%=o.getDate() %></td>
                        <td><%=o.getName() %></td>
                        <td><%=o.getCategory() %></td>
                        <td><%=o.getQuantity() %></td>
                        <td>$<%=dcf.format(o.getPrice()) %></td>
                        <td><a class="btn btn-sm btn-danger" href="cancel-order?id=<%=o.getOrderId()%>">Cancel Order</a></td>
                    </tr>
                <%}
            }
            %>
            
            
            </tbody>
        </table>
    </div>
    <%@include file="/includes/footer.jsp"%>
</body>
</html>
