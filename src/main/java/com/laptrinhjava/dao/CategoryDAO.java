package com.laptrinhjava.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.laptrinhjava.models.CategoryModel;

public class CategoryDAO extends AbstractDAO{
	
	public static List<CategoryModel> find(){
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		List<CategoryModel> listCategory = new ArrayList<CategoryModel>();
		try {
			connection = getConnection();
			statement = connection.prepareStatement("SELECT * FROM CATEGORY");
			rs = statement.executeQuery();
			CategoryModel category = null;
			while (rs.next()) {
				category = new CategoryModel();
				category.setId(rs.getString("ID"));
				category.setName(rs.getString("NAME"));
				category.setImage(rs.getString("IMAGE"));
				category.setParentId(rs.getString("PARENT_ID"));
				listCategory.add(category);
			}
			return listCategory;
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
}
