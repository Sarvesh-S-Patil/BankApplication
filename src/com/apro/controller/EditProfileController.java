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
import com.mysql.cj.Session;

/**
 * Servlet implementation class EditProfileController
 */
@WebServlet("/EditProfileController")
public class EditProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditProfileController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("customerId")==null) { 
	    
	        request.setAttribute("loginStatus", "false"); 
	        RequestDispatcher dispatcher = request.getRequestDispatcher("Login.jsp"); 
	        dispatcher.forward(request, response); 
	        return; 
	    } 
		Connection connection = (Connection) session.getAttribute("connection");
		CustomerUtil customerUtil = new CustomerUtil(connection);
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String password = request.getParameter("password");
		long customerId = (long) session.getAttribute("customerId");
		boolean result =customerUtil.updateCustomer(firstName, lastName, password,customerId);
		session.setAttribute("editProfileStatus", result );
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("EditProfileHome.jsp");
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
