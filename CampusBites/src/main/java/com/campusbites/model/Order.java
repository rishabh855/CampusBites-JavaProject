package com.campusbites.model;

public class Order {

    private int id;
    private String customerName;
    private String phone;
    private double totalAmount;
    private String status;

    public Order() {
    }

    public Order(int id, String customerName, String phone,
                 double totalAmount, String status) {

        this.id = id;
        this.customerName = customerName;
        this.phone = phone;
        this.totalAmount = totalAmount;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}