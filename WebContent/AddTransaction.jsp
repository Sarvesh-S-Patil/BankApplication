<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Transaction</title>
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
        }

        main {
            padding-bottom: 70px; /* Ensure space for the footer */
        }

        .card {
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <% 
    session = request.getSession(false); 

    if (session == null || session.getAttribute("customerId") == null) { 
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
                <li class="nav-item"><a href="AddTransaction.jsp" class="nav-link active" aria-current="page">New Transaction</a></li>
                <li class="nav-item"><a href="EditProfileHome.jsp" class="nav-link">Edit Profile</a></li>
                <li class="nav-item"><a href="LogoutController" class="nav-link text-danger">Logout</a></li>
            </ul>
        </div>
    </header>

    <main class="container">
        <div class="card">
            <div class="card-body">
                <h5 class="card-title">Enter Transaction Details</h5>
                <form action="AddTransactionController" method="post" onsubmit="return validateForm()">
                    <div class="mb-3">
                        <label for="senderAccountId" class="form-label">Select your Account</label>
                        <select id="senderAccountId" name="senderAccountId" class="form-select">
                            <option value="">Select Account</option>
                            <c:forEach var="account" items="${accounts}">
                                <option value="${account.accountId}">${account.accountId} - ${account.balance}</option>
                            </c:forEach>
                        </select>
                        <div class="form-text">Leave empty if not applicable (for Debit transactions).</div>
                    </div>
                    <div class="mb-3">
                        <label for="receiverAccountId" class="form-label">Receiver Account ID</label>
                        <input type="number" id="receiverAccountId" name="receiverAccountId" class="form-control">
                        <div class="form-text">Leave empty if not applicable (for Credit transactions).</div>
                    </div>
                    <div class="mb-3">
                        <label for="transactionType" class="form-label">Transaction Type</label>
                        <select id="transactionType" name="transactionType" class="form-select" required>
                            <option value="Credit">Credit</option>
                            <option value="Debit">Debit</option>
                            <option value="transfer">Transfer</option>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label for="amount" class="form-label">Amount</label>
                        <input type="number" id="amount" name="amount" step="0.01" class="form-control" required>
                    </div>
                    <button type="submit" class="btn btn-primary">Submit</button>
                </form>
            </div>
        </div>
    </main>

    <footer class="footer">
        <span>&copy; Sarvesh</span>
    </footer>

    <script>
        function validateForm() {
            var senderAccountId = document.getElementById('senderAccountId').value;
            var receiverAccountId = document.getElementById('receiverAccountId').value;

            if (senderAccountId && receiverAccountId && senderAccountId === receiverAccountId) {
                alert("Sender and Receiver Account IDs cannot be the same.");
                return false;
            }

            return true;
        }

        window.addEventListener("load", function() {
            var transactionStatus = "<%= session.getAttribute("transactionStatus") %>";
            if (transactionStatus === "true") {
                alert("Transaction Successful");
                session.setAttribute("transactionStatus", ""); 
            } else if (transactionStatus === "false") {
                alert("Transaction Failed");
                session.setAttribute("transactionStatus", ""); 
            }
        });
    </script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>
