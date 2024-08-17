package com.apro.controller;

import java.io.IOException;
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

import com.apro.model.AccountUtil;
import com.apro.model.Customer;
import com.apro.model.CustomerUtil;
import com.mysql.cj.Session;

/**
 * Servlet implementation class ViewCustomerController
 */
@WebServlet("/AddAccountController")
public class AddAccountController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddAccountController() {
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
	        response.sendRedirect("Login.jsp");
	        return; 
	    } 
		Connection connection = (Connection) session.getAttribute("connection");
		Customer customer;
		String addAccountStatus = (String) session.getAttribute("addAccountStatus");
		if(session.getAttribute("viewCustomerStatus")== null || session.getAttribute("viewCustomerStatus").equals("false")) {
			long customerId = Long.parseLong(request.getParameter("customerId"));
			CustomerUtil customerUtil = new CustomerUtil(connection);
			try {
				customer =customerUtil.getCustomerById(customerId);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("AddBankAccount.jsp");
				if(customer==null) {
					session.setAttribute("viewCustomerStatus","false");
				}
				else {
					System.out.println(customer);
					session.setAttribute("customerId",customerId);
					session.setAttribute("customer",customer);
					session.setAttribute("viewCustomerStatus","true");
				}
				requestDispatcher.forward(request, response);
			}
			catch (Exception e) {
				// TODO: handle exception
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("AdminException.jsp");
				request.setAttribute("errorMessage",e.getMessage());
				requestDispatcher.forward(request, response);
			}
			

		}
		else {
			long customerId = (long) session.getAttribute("customerId");
			
			AccountUtil accountUtil= new AccountUtil(connection);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("AddBankAccount.jsp");
			
			Long accountId = accountUtil.addAccount(0,customerId);
			if(accountId==-1) {
				
				session.setAttribute("addAccountStatus", "false");
				requestDispatcher.forward(request, response);
			}
			
			session.setAttribute("accountId", accountId);
			session.setAttribute("addAccountStatus", "true");
			requestDispatcher.forward(request, response);
			
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
