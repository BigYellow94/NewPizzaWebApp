import DataBaseConnection.MySqlConnector;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "OrderServlet", value = "/OrderServlet")
public class OrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Получение выбранных пицц и их количества из запроса
        String[] selectedPizzas = request.getParameterValues("selectedPizzas");
        String quantityPizza1 = request.getParameter("quantity_1"); // Предполагается, что id пиццы равен 1

        // Получение названия и цены каждой выбранной пиццы
        String[] selectedPizzaNames = new String[selectedPizzas.length];
        double[] selectedPizzaPrices = new double[selectedPizzas.length];

        // Проходим по выбранным пиццам и получаем их название и цену
        for (int i = 0; i < selectedPizzas.length; i++) {
            int pizzaId = Integer.parseInt(selectedPizzas[i]);
            // Здесь вызываем методы для получения названия и цены пиццы по ее id
            String pizzaName = getPizzaNameById(pizzaId);
            double pizzaPrice = getPizzaPriceById(pizzaId);
            selectedPizzaNames[i] = pizzaName;
            selectedPizzaPrices[i] = pizzaPrice;
            // Вывод информации о пицце в консоль
            System.out.println("Selected Pizza: " + pizzaName + ", Price: " + pizzaPrice);
        }

        // Дополнительная обработка данных о заказе (например, сохранение в базе данных или передача другому компоненту)

        // Перенаправление пользователя на другую страницу или вывод подтверждения заказа
        request.setAttribute("selectedPizzaNames", selectedPizzaNames);
        request.setAttribute("selectedPizzaPrices", selectedPizzaPrices);
        request.getRequestDispatcher("/WEB-INF/orderConfirmation.jsp").forward(request, response);
    }

    // Метод для получения названия пиццы по ее идентификатору
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

    // Метод для получения цены пиццы по ее идентификатору
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
}