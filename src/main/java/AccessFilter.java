import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class AccessFilter implements Filter {
    private static final String[] allowedURLs = {"/login", "/register", "/index.jsp"};

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String requestURI = httpRequest.getRequestURI();

        if (isAllowed(requestURI)) {
            chain.doFilter(request, response);
        } else {
            HttpSession session = httpRequest.getSession(false);
            if (session != null && session.getAttribute("user") != null) {
                // Пользователь аутентифицирован, разрешаем доступ к странице
                chain.doFilter(request, response);
            } else {
                // Пользователь не аутентифицирован, перенаправляем на страницу входа
                httpResponse.sendRedirect(httpRequest.getContextPath() + "/login");
            }
        }
    }

    @Override
    public void destroy() {
    }

    private boolean isAllowed(String requestURI) {
        for (String url : allowedURLs) {
            if (requestURI.endsWith(url)) {
                return true;
            }
        }
        return false;
    }
}
