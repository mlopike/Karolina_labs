<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="listener.ActiveUsersListener, listener.AppContextListener" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Active Users - JSP</title>
    <style>
        * { box-sizing: border-box; margin: 0; padding: 0; }
        body { 
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; 
            background: linear-gradient(135deg, #1a1a2e 0%, #16213e 50%, #0f3460 100%); 
            min-height: 100vh; 
            padding: 20px;
        }
        .container { max-width: 900px; margin: 0 auto; }
        .card { 
            background: rgba(255, 255, 255, 0.05); 
            backdrop-filter: blur(10px);
            border: 1px solid rgba(255, 255, 255, 0.1);
            border-radius: 15px; 
            padding: 30px; 
            margin-bottom: 20px; 
            box-shadow: 0 10px 40px rgba(0,0,0,0.3);
        }
        h1 { color: #ffffff; margin-bottom: 20px; text-align: center; }
        h2 { color: #e94560; margin-bottom: 15px; border-bottom: 2px solid rgba(233, 69, 96, 0.3); padding-bottom: 10px; }
        
        .users-count { 
            text-align: center; 
            padding: 40px; 
            background: linear-gradient(135deg, #e94560 0%, #c73e54 100%);
            border-radius: 15px;
            color: white;
            margin-bottom: 20px;
            box-shadow: 0 10px 40px rgba(233, 69, 96, 0.4);
        }
        .users-count .number { font-size: 72px; font-weight: bold; text-shadow: 0 2px 10px rgba(0,0,0,0.3); }
        .users-count .label { font-size: 20px; opacity: 0.95; }
        
        .info-grid { display: grid; grid-template-columns: 1fr 1fr; gap: 20px; }
        .info-item { 
            background: rgba(255, 255, 255, 0.03); 
            padding: 20px; 
            border-radius: 10px;
            border: 1px solid rgba(255, 255, 255, 0.05);
        }
        .info-item .label { color: #a0a0a0; font-size: 14px; margin-bottom: 8px; }
        .info-item .value { color: #ffffff; font-size: 18px; font-weight: bold; }
        
        .nav { text-align: center; margin-top: 20px; }
        .nav a { 
            display: inline-block; 
            padding: 12px 30px; 
            background: linear-gradient(135deg, #e94560 0%, #c73e54 100%);
            color: white; 
            text-decoration: none; 
            border-radius: 8px; 
            margin: 0 10px; 
            transition: all 0.3s;
        }
        .nav a:hover { 
            background: linear-gradient(135deg, #ff6b6b 0%, #e94560 100%);
            box-shadow: 0 5px 20px rgba(233, 69, 96, 0.4);
            transform: translateY(-2px);
        }
        
        .session-info { 
            background: rgba(76, 175, 80, 0.1); 
            padding: 20px; 
            border-radius: 10px; 
            margin-top: 15px;
            border: 1px solid rgba(76, 175, 80, 0.3);
        }
        .session-info h3 { color: #4CAF50; margin-bottom: 15px; }
        .session-info p { margin: 8px 0; color: #a0a0a0; }
        .session-info strong { color: #ffffff; }
        
        table { width: 100%; border-collapse: collapse; margin-top: 15px; }
        th, td { padding: 12px; text-align: left; border-bottom: 1px solid rgba(255,255,255,0.05); }
        th { background: rgba(233, 69, 96, 0.2); color: #e94560; }
        tr:hover { background: rgba(255, 255, 255, 0.03); }
    </style>
</head>
<body>
    <div class="container">
        <div class="card">
            <h1>👥 Активные пользователи</h1>
            
            <div class="users-count">
                <div class="number"><%= ActiveUsersListener.getActiveSessionsCount() %></div>
                <div class="label">пользователей онлайн</div>
            </div>
            
            <div class="info-grid">
                <div class="info-item">
                    <div class="label">📊 Всего пользователей зашло:</div>
                    <div class="value"><%= AppContextListener.getTotalUsers() %></div>
                </div>
                <div class="info-item">
                    <div class="label">🕐 Время на сервере:</div>
                    <div class="value"><%= new java.util.Date() %></div>
                </div>
            </div>
            
            <div class="session-info">
                <h3>ℹ️ Информация о вашей сессии:</h3>
                <p><strong>ID сессии:</strong> <%= session.getId() %></p>
                <p><strong>Время создания:</strong> <%= new java.util.Date(session.getCreationTime()) %></p>
                <p><strong>Последнее обращение:</strong> <%= new java.util.Date(session.getLastAccessedTime()) %></p>
                <p><strong>Макс. время неактивности:</strong> <%= session.getMaxInactiveInterval() / 60 %> мин</p>
            </div>
            
            <%
                String username = (String) session.getAttribute("username");
                if (username == null) {
                    username = "User_" + session.getId().substring(0, 8);
                    session.setAttribute("username", username);
                }
            %>
            
            <div class="session-info" style="background: rgba(255, 152, 0, 0.1); border-color: rgba(255, 152, 0, 0.3);">
                <h3 style="color: #FF9800;">👤 Ваше имя пользователя:</h3>
                <p><strong><%= username %></strong></p>
            </div>
        </div>
        
        <div class="card">
            <h2>🧪 Тестирование сессии</h2>
            <p style="color: #a0a0a0; margin-bottom: 15px;">
                Откройте эту страницу в нескольких вкладках или браузерах, 
                чтобы увидеть изменение счётчика активных пользователей.
            </p>
            
            <table>
                <tr>
                    <th>Действие</th>
                    <th>Описание</th>
                </tr>
                <tr>
                    <td>🔄 Обновить страницу</td>
                    <td>Время последнего обращения обновится</td>
                </tr>
                <tr>
                    <td>🌐 Открыть в новой вкладке</td>
                    <td>Сессия останется той же (общий ID)</td>
                </tr>
                <tr>
                    <td>🔓 Открыть в режиме инкогнито</td>
                    <td>Создастся новая сессия, счётчик увеличится</td>
                </tr>
                <tr>
                    <td>🗑️ Закрыть все вкладки</td>
                    <td>Сессия уничтожится через таймаут, счётчик уменьшится</td>
                </tr>
            </table>
        </div>
        
        <div class="nav">
            <a href="index.html">🏠 На главную</a>
            <a href="stats">📊 Статистика (Servlet)</a>
            <a href="users.jsp">🔄 Обновить</a>
        </div>
    </div>
</body>
</html>
