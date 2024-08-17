<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.apro.model.Customer" %>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <title>View Transaction</title>
        <style>
    .header {
        background-color: #f8f9fa;
    }

    .nav-pills {
        justify-content: center;
    }

    .footer {
        position: fixed;
        bottom: 0;
        left: 0;
        width: 100%;
        text-align: center;
        padding: 10px;
        background-color: #f8f9fa;
        box-shadow: 0 -2px 5px rgba(0, 0, 0, 0.1);
    }
    
    .nav-link.active {
        background-color: #0d6efd;
        color: white;
    }
</style>
</head>
<body>
	<% 
    session = request.getSession(false); 
 
    if (session == null || session.getAttribute("adminId")==null) { 
        // No session found, forward to login page 
        request.setAttribute("loginStatus", "false"); 
        RequestDispatcher dispatcher = request.getRequestDispatcher("Login.jsp"); 
        dispatcher.forward(request, response); 
        return; 
    } 
	%>
	<header class="d-flex flex-wrap justify-content-center py-3 mb-4 border-bottom header">
    <div class="container">
        <ul class="nav nav-pills">
            <li class="nav-item"><a href="AdminHome.jsp" class="nav-link">Home</a></li>
            <li class="nav-item"><a href="AddCustomer.jsp" class="nav-link" >Add Customer</a></li>
            <li class="nav-item"><a href="ViewCustomerController" class="nav-link">View Customer</a></li>
            <li class="nav-item"><a href="AddBankAccount.jsp" class="nav-link">Add Bank Account</a></li>   
            <li class="nav-item"><a href="ViewTransactionController" class="nav-link active" aria-current="page">View Transactions</a></li>
            <li class="nav-item"><a href="LogoutController" class="nav-link text-danger">Logout</a></li>
        </ul>
    </div>
</header>

	    <div class="container">
        <div class="d-flex justify-content-center align-items-center" style="height: 100vh;">
            <form action="ViewTransactionController" method="post">
   
                <div class="text-center">
                    <button type="submit" class="btn btn-primary">View Transaction</button>
                </div>
            </form>
        </div>
    </div>
    
    
    <!-- Logout button centered at the bottom -->
    <form action="Home.jsp">
    <div class="footer">
    	<button type="submit" class="btn btn-danger">Logout</button>
        
    </div>
    </form>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>
