package com.apro.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.apro.model.Account;
import com.apro.model.AccountUtil;
import com.apro.model.CustomerUtil;
import com.apro.model.Transaction;
import com.apro.model.TransactionUtil;

@WebServlet("/ViewPassbookController")
public class ViewPassbookController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ViewPassbookController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("customerId")==null) { 

            request.setAttribute("loginStatus", "false"); 
            RequestDispatcher dispatcher = request.getRequestDispatcher("Login.jsp"); 
            dispatcher.forward(request, response); 
            return; 
        } 
        Connection connection = (Connection) session.getAttribute("connection");
        Long customerId = (Long) session.getAttribute("customerId");
        long id = (customerId != null) ? customerId : -1L;

        RequestDispatcher requestDispatcher;

        if (customerId == -1L) {
            requestDispatcher = request.getRequestDispatcher("Login.jsp");
            requestDispatcher.forward(request, response);
            return;
        }

        CustomerUtil customerUtil = new CustomerUtil(connection);
        List<Account> accounts = customerUtil.getCustomerAccounts(customerId);
        request.setAttribute("accounts", accounts);

       
        String accountIdParam = request.getParameter("accountId");
        List<Transaction> transactions = new ArrayList<>();
        
        if (accountIdParam != null && !accountIdParam.trim().isEmpty()) {
            try {
                long accountId = Long.parseLong(accountIdParam.trim());
                AccountUtil accountUtil = new AccountUtil(connection);
                transactions = accountUtil.accountTransactions(accountId);
                request.setAttribute("transactions", transactions);
            } catch (NumberFormatException e) {
               
                request.setAttribute("errorMessage", "Invalid account ID format.");
            }
        }

        requestDispatcher = request.getRequestDispatcher("View_Passbook.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
