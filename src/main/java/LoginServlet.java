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

        if (authenticateUser(email, password)) {

            HttpSession session = request.getSession();


            String username = getUsernameByEmail(email);


            session.setAttribute("username", username);

            response.sendRedirect("/home");
        } else {

            response.sendRedirect("/login?error=Authentication Failed. Incorrect email or password, please try again.");
        }
    }

    private boolean authenticateUser(String email, String password) {
        try (Connection connection = MySqlConnector.getConnection()) {
            String query = "SELECT * FROM clients_data WHERE email = ? AND password = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, password);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    return resultSet.next();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private String getUsernameByEmail(String email) {
        try (Connection connection = MySqlConnector.getConnection()) {
            String query = "SELECT username FROM clients_data WHERE email = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, email);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getString("username");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
