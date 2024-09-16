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
import org.atharva.dao.UserDao;

@WebServlet("/user-login")
public class LoginServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public LoginServelet() {
     
       
    }

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			String email=request.getParameter("login-email");
			String password=request.getParameter("user-password");
		UserDao udao = new UserDao(DbCon.getConneciton());
		 User user = udao.userLogin(email, password);
		 if(user!=null) {
			 request.getSession().setAttribute("auth", user);
			 response.sendRedirect("index.jsp");
		 }
		 else {
			 out.print("User login not sucessfull");
		 }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
