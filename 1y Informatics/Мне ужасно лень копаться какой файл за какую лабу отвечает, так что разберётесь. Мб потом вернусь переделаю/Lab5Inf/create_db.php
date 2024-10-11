<!DOCTYPE HTML PUBLIC>
<html lang = rus>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>BDSITE</title>
</head>
<body>

<h1>Работа с БД и бла-бла-бла</h1>
<div>
<?php

    $db_host = 'localhost';
    $db_user = 'root';
    $db_password = '1234';
    $db_name = 'MySiteDB';

//    echo phpinfo();

//    $con = new mysqli('khjvk');
    $connection = mysqli_connect($db_host, $db_user, $db_password, $db_name);
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

?>