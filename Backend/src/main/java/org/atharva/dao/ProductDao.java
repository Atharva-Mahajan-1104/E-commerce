package org.atharva.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.atharva.Model.Cart;
import org.atharva.Model.Product;

public class ProductDao {
	private Connection con;
	private String querry;
	private PreparedStatement pst;
	private ResultSet rs;
	
	public ProductDao(Connection con) {
		this.con = con;
	}
	public List<Product> getallproducts() throws SQLException{
		
		List<Product> products = new ArrayList<Product>();
		try {
		querry=" SELECT * FROM ecommerce_cart.products;";
		pst=this.con.prepareStatement(querry);
		rs=pst.executeQuery();
		while(rs.next()) {
			Product row = new Product();
			row.setId(rs.getInt("id"));
			row.setName(rs.getNString("name"));
			row.setCategory(rs.getString("category"));
			row.setPrice(rs.getDouble("price"));
			row.setImage(rs.getString("image"));
		products.add(row);
		}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return products;
			
	}
public List<Cart> getcartproducts(List<Cart> cartList) {
	List<Cart> products = new ArrayList<Cart>();
	if(cartList.size()>0) {
		for(Cart item : cartList) {
		try {
		querry="SELECT * FROM ecommerce_cart.products where id=?";
		pst=this.con.prepareStatement(querry);
		pst.setInt(1, item.getId() );
		rs=pst.executeQuery();
		while(rs.next()) {
			Cart row = new Cart();
			row.setId(rs.getInt("id"));
			row.setName(rs.getNString("name"));
			row.setCategory(rs.getString("category"));
			row.setPrice(rs.getDouble("price") * item.getQuantity());
			row.setImage(rs.getString("image"));
			row.setQuantity(item.getQuantity());
			products.add(row);
		}
		
		
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	}
	
	
	return products;
	
}
public double getitemtotal(List<Cart> cartList) {
	double sum =0;
	try {
	if(cartList.size()>0) {
	for(Cart item: cartList) {
		querry="SELECT price FROM ecommerce_cart.products where id=?";
	pst=this.con.prepareStatement(querry);
	pst.setInt(1, item.getId());
	rs=pst.executeQuery();
	while(rs.next() ) {
		sum+=rs.getDouble("price") * item.getQuantity();
	}
	}
	}
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	
	return sum;
}

public Product getSingleProduct(int id) {
	Product product = null;
	try {
	querry="select * from products where id=?";
	 pst=this.con.prepareStatement(querry);
	 pst.setInt(1, id);
	 rs=pst.executeQuery();
	 while(rs.next()) {
		 product = new Product();
		product.setId(rs.getInt("id"));
		 product.setName(rs.getString("name"));
		 product.setCategory(rs.getString("category"));
		 product.setPrice(rs.getDouble("price"));
		 product.setImage(rs.getString("image"));
	 }
	 
	 
	 
	}catch (Exception e) {
		e.printStackTrace();
	}
	
	
	
	return product;
}




}
