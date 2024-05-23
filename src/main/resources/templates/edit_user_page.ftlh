<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Edit Profile</title>
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
            height: 75%;
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

        main {
            text-align: center;
            width: 100%;
            display: flex;
            justify-content: center;
        }

        .form-container {
            background-color: #ffffff;
            border: 1px solid #000080;
            border-radius: 10px;
            padding: 20px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            width: 20%;
            margin-top: 20px;
        }

        h1 {
            margin-top: 0;
        }

        .form-group {
            margin-bottom: 15px;
            width: 100%;
        }

        .form-group label {
            display: block;
            margin-bottom: 5px;
            color: #000080;
        }

        .form-group input {
            width: 100%;
            padding: 10px;
            box-sizing: border-box;
            border: 1px solid #000080;
            border-radius: 5px;
        }

        .form-group button {
            padding: 10px 15px;
            background-color: #ffffff;
            color: #00008B;
            border: 2px solid #00008B;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s, color 0.3s;
        }

        .form-group button:hover {
            background-color: #00008B;
            color: #ffffff;
        }
    </style>
</head>
<body>
<header>
    <div class="left-section">
        <a href="http://localhost:8080/profile/user"><button>Back</button></a>
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
    <div class="form-container">
        <h1>Edit Profile</h1>
        <form action="/user/edit" method="post">
            <div class="form-group">
                <label for="firstname">First Name</label>
                <input type="text" id="firstname" name="firstname" value="${user.firstname}">
            </div>
            <div class="form-group">
                <label for="lastname">Last Name</label>
                <input type="text" id="lastname" name="lastname" value="${user.lastname}">
            </div>
            <div class="form-group">
                <button type="submit">Save Changes</button>
            </div>
        </form>
    </div>
</main>
</body>
</html>

