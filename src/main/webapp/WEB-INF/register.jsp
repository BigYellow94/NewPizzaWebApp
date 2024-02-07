<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<html>
<head>
    <title>Register - Pizza Express</title>
</head>
<body>
<h2>Create an Account</h2>

<%-- Check for success message and display it --%>
<% if (request.getAttribute("successMessage") != null) { %>
<p style="color: green;"><%= request.getAttribute("successMessage") %></p>
<% } %>

<form action="/register" method="post">
    <label for="username">Username:</label>
    <input type="text" id="username" name="username" required>
    <br>

    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required>
    <br>

    <label for="confirmPassword">Confirm Password:</label>
    <input type="password" id="confirmPassword" name="confirmPassword" required>
    <br>

    <label for="email">Email:</label>
    <input type="text" id="email" name="email" required>
    <br>

    <label for="telephoneNumber">Telephone number:</label>
    <input type="text" id="telephoneNumber" name="telephoneNumber" required>
    <br>

    <button type="submit">Register</button>
</form>

<p>Already have an account? <a href="/login">Sign in here</a>.</p>
</body>
</html>
