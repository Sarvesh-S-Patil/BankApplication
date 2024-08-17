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

import com.apro.model.Transaction;
import com.apro.model.TransactionUtil;

@WebServlet("/ViewTransactionController")
public class ViewTransactionController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ViewTransactionController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("adminId") == null) {
            request.setAttribute("loginStatus", "false");
            RequestDispatcher dispatcher = request.getRequestDispatcher("Login.jsp");
            dispatcher.forward(request, response);
            return;
        }
        
        Connection connection = (Connection) session.getAttribute("connection");
        TransactionUtil transactionUtil = new TransactionUtil(connection);
        
        String sortBy = request.getParameter("sortBy");
        String searchByAccountId = request.getParameter("searchByAccountId");
        
        List<Transaction> transactions = transactionUtil.viewTransactions(sortBy, searchByAccountId);
        
        request.setAttribute("transactions", transactions);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("ViewTransaction.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
