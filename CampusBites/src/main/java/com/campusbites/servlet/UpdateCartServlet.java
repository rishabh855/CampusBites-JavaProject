package com.campusbites.servlet;

import java.io.IOException;
import java.util.List;

import com.campusbites.model.CartItem;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/update-cart")
public class UpdateCartServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        // Get food ID
        int foodId = Integer.parseInt(
                request.getParameter("id")
        );

        // Get action
        String action =
                request.getParameter("action");

        // Get session
        HttpSession session =
                request.getSession();

        // Get cart
        List<CartItem> cart =
                (List<CartItem>) session.getAttribute("cart");

        if (cart != null) {

            for (CartItem item : cart) {

                if (item.getFood().getId() == foodId) {

                    if ("increase".equals(action)) {

                        item.setQuantity(
                                item.getQuantity() + 1
                        );

                    } else if ("decrease".equals(action)) {

                        if (item.getQuantity() > 1) {

                            item.setQuantity(
                                    item.getQuantity() - 1
                            );
                        }

                    } else if ("remove".equals(action)) {

                        cart.remove(item);
                    }

                    break;
                }
            }

            // Save updated cart
            session.setAttribute("cart", cart);
        }

        // Return to cart
        response.sendRedirect("cart.jsp");
    }
}