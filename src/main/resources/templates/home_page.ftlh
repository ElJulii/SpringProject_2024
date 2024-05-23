<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Home Page</title>
    <style>
        body {
            font-family: 'Helvetica', Arial, sans-serif;
            background-color: #f0f8ff;
            color: #000080;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        header {
            width: 100%;
            height: 75px;
            background-color: #000000;
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 10px;
        }

        header .left-section, header .center-section, header .right-section {
            display: flex;
            align-items: center;
        }

        header .center-section {
            justify-content: center;
            flex-grow: 1;
            text-align: center;
        }

        header .center-section h1 {
            font-size: 40px;
            margin: 0;
        }

        header .center-section h1 .yellow {
            color: yellow;
        }

        header .center-section h1 .blue {
            color: blue;
        }

        header .center-section h1 .red {
            color: red;
        }

        header img {
            height: 50px;
            margin: 0 10px;
        }

        header a {
            margin: 0 10px;
        }

        header button {
            background-color: #ffffff;
            color: #00008B;
            border: 2px solid #00008B;
            border-radius: 5px;
            padding: 10px 20px;
            font-size: 14px;
            cursor: pointer;
            transition: background-color 0.3s, color 0.3s;
        }

        header button:hover {
            background-color: #00008B;
            color: #ffffff;
        }

        h1 {
            margin-top: 20px;
        }

        hr {
            width: 80%;
            margin-top: 20px;
            border: none;
            border-top: 1px solid #000080;
        }

        input[type="text"], textarea {
            width: 40%;
            padding: 10px;
            margin: 5px 0;
            border: 1px solid #000080;
            border-radius: 5px;
            box-sizing: border-box;
        }

        input[type="file"] {
            width: 80%;
            padding: 10px;
            margin: 5px 0;
        }

        button {
            background-color: #ffffff;
            color: #00008B;
            border: 2px solid #00008B;
            border-radius: 5px;
            padding: 10px 20px;
            font-size: 14px;
            cursor: pointer;
            transition: background-color 0.3s, color 0.3s;
        }

        button:hover {
            background-color: #00008B;
            color: #ffffff;
        }

        .post {
            margin-top: 20px;
            width: 60%;
            padding: 20px;
            border: 1px solid #000080;
            border-radius: 5px;
            margin-left: 20%;
            text-align: center;
        }

        .post img {
            width: 60%;
            border-radius: 5px;
        }

        .post button {
            margin-top: 10px;
        }

        main {
            text-align: center;
        }

        .photo {
            width: 300px;
        }

        .pagination {
            display: flex;
            justify-content: center;
            margin-top: 20px;
        }

        .pagination a {
            margin: 0 5px;
            padding: 10px 15px;
            background-color: #007bff;
            color: white;
            text-decoration: none;
        }

        .pagination a.active {
            background-color: #0056b3;
        }
        .likes-container {
            display: flex;
            gap: 10px;
        }
    </style>
</head>
<body>
<header>
    <div class="left-section">
        <a href="http://localhost:8080/welcome"><button>EXIT</button></a>
        <#if role == "ADMIN">
            <a href="http://localhost:8080/profile/admin"><button>PROFILE</button></a>
        <#else>
            <a href="http://localhost:8080/profile/user"><button>PROFILE</button></a>
        </#if>
    </div>
    <div class="center-section">
        <h1>
            <span class="yellow">Ecuadorian</span>
            <span class="blue">Tasty</span>
            <span class="red">Food!</span>
        </h1>
        <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQsLdLOAlrg6Wi7fFX_mKmB-VVD7U4GgaFEY06nPS-BeA&s" alt="Icon 1">
        <img src="https://banner2.cleanpng.com/20180926/vjt/kisspng-spectacled-bear-andes-glasses-mammal-support-ecuadorian-students-ecology-project-inte-5bac495f3a52b6.4918360715380176312389.jpg" alt="Icon 2">
    </div>
    <div class="right-section">
        <img src="https://upload.wikimedia.org/wikipedia/commons/e/e8/Flag_of_Ecuador.svg" alt="Ecuador Flag" height="40">
    </div>
</header>
<h1>RECIPES THAT YOU WILL ENJOY</h1>
<div id="recipes-container">
    <#list recipes as recipe>
        <div class="post">
            <h2>Chef ${recipe.username}</h2>
            <h3>Recipe: ${recipe.nameRecipe}</h3>
            <a href="http://localhost:8080/comments/${recipe.id}">
                <img  class="photo" src="/profile/user/${recipe.storageFileName}" alt="${recipe.nameRecipe}">
            </a>
            <p>Likes: ${recipe.likeCount}</p>

            <h3>Ingredients:</h3>
            <p>${recipe.ingredients}</p>
            <h3>Steps:</h3>
            <p>${recipe.steps}</p>

            <form method="post" action="/comment">
                <input type="hidden" name="recipeId" value="${recipe.id}">
                <input type="text" name="comment" placeholder="Add a comment">
                <button type="submit">Say it!</button>
            </form>

            <div class="likes-container">
                <form method="post" action="/like">
                    <input type="hidden" name="recipeId" value="${recipe.id}">
                    <button type="submit">Like</button>
                </form>
                <form method="post" action="/unlike">
                    <input type="hidden" name="recipeId" value="${recipe.id}">
                    <button type="submit">Unlike</button>
                </form>
            </div>

            <hr>
        </div>
    </#list>
</div>

<div class="pagination">
    <#list 1..totalPages as pageNum>
        <#if pageNum == currentPage>
            <span class="active">${pageNum}</span>
        <#else>
            <a href="#" onclick="loadPage(${pageNum})">${pageNum}</a>
        </#if>
    </#list>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>

    function loadPage(page) {
        $.get("/home?page=" + page, function(data) {
            $("#recipes-container").html($(data).filter("#recipes-container").html());
            $(".pagination").html($(data).filter(".pagination").html());
        });
    }

    function clearSearch() {
        document.querySelector("input[name='search']").value = '';
        document.getElementById('search-form').submit();
    }
</script>

</body>
</html>