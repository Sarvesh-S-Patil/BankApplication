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
	<main>
		<div class="container">
          <header class="d-flex flex-wrap justify-content-center py-3 mb-4 border-bottom">
            <a href="" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-dark text-decoration-none">
              <svg class="bi me-2" width="40" height="32"><use xlink:href="#bootstrap"/></svg>
              <span class="fs-4">View Customer</span>
            </a>
            <div class="d-flex">
    				<a href="AdminHome.jsp" class="btn btn-primary btn-lg ms-auto" tabindex="-1" role="button">
    				Go back</a>
			</div>
            	
		</header>
	</main>

   	<div class="container">
        <div class="d-flex justify-content-center align-items-center" style="height: 100vh;">
            <form action="ViewCustomerController" method="post">
   
                <div class="text-center">
                    <button type="submit" class="btn btn-primary">View Customer</button>
                </div>
            </form>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>