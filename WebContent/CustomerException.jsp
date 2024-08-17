<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
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
        height: calc(100vh - 150px);
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

    .nav-link {
        font-size: 1.125rem;
        padding: 0.5rem 1rem;
    }

    .nav-link.active {
        background-color: #0d6efd;
        color: white;
    }

    .welcome-text {
        font-size: 2.5rem;
        font-weight: bold;
        color: #000;
        margin: 0;
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
            <li class="nav-item"><a href="CustomerHome.jsp" class="nav-link">Home</a></li>
            <li class="nav-item"><a href="PassbookHome.jsp" class="nav-link">Passbook</a></li>
            <li class="nav-item"><a href="AddTransactionController" class="nav-link">New Transaction</a></li>
            <li class="nav-item"><a href="EditProfileHome.jsp" class="nav-link">Edit Profile</a></li>
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
