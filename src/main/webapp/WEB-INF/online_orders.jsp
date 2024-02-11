<%@ page import="Classes.Order" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Online orders - Admin Mode</title>
</head>
<body>
<h1>List of orders</h1>
<form action="StatusServlet" method="post">
    <table border="1">
        <thead>
        <tr>
            <th>ID</th>
            <th>Details</th>
            <th>Total Price</th>
            <th>Status</th>
            <th>Select</th>
        </tr>
        </thead>
        <tbody>
        <% for (Order order : (List<Order>) request.getAttribute("orders")) { %>
        <tr>
            <td><%= order.getId() %></td>
            <td><textarea name="details_<%= order.getId() %>" cols="60"><%= order.getDetails() %></textarea></td>
            <td><%= order.getTotalPrice() %></td>
            <td><%= order.getStatus() %></td>
            <td><input type="checkbox" name="selectedOrder" value="<%= order.getId() %>"></td>
        </tr>
        <% } %>
        </tbody>
    </table>
    New Status: <input type="text" name="newStatus">
    <input type="submit" value="Change the status">
</form>
<form action="/admin" method="get">
    <button type="submit">Back to Home</button>
</form>
</body>
</html>
