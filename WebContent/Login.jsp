<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
    <title>Home</title>
    <style>
        .form-signin {
            max-width: 330px;
            padding: 15px;
            margin: auto;
        }

        .form-signin img {
            width: 150px;
            height: auto;
        }

        .form-signin .form-floating {
            margin-bottom: 1rem;
        }

        body {
            background-color: #f8f9fa;
        }

        .alert {
            margin: 15px auto;
            width: 100%;
        }

        .login-header {
            margin-bottom: 20px;
        }
    </style>
</head>
<body class="text-center">
    <div class="container">
        <div class="login-header text-center">
            <% 
                String loginStatus = (String) request.getAttribute("loginStatus");
                if ("false".equals(loginStatus)) { 
            %>
                <div class="alert alert-danger" role="alert">
                    Login Failed. Please try again.
                </div>
            <% } %>
        </div>

        <main class="form-signin">
            <form action="login" method="post">
                <img class="mb-4" src="assets/Bank Logo 3.png" alt="Bank Logo">
                <h1 class="h3 mb-3 fw-normal">Sign in as:</h1>

                <select class="form-select mb-3" aria-label="Login Type" name="login-type">
                    <option selected value="customer">Customer</option>
                    <option value="admin">Admin</option>
                </select>

                <div class="form-floating">
                    <input type="email" class="form-control" id="floatingInput" placeholder="name@example.com" name="username" required>
                    <label for="floatingInput">Email address</label>
                </div>
                <div class="form-floating">
                    <input type="password" class="form-control" id="floatingPassword" placeholder="Password" name="password" required>
                    <label for="floatingPassword">Password</label>
                </div>

                <button class="w-100 btn btn-lg btn-primary" type="submit">Sign in</button>
            </form>
        </main>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>
