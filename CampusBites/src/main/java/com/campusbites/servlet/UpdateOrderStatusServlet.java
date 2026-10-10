package com.campusbites.servlet;

import java.io.IOException;

import com.campusbites.dao.OrderDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/update-order-status")
public class UpdateOrderStatusServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        // Get order ID
        int orderId = Integer.parseInt(
                request.getParameter("id")
        );

        // Get new status
        String status =
                request.getParameter("status");

        // Update order status
        OrderDAO orderDAO =
                new OrderDAO();

        boolean success =
                orderDAO.updateOrderStatus(
                        orderId,
                        status
                );

        if (success) {

            response.sendRedirect(
                    "view-orders"
            );

        } else {

            response.getWriter().println(
                    "Failed to update order status."
            );
        }
    }
}