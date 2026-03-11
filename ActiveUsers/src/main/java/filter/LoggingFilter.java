package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Фильтр для логирования всех запросов.
 */
@WebFilter("/*")
public class LoggingFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("[LoggingFilter] Инициализация фильтра...");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, 
                         FilterChain chain) throws IOException, ServletException {
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession(false);
        
        // Логируем запрос
        System.out.println("===========================================");
        System.out.println("[LOG] Запрос получен:");
        System.out.println("  Метод: " + httpRequest.getMethod());
        System.out.println("  URL: " + httpRequest.getRequestURL());
        System.out.println("  URI: " + httpRequest.getRequestURI());
        System.out.println("  RemoteAddr: " + httpRequest.getRemoteAddr());
        System.out.println("  SessionID: " + (session != null ? session.getId() : "нет сессии"));
        
        // Логируем параметры
        if (!httpRequest.getParameterMap().isEmpty()) {
            System.out.println("  Параметры:");
            httpRequest.getParameterMap().forEach((key, values) -> {
                for (String value : values) {
                    System.out.println("    " + key + " = " + value);
                }
            });
        }
        
        // Логируем заголовки
        System.out.println("  Заголовки:");
        java.util.Enumeration<String> headers = httpRequest.getHeaderNames();
        while (headers.hasMoreElements()) {
            String headerName = headers.nextElement();
            String headerValue = httpRequest.getHeader(headerName);
            System.out.println("    " + headerName + " = " + headerValue);
        }
        
        long startTime = System.currentTimeMillis();
        
        // Передаём запрос дальше
        chain.doFilter(request, response);
        
        long endTime = System.currentTimeMillis();
        System.out.println("[LOG] Время обработки: " + (endTime - startTime) + " мс");
        System.out.println("===========================================");
    }

    @Override
    public void destroy() {
        System.out.println("[LoggingFilter] Фильтр уничтожен");
    }
}
