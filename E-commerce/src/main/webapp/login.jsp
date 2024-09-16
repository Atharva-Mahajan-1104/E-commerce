<%@page import="org.atharva.connection.DbCon"%>
<%@page import="org.atharva.Model.*"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%
User auth = (User) request.getSession().getAttribute("auth");
if (auth != null) {
    request.setAttribute("auth", auth);
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Page</title>
<%@ include file="includes/header.jsp"%>
<style>
    body {
        background-color: #121212; /* Dark background */
        color: #e0e0e0; /* Light grey text */
    }
    .card {
        background-color: #1e1e1e; /* Slightly lighter dark shade for cards */
        color: #ffffff; /* White text for better readability */
        border: none;
    }
    .form-control {
        background-color: #2C2C2E; /* Even darker shade for form inputs */
        color: #e0e0e0; /* Light grey text */
        border: 1px solid #5e5e5e; /* Slightly lighter grey for border */
    }
    .btn-primary {
        background-color: #556272; /* Subdued blue for a professional look */
        color: #ffffff; /* White text for contrast */
        border: none;
        padding: 10px 20px;
        font-size: 1rem;
        transition: background-color 0.3s ease;
        border-radius: 8px;
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2); /* Subtle shadow for depth */
    }
    .btn-primary:hover {
        background-color: #6b7a8f; /* Slightly lighter blue on hover */
        box-shadow: 0 2px 8px rgba(0, 0, 0, 0.3); /* Slightly more pronounced shadow on hover */
    }
    a {
        color: #bb86fc; /* Soft purple, good for links on a dark background */
    }
    a:hover {
        color: #3700B3; /* Deeper purple when hovering over links */
    }

    /* Static Neon Color for User Login Text */
    .card-header {
        text-align: center;
        font-weight: bold;
        color: #bb86fc; /* Neon purple without the glow */
    }
</style>


</head>
<body>
    <%@ include file="includes/navbar.jsp"%>
    <div class="container" style="padding-top: 20px;">
        <div class="card w-50 mx-auto my-5">
            <div class="card-header text-center">User Login</div>
            <div class="card-body">
                <form action="user-login" method="post">
                    <div class="form-group">
                        <label>Email-address</label>
                        <input type="email" name="login-email" class="form-control" placeholder="Enter your email Id">
                    </div>

                    <div class="form-group">
                        <label>Password</label>
                        <input type="password" name="user-password" class="form-control" placeholder="Enter your password">
                    </div>

                    <div class="text-center">
                        <button type="submit" class="btn btn-primary">Login</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <%@ include file="includes/footer.jsp"%>
</body>
</html>
