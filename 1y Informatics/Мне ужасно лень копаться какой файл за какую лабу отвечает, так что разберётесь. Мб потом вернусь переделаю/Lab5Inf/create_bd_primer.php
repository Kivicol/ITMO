<!DOCTYPE HTML PUBLIC>
<html lang = rus>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Сайт на Denwer</title>
</head>
<body>

<h1>Это 4 страница сайта:)</h1>
<div>
<?php
    $db_host = 'localhost';
    $db_user = 'root';
    $db_password = 'qwerty0987654321';
    $db_name = 'MySiteDB';

    $connection = mysqli_connect($db_host, $db_user, $db_password);

    if (!$connection) {
        die("Нет соединения с сервером: " . mysqli_connect_error());
    }

    $create_db = "CREATE DATABASE IF NOT EXISTS $db_name";

    if (mysqli_query($connection, $create_db)) {
        echo "База данных создана или уже существует<br>";
    } else {
        die("Ошибка при создании базы данных: " . mysqli_error($connection));
    }

    $db_selected = mysqli_select_db($connection, $db_name);

    if (!$db_selected) {
        die("Не удалось выбрать базу данных: " . mysqli_error($connection));
    }

    $sql = "CREATE TABLE IF NOT EXISTS users (
        id INT AUTO_INCREMENT PRIMARY KEY,
        name TEXT NOT NULL,
        age INT,
        gender TEXT,
        region TEXT,
        PRIMARY KEY (id)
       ) ENGINE = InnoDB";       
    $sql_fill_table = "INSERT INTO
    users (name, age, gender, region)
   VALUES
    ('Серёга', 25, 'male', 'Гатчина'),
    ('Анатолий', 18, 'male', 'Санкт-Петербург'),
    ('Мария', 32, 'female', 'Севастополь'),
    ('Гарик', 40, 'male', 'Уфа'),
    ('Елизавета', 21, 'female', 'Калининград');";  

    if (mysqli_query($connection, $sql)) {
        echo "Создание таблицы завершено";
    } else {
        die("Ошибка при создании таблицы: " . mysqli_error($connection));
    }
?>

<form action = "create_admin.php" method="get">
<input type="submit" value="Создать Админа!">
</form>

</div>
</body>
</html>

