package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import listener.ActiveUsersListener;
import listener.AppContextListener;

/**
 * Servlet для отображения статистики активных пользователей.
 */
@WebServlet("/stats")
public class StatsServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(true);
        
        // Увеличиваем счётчик总 пользователей (только для новых пользователей)
        Boolean isNewUser = (Boolean) session.getAttribute("isNewUser");
        if (isNewUser == null || isNewUser) {
            AppContextListener.incrementTotalUsers();
            session.setAttribute("isNewUser", false);
        }
        
        // Обновляем время последнего обращения
        session.setAttribute("lastAccessTime", new java.util.Date());
        session.setAttribute("sessionCount", ActiveUsersListener.getActiveSessionsCount());
        
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        out.println("<!DOCTYPE html>");
        out.println("<html lang='ru'>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("<title>Статистика - ActiveUsers</title>");
        out.println("<style>");
        out.println("* { box-sizing: border-box; margin: 0; padding: 0; }");
        out.println("body { font-family: Arial, sans-serif; background: linear-gradient(135deg, #1a1a2e 0%, #16213e 50%, #0f3460 100%); min-height: 100vh; padding: 20px; }");
        out.println(".container { max-width: 800px; margin: 0 auto; }");
        out.println(".card { background: rgba(255, 255, 255, 0.05); backdrop-filter: blur(10px); border: 1px solid rgba(255, 255, 255, 0.1); border-radius: 15px; padding: 30px; margin-bottom: 20px; box-shadow: 0 10px 40px rgba(0,0,0,0.3); }");
        out.println("h1 { color: #ffffff; margin-bottom: 20px; text-align: center; }");
        out.println("h2 { color: #e94560; margin-bottom: 15px; border-bottom: 2px solid rgba(233, 69, 96, 0.3); padding-bottom: 10px; }");
        out.println(".stat-item { display: flex; justify-content: space-between; padding: 15px 0; border-bottom: 1px solid rgba(255,255,255,0.05); }");
        out.println(".stat-item:last-child { border-bottom: none; }");
        out.println(".stat-label { color: #a0a0a0; font-weight: 500; }");
        out.println(".stat-value { color: #ffffff; font-weight: bold; font-size: 18px; }");
        out.println(".stat-value.highlight { color: #4CAF50; font-size: 24px; }");
        out.println(".nav { text-align: center; margin-top: 20px; }");
        out.println(".nav a { display: inline-block; padding: 12px 30px; background: linear-gradient(135deg, #e94560 0%, #c73e54 100%); color: white; text-decoration: none; border-radius: 8px; margin: 0 10px; transition: all 0.3s; }");
        out.println(".nav a:hover { background: linear-gradient(135deg, #ff6b6b 0%, #e94560 100%); box-shadow: 0 5px 20px rgba(233, 69, 96, 0.4); transform: translateY(-2px); }");
        out.println(".nav .refresh { background: linear-gradient(135deg, #4CAF50 0%, #45a049 100%) !important; }");
        out.println(".nav .refresh:hover { background: linear-gradient(135deg, #66bb6a 0%, #4CAF50 100%) !important; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class='container'>");
        out.println("<div class='card'>");
        out.println("<h1>📊 Статистика активных пользователей</h1>");
        
        out.println("<h2>📈 Общая статистика</h2>");
        out.println("<div class='stat-item'>");
        out.println("<span class='stat-label'>Активных сессий сейчас:</span>");
        out.println("<span class='stat-value highlight'>" + ActiveUsersListener.getActiveSessionsCount() + "</span>");
        out.println("</div>");
        out.println("<div class='stat-item'>");
        out.println("<span class='stat-label'>Всего пользователей зашло:</span>");
        out.println("<span class='stat-value'>" + AppContextListener.getTotalUsers() + "</span>");
        out.println("</div>");
        
        out.println("<h2>🔑 Информация о вашей сессии</h2>");
        out.println("<div class='stat-item'>");
        out.println("<span class='stat-label'>ID сессии:</span>");
        out.println("<span class='stat-value'>" + session.getId() + "</span>");
        out.println("</div>");
        out.println("<div class='stat-item'>");
        out.println("<span class='stat-label'>Время создания сессии:</span>");
        out.println("<span class='stat-value'>" + new java.util.Date(session.getCreationTime()) + "</span>");
        out.println("</div>");
        out.println("<div class='stat-item'>");
        out.println("<span class='stat-label'>Последнее обращение:</span>");
        out.println("<span class='stat-value'>" + new java.util.Date(session.getLastAccessedTime()) + "</span>");
        out.println("</div>");
        out.println("<div class='stat-item'>");
        out.println("<span class='stat-label'>Максимальное время неактивности:</span>");
        out.println("<span class='stat-value'>" + (session.getMaxInactiveInterval() / 60) + " мин</span>");
        out.println("</div>");
        
        out.println("</div>");
        
        out.println("<div class='nav'>");
        out.println("<a href='index.html'>🏠 На главную</a>");
        out.println("<a href='stats' class='refresh'>🔄 Обновить</a>");
        out.println("</div>");
        
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }
}
