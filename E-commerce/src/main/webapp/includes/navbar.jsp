<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="org.atharva.Model.User" %>
<style>
    @keyframes neon-glow {
        0%, 100% {
            text-shadow:
                0 0 5px #bb86fc,
                0 0 10px #bb86fc,
                0 0 20px #bb86fc,
                0 0 40px #3700B3,
                0 0 80px #3700B3,
                0 0 90px #3700B3,
                0 0 100px #3700B3,
                0 0 150px #3700B3;
        }
        50% {
            text-shadow:
                0 0 5px #bb86fc,
                0 0 10px #bb86fc,
                0 0 20px #bb86fc,
                0 0 40px #3700B3,
                0 0 80px #3700B3,
                0 0 90px #3700B3,
                0 0 100px #3700B3,
                0 0 150px #3700B3,
                0 0 200px #3700B3;
        }
    }

    .neon-text {
        font-size: 1.5rem;
        color: #bb86fc; /* Fallback color */
        animation: neon-glow 2s infinite ease-in-out;
    }
</style>
<nav class="navbar navbar-expand-lg" style="background-color: #121212; border-bottom: 1px solid #333;">
    <div class="container">
        <a class="navbar-brand neon-text" href="index.jsp">Coder's Heaven</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" 
            data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
            aria-expanded="false" aria-label="Toggle navigation" style="border-color: #333;">
            <span class="navbar-toggler-icon" style="background-image: url('data:image/svg+xml;charset=utf8,%3Csvg viewBox=\'0 0 30 30\' xmlns=\'http://www.w3.org/2000/svg\'%3E%3Cpath stroke=\'rgba(187, 134, 252, 1)\' stroke-width=\'2\' stroke-linecap=\'round\' stroke-miterlimit=\'10\' d=\'M4 7h22M4 15h22M4 23h22\'/%3E%3C/svg%3E');"></span>
        </button>
    
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item"><a class="nav-link" href="index.jsp" style="color: #e0e0e0;">Home</a></li>
                <li class="nav-item"><a class="nav-link" href="cart.jsp" style="color: #e0e0e0;">Cart <span class="badge badge-danger">${cart_list.size()}</span> </a></li>
                
                <li class="nav-item"><a class="nav-link" href="order.jsp" style="color: #e0e0e0;">Orders</a></li>
                <li class="nav-item"><a class="nav-link" href="log-out" style="color: #e0e0e0;">Logout</a></li>
                
                <li class="nav-item"><a class="nav-link" href="login.jsp" style="color: #e0e0e0;">Login</a></li>
                
            </ul>
        </div>
    </div>
</nav>
