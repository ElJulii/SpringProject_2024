<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <script
            src="https://code.jquery.com/jquery-3.6.0.js"
            integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
            crossorigin="anonymous"></script>
    <title>Document</title>
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
        }

        .post img {
            width: 70%;
            border-radius: 20px;
        }

        .post button {
            margin-top: 10px;
        }

        main {
            text-align: center;
        }
        .upload-file {
            width: 50%;
            padding: 10px;
            border: 1px solid #000080;
            border-radius: 5px;
            margin-bottom: 20px;
            margin-left: 25%;
        }
    </style>
    <script>
        function confirmDelete(username, nameRecipe, postId) {
            if (confirm("Are you sure that you want to delete this post: " + nameRecipe + "?")) {
                window.location.href = "/profile/user/delete/" + postId;
            }
        }

        function sendFile() {
            let nameRecipe = document.getElementById("nameRecipe").value;
            let ingredients = document.getElementById("ingredients").value;
            let steps = document.getElementById("steps").value;

            let formData = new FormData();
            let files = ($('#file'))[0]['files'];
            [].forEach.call(files, function (file, i, files) {
                formData.append("file", file);
            });

            formData.append("nameRecipe", nameRecipe);
            formData.append("ingredients", ingredients);
            formData.append("steps", steps);

            $.ajax({
                type: "POST",
                url: "/profile/user",
                data: formData,
                processData: false,
                contentType: false
            })
                .done(function (response) {
                    window.location.href = response;
                })
                .fail(function () {
                    alert('Error');
                });
        }
    </script>
</head>
<body>
<header>
    <div class="left-section">
        <a href="http://localhost:8080/welcome"><button>EXIT</button></a>
        <a href="http://localhost:8080/home"><button>HOME</button></a>
        <a href="http://localhost:8080/user/edit"><button>EDIT PROFILE</button></a>
    </div>
    <div class="center-section">
        <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQsLdLOAlrg6Wi7fFX_mKmB-VVD7U4GgaFEY06nPS-BeA&s" alt="Icon 1">
        <img src="https://banner2.cleanpng.com/20180926/vjt/kisspng-spectacled-bear-andes-glasses-mammal-support-ecuadorian-students-ecology-project-inte-5bac495f3a52b6.4918360715380176312389.jpg" alt="Icon 2">
    </div>
    <div class="right-section">
        <img src="https://upload.wikimedia.org/wikipedia/commons/e/e8/Flag_of_Ecuador.svg" alt="Ecuador Flag" height="40">
    </div>
</header>
<main>
    <h1>Welcome ${username}!</h1>
    <hr>
    <h2>Do you want to add a new recipe?</h2>
    <div class="upload-file">
        <input type="text" name="nameRecipe" id="nameRecipe" placeholder="NAME OF RECIPE">
        <textarea id="ingredients" placeholder="INGREDIENTS"></textarea>
        <input type="file" id="file" name="file" placeholder="name file">
        <textarea id="steps" placeholder="WHICH ARE THE STEPS?"></textarea>
        <button onclick="sendFile()">post</button>
        <div id="recipe-name"></div>
    </div>
    <br>
    <h2>My Posts</h2>
    <#list recipes as recipe>
        <div class="post">
            <h3>${recipe.nameRecipe}</h3>
            <img class="photo" src="/profile/user/${recipe.storageFileName}" alt="${recipe.nameRecipe}">
            <h3>Ingredients:</h3>
            <p>${recipe.ingredients}</p>
            <h3>Steps:</h3>
            <p>${recipe.steps}</p>
            <a href="http://localhost:8080/comments/${recipe.id}">
                <button>Information</button>
            </a>
            <button onclick="confirmDelete('${recipe.username}', '${recipe.nameRecipe}', '${recipe.id}')">Delete Recipe</button>
            <a href="http://localhost:8080/recipes/edit?id=${recipe.id}">
                <button>Edit Recipe</button>
            </a>
        </div>
        <hr>
    </#list>
</main>
</body>
</html>
