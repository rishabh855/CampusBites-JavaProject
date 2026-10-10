package com.campusbites.servlet;

import java.io.IOException;
import java.util.List;

import com.campusbites.dao.OrderDAO;
import com.campusbites.model.Order;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/view-orders")
public class OrderListServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        OrderDAO orderDAO = new OrderDAO();

        List<Order> orderList =
                orderDAO.getAllOrders();

        request.setAttribute(
                "orderList",
                orderList
        );

        request.getRequestDispatcher(
                "orders.jsp"
        ).forward(request, response);
    }
}