<%@ page import="Classes.Pizza" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Confirmation</title>
</head>
<body>
<h1>Confirmation</h1>
<p>Your order has been received:</p>
<ul>
    <%
        List<Pizza> selectedPizzasList = (List<Pizza>) request.getAttribute("selectedPizzasList");

        if (selectedPizzasList != null) {
            for (Pizza pizza : selectedPizzasList) {
    %>
    <li>
        Pizza Name: <%= pizza.getName() %>, Quantity: <%= pizza.getQuantity() %>, Price: <%= pizza.getPrice() %>
    </li>
    <%
            }
        }
    %>
</ul>

<p>Total Order Price: $ <%= request.getAttribute("totalOrderPrice") %></p>

<form action="/home" method="get">
    <button type="submit">Back to Home</button>
</form>

</body>
</html>
