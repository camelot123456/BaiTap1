package com.laptrinhjava.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.laptrinhjava.dao.UserDAO;
import com.laptrinhjava.models.UserModel;
import com.laptrinhjava.utils.FormUtil;
import com.laptrinhjava.utils.SessionUtil;

@WebServlet(urlPatterns= {"/login"})
public class LoginController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher rd = req.getRequestDispatcher("/views/login.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		UserModel user = FormUtil.toModel(UserModel.class, req);
		user = UserDAO.findOne(user.getUsername(), user.getPassword());
		if (user != null) {
			SessionUtil.getInstance().putValue(req, "USERMODEL", (UserModel) user);
			resp.sendRedirect("/product-home");
		} else {
			resp.sendRedirect("/login");
		}
	}
}
