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
        String[] selectedPizzaNames = (String[]) request.getAttribute("selectedPizzaNames");
        double[] selectedPizzaPrices = (double[]) request.getAttribute("selectedPizzaPrices");

        if (selectedPizzaNames != null && selectedPizzaPrices != null) {
            for (int i = 0; i < selectedPizzaNames.length; i++) {
    %>
    <li>
        Pizza Name: <%= selectedPizzaNames[i] %>, Price: <%= selectedPizzaPrices[i] %>
    </li>
    <%
            }
        }
    %>
    <!-- Здесь вы можете добавить дополнительную информацию о заказе, если это необходимо -->
</ul>

<form action="/home" method="get">
    <button type="submit">Back to Home</button>
</form>

</body>
</html>
