package listener;

import java.util.concurrent.atomic.AtomicInteger;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Слушатель контекста для подсчёта общего количества пользователей.
 */
@WebListener
public class AppContextListener implements ServletContextListener {

    // Счётчик всех пользователей за всё время
    private static final AtomicInteger totalUsers = new AtomicInteger(0);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        
        System.out.println("===========================================");
        System.out.println("Приложение ActiveUsers запущено!");
        System.out.println("Время запуска: " + new java.util.Date());
        System.out.println("Имя приложения: " + context.getServletContextName());
        System.out.println("===========================================");
        
        // Сохраняем время запуска в контексте
        context.setAttribute("startTime", new java.util.Date());
        context.setAttribute("totalUsers", 0);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("===========================================");
        System.out.println("Приложение ActiveUsers остановлено!");
        System.out.println("Время остановки: " + new java.util.Date());
        System.out.println("Всего пользователей за время работы: " + totalUsers.get());
        System.out.println("===========================================");
    }

    /**
     * Увеличить счётчик总 пользователей.
     */
    public static int incrementTotalUsers() {
        return totalUsers.incrementAndGet();
    }

    /**
     * Получить общее количество пользователей.
     */
    public static int getTotalUsers() {
        return totalUsers.get();
    }
}
