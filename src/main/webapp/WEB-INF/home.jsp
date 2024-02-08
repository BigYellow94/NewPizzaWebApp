<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<html>
<head>
    <title>Welcome to Pizza Express</title>
</head>
<body>
<h2>You are welcome on Pizza Express!</h2>
<p>Would you like to make an order?</p>

<form action="/pizzamenu" method="get">
    <button type="submit">Make an order</button>
</form>

<form action="/logout" method="post">
    <button type="submit">Exit</button>
</form>
</body>
</html>
