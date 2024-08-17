package com.apro.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.apro.model.Customer;
import com.apro.model.CustomerUtil;
import com.mysql.cj.Session;

/**
 * Servlet implementation class ViewCustomerController
 */
@WebServlet("/ViewCustomerController")
public class ViewCustomerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewCustomerController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("adminId")==null) { 
	       
	        request.setAttribute("loginStatus", "false"); 
	        RequestDispatcher dispatcher = request.getRequestDispatcher("Login.jsp"); 
	        dispatcher.forward(request, response); 
	        return; 
	    } 
        Connection connection = (Connection)session.getAttribute("connection"); 
        CustomerUtil customerUtil = new CustomerUtil(connection);
        List<Customer> customers = customerUtil.getCustomers();
        System.out.println("Number of customers retrieved: " + (customers != null ? customers.size() : 0));
   
        request.setAttribute("customers", customers);

        RequestDispatcher dispatcher = request.getRequestDispatcher("ViewCustomer.jsp");
        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
