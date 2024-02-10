import Classes.Pizza;
import DataBaseConnection.MySqlConnector;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "PizzaServlet", value = "/menu")
public class PizzaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Pizza> pizzas = new ArrayList<>();
        try (Connection connection = MySqlConnector.getConnection()) {
            String sql = "SELECT * FROM pizza_types";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String name = resultSet.getString("name");
                        double price = resultSet.getDouble("price");
                        pizzas.add(new Pizza(id, name, price));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("pizzas", pizzas);
        request.getRequestDispatcher("/WEB-INF/pizzas.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}