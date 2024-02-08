import DataBaseConnection.MySqlConnector;

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
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            // Если пользователь уже аутентифицирован, перенаправляем его на домашнюю страницу
            response.sendRedirect("/home");
        } else {
            // Иначе, показываем страницу входа
            String errorMessage = request.getParameter("error");
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            if (authenticateUser(email, password)) {
                // Если аутентификация успешна, сохраняем пользователя в сессии и перенаправляем на домашнюю страницу
                HttpSession session = request.getSession();
                session.setAttribute("user", email);
                response.sendRedirect("/home");
            } else {
                // Иначе, перенаправляем обратно на страницу входа с сообщением об ошибке
                response.sendRedirect("/login?error=Authentication Failed. Incorrect email or password, please try again.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("/login?error=Database Error");
        }
    }

    private boolean authenticateUser(String email, String password) throws SQLException {
        try (Connection connection = MySqlConnector.getConnection()) {
            String query = "SELECT * FROM clients_data WHERE email = ? AND password = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, password);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    return resultSet.next();
                }
            }
        }
    }
}
