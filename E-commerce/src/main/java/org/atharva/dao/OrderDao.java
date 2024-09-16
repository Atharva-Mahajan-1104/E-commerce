package org.atharva.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.atharva.Model.Order;
import org.atharva.Model.Product;

public class OrderDao {
	private Connection con;
	private String querry;
	private PreparedStatement pst;
	private ResultSet rs;
	public OrderDao(Connection con) {
		this.con = con;
	}
	public boolean insertorder(Order model) {
		 boolean result = false;
	        try {
	            querry = "insert into orders (p_id, u_id, o_quantity, o_date) values(?,?,?,?)";
	            pst = this.con.prepareStatement(querry);
	            pst.setInt(1, model.getId());
	            pst.setInt(2, model.getUid());
	            pst.setInt(3, model.getQuantity());
	            pst.setString(4, model.getDate());
	            pst.executeUpdate();
	            result = true;
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	        return result;
	    }
		
		public List<Order> userOrders(int id){
			List<Order> list = new ArrayList<Order>();
			try {
			querry="select * from orders where u_id=? order by orders.o_id desc";
			pst=this.con.prepareStatement(querry);
			pst.setInt(1, id);
			rs=pst.executeQuery();
			while(rs.next()) {
				
				Order order = new Order();
				ProductDao pDao = new ProductDao(this.con);
				int pId = rs.getInt("p_id");
				
				Product product =  pDao.getSingleProduct(pId);
				order.setId(pId);
				order.setOrderId(rs.getInt("o_id"));
				order.setName(product.getName());
				order.setPrice(product.getPrice() * rs.getInt("o_quantity"));
				order.setCategory(product.getCategory());
				order.setQuantity(rs.getInt("o_quantity"));
				order.setDate(rs.getString("o_date"));
				list.add(order);
			}
			
			
			}catch (Exception e) {
				e.getMessage();
				e.printStackTrace();
			}
			
			return list;
		}
		public void cancelOrder(int id) throws SQLException {
			querry = "delete from orders where o_id=?";
			pst = this.con.prepareStatement(querry);
			pst.setInt(1, id);
			pst.execute();
		}
	}

