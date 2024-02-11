import DataBaseConnection.MySqlConnector;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(name = "StatusServlet", value = "/StatusServlet")
public class StatusServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");


        String newStatus = request.getParameter("newStatus");


        String[] selectedOrders = request.getParameterValues("selectedOrder");

        if (selectedOrders != null && newStatus != null) {
            try (Connection connection = MySqlConnector.getConnection()) {

                String updateSql = "UPDATE all_orders SET order_status = ? WHERE order_id = ?";
                try (PreparedStatement statement = connection.prepareStatement(updateSql)) {

                    for (String orderId : selectedOrders) {

                        statement.setString(1, newStatus);
                        statement.setInt(2, Integer.parseInt(orderId));

                        statement.executeUpdate();
                    }
                }
                response.sendRedirect(request.getContextPath() + "/OnlineOrdersServlet");

            } catch (SQLException e) {

                e.printStackTrace();

            }
        } else {

            response.getWriter().println("Error: required parameters are not passed.");
        }
    }
}
