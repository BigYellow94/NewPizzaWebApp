import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Pass an error message to the login page if available
        String errorMessage = request.getParameter("error");
        request.setAttribute("errorMessage", errorMessage);

        request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Authenticate the user
        if (authenticateUser(email, password)) {
            // Successful authentication
            response.sendRedirect("/home");
        } else {
            // Authentication failed, redirect to login page with an error parameter
            response.sendRedirect("/login?error=authenticationFailed");
        }
    }

    private boolean authenticateUser(String email, String password) {
        try (Connection connection = MySqlConnector.getConnection()) {
            // Query to check if the user with the provided email and password exists
            String query = "SELECT * FROM clients_data WHERE email = ? AND password = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, password);

                // Execute the query
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    return resultSet.next(); // If there is a result, the user exists
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
            return false;
        }
    }
}
