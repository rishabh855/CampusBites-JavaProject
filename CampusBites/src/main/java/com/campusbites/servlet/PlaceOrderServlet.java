package com.campusbites.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.campusbites.dao.OrderDAO;
import com.campusbites.model.CartItem;
import com.campusbites.model.Order;
import com.campusbites.model.OrderItem;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/place-order")
public class PlaceOrderServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        // Get customer details
        String customerName =
                request.getParameter("customerName");

        String phone =
                request.getParameter("phone");

        // Get current session
        HttpSession session =
                request.getSession();

        // Get cart
        List<CartItem> cart =
                (List<CartItem>) session.getAttribute("cart");

        // Check if cart is empty
        if (cart == null || cart.isEmpty()) {

            response.getWriter().println(
                    "Your cart is empty."
            );

            return;
        }

        // Calculate total
        double totalAmount = 0;

        for (CartItem item : cart) {

            totalAmount += item.getSubtotal();
        }

        // Create Order object
        Order order = new Order();

        order.setCustomerName(customerName);
        order.setPhone(phone);
        order.setTotalAmount(totalAmount);
        order.setStatus("Pending");

        // Create OrderItem list
        List<OrderItem> orderItems =
                new ArrayList<>();

        for (CartItem item : cart) {

            OrderItem orderItem =
                    new OrderItem();

            orderItem.setFoodId(
                    item.getFood().getId()
            );

            orderItem.setQuantity(
                    item.getQuantity()
            );

            orderItem.setPrice(
                    item.getFood().getPrice()
            );

            orderItem.setSubtotal(
                    item.getSubtotal()
            );

            orderItems.add(orderItem);
        }

        // Save order to database
        OrderDAO orderDAO =
                new OrderDAO();

        int orderId =
                orderDAO.createOrder(
                        order,
                        orderItems
                );

        // Check result
        if (orderId > 0) {

            // Remove cart after successful order
            session.removeAttribute("cart");

            // Store order ID for confirmation page
            session.setAttribute(
                    "orderId",
                    orderId
            );

            response.sendRedirect(
                    "order-confirmation.jsp"
            );

        } else {

            response.getWriter().println(
                    "Failed to place order."
            );
        }
    }
}