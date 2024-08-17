package com.apro.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.apro.model.AdminLogin;
import com.apro.model.CustomerLogin;
import com.apro.model.DbUtil;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter writer = response.getWriter();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String loginType = request.getParameter("login-type");
		HttpSession session = request.getSession();
		DbUtil dbUtil = new DbUtil();
		dbUtil.connectDB();
		Connection connection =dbUtil.getConnection();
		
		session.setAttribute("loginType", loginType);
		session.setAttribute("username", username);
		session.setAttribute("password", password);
		session.setAttribute("connection",connection);
		RequestDispatcher requestDispatcher = null;
		if(loginType.equals("admin")) {
			session.setAttribute("adminLogged","true");
			AdminLogin adminLogin = new AdminLogin(connection);
			boolean validCombination =adminLogin.verifyLogin(username, password);
			if(validCombination) {
				long adminId = adminLogin.getAdminId(username, password);
				requestDispatcher = request.getRequestDispatcher("/AdminHome.jsp");
				session.setAttribute("adminId", adminId);
				
				
				requestDispatcher.forward(request, response);
			}
			else {
				request.setAttribute("loginStatus","false");
				requestDispatcher = request.getRequestDispatcher("/Login.jsp");
				requestDispatcher.forward(request, response);
			}
		}
		else {
			CustomerLogin customerLogin = new CustomerLogin(connection);
			boolean validCombination =customerLogin.verifyLogin(username, password);
			if(validCombination) {
				long customerId = customerLogin.getCustomerId(username, password);
				System.out.println(customerId);
				requestDispatcher = request.getRequestDispatcher("/CustomerHome.jsp");
				session.setAttribute("customerId", customerId);
				session.setAttribute("customerLogged","true");
				requestDispatcher.forward(request, response);
			}
			else {
				request.setAttribute("loginStatus","false");
				requestDispatcher = request.getRequestDispatcher("/Login.jsp");
				requestDispatcher.forward(request, response);
			}
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
