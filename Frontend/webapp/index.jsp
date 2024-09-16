<%@page import="org.atharva.dao.ProductDao"%>
<%@page import="org.atharva.connection.DbCon"%>
<%@page import="org.atharva.Model.*"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<% 
ProductDao pd = new ProductDao(DbCon.getConneciton());
List<Product> products = pd.getallproducts();

ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
if (cart_list != null) {
	request.setAttribute("cart_list", cart_list);
}
%>


<!DOCTYPE html>
<html>
<head>
<%@ include file="includes/header.jsp"%>
<title>Atharva's WebPage</title>
 <style>
        body {
            background-color: #121212; /* Dark background */
            color: #e0e0e0; /* Light grey text */
        }
        .card {
            background-color: #1e1e1e; /* Slightly lighter dark shade for cards */
            color: #ffffff; /* White text for better readability */
        }
        a {
            color: #bb86fc; /* A soft purple, good for links on a dark background */
        }
        a:hover {
            color: #3700B3; /* A deeper purple when hovering over links */
        }
        .price {
            color: #03dac6; /* A teal color for prices, adds a nice contrast */
        }
    </style>
</head>
<body>
	<%@include file="/includes/navbar.jsp"%>

	<div class="container">
		<div class="card-header my-3">All Products</div>
		<div class="row">
			<%
			if (!products.isEmpty()) {
				for (Product p : products) {
			%>
			<div class="col-md-3 my-3">
				<div class="card w-100">
					<img class="card-img-top" src="product-images/<%=p.getImage() %>"
						alt="Card image cap">
					<div class="card-body">
						<h5 class="card-title"><%=p.getName() %></h5>
						<h6 class="price">Price: $<%=p.getPrice() %></h6>
						<h6 class="category">Category: <%=p.getCategory() %></h6>
						<div class="mt-3 d-flex justify-content-between">
							<a  href="add-to-cart?quantity=1&id=<%= p.getId() %>" class= "btn btn-dark" >Add to Cart</a> 
							<a	href="order-now?quantity=1&id=<%= p.getId() %>" class="btn btn-primary">Buy Now</a>
						</div>
					</div>
				</div>
			</div>
			<%
			}
			} else {
			out.println("There is no proucts");
			}
			%>

		</div>
	</div>

	<%@include file="/includes/footer.jsp"%>
</body>
</html>