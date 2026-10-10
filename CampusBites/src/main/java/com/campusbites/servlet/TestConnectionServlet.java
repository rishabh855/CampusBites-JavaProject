package com.campusbites.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import com.campusbites.util.DBConnection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/test-connection")
public class TestConnectionServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        Connection connection = DBConnection.getConnection();

        out.println("<html>");
        out.println("<head><title>Database Test</title></head>");
        out.println("<body>");

        if (connection != null) {

            out.println("<h1>Database Connected Successfully! ✅</h1>");
            out.println("<p>Campus Bites can communicate with MySQL.</p>");

        } else {

            out.println("<h1>Database Connection Failed ❌</h1>");
            out.println("<p>Check the Eclipse console for the error.</p>");
        }

        out.println("</body>");
        out.println("</html>");
    }
}