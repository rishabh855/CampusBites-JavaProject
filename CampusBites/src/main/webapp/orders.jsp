<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.campusbites.model.Order" %>

<!DOCTYPE html>
<html>

<head>

    <meta charset="UTF-8">

    <title>Manage Orders - Campus Bites</title>

    <link rel="stylesheet" href="css/style.css">

</head>

<body>

<nav class="navbar">

    <h2>🍔 Campus Bites</h2>

    <div>

        <a href="index.jsp">Home</a>
        <a href="menu">Menu</a>
        <a href="cart.jsp">🛒 Cart</a>
        <a href="view-orders">📋 Orders</a>

    </div>

</nav>

<div class="container">

    <h1>📋 Manage Orders</h1>

    <a href="menu" class="btn btn-secondary">
        ← Back to Menu
    </a>

    <br><br>

<%
    List<Order> orderList =
        (List<Order>) request.getAttribute("orderList");

    if (orderList != null && !orderList.isEmpty()) {
%>

<div class="table-container">

<table>

    <tr>

        <th>Order ID</th>
        <th>Customer</th>
        <th>Phone</th>
        <th>Total</th>
        <th>Status</th>
        <th>Update</th>

    </tr>

<%
    for (Order order : orderList) {
%>

    <tr>

        <td>
            #<%= order.getId() %>
        </td>

        <td>
            <%= order.getCustomerName() %>
        </td>

        <td>
            <%= order.getPhone() %>
        </td>

        <td>
            ₹<%= order.getTotalAmount() %>
        </td>

        <td>
            <strong>
                <%= order.getStatus() %>
            </strong>
        </td>

        <td>

            <form
                action="update-order-status"
                method="get">

                <input
                    type="hidden"
                    name="id"
                    value="<%= order.getId() %>">

                <select name="status">

                    <option
                        value="Pending"
                        <%= "Pending".equals(order.getStatus())
                            ? "selected" : "" %>>
                        Pending
                    </option>

                    <option
                        value="Preparing"
                        <%= "Preparing".equals(order.getStatus())
                            ? "selected" : "" %>>
                        Preparing
                    </option>

                    <option
                        value="Ready"
                        <%= "Ready".equals(order.getStatus())
                            ? "selected" : "" %>>
                        Ready
                    </option>

                    <option
                        value="Completed"
                        <%= "Completed".equals(order.getStatus())
                            ? "selected" : "" %>>
                        Completed
                    </option>

                </select>

                <button type="submit">
                    Update
                </button>

            </form>

        </td>

    </tr>

<%
    }
%>

</table>

</div>

<%
    } else {
%>

<div class="empty-state">

    <h2>No orders found.</h2>

    <p>
        Orders placed by customers will appear here.
    </p>

</div>

<%
    }
%>

</div>

<div class="footer">
    Campus Bites © 2026
</div>

</body>

</html>