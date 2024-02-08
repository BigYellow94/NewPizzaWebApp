import DataBaseConnection.MySqlConnector;

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

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String email = request.getParameter("email");
        String telephoneNumber = request.getParameter("telephoneNumber");


        if (!password.equals(confirmPassword)) {

            response.sendRedirect("/register?error=passwordMismatch");

            return;
        }


        try (Connection connection = MySqlConnector.getConnection()) {

            String insertQuery = "INSERT INTO clients_data (username, password, email, telephone_number) VALUES (?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                preparedStatement.setString(3, email);
                preparedStatement.setString(4, telephoneNumber);


                preparedStatement.executeUpdate();
            }

            request.setAttribute("successMessage", "You have successfully registered! Please sign in");

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("/register?error=databaseError");
            return;
        }
        request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
    }
}
