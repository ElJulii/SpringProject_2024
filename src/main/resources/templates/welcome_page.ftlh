<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Welcome</title>
    <style>
        body {
            font-family: 'Helvetica', Arial, sans-serif;
            background-color: #f0f8ff;
            color: #000080;
            margin: 0;
            display: flex;
            flex-direction: column;
            min-height: 100vh;
        }

        header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            background-color: #000000;
            padding: 20px 40px;
            color: white;
        }

        header img {
            height: 50px;
        }

        header .icons {
            display: flex;
            gap: 20px;
        }

        header .icons img {
            height: 50px;
        }

        header .buttons {
            display: flex;
            gap: 20px;
        }

        header .buttons a {
            text-decoration: none;
        }

        header .buttons button {
            background-color: white;
            color: black;
            border: 2px solid #00008B;
            padding: 10px 20px;
            font-size: 10px;
            cursor: pointer;
            transition: background-color 0.3s, color 0.3s;
        }

        header .buttons button:hover {
            background-color: #00008B;
            color: white;
        }

        main {
            display: flex;
            justify-content: space-between;
            align-items: flex-start;
            padding: 40px;
            flex: 1;
        }

        .photo {
            max-width: 50%;
            height: auto;
            margin-right: 40px;
        }

        .description {
            max-width: 50%;
            font-size: 20px;
            line-height: 1.8;
        }

        .description a {
            color: #1e90ff;
            text-decoration: none;
        }

        .description a:hover {
            text-decoration: underline;
        }

        h1 {
            color: #1e90ff;
            text-align: center;
            margin-top: 40px;
            font-size: 36px;
        }

        h2 {
            color: #4682b4;
            text-align: center;
            margin-bottom: 40px;
            font-size: 28px;
        }

        h3 {
            font-size: 15px;
        }

        footer {
            background-color: #000000;
            color: white;
            text-align: center;
            padding: 20px;
            margin-top: 40px;
        }

        footer p {
            margin: 0;
            font-size: 16px;
        }
    </style>
</head>
<body>
<header>
    <img src="https://upload.wikimedia.org/wikipedia/commons/e/e8/Flag_of_Ecuador.svg" alt="Ecuador Flag">
    <div class="icons">
        <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQsLdLOAlrg6Wi7fFX_mKmB-VVD7U4GgaFEY06nPS-BeA&s" alt="Icon 1">
        <img src="https://banner2.cleanpng.com/20180926/vjt/kisspng-spectacled-bear-andes-glasses-mammal-support-ecuadorian-students-ecology-project-inte-5bac495f3a52b6.4918360715380176312389.jpg" alt="Icon 2">
    </div>
    <div class="buttons">
        <div>
            <h3>Register</h3>
            <a href="http://localhost:8080/signUp"><button>SIGN UP</button></a>
        </div>
        <div>
            <h3>Already have an account?</h3>
            <a href="http://localhost:8080/logIn"><button>LOG IN</button></a>
        </div>
    </div>
</header>
<h1>
    Welcome to Ecuadorian Tasty Food!
</h1>
<main>
    <img class="photo" src="https://imageio.forbes.com/specials-images/imageserve/6477e7e4d5a222cb969b8c15/Ministry-of-Tourism-of-Ecuador-Typical-Dishes/960x0.jpg?format=jpg&width=960" alt="Ecuadorian Tasty Food">
    <div class="description">
        <p>
            Ecuadorian gastronomy is a varied way of preparing foods and drinks made in the country, enriched by the multiculturalism, contributions from the various regions, and climates that make it up.
        </p>
        <p>
            <a href="https://www.youtube.com/watch?v=l9Ol9s-lbAY&ab_channel=EcuadorTravel" target="_blank">Do you know Ecuador?</a>
            <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ_ALMs0aWOXlbDCoc6jMrww4SsClGgTJ67tZmuobJkmg&s" alt="Ecuador Image" style="height: 50px; vertical-align: middle;">
        </p>
    </div>
</main>
<h2>
    Here is the best site to look for, share and add recipes.
</h2>
<footer>
    <p>&copy; 2024 Ecuadorian Tasty Food. All rights reserved.</p>
</footer>
</body>
</html>