import Classes.Order;
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

@WebServlet(name = "OnlineOrdersServlet", value = "/OnlineOrdersServlet")
public class OnlineOrdersServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Order> orders = new ArrayList<>();
        try (Connection connection = MySqlConnector.getConnection()) {
            String sql = "SELECT order_id, order_details, total_price, order_status FROM all_orders";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        int id = resultSet.getInt("order_id");
                        String orderDetails = resultSet.getString("order_details");
                        double totalPrice = resultSet.getDouble("total_price");
                        String status = resultSet.getString("order_status");

                        // Создание объекта Order и добавление его в список
                        Order order = new Order(id, orderDetails, totalPrice, status);
                        orders.add(order);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        request.setAttribute("orders", orders);


        request.getRequestDispatcher("/WEB-INF/online_orders.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}