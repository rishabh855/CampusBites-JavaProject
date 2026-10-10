# CampusBites

CampusBites is a college canteen ordering app built with Java Servlets, JSP, and MySQL. Customers can browse and search food, manage a session-based cart, and place orders. Canteen staff can add, edit, and remove menu items and update order statuses.

## Features

- Browse the food menu and search by food name or category.
- Add available food items to a session-based cart and change quantities.
- Place an order with a customer name and phone number.
- View orders and update their status.
- Add, edit, and delete food items.
- Check the application's MySQL connection.

## Technology

- Java 21
- Jakarta Servlets and JSP
- Apache Tomcat 11
- MySQL with MySQL Connector/J
- Eclipse Web Tools Platform project configuration

This repository is an Eclipse Dynamic Web Project. It does not currently include a Maven or Gradle build file.

## Project layout

```text
CampusBites/
├── src/main/java/com/campusbites/
│   ├── dao/          # SQL access for food and orders
│   ├── model/        # Food, cart, and order model classes
│   ├── servlet/      # HTTP route handlers
│   └── util/         # MySQL connection helper
├── src/main/webapp/
│   ├── WEB-INF/      # Web descriptor and JDBC driver
│   ├── css/          # Application stylesheet
│   └── *.jsp         # Application pages
└── README.md
```

## Database setup

The application connects to a database named `campus_bites`. The repository does not contain a database migration or schema file. The DDL below is a starter schema compatible with the columns queried and written by the current DAOs.

Run it in MySQL:

```sql
CREATE DATABASE campus_bites
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

USE campus_bites;

CREATE TABLE food_items (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(150) NOT NULL,
  category VARCHAR(100) NOT NULL,
  price DECIMAL(10, 2) NOT NULL,
  description TEXT,
  availability BOOLEAN NOT NULL DEFAULT TRUE,
  PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE orders (
  id INT NOT NULL AUTO_INCREMENT,
  customer_name VARCHAR(150) NOT NULL,
  phone VARCHAR(20) NOT NULL,
  total_amount DECIMAL(10, 2) NOT NULL,
  status VARCHAR(30) NOT NULL DEFAULT 'Pending',
  order_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE order_items (
  id INT NOT NULL AUTO_INCREMENT,
  order_id INT NOT NULL,
  food_id INT NULL,
  quantity INT NOT NULL,
  price DECIMAL(10, 2) NOT NULL,
  subtotal DECIMAL(10, 2) NOT NULL,
  PRIMARY KEY (id),
  INDEX idx_order_items_order_id (order_id),
  INDEX idx_order_items_food_id (food_id),
  CONSTRAINT fk_order_items_order
    FOREIGN KEY (order_id) REFERENCES orders (id)
    ON DELETE CASCADE,
  CONSTRAINT fk_order_items_food
    FOREIGN KEY (food_id) REFERENCES food_items (id)
    ON DELETE SET NULL
) ENGINE=InnoDB;
```

`order_items.food_id` is nullable so an order's saved item and price can remain in the order history if its menu item is later deleted. The current application writes order items but does not display them in the order list.

### Database credentials

`src/main/java/com/campusbites/util/DBConnection.java` contains the JDBC URL, username, and password as constants. Set these to credentials for your local MySQL instance before running the app. Do not commit real database passwords to a public repository; for a deployed app, load credentials from environment variables or a protected configuration source.

The JDBC URL currently targets `localhost:3306/campus_bites`. The MySQL Connector/J JAR is stored under `src/main/webapp/WEB-INF/lib/` and is included in the web application deployment.

## Run in Eclipse

1. Make sure MySQL is running and the database and tables above exist.
2. Import the project using **File → Import → Existing Projects into Workspace**.
3. Configure a Java 21 JDK and an Apache Tomcat 11 server in Eclipse.
4. Confirm the project's Tomcat runtime and Dynamic Web Module facet are set for the installed Eclipse/Tomcat combination.
5. Add **CampusBites** to the Tomcat server, then publish and start the server.
6. Open the application at:

   ```text
   http://localhost:8080/CampusBites/
   ```

If Eclipse reports a deployment descriptor error, refresh the project and check that `src/main/webapp/WEB-INF/web.xml` is recognized as a Jakarta Servlet descriptor. The descriptor in this project uses Servlet 6.0, which Tomcat 11 supports.

## Routes

In the tables below, prepend the context path `/CampusBites` to each application path. For example, `GET /menu` is `http://localhost:8080/CampusBites/menu` in the default local setup.

### Servlet routes

| Method | Path | Purpose and inputs |
|---|---|---|
| `GET` | `/menu` | Show menu; optional `keyword` query parameter searches food name and category. |
| `GET` | `/add-to-cart?id={foodId}` | Add one available food item to the session cart. |
| `GET` | `/update-cart?id={foodId}&action={action}` | Change a cart item. `action` is `increase`, `decrease`, or `remove`. |
| `POST` | `/place-order` | Place an order from the session cart. Form fields: `customerName`, `phone`. |
| `GET` | `/view-orders` | Load all orders and display the order management page. |
| `GET` | `/update-order-status?id={orderId}&status={status}` | Update an order. The UI offers `Pending`, `Preparing`, `Ready`, and `Completed`. |
| `POST` | `/add-food` | Add a food item. Form fields: `name`, `category`, `price`, `description`. New items are marked available. |
| `GET` | `/edit-food?id={foodId}` | Load an existing food item into the edit form. |
| `POST` | `/edit-food` | Save food changes. Form fields: `id`, `name`, `category`, `price`, `description`, and optional `availability` checkbox. |
| `GET` | `/delete-food?id={foodId}` | Delete a food item. |
| `GET` | `/test-connection` | Render a basic MySQL connectivity result. |

### JSP pages and static resources

| Method | Path | Purpose |
|---|---|---|
| `GET` | `/` | Welcome page (`index.jsp`, configured as the welcome file). |
| `GET` | `/index.jsp` | Welcome page. |
| `GET` | `/menu.jsp` | Menu JSP view. Use `/menu` for the normal menu flow so the servlet supplies the food list. |
| `GET` | `/cart.jsp` | Show the current session cart. |
| `GET` | `/checkout.jsp` | Collect customer details before submitting `/place-order`. |
| `GET` | `/add-food.jsp` | Show the add-food form. |
| `GET` | `/edit-food.jsp` | Edit form view. Normally reach it through `/edit-food?id={foodId}` so the servlet loads the food item. |
| `GET` | `/orders.jsp` | Order list view. Normally reach it through `/view-orders` so the servlet supplies the orders. |
| `GET` | `/order-confirmation.jsp` | Order confirmation view, using `orderId` stored in the session after a successful order. |
| `GET` | `/css/style.css` | Application stylesheet. |

## Database tables used by the application

- **`food_items`** — menu item identity, name, category, price, description, and availability.
- **`orders`** — customer name, phone, total, status, and order date.
- **`order_items`** — item quantities and price snapshots associated with an order.

## Current implementation notes

- The cart is stored in the user's HTTP session; it is not persisted between sessions.
- There is no login or role-based authorization. Food administration and order-management routes are currently accessible without authentication.
- The current implementation uses `GET` for cart updates, status updates, and food deletion. For a public deployment, these state-changing actions should use `POST` (or another non-`GET` method) and be protected with authorization and CSRF safeguards.
- Database connection settings are hard-coded in `DBConnection.java`; use protected configuration for anything beyond local development.
