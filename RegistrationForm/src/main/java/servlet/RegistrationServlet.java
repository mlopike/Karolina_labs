package servlet;

import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html lang='ru'>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("<title>Регистрация</title>");
        out.println("<style>");
        out.println("* { box-sizing: border-box; margin: 0; padding: 0; }");
        out.println("body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; background: linear-gradient(135deg, #1a1a2e 0%, #16213e 50%, #0f3460 100%); min-height: 100vh; display: flex; justify-content: center; align-items: center; padding: 20px; }");
        out.println(".container { background: rgba(255, 255, 255, 0.05); backdrop-filter: blur(10px); border: 1px solid rgba(255, 255, 255, 0.1); border-radius: 20px; padding: 50px; max-width: 500px; width: 100%; box-shadow: 0 20px 60px rgba(0,0,0,0.3); }");
        out.println("h1 { color: #e94560; text-align: center; margin-bottom: 30px; font-size: 32px; }");
        out.println(".form-group { margin-bottom: 25px; }");
        out.println(".form-group label { display: block; color: #ffffff; margin-bottom: 8px; font-weight: 500; }");
        out.println(".form-group input { width: 100%; padding: 14px; background: rgba(255, 255, 255, 0.05); border: 1px solid rgba(255, 255, 255, 0.1); border-radius: 10px; color: #ffffff; font-size: 16px; }");
        out.println(".form-group input:focus { outline: none; border-color: #e94560; box-shadow: 0 0 10px rgba(233, 69, 96, 0.3); }");
        out.println(".form-group input::placeholder { color: #666; }");
        out.println(".btn { width: 100%; padding: 16px; background: linear-gradient(135deg, #e94560 0%, #c73e54 100%); color: white; border: none; border-radius: 10px; font-size: 18px; font-weight: 600; cursor: pointer; transition: all 0.3s; }");
        out.println(".btn:hover { background: linear-gradient(135deg, #ff6b6b 0%, #e94560 100%); box-shadow: 0 5px 20px rgba(233, 69, 96, 0.4); transform: translateY(-2px); }");
        out.println(".error { background: rgba(244, 67, 54, 0.2); border: 1px solid rgba(244, 67, 54, 0.3); color: #f44336; padding: 15px; border-radius: 10px; margin-bottom: 20px; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class='container'>");
        out.println("<h1>Регистрация</h1>");

        String error = request.getParameter("error");
        if (error != null) {
            out.println("<div class='error'>");
            if ("name".equals(error)) {
                out.println("Введите имя (от 2 до 50 символов)");
            } else if ("email".equals(error)) {
                out.println("Введите корректный email");
            } else if ("password".equals(error)) {
                out.println("Пароль должен быть не менее 6 символов");
            } else {
                out.println("Ошибка валидации данных");
            }
            out.println("</div>");
        }

        out.println("<form action='register' method='post'>");
        out.println("<div class='form-group'>");
        out.println("<label for='name'>Имя:</label>");
        out.println("<input type='text' id='name' name='name' required placeholder='Введите ваше имя'>");
        out.println("</div>");

        out.println("<div class='form-group'>");
        out.println("<label for='email'>Email:</label>");
        out.println("<input type='email' id='email' name='email' required placeholder='example@email.com'>");
        out.println("</div>");

        out.println("<div class='form-group'>");
        out.println("<label for='password'>Пароль:</label>");
        out.println("<input type='password' id='password' name='password' required minlength='6' placeholder='Минимум 6 символов'>");
        out.println("</div>");

        out.println("<button type='submit' class='btn'>Зарегистрироваться</button>");
        out.println("</form>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (name == null || name.trim().length() < 2 || name.trim().length() > 50) {
            response.sendRedirect("register?error=name");
            return;
        }

        if (email == null || !email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            response.sendRedirect("register?error=email");
            return;
        }

        if (password == null || password.length() < 6) {
            response.sendRedirect("register?error=password");
            return;
        }

        User user = new User(name.trim(), email.trim(), password);
        request.getSession().setAttribute("user", user);

        response.sendRedirect("confirmation.jsp");
    }
}
