<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<html>
<head>
  <title>Login - Pizza Express</title>
</head>
<body>
<h2>Login to Pizza Express</h2>


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
