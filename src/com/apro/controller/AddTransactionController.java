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

import com.apro.model.Account;
import com.apro.model.CustomerUtil;
import com.apro.model.TransactionUtil;

@WebServlet("/AddTransactionController")
public class AddTransactionController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AddTransactionController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("customerId") == null) { 
            request.setAttribute("loginStatus", "false"); 
            RequestDispatcher dispatcher = request.getRequestDispatcher("Login.jsp"); 
            dispatcher.forward(request, response); 
            return; 
        }
        
        Connection connection = (Connection) session.getAttribute("connection");
        long customerId = (Long) session.getAttribute("customerId");
        
        // Fetch customer accounts
        CustomerUtil customerUtil = new CustomerUtil(connection);
        List<Account> accounts = customerUtil.getCustomerAccounts(customerId);
        request.setAttribute("accounts", accounts);
        
        // Forward to the AddTransaction.jsp page
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("AddTransaction.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("customerId") == null) { 
            request.setAttribute("loginStatus", "false"); 
            RequestDispatcher dispatcher = request.getRequestDispatcher("Login.jsp"); 
            dispatcher.forward(request, response); 
            return; 
        }
        
        Connection connection = (Connection) session.getAttribute("connection");
       
        long senderAccountId = request.getParameter("senderAccountId").isEmpty() ? 0 : Long.parseLong(request.getParameter("senderAccountId"));
        System.out.println(senderAccountId);
        long receiverAccountId = request.getParameter("receiverAccountId").isEmpty() ? 0 : Long.parseLong(request.getParameter("receiverAccountId"));
        String transactionType = request.getParameter("transactionType");
        double amount = Double.parseDouble(request.getParameter("amount"));
        try {
    
            TransactionUtil transactionUtil = new TransactionUtil(connection);
            boolean success = transactionUtil.addTransaction(senderAccountId, receiverAccountId, transactionType, amount);

        
            session.setAttribute("transactionStatus", success ? "true" : "false");

            RequestDispatcher requestDispatcher = request.getRequestDispatcher("AddTransaction.jsp");
            requestDispatcher.forward(request, response);
        }
        catch (Exception e) {
			// TODO: handle exception
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("CustomerException.jsp");
			request.setAttribute("errorMessage",e.getMessage());
			requestDispatcher.forward(request, response);
		}

    }
}
