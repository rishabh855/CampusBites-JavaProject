package com.campusbites.servlet;

import java.io.IOException;
import java.util.List;

import com.campusbites.dao.FoodDAO;
import com.campusbites.model.FoodItem;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/menu")
public class ViewFoodServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        // Create DAO object
        FoodDAO foodDAO = new FoodDAO();

        // Get search keyword from URL
        String keyword = request.getParameter("keyword");

        List<FoodItem> foodList;

        // If user searched something
        if (keyword != null && !keyword.trim().isEmpty()) {

            foodList = foodDAO.searchFood(keyword.trim());

        } else {

            // Otherwise show all food
            foodList = foodDAO.getAllFood();
        }

        // Send food list to JSP
        request.setAttribute("foodList", foodList);

        // Send keyword to JSP
        request.setAttribute("keyword", keyword);

        // Open menu page
        request.getRequestDispatcher("menu.jsp")
               .forward(request, response);
    }
}