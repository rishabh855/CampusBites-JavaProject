package com.campusbites.servlet;

import java.io.IOException;

import com.campusbites.dao.FoodDAO;
import com.campusbites.model.FoodItem;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/add-food")
public class AddFoodServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String category = request.getParameter("category");

        double price = Double.parseDouble(
                request.getParameter("price")
        );

        String description =
                request.getParameter("description");

        FoodItem food = new FoodItem();

        food.setName(name);
        food.setCategory(category);
        food.setPrice(price);
        food.setDescription(description);
        food.setAvailability(true);

        FoodDAO foodDAO = new FoodDAO();

        boolean success = foodDAO.addFood(food);

        if (success) {

            response.sendRedirect("add-food.jsp");

        } else {

            response.getWriter().println(
                    "Failed to add food item."
            );
        }
    }
}