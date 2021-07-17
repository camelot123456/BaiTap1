package com.laptrinhjava.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.laptrinhjava.models.UserModel;
import com.laptrinhjava.utils.SessionUtil;

public class Authorization implements Filter{

	private ServletContext context;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		this.context = filterConfig.getServletContext();
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		String url = req.getRequestURI();
		if(url.startsWith("/product")) {
			UserModel user = (UserModel) SessionUtil.getInstance().getValue(req, "USERMODEL");
			
			if (user != null) {
				if (user.getRole().getCode().equals("ADMIN")) {
					chain.doFilter(req, resp);
				}
			} else {
				resp.sendRedirect("/login");
			}
		} else {
			chain.doFilter(req, resp);
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
