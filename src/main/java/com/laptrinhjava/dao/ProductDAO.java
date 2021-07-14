package com.laptrinhjava.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.laptrinhjava.models.ProductModel;

public class ProductDAO extends AbstractDAO{


	public static List<ProductModel> find() {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		List<ProductModel> listProduct = new ArrayList<>();

		try {
			connection = getConnection();
			statement = connection.prepareStatement("SELECT * FROM PRODUCT");
			rs = statement.executeQuery();
			ProductModel product = null;
			while (rs.next()) {
				product = new ProductModel();
				product.setId(rs.getString("ID"));
				product.setName(rs.getString("NAME"));
				product.setQuantity(rs.getInt("QUANTITY"));
				product.setPrice(rs.getDouble("PRICE"));
				product.setImage(rs.getString("IMAGE"));
				product.setCategoryId(rs.getString("CATEGORY_ID"));
				listProduct.add(product);
			}
			return listProduct;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (statement != null) {
					statement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public static void save(ProductModel product) {
		Connection connection = null;
		PreparedStatement statement = null;

		try {
			connection = getConnection();
			connection.setAutoCommit(false);
			statement = connection.prepareStatement("INSERT INTO PRODUCT(ID, NAME, QUANTITY, PRICE, IMAGE, CATEGORY_ID) VALUES(?, ?, ?, ?, ?, ?)");
			
			statement.setString(1, product.getId());
			statement.setString(2, product.getName());
			statement.setInt(3, product.getQuantity());
			statement.setDouble(4, product.getPrice());
			statement.setString(5, product.getImage());
			statement.setString(6, product.getCategoryId());
			
			statement.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static ProductModel findById(String id) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		
			try {
				connection = getConnection();
				statement = connection.prepareStatement("SELECT * FROM PRODUCT WHERE ID = ?");
				statement.setString(1, id);
				rs = statement.executeQuery();
				ProductModel product = null;
				while (rs.next()) {
					product = new ProductModel();
					product.setId(rs.getString("ID"));
					product.setName(rs.getString("NAME"));
					product.setQuantity(rs.getInt("QUANTITY"));
					product.setPrice(rs.getDouble("PRICE"));
					product.setImage(rs.getString("IMAGE"));
					product.setCategoryId(rs.getString("CATEGORY_ID"));
				}
				return product;
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					if (rs != null) {
						rs.close();
					}
					if (statement != null) {
						statement.close();
					}
					if (connection != null) {
						connection.close();
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return null;
	}
	
	public static void updateOne(ProductModel product) {
		Connection connection = null;
		PreparedStatement statement = null;

		try {
			connection = getConnection();
			connection.setAutoCommit(false);
			statement = connection.prepareStatement("UPDATE PRODUCT SET NAME=?, QUANTITY=?, PRICE=?, IMAGE=?, CATEGORY_ID=? WHERE ID=?");
			
			statement.setString(6, product.getId());
			statement.setString(1, product.getName());
			statement.setInt(2, product.getQuantity());
			statement.setDouble(3, product.getPrice());
			statement.setString(4, product.getImage());
			statement.setString(5, product.getCategoryId());
			
			statement.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void deleteOne(ProductModel product) {
		Connection connection = null;
		PreparedStatement statement = null;

		try {
			connection = getConnection();
			connection.setAutoCommit(false);
			statement = connection.prepareStatement("DELETE FROM PRODUCT WHERE ID=?");
			
			statement.setString(1, product.getId());
			
			statement.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
