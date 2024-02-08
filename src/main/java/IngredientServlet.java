import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "IngredientServlet", value = "/ingredients")
public class IngredientServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String[] selectedIngredients = request.getParameterValues("selected_ingredients");


        if (selectedIngredients != null && selectedIngredients.length > 0) {

            List<String> selectedIngredientList = new ArrayList<>();


            for (String ingredientId : selectedIngredients) {
                selectedIngredientList.add(ingredientId);
            }


            HttpSession session = request.getSession();

            session.setAttribute("selectedIngredients", selectedIngredientList);
        }


        response.sendRedirect(request.getContextPath() + "/orderconfirmation");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/ingredients.jsp").forward(request, response);
    }
}
