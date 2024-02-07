import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(name = "RegisterServlet", value = "/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve user information from the request
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String email = request.getParameter("email");
        String telephoneNumber = request.getParameter("telephoneNumber");

        // Validate password and confirm password
        if (!password.equals(confirmPassword)) {
            // Passwords do not match, handle this case as needed
            response.sendRedirect("/register?error=passwordMismatch");
            return;
        }

        // Perform user registration and database insertion
        try (Connection connection = MySqlConnector.getConnection()) {
            // Create a prepared statement to insert data into the clients_data table
            String insertQuery = "INSERT INTO clients_data (username, password, email, telephone_number) VALUES (?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                preparedStatement.setString(3, email);
                preparedStatement.setString(4, telephoneNumber);

                // Execute the query
                preparedStatement.executeUpdate();
            }

            // Set a success message as a request attribute
            request.setAttribute("successMessage", "You have successfully registered!");
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
            response.sendRedirect("/register?error=databaseError");
            return;
        }

        // Redirect the user to a different page after registration (e.g., login page)
        request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
    }
}
