# Bank Application

A web banking application built with Java Servlets, JSP and JDBC on MySQL. It has separate portals for bank admins and customers.

🎥 **Demo:** [`Bank Website.mp4`](Bank%20Website.mp4)

## Features

**Admin portal**
- Sign in as an admin
- Add customers and open bank accounts for them
- View all customers and all transactions

**Customer portal**
- Sign in as a customer
- Make transactions between accounts
- View your passbook and transaction history
- Edit your profile

**Under the hood**
- MVC: servlets are the controllers, JSPs and JSTL are the views, and plain Java classes hold the model and data access (`*Util` classes over JDBC)
- Session-based authentication. Every protected servlet sends users who aren't logged in back to the login page.
- Custom exceptions (`AccountNotFoundException`, `CustomerNotFoundException`) and dedicated error pages for admins and customers

## Tech stack

Java · Servlets · JSP · JSTL · JDBC · MySQL · HTML · CSS · Bootstrap · Apache Tomcat

## Project structure

```
src/com/apro/
├── controller/   # Servlets: login, logout, customers, accounts, transactions, passbook
├── model/        # Customer, Account, Transaction, and their JDBC *Util classes
└── exception/    # Custom exceptions
WebContent/       # JSP views, CSS, assets, WEB-INF/lib (JSTL, MySQL driver)
```

## Run locally

1. Create a MySQL database named `bank`.
2. Set `DB_PASSWORD` (and `DB_USERNAME` if it isn't `root`) as environment variables.
3. Import the project into Eclipse as a **Dynamic Web Project**, add Apache Tomcat as the server, and run it.
4. Open `http://localhost:8080/BankApplication/Login.jsp`.
