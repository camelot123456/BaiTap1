package com.laptrinhjava.controllers;

import java.io.IOException;
import java.text.Normalizer.Form;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.laptrinhjava.dao.CategoryDAO;
import com.laptrinhjava.dao.ProductDAO;
import com.laptrinhjava.dao.UserDAO;
import com.laptrinhjava.models.UserModel;
import com.laptrinhjava.utils.FormUtil;
import com.laptrinhjava.utils.SessionUtil;

@WebServlet(urlPatterns = { "/product-home" })
public class ProductDisplay extends HttpServlet {

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
		if ((UserModel)SessionUtil.getInstance().getValue(req, "USERMODEL") == null) {
			resp.sendRedirect("/login");
		} else {
			if (action == null) {
				req.setAttribute(products, ProductDAO.find());
				path = "/views/display.jsp";
			} else if (action.equals("add")) {
				req.setAttribute(categorys, CategoryDAO.find());
				path = "/views/insert.jsp";
			} else if (action.equals("edit")) {
				req.setAttribute(categorys, CategoryDAO.find());
				req.setAttribute("product", ProductDAO.findById(req.getParameter("id")));
				path = "/views/update.jsp";
			} else if (action.equals("logout")) {
				SessionUtil.getInstance().removeValue(req, "USERMODEL");
				path = "/views/login.jsp";
			}
			RequestDispatcher rd = req.getRequestDispatcher(path);
			rd.forward(req, resp);
		}

	}

	

}
