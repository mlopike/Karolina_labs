package listener;

import java.util.concurrent.atomic.AtomicInteger;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Слушатель сессий для подсчёта активных пользователей.
 */
public class ActiveUsersListener implements HttpSessionListener {

    // Счётчик активных сессий
    private static final AtomicInteger activeSessions = new AtomicInteger(0);

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        int count = activeSessions.incrementAndGet();
        System.out.println("=== Сессия создана ===");
        System.out.println("ID сессии: " + se.getSession().getId());
        System.out.println("Время создания: " + se.getSession().getCreationTime());
        System.out.println("Активных сессий: " + count);
        System.out.println("======================");
        
        // Сохраняем счётчик в сессии
        se.getSession().setAttribute("sessionCount", count);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        int count = activeSessions.decrementAndGet();
        System.out.println("=== Сессия уничтожена ===");
        System.out.println("ID сессии: " + se.getSession().getId());
        System.out.println("Последнее обращение: " + se.getSession().getLastAccessedTime());
        System.out.println("Активных сессий: " + count);
        System.out.println("========================");
    }

    /**
     * Получить текущее количество активных сессий.
     */
    public static int getActiveSessionsCount() {
        return activeSessions.get();
    }
}
