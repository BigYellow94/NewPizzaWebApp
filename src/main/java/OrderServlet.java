import Classes.Pizza;
import DataBaseConnection.MySqlConnector;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "OrderServlet", value = "/OrderServlet")
public class OrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String[] selectedPizzas = request.getParameterValues("selectedPizzas");

        List<Pizza> selectedPizzasList = new ArrayList<>();

        for (int i = 0; i < selectedPizzas.length; i++) {
            int pizzaId = Integer.parseInt(selectedPizzas[i]);
            int quantity = Integer.parseInt(request.getParameter("quantity_" + pizzaId));

            String pizzaName = getPizzaNameById(pizzaId);
            double pizzaPrice = getPizzaPriceById(pizzaId);

            Pizza pizza = new Pizza(pizzaId, pizzaName, pizzaPrice);
            pizza.setQuantity(quantity);

            selectedPizzasList.add(pizza);

        }

        // Расчет общей суммы заказа
        double totalOrderPrice = calculateTotalOrderPrice(selectedPizzasList);

        request.setAttribute("selectedPizzasList", selectedPizzasList);
        request.setAttribute("totalOrderPrice", totalOrderPrice);
        request.getRequestDispatcher("/WEB-INF/orderConfirmation.jsp").forward(request, response);
    }

    private String getPizzaNameById(int pizzaId) {
        String pizzaName = null;
        try (Connection connection = MySqlConnector.getConnection()) {
            String sql = "SELECT name FROM pizza_types WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, pizzaId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        pizzaName = resultSet.getString("name");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pizzaName;
    }

    private double getPizzaPriceById(int pizzaId) {
        double pizzaPrice = 0.0;
        try (Connection connection = MySqlConnector.getConnection()) {
            String sql = "SELECT price FROM pizza_types WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, pizzaId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        pizzaPrice = resultSet.getDouble("price");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pizzaPrice;
    }

    private double calculateTotalOrderPrice(List<Pizza> selectedPizzasList) {
        double totalOrderPrice = 0.0;
        for (Pizza pizza : selectedPizzasList) {
            totalOrderPrice += pizza.getPrice() * pizza.getQuantity();
        }
        return totalOrderPrice;
    }
}