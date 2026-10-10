package com.campusbites.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.campusbites.model.FoodItem;
import com.campusbites.util.DBConnection;

public class FoodDAO {

    // =========================================================
    // CREATE - Add Food Item
    // =========================================================

    public boolean addFood(FoodItem food) {

        String sql = "INSERT INTO food_items " +
                     "(name, category, price, description, availability) " +
                     "VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, food.getName());
            statement.setString(2, food.getCategory());
            statement.setDouble(3, food.getPrice());
            statement.setString(4, food.getDescription());
            statement.setBoolean(5, food.isAvailability());

            int rowsInserted = statement.executeUpdate();

            return rowsInserted > 0;

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }


    // =========================================================
    // READ - Get All Food Items
    // =========================================================

    public List<FoodItem> getAllFood() {

        List<FoodItem> foodList = new ArrayList<>();

        String sql = "SELECT * FROM food_items";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql);
             ResultSet resultSet =
                     statement.executeQuery()) {

            while (resultSet.next()) {

                FoodItem food = new FoodItem();

                food.setId(resultSet.getInt("id"));
                food.setName(resultSet.getString("name"));
                food.setCategory(resultSet.getString("category"));
                food.setPrice(resultSet.getDouble("price"));
                food.setDescription(
                        resultSet.getString("description")
                );
                food.setAvailability(
                        resultSet.getBoolean("availability")
                );

                foodList.add(food);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return foodList;
    }


    // =========================================================
    // READ - Get Food Item By ID
    // =========================================================

    public FoodItem getFoodById(int id) {

        FoodItem food = null;

        String sql = "SELECT * FROM food_items WHERE id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            try (ResultSet resultSet =
                         statement.executeQuery()) {

                if (resultSet.next()) {

                    food = new FoodItem();

                    food.setId(resultSet.getInt("id"));
                    food.setName(resultSet.getString("name"));
                    food.setCategory(
                            resultSet.getString("category")
                    );
                    food.setPrice(
                            resultSet.getDouble("price")
                    );
                    food.setDescription(
                            resultSet.getString("description")
                    );
                    food.setAvailability(
                            resultSet.getBoolean("availability")
                    );
                }
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return food;
    }


    // =========================================================
    // UPDATE - Update Food Item
    // =========================================================

    public boolean updateFood(FoodItem food) {

        String sql = "UPDATE food_items SET " +
                     "name = ?, " +
                     "category = ?, " +
                     "price = ?, " +
                     "description = ?, " +
                     "availability = ? " +
                     "WHERE id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, food.getName());
            statement.setString(2, food.getCategory());
            statement.setDouble(3, food.getPrice());
            statement.setString(4, food.getDescription());
            statement.setBoolean(5, food.isAvailability());
            statement.setInt(6, food.getId());

            int rowsUpdated = statement.executeUpdate();

            return rowsUpdated > 0;

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }


    // =========================================================
    // DELETE - Delete Food Item
    // =========================================================

    public boolean deleteFood(int id) {

        String sql = "DELETE FROM food_items WHERE id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            int rowsDeleted = statement.executeUpdate();

            return rowsDeleted > 0;

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }


    // =========================================================
    // SEARCH - Search Food Items
    // =========================================================

    public List<FoodItem> searchFood(String keyword) {

        List<FoodItem> foodList = new ArrayList<>();

        String sql = "SELECT * FROM food_items " +
                     "WHERE name LIKE ? " +
                     "OR category LIKE ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            String searchKeyword = "%" + keyword + "%";

            statement.setString(1, searchKeyword);
            statement.setString(2, searchKeyword);

            try (ResultSet resultSet =
                         statement.executeQuery()) {

                while (resultSet.next()) {

                    FoodItem food = new FoodItem();

                    food.setId(
                            resultSet.getInt("id")
                    );

                    food.setName(
                            resultSet.getString("name")
                    );

                    food.setCategory(
                            resultSet.getString("category")
                    );

                    food.setPrice(
                            resultSet.getDouble("price")
                    );

                    food.setDescription(
                            resultSet.getString("description")
                    );

                    food.setAvailability(
                            resultSet.getBoolean("availability")
                    );

                    foodList.add(food);
                }
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return foodList;
    }
}