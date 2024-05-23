<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Log In</title>
    <style>
        body {
            font-family: 'Helvetica', Arial, sans-serif;
            background: url('https://imageio.forbes.com/specials-images/imageserve/6477e7e4d5a222cb969b8c15/Ministry-of-Tourism-of-Ecuador-Typical-Dishes/960x0.jpg?format=jpg&width=960') no-repeat center center fixed;
            background-size: cover;
            color: #000080;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            position: relative;
        }

        body::before {
            content: "";
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(255, 255, 255, 0.5);
            z-index: -1;
        }

        .form-container {
            background-color: #ffffff;
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        form {
            display: flex;
            flex-direction: column;
            width: 300px;
        }

        form h1 {
            color: #00008B;
            margin-bottom: 20px;
            font-size: 24px;
        }

        form input {
            margin-bottom: 20px;
            padding: 10px;
            font-size: 16px;
            border: 1px solid #00008B;
            border-radius: 5px;
        }

        form input[type="submit"] {
            background-color: #00008B;
            color: white;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        form input[type="submit"]:hover {
            background-color: #4682b4;
        }

        form input::placeholder {
            color: #00008B;
        }
    </style>
</head>
<body>
<div class="form-container">
    <form action="/logIn" method="post">
        <h1>Log in</h1>
        <input name="email" type="email" placeholder="EMAIL" required>
        <input name="password" type="password" placeholder="PASSWORD" required>
        <input type="submit" value="SUBMIT">
    </form>
</div>
</body>
</html>
