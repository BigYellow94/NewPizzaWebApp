<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<html>
<head>
    <title>Admin page - Pizza Express</title>
</head>
<body>

<form action="/OnlineOrdersServlet" method="get">
    <button type="submit">Check the list of Online orders</button>
</form>

<form action="/logout" method="post">
    <button type="submit">Logout</button>
</form>

</body>
</html>