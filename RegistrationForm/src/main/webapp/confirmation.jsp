<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.User" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Подтверждение регистрации</title>
    <style>
        * { box-sizing: border-box; margin: 0; padding: 0; }
        body { 
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; 
            background: linear-gradient(135deg, #1a1a2e 0%, #16213e 50%, #0f3460 100%); 
            min-height: 100vh; 
            display: flex; 
            justify-content: center; 
            align-items: center; 
            padding: 20px;
        }
        .container { 
            background: rgba(255, 255, 255, 0.05); 
            backdrop-filter: blur(10px);
            border: 1px solid rgba(255, 255, 255, 0.1);
            border-radius: 20px; 
            padding: 50px; 
            max-width: 550px; 
            width: 100%; 
            box-shadow: 0 20px 60px rgba(0,0,0,0.3);
        }
        h1 { color: #4CAF50; text-align: center; margin-bottom: 20px; font-size: 32px; }
        .subtitle { color: #a0a0a0; text-align: center; margin-bottom: 30px; font-size: 16px; }
        .success-icon { 
            width: 80px; 
            height: 80px; 
            background: linear-gradient(135deg, #4CAF50 0%, #45a049 100%);
            border-radius: 50%; 
            display: flex; 
            align-items: center; 
            justify-content: center;
            margin: 0 auto 20px;
            font-size: 40px;
            color: white;
            box-shadow: 0 5px 20px rgba(76, 175, 80, 0.4);
        }
        .user-card { 
            background: rgba(255, 255, 255, 0.03); 
            border: 1px solid rgba(255, 255, 255, 0.05);
            border-radius: 15px; 
            padding: 25px; 
            margin-bottom: 25px;
        }
        .user-row { 
            display: flex; 
            justify-content: space-between; 
            padding: 15px 0; 
            border-bottom: 1px solid rgba(255, 255, 255, 0.05);
        }
        .user-row:last-child { border-bottom: none; }
        .label { color: #a0a0a0; font-weight: 500; }
        .value { color: #ffffff; font-weight: 600; }
        .btn { 
            display: block;
            width: 100%; 
            padding: 16px; 
            background: linear-gradient(135deg, #e94560 0%, #c73e54 100%); 
            color: white; 
            text-decoration: none;
            text-align: center;
            border-radius: 10px; 
            font-size: 18px; 
            font-weight: 600; 
            transition: all 0.3s; 
            margin-bottom: 15px;
        }
        .btn:hover { 
            background: linear-gradient(135deg, #ff6b6b 0%, #e94560 100%); 
            box-shadow: 0 5px 20px rgba(233, 69, 96, 0.4); 
            transform: translateY(-2px); 
        }
        .btn-secondary {
            background: rgba(255, 255, 255, 0.1);
        }
        .btn-secondary:hover {
            background: rgba(255, 255, 255, 0.2);
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="success-icon">&#10004;</div>
        <h1>Регистрация успешна!</h1>
        <p class="subtitle">Ваши данные были успешно сохранены</p>
        
        <%
            User user = (User) session.getAttribute("user");
            if (user == null) {
                response.sendRedirect("register.jsp");
                return;
            }
        %>
        
        <div class="user-card">
            <div class="user-row">
                <span class="label">Имя:</span>
                <span class="value"><%= user.getName() %></span>
            </div>
            <div class="user-row">
                <span class="label">Email:</span>
                <span class="value"><%= user.getEmail() %></span>
            </div>
            <div class="user-row">
                <span class="label">Пароль:</span>
                <span class="value"><%= "*".repeat(user.getPassword().length()) %></span>
            </div>
        </div>
        
        <a href="register.jsp" class="btn">Зарегистрировать ещё одного</a>
        <a href="index.html" class="btn btn-secondary">На главную</a>
    </div>
</body>
</html>
