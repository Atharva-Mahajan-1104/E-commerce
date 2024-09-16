package org.atharva.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.atharva.*;
import org.atharva.Model.User;

public class UserDao {
private Connection con;
private String querry;
private PreparedStatement pst;
private ResultSet rs;
public UserDao(Connection con) {
	
	this.con = con;
}
public User userLogin(String email,String password) {
	User user = null;
	try {
		querry=("select * from users where email=? and password=?");
		pst= this.con.prepareStatement(querry);	
		pst.setString(1, email);
		pst.setString(2, password);
		rs=pst.executeQuery();
		if(rs.next()) {
			user = new User();
        	user.setId(rs.getInt("id"));
        	user.setName(rs.getString("name"));
        	user.setEmail(rs.getString("email"));
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	 return user;
}

}
