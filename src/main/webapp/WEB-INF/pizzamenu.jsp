<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="DataBaseConnection.MySqlConnector" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Order Pizza - Pizza Express</title>
</head>
<body>
<h1>Available Pizzas</h1>
<form action="/pizzamenu" method="post">
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Price</th>
            <th>Quantity</th>
        </tr>

        <%
            try {
                Statement statement = MySqlConnector.getConnection().createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM pizza_types");

                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    double price = resultSet.getDouble("price");
        %>
        <tr>
            <td><%= id %></td>
            <td><%= name %></td>
            <td><%= price %></td>
            <td><input type="number" name="quantity_<%= id %>" value="0" min="0"></td>
        </tr>
        <%
                }
                resultSet.close();
                statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        %>
    </table>
</form>

<form action="/ingredients" method="get">
    <button type="submit">Add Extra Ingredients</button>
</form>

<form action="/ordersubmission" method="post">
    <button type="submit">Place Order</button>
</form>

<form action="/home" method="get">
    <button type="submit">Back to Home</button>
</form>

</body>
</html>
