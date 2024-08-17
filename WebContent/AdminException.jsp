<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Error</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
<style>
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

    header {
        background-color: #f8f9fa;
    }

    .nav-pills {
        justify-content: center;
    }

    .error-content {
        display: flex;
        justify-content: center;
        align-items: center;
        height: calc(100vh - 80px);
        text-align: center;
        flex-direction: column;
    }

    .error-message {
        font-size: 1.5rem;
        color: #dc3545;
    }

    .error-details {
        margin-top: 1rem;
        font-size: 1.2rem;
        color: #6c757d;
    }
</style>
</head>
<body>
<%

    String errorMessage = (String)request.getAttribute("errorMessage");
%>

<header class="d-flex flex-wrap justify-content-center py-3 mb-4 border-bottom">
    <div class="container">
        <ul class="nav nav-pills">
            <li class="nav-item"><a href="AdminHome.jsp" class="nav-link">Home</a></li>
            <li class="nav-item"><a href="AddCustomer.jsp" class="nav-link">Add Customer</a></li>
            <li class="nav-item"><a href="ViewCustomerController" class="nav-link">View Customer</a></li>
            <li class="nav-item"><a href="AddBankAccount.jsp" class="nav-link">Add Bank Account</a></li>
            <li class="nav-item"><a href="ViewTransactionController" class="nav-link">View Transactions</a></li>
            <li class="nav-item"><a href="LogoutController" class="nav-link text-danger">Logout</a></li>
        </ul>
    </div>
</header>

<main class="container error-content">
    <div class="error-message">Something went wrong!</div>
    <div class="error-details">
  
    	<p><strong>Error:</strong> <%= errorMessage %></p>
 
        
    </div>
</main>

<footer class="footer">
    <span>&copy; Sarvesh</span>
</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>
