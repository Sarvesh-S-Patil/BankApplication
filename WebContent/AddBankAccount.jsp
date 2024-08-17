<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <!-- Option 1: Include in HTML -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
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
<title>Add Account</title>
</head>
<body>
	<% 
    session = request.getSession(false); 
 
    if (session == null ||  session.getAttribute("adminId")==null) { 
     
        request.setAttribute("loginStatus", "false"); 
        response.sendRedirect("Login.jsp"); 
        return; 
    } 
	%>
	<% 
	    String viewCustomerStatus = (String) session.getAttribute("viewCustomerStatus");
	%>
	
	<div class="text-center mb-4">
	    <script>
	        window.addEventListener("load", function() {
	            <% if ("false".equals(viewCustomerStatus)) { %>
	                alert("Customer with given customer id is not present, Try Again !");
	            <% } %>
	        });
	    </script>
	</div>
<header class="d-flex flex-wrap justify-content-center py-3 mb-4 border-bottom header">
    <div class="container">
        <ul class="nav nav-pills">
            <li class="nav-item"><a href="AdminHome.jsp" class="nav-link">Home</a></li>
            <li class="nav-item"><a href="AddCustomer.jsp" class="nav-link" >Add Customer</a></li>
            <li class="nav-item"><a href="ViewCustomerController" class="nav-link">View Customer</a></li>
            <li class="nav-item"><a href="AddBankAccount.jsp" class="nav-link active" aria-current="page">Add Bank Account</a></li>   
            <li class="nav-item"><a href="ViewTransactionController" class="nav-link">View Transactions</a></li>
            <li class="nav-item"><a href="LogoutController" class="nav-link text-danger">Logout</a></li>
        </ul>
    </div>
</header>
	
	<section class="vh-100 gradient-custom">
    <div class="container py-5 h-100">
        <div class="row justify-content-center align-items-center h-100">
            <div class="col-12 col-md-8 col-lg-6 col-xl-4">
                <div class="card shadow-2-strong card-registration" style="border-radius: 15px;">
                    <div class="card-body p-3 p-md-4">
                        <h3 class="mb-3 pb-2">View Customer By Id</h3>
                        <form action="AddAccountController" method="post">
                            <div class="mb-3">
                                <div class="form-outline">
                                    <input type="number" id="customerId" name="customerId" class="form-control form-control-sm" required />
                                    <label class="form-label" for="customerId">Customer Id</label>
                                </div>     
                            </div>
                            <div class="mt-3">
                                <input class="btn btn-primary btn-sm" type="submit" value="Submit" />
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
	</section>
	
      
    <c:if test="${viewCustomerStatus eq 'true'}">
		<div class="card">
                <div class="card-header">
                    <h3>Customer Details</h3>
                </div>
                <div class="card-body">
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th scope="col">Field</th>
                                <th scope="col">Value</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <th scope="row">Customer ID</th>
                                <td>${customer.customerId}</td>
                            </tr>
                            <tr>
                                <th scope="row">First Name</th>
                                <td>${customer.firstName}</td>
                            </tr>
                            <tr>
                                <th scope="row">Last Name</th>
                                <td>${customer.lastName}</td>
                            </tr>
                            <tr>
                                <th scope="row">Email</th>
                                <td>${customer.email}</td>
                            </tr>
                            <tr>
                                <th scope="row">Password</th>
                                <td>${customer.password}</td>
                            </tr>
                            <tr>
                                <th scope="row">Admin ID</th>
                                <td>${customer.adminId}</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
	</c:if>
	
	
	
	
	
	
	
	<div class="text-center mb-4">
    <script>
        window.addEventListener("load", function() {
            var addAccountStatus = "<%= session.getAttribute("addAccountStatus") %>";
            <% 
                Long accountId = (Long) session.getAttribute("accountId");
                String accountIdString = accountId != null ? accountId.toString() : "";
            %>
            if (addAccountStatus === "true") {
                alert("Account Created Successfully, Account ID is : " + "<%= accountIdString %>");
            } else if (addAccountStatus === "false") {
                alert("Account Creation Failed");
            }
        });
    	</script>
	</div>
	
	
	<main>
		<div class="container">
          <header class="d-flex flex-wrap justify-content-center py-3 mb-4 border-bottom">
            <a href="" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-dark text-decoration-none">
              <svg class="bi me-2" width="40" height="32"><use xlink:href="#bootstrap"/></svg>
              <span class="fs-4">Add Bank Account</span>
            </a>
            <div class="d-flex">
    				<a href="Admin.jsp" class="btn btn-primary btn-lg ms-auto" tabindex="-1" role="button">Go back</a>
			</div>
            	
	</main>
	<section class="vh-100 gradient-custom">
        <div class="container py-5 h-100">
          <div class="row justify-content-center align-items-center h-100">
            <div class="col-12 col-lg-9 col-xl-7">
              <div class="card shadow-2-strong card-registration" style="border-radius: 15px;">
                <div class="card-body p-4 p-md-5">
                  <h3 class="mb-4 pb-2 pb-md-0 mb-md-5">Registration Form</h3>
                  <form action="AddAccountController" method="post">   
                    <div class="mt-4 pt-2">
                      <input data-mdb-ripple-init class="btn btn-primary btn-lg" type="submit" value="Generate Account Number" />
                    </div>
      
                  </form>
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>
	
	
	 <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

</body>
</html>