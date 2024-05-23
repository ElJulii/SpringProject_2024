<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Edit Recipe</title>
    <style>
        body {
            font-family: 'Helvetica', Arial, sans-serif;
            background-color: #f0f8ff;
            color: #000080;
            margin: 0;
            padding: 0;
        }

        header {
            background-color: #000000;
            height: 75px;
            padding: 10px;
            text-align: center;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        header img {
            width: 50px;
            height: 30px;
            margin: 0 10px;
        }

        header a {
            text-decoration: none;
        }

        header button {
            padding: 10px 20px;
            background-color: #ffffff;
            color: #000080;
            border: 2px solid #000080;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s, color 0.3s;
        }

        header button:hover {
            background-color: #000080;
            color: #ffffff;
        }

        h1 {
            text-align: center;
            margin-top: 20px;
        }

        form {
            max-width: 600px;
            margin: 0 auto;
            padding: 20px;
            background-color: #ffffff;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .form-group {
            margin-bottom: 15px;
        }

        .form-group label {
            display: block;
            margin-bottom: 5px;
        }

        .form-group input, .form-group textarea {
            width: 100%;
            padding: 10px;
            box-sizing: border-box;
            border: 1px solid #ced4da;
            border-radius: 5px;
        }

        .form-group button {
            width: 100%;
            padding: 10px 15px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        .form-group button:hover {
            background-color: #0056b3;
        }

        img {
            width: 30%;
            display: block;
            margin: 0 auto;
            border-radius: 5px;
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
<header>
    <a href="http://localhost:8080/profile/user"><button>BACK</button></a>
    <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQsLdLOAlrg6Wi7fFX_mKmB-VVD7U4GgaFEY06nPS-BeA&s" alt="Icon 1">
    <img src="https://banner2.cleanpng.com/20180926/vjt/kisspng-spectacled-bear-andes-glasses-mammal-support-ecuadorian-students-ecology-project-inte-5bac495f3a52b6.4918360715380176312389.jpg" alt="Icon 2">
    <img src="https://upload.wikimedia.org/wikipedia/commons/e/e8/Flag_of_Ecuador.svg" alt="Ecuador Flag" height="40">
</header>
<h1>Edit Recipe</h1>
<img src="/profile/user/${recipe.storageFileName}">
<form action="/recipes/edit" method="post">
    <input type="hidden" name="id" value="${recipe.id}">
    <div class="form-group">
        <label for="nameRecipe">Name of Recipe</label>
        <input type="text" id="nameRecipe" name="nameRecipe" value="${recipe.nameRecipe}">
    </div>
    <div class="form-group">
        <label for="ingredients">Ingredients</label>
        <textarea id="ingredients" name="ingredients">${recipe.ingredients}</textarea>
    </div>
    <div class="form-group">
        <label for="steps">Steps</label>
        <textarea id="steps" name="steps">${recipe.steps}</textarea>
    </div>
    <div class="form-group">
        <button type="submit">Save Changes</button>
    </div>
</form>
</body>
</html>



