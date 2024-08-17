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
    <title>Customer List</title>
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
            <li class="nav-item"><a href="AddCustomer.jsp" class="nav-link">Add Customer</a></li>
            <li class="nav-item"><a href="ViewCustomerController" class="nav-link active" aria-current="page">View Customer</a></li>
            <li class="nav-item"><a href="AddBankAccount.jsp" class="nav-link">Add Bank Account</a></li>   
            <li class="nav-item"><a href="ViewTransactionController" class="nav-link">View Transactions</a></li>
            <li class="nav-item"><a href="LogoutController" class="nav-link text-danger">Logout</a></li>
        </ul>
    </div>
</header>

	<div class="container mt-5">
        <h2 class="mb-4">Customer List</h2>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th scope="col">Customer ID</th>
                    <th scope="col">First Name</th>
                    <th scope="col">Last Name</th>
                    <th scope="col">Email</th>
                    <th scope="col">Password</th>
                    <th scope="col">Admin ID</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="customer" items="${customers}">
                    <tr>
                        <td>${customer.customerId}</td>
                        <td>${customer.firstName}</td>
                        <td>${customer.lastName}</td>
                        <td>${customer.email}</td>
                        <td>${customer.password}</td>
                        <td>${customer.adminId}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>
