package com.campusbites.servlet;

import java.io.IOException;

import com.campusbites.dao.FoodDAO;
import com.campusbites.model.FoodItem;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/edit-food")
public class EditFoodServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(
                request.getParameter("id")
        );

        FoodDAO foodDAO = new FoodDAO();

        FoodItem food = foodDAO.getFoodById(id);

        request.setAttribute("food", food);

        request.getRequestDispatcher("edit-food.jsp")
               .forward(request, response);
    }


    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(
                request.getParameter("id")
        );

        String name =
                request.getParameter("name");

        String category =
                request.getParameter("category");

        double price = Double.parseDouble(
                request.getParameter("price")
        );

        String description =
                request.getParameter("description");

        boolean availability =
                request.getParameter("availability") != null;

        FoodItem food = new FoodItem();

        food.setId(id);
        food.setName(name);
        food.setCategory(category);
        food.setPrice(price);
        food.setDescription(description);
        food.setAvailability(availability);

        FoodDAO foodDAO = new FoodDAO();

        boolean success =
                foodDAO.updateFood(food);

        if (success) {

            response.sendRedirect("menu");

        } else {

            response.getWriter().println(
                    "Failed to update food item."
            );
        }
    }
}