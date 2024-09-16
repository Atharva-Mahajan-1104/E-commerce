<%@page import="java.text.DecimalFormat"%>
<%@page import="org.atharva.dao.ProductDao"%>
<%@page import="org.atharva.connection.DbCon"%>
<%@page import="org.atharva.Model.*"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%
DecimalFormat dcf= new DecimalFormat("#.##");
request.setAttribute("dcf", dcf);
ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
List<Cart> cartProduct = null;
if (cart_list != null) {
    ProductDao pDao = new ProductDao(DbCon.getConneciton());
    cartProduct = pDao.getcartproducts(cart_list);
    double total = pDao.getitemtotal(cart_list);
    request.setAttribute("total", total);
    request.setAttribute("cart_list", cart_list);
}
%>
<!DOCTYPE html>
<html>
<head>
<%@include file="includes/header.jsp"%>
<title>E-Commerce Cart</title>
<style type="text/css">
    body {
        background-color: #121212; /* Dark background */
        color: #e0e0e0; /* Light grey text */
    }
    .table {
        background-color: #1e1e1e; /* Slightly lighter dark shade for cards */
        color: #ffffff; /* White text for better readability */
    }
    .table thead th {
        color: #e0e0e0; /* Light grey text for table headers */
    }
    .table tbody td {
        vertical-align: middle;
        color: #e0e0e0; /* Light grey text for table data */
    }
    a {
        color: #bb86fc; /* A soft purple, good for links on a dark background */
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
    .btn-primary {
        box-shadow: 0 2px 2px 0 rgba(0, 0, 0, 0.14), 0 3px 1px -2px rgba(0, 0, 0, 0.2), 0 1px 5px 0 rgba(0, 0, 0, 0.12); /* Subtle shadow for depth */
    }
    .btn-incre, .btn-decre {
        box-shadow: none;
        font-size: 25px;
        color: #ffffff; /* Adjust color to fit the theme */
    }
</style>

</head>
<body>
    <%@include file="/includes/navbar.jsp"%>

    <div class="container my-3">
        <div class="d-flex py-3"><h3>Total Price: $ ${(total>0)?dcf.format(total):0} </h3> <a class="mx-3 btn btn-primary" href="cart-check-out">Check Out</a></div>
        <table class="table">
            <thead>
                <tr>
                    <th scope="col">Name</th>
                    <th scope="col">Category</th>
                    <th scope="col">Price</th>
                    <th scope="col">Buy Now</th>
                    <th scope="col">Cancel</th>
                </tr>
            </thead>
            <tbody>
                <%
                if (cart_list != null) {
                    for (Cart c : cartProduct) {
                %>
                <tr>
                    <td><%=c.getName()%></td>
                    <td><%=c.getCategory()%></td>
                    <td>$<%= dcf.format(c.getPrice()) %></td>
                    <td>
                        <form action="order-now" method="post" class="form-inline">
                            <input type="hidden" name="id" value="<%= c.getId()%>" class="form-input">
                            <div class="form-group d-flex justify-content-between">
                                <a class="btn btn-sm btn-incre" href="quantity-inc-dec?action=inc&id=<%= c.getId() %>">&#43;</a>
                                <input type="text" name="quantity" class="form-control" value="<%=c.getQuantity()%>" readonly>
                                <a class="btn btn-sm btn-decre mr-4" href="quantity-inc-dec?action=dec&id=<%= c.getId() %>">&#8722;</a>
                            </div>
                            <button type="submit" class="btn btn-primary btn-sm">Buy</button>
                        </form>
                    </td>
                    <td><a href="remove-from-cart?id=<%=c.getId() %>" class="btn btn-sm btn-danger">Remove</a></td>
                </tr>
                <%
                    }
                }
                %>
            </tbody>
        </table>
    </div>

    <%@include file="/includes/footer.jsp"%>
</body>
</html>
