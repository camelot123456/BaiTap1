package com.laptrinhjava.controllers;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.laptrinhjava.dao.CategoryDAO;
import com.laptrinhjava.dao.ProductDAO;
import com.laptrinhjava.models.CategoryModel;
import com.laptrinhjava.models.ProductModel;
import com.laptrinhjava.utils.FormUtil;
import com.laptrinhjava.utils.JsonUtil;

@WebServlet(urlPatterns = { "/api-product" })
public class ProductController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String categorys = "categorys";
	private String products = "products";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String action = req.getParameter("action");
		String path = "";
		if(action == null) {
			req.setAttribute(products, ProductDAO.find());
			path = "/views/display.jsp";
		} 
		else if(action.equals("add")) {
			req.setAttribute(categorys, CategoryDAO.find());
			path = "/views/insert.jsp";
		} 
		else if(action.equals("edit")) {
			req.setAttribute(categorys, CategoryDAO.find());
			req.setAttribute("product", ProductDAO.findById(req.getParameter("id")));
			path = "/views/update.jsp";
		}
		
		RequestDispatcher rd = req.getRequestDispatcher(path);
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		ProductModel product = FormUtil.toModel(ProductModel.class, req);
		ProductDAO.save(product);
		resp.sendRedirect("/api-product");
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		ProductModel product = JsonUtil.toModel(req.getReader(), ProductModel.class);
		ProductDAO.updateOne(product);
		resp.sendRedirect("/api-product");
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		ProductModel product = JsonUtil.toModel(req.getReader(), ProductModel.class);
		ProductDAO.deleteOne(product);
	}

}
