<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Регистрация</title>
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
            max-width: 500px; 
            width: 100%; 
            box-shadow: 0 20px 60px rgba(0,0,0,0.3);
        }
        h1 { color: #e94560; text-align: center; margin-bottom: 30px; font-size: 32px; }
        .form-group { margin-bottom: 25px; }
        .form-group label { display: block; color: #ffffff; margin-bottom: 8px; font-weight: 500; }
        .form-group input { 
            width: 100%; 
            padding: 14px; 
            background: rgba(255, 255, 255, 0.05); 
            border: 1px solid rgba(255, 255, 255, 0.1); 
            border-radius: 10px; 
            color: #ffffff; 
            font-size: 16px; 
        }
        .form-group input:focus { 
            outline: none; 
            border-color: #e94560; 
            box-shadow: 0 0 10px rgba(233, 69, 96, 0.3); 
        }
        .form-group input::placeholder { color: #666; }
        .btn { 
            width: 100%; 
            padding: 16px; 
            background: linear-gradient(135deg, #e94560 0%, #c73e54 100%); 
            color: white; 
            border: none; 
            border-radius: 10px; 
            font-size: 18px; 
            font-weight: 600; 
            cursor: pointer; 
            transition: all 0.3s; 
        }
        .btn:hover { 
            background: linear-gradient(135deg, #ff6b6b 0%, #e94560 100%); 
            box-shadow: 0 5px 20px rgba(233, 69, 96, 0.4); 
            transform: translateY(-2px); 
        }
        .error { 
            background: rgba(244, 67, 54, 0.2); 
            border: 1px solid rgba(244, 67, 54, 0.3); 
            color: #f44336; 
            padding: 15px; 
            border-radius: 10px; 
            margin-bottom: 20px; 
        }
        .info {
            background: rgba(33, 150, 243, 0.2);
            border: 1px solid rgba(33, 150, 243, 0.3);
            color: #2196F3;
            padding: 15px;
            border-radius: 10px;
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Регистрация</h1>
        
        <%
            String error = request.getParameter("error");
            String success = request.getParameter("success");
            
            if ("name".equals(error)) {
        %>
            <div class="error">Введите имя (от 2 до 50 символов)</div>
        <%
            } else if ("email".equals(error)) {
        %>
            <div class="error">Введите корректный email</div>
        <%
            } else if ("password".equals(error)) {
        %>
            <div class="error">Пароль должен быть не менее 6 символов</div>
        <%
            } else if (success != null) {
        %>
            <div class="info">Регистрация успешна! Теперь вы можете войти.</div>
        <%
            }
        %>
        
        <form action="register" method="post">
            <div class="form-group">
                <label for="name">Имя:</label>
                <input type="text" id="name" name="name" required placeholder="Введите ваше имя">
            </div>
            
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" required placeholder="example@email.com">
            </div>
            
            <div class="form-group">
                <label for="password">Пароль:</label>
                <input type="password" id="password" name="password" required pattern=".{6,}" placeholder="Минимум 6 символов">
            </div>
            
            <button type="submit" class="btn">Зарегистрироваться</button>
        </form>
    </div>
</body>
</html>
