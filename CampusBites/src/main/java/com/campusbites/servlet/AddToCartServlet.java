package com.campusbites.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.campusbites.dao.FoodDAO;
import com.campusbites.model.CartItem;
import com.campusbites.model.FoodItem;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/add-to-cart")
public class AddToCartServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        // Get food ID from URL
        int foodId = Integer.parseInt(
                request.getParameter("id")
        );

        // Get food from database
        FoodDAO foodDAO = new FoodDAO();

        FoodItem food = foodDAO.getFoodById(foodId);

        if (food == null) {
            response.getWriter().println(
                    "Food item not found."
            );
            return;
        }

        // Get current session
        HttpSession session = request.getSession();

        // Get cart from session
        List<CartItem> cart =
                (List<CartItem>) session.getAttribute("cart");

        // If cart does not exist, create it
        if (cart == null) {
            cart = new ArrayList<>();
        }

        // Check if item already exists in cart
        boolean itemExists = false;

        for (CartItem item : cart) {

            if (item.getFood().getId() == foodId) {

                // Increase quantity
                item.setQuantity(
                        item.getQuantity() + 1
                );

                itemExists = true;
                break;
            }
        }

        // If item is not already in cart
        if (!itemExists) {

            CartItem cartItem =
                    new CartItem(food, 1);

            cart.add(cartItem);
        }

        // Save cart back into session
        session.setAttribute("cart", cart);

        // Go back to menu
        response.sendRedirect("menu");
    }
}