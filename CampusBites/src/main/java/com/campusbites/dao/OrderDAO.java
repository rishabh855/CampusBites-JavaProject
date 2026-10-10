package com.campusbites.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.campusbites.model.Order;
import com.campusbites.model.OrderItem;
import com.campusbites.util.DBConnection;

public class OrderDAO {

    // Create a new order and save its items
    public int createOrder(Order order, List<OrderItem> items) {

        String orderSQL =
                "INSERT INTO orders " +
                "(customer_name, phone, total_amount, status) " +
                "VALUES (?, ?, ?, ?)";

        String itemSQL =
                "INSERT INTO order_items " +
                "(order_id, food_id, quantity, price, subtotal) " +
                "VALUES (?, ?, ?, ?, ?)";

        Connection connection = null;

        try {

            connection = DBConnection.getConnection();

            connection.setAutoCommit(false);

            // Insert order
            try (PreparedStatement orderStatement =
                    connection.prepareStatement(
                            orderSQL,
                            PreparedStatement.RETURN_GENERATED_KEYS)) {

                orderStatement.setString(
                        1,
                        order.getCustomerName()
                );

                orderStatement.setString(
                        2,
                        order.getPhone()
                );

                orderStatement.setDouble(
                        3,
                        order.getTotalAmount()
                );

                orderStatement.setString(
                        4,
                        order.getStatus()
                );

                orderStatement.executeUpdate();

                // Get generated order ID
                try (ResultSet resultSet =
                        orderStatement.getGeneratedKeys()) {

                    if (!resultSet.next()) {

                        connection.rollback();

                        return -1;
                    }

                    int orderId =
                            resultSet.getInt(1);

                    // Insert order items
                    try (PreparedStatement itemStatement =
                            connection.prepareStatement(itemSQL)) {

                        for (OrderItem item : items) {

                            itemStatement.setInt(
                                    1,
                                    orderId
                            );

                            itemStatement.setInt(
                                    2,
                                    item.getFoodId()
                            );

                            itemStatement.setInt(
                                    3,
                                    item.getQuantity()
                            );

                            itemStatement.setDouble(
                                    4,
                                    item.getPrice()
                            );

                            itemStatement.setDouble(
                                    5,
                                    item.getSubtotal()
                            );

                            itemStatement.addBatch();
                        }

                        itemStatement.executeBatch();
                    }

                    connection.commit();

                    return orderId;
                }
            }

        } catch (Exception e) {

            e.printStackTrace();

            if (connection != null) {

                try {
                    connection.rollback();

                } catch (Exception rollbackException) {

                    rollbackException.printStackTrace();
                }
            }

            return -1;

        } finally {

            if (connection != null) {

                try {

                    connection.setAutoCommit(true);
                    connection.close();

                } catch (Exception e) {

                    e.printStackTrace();
                }
            }
        }
    }


    // Get all orders
    public List<Order> getAllOrders() {

        List<Order> orderList =
                new ArrayList<>();

        String sql =
                "SELECT * FROM orders " +
                "ORDER BY order_date DESC";

        try (Connection connection =
                     DBConnection.getConnection();

             PreparedStatement statement =
                     connection.prepareStatement(sql);

             ResultSet resultSet =
                     statement.executeQuery()) {

            while (resultSet.next()) {

                Order order =
                        new Order();

                order.setId(
                        resultSet.getInt("id")
                );

                order.setCustomerName(
                        resultSet.getString("customer_name")
                );

                order.setPhone(
                        resultSet.getString("phone")
                );

                order.setTotalAmount(
                        resultSet.getDouble("total_amount")
                );

                order.setStatus(
                        resultSet.getString("status")
                );

                orderList.add(order);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return orderList;
    }


    // Update order status
    public boolean updateOrderStatus(
            int orderId,
            String status) {

        String sql =
                "UPDATE orders SET status = ? " +
                "WHERE id = ?";
        try (Connection connection =
                DBConnection.getConnection();

        PreparedStatement statement =
                connection.prepareStatement(sql)) {

       statement.setString(1, status);
       statement.setInt(2, orderId);

       int rowsUpdated =
               statement.executeUpdate();

       return rowsUpdated > 0;

   } catch (Exception e) {

       e.printStackTrace();

       return false;
   }
}
}