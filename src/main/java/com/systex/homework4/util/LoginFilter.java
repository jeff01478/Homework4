package com.systex.homework4.util;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LoginFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("Filter is executed");
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		HttpSession session = httpRequest.getSession();
		if (request.getParameter("userName") != null) {
			session.setAttribute("authUserName", (String)request.getParameter("userName"));
		}
	
		if (session.getAttribute("authUserName") == null) {
			System.out.println("GOOD");
			httpResponse.sendRedirect(httpRequest.getContextPath() + "/");;
		}
        chain.doFilter(request, response);
	}
}
