package org.atharva.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbCon {
private  static Connection connection = null;
public static Connection getConneciton() throws SQLException, ClassNotFoundException {
	if(connection==null) {
		 Class.forName("com.mysql.cj.jdbc.Driver");
         connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce_cart","root","atharva@1104");
         System.out.print("connected");
	}
	return connection;
}
}
