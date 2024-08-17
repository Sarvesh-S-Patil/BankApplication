<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Profile</title>
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

        .nav-link {
            font-size: 1.125rem; 
            padding: 0.5rem 1rem; 
        }

        .nav-link.active {
            background-color: #0d6efd; 
            color: white; 
        }

        header {
            background-color: #f8f9fa; 
        }

        .nav-pills {
            justify-content: center; 

        .main-content {
            display: flex;
            justify-content: center;
            align-items: center;
            height: calc(100vh - 150px); 
            text-align: center;
            padding: 2rem;
        }

        .welcome-text {
            font-size: 1.5rem; 
            font-weight: bold; 
            color: #000; 
            margin-bottom: 2rem; 
        }

        .form-container {
            max-width: 500px; 
            margin: auto; 
        }
    </style>
</head>
<body>
    <% 
    session = request.getSession(false); 

    if (session == null || session.getAttribute("customerId") == null) { 
        // No session found, forward to login page 
        request.setAttribute("loginStatus", "false"); 
        RequestDispatcher dispatcher = request.getRequestDispatcher("Login.jsp"); 
        dispatcher.forward(request, response); 
        return; 
    } 
    %>

    <header class="d-flex flex-wrap justify-content-center py-3 mb-4 border-bottom">
        <div class="container">
            <ul class="nav nav-pills">
                <li class="nav-item"><a href="CustomerHome.jsp" class="nav-link">Home</a></li>
                <li class="nav-item"><a href="PassbookHome.jsp" class="nav-link">Passbook</a></li>
                <li class="nav-item"><a href="AddTransactionController" class="nav-link">New Transaction</a></li>
                <li class="nav-item"><a href="EditProfileHome.jsp" class="nav-link active" aria-current="page">Edit Profile</a></li>
                <li class="nav-item"><a href="LogoutController" class="nav-link text-danger">Logout</a></li>
            </ul>
        </div>
    </header>

      <div class="container mt-5">
    
        <form action="EditProfileController" method="post">
            <div class="mb-3">
                <label for="firstName" class="form-label">First Name</label>
                <input type="text" class="form-control" id="firstName" name="firstName" required>
            </div>
            <div class="mb-3">
                <label for="lastName" class="form-label">Last Name</label>
                <input type="text" class="form-control" id="lastName" name="lastName" required>
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">Password</label>
                <input type="password" class="form-control" id="password" name="password" required>
            </div>
            <button type="submit" class="btn btn-primary">Update Profile</button>
        </form>

        <!-- Display status message -->
        <c:if test="${not empty updateStatus}">
            <div class="alert alert-info mt-3">
                ${updateStatus}
            </div>
        </c:if>
    </div>

    <footer class="footer">
        <span>&copy; Sarvesh</span>
    </footer>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

    <script>
        window.addEventListener("load", function() {
            var editProfileStatus = "<%= session.getAttribute("editProfileStatus") %>";
            <% 
                Long customerId = (Long) session.getAttribute("customerId");
                String customerIdString = customerId != null ? customerId.toString() : "";
            %>
            if (editProfileStatus === "true") {
                alert("Profile updated successfully, Customer ID is: " + "<%= customerIdString %>");
            } else if (editProfileStatus === "false") {
                alert("Profile update failed");
            }
        });
    </script>
</body>
</html>
