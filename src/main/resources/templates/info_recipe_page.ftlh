<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Recipe Info</title>
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

        .post img {
            width: 80%;
            border-radius: 5px;
        }

        .likes-table {
            margin: 20px 0;
            border-collapse: collapse;
            text-align: center;
        }

        .likes-table th, .likes-table td {
            padding: 10px;
        }


        .comments-table {
            width: 80%;
            border-collapse: collapse;
            margin: 20px 0;
        }

        .comments-table, .comments-table th, .comments-table td {
            border: 0 solid #000080;
        }

        .comments-table th, .comments-table td {
            padding: 10px;
            text-align: left;
        }

        .comments-table .action-cell {
            border: none;
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
    </style>
</head>
<body>
<header>
    <div class="left-section">
        <a href="http://localhost:8080/home"><button>HOME</button></a>
    </div>
    <div class="center-section">
        <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQsLdLOAlrg6Wi7fFX_mKmB-VVD7U4GgaFEY06nPS-BeA&s" alt="Icon 1">
        <img src="https://banner2.cleanpng.com/20180926/vjt/kisspng-spectacled-bear-andes-glasses-mammal-support-ecuadorian-students-ecology-project-inte-5bac495f3a52b6.4918360715380176312389.jpg" alt="Icon 2">
    </div>
    <div class="right-section">
        <img src="https://upload.wikimedia.org/wikipedia/commons/e/e8/Flag_of_Ecuador.svg" alt="Ecuador Flag" height="40">
    </div>
</header>
<hr>
<h1>${recipeName}</h1>
<img src="/profile/user/${storageFileName}" alt="Recipe Image" style="width: 500px;">
<hr>
<h2>Likes of Users</h2>
<table class="likes-table">
    <#list likes as like>
        <tr>
            <td>${like.username}</td>
        </tr>
    </#list>
</table>
<hr>
<h2>Comments</h2>
<table class="comments-table">
    <tr>
        <th>Username</th>
        <th>Comment</th>
    </tr>
    <#list comments as comment>
        <tr>
            <td>${comment.username}</td>
            <td>${comment.comment}</td>
            <td class="action-cell">
                <#if comment.username == currentUsername || role == "ADMIN">
                    <form method="post" action="/comments/delete/${comment.id}">
                        <button type="submit">Delete</button>
                    </form>
                </#if>
            </td>
        </tr>
    </#list>
</table>
</body>
</html>