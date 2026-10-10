package com.campusbites.servlet;

import java.io.IOException;

import com.campusbites.dao.FoodDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/delete-food")
public class DeleteFoodServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(
                request.getParameter("id")
        );

        FoodDAO foodDAO = new FoodDAO();

        boolean success = foodDAO.deleteFood(id);

        if (success) {

            response.sendRedirect("menu");

        } else {

            response.getWriter().println(
                    "Failed to delete food item."
            );
        }
    }
}