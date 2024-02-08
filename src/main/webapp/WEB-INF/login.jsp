<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<html>
<head>
  <title>Main Login Page - Pizza Express</title>
</head>
<body>
<h1>We are pleased to welcome you to our website!</h1>
<p>Only registered users can place an order, please sign in.</p>


<% if (request.getAttribute("errorMessage") != null) { %>
<p style="color: red;"><%= request.getAttribute("errorMessage") %></p>
<% } %>

<form action="/login" method="post">
  <label for="email">Email:</label>
  <input type="text" id="email" name="email" required>
  <br>

  <label for="password">Password:</label>
  <input type="password" id="password" name="password" required>
  <br>

  <button type="submit">Sign In</button>
</form>

<p>Don't have an account? <a href="/register">Register here</a>.</p>
</body>
</html>
