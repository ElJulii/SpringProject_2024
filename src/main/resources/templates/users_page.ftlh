<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Users Management</title>
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

        table {
            width: 80%;
            margin-top: 20px;
            border-collapse: collapse;
        }

        table, th, td {
            border: 1px solid #00008B;
        }

        th, td {
            padding: 10px;
            text-align: left;
        }

        th {
            background-color: #00008B;
            color: white;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        tr:hover {
            background-color: #e6f7ff;
        }

        button.delete-button {
            background-color: #ff4d4d;
            color: white;
            border: none;
            border-radius: 5px;
            padding: 5px 10px;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        button.delete-button:hover {
            background-color: #ff1a1a;
        }
    </style>
    <script>
        function confirmDelete(email, userID) {
            if (confirm("Are you sure you want to delete user with email: " + email + "?")) {
                window.location.href = "/users/delete/" + userID;
            }
        }
    </script>
</head>
<body>
<header>
    <div class="left-section">
        <a href="http://localhost:8080/profile/admin"><button>BACK</button></a>
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
    <h1>User Management</h1>
    <table>
        <tr>
            <th>ID</th>
            <th>Email</th>
            <th>Actions</th>
        </tr>
        <#list usersList as user>
            <tr>
                <td>${user.id}</td>
                <td>${user.email}</td>
                <td>
                    <button class="delete-button" onclick="confirmDelete('${user.email}', '${user.id}')">Delete USER</button>
                </td>
            </tr>
        </#list>
    </table>
</main>
</body>
</html>
