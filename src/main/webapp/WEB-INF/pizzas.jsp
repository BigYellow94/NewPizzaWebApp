<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="Classes.Pizza" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Menu - Pizza Express</title>
</head>
<body>
<h1>Menu of Available Pizzas</h1>
<form action="OrderServlet" method="post">
  <table border="1">
    <thead>
    <tr>
      <th>ID</th>
      <th>Name</th>
      <th>Price</th>
      <th>Select</th>
      <th>Quantity</th>
    </tr>
    </thead>
    <tbody>
    <% for (Pizza pizza : (List<Pizza>) request.getAttribute("pizzas")) { %>
    <tr>
      <td><%= pizza.getId() %></td>
      <td><%= pizza.getName() %></td>
      <td><%= pizza.getPrice() %></td>
      <td><input type="checkbox" name="selectedPizzas" value="<%= pizza.getId() %>"></td>
      <td><input type="number" name="quantity_<%= pizza.getId() %>" value="1" min="1"></td>
    </tr>
    <% } %>
    </tbody>
  </table>
  <input type="submit" value="Make the order">
</form>

<form action="/home" method="get">
  <button type="submit">Back to Home</button>
</form>

</body>
</html>