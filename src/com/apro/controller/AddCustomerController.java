package com.apro.controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.apro.model.CustomerUtil;
import com.apro.model.DbUtil;
import com.mysql.cj.Session;

/**
 * Servlet implementation class AddCustomerController
 */
@WebServlet("/AddCustomerController")
public class AddCustomerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCustomerController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("adminId")==null) { 
	        // No session found, forward to login page 
	        request.setAttribute("loginStatus", "false"); 
	        RequestDispatcher dispatcher = request.getRequestDispatcher("Login.jsp"); 
	        dispatcher.forward(request, response); 
	        return; 
	    } 
		String firstName=request.getParameter("firstName");
		String lastName=request.getParameter("lastName");
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		long adminId=(long) session.getAttribute("adminId");
		System.out.println(adminId);
		String adminLogged = (String) session.getAttribute("adminLogged");
		System.out.println(adminLogged);
		Connection connection = (Connection) session.getAttribute("connection");
		CustomerUtil customerUtil= new CustomerUtil(connection);
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("AddCustomer.jsp");
		if(adminLogged.equals("true")) {
			
			customerUtil.addCustomer(firstName, lastName, email, password, adminId);
			request.setAttribute("addCustomerStatus", "true");
			System.out.println("Customer Added Sucessfully");
		}
		else {
			session.setAttribute("addCustomerStatus", "false");
		}
		requestDispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
