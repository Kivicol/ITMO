<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang = eng>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>examples</title>
</head>

<body>

<h1>Да, у меня не заработал ни денвер, ни MAMP</h1>

<div>
    <body style='background-color:darkgrey'>

    <?php
    $a = 10;
    echo "a = ".$a."<br>";
    $b = 20;
    echo "b = ".$b."<br>";

    echo "a + b = ".($a + $b)."<br><br>";
    ?>
</div>

<div>
    <?php
    $c = $a + $b;
    echo "c = ".$c."<br><br>";
    ?>
</div>

<div><?php
    $c *= 3;
    echo "c*3 = ".$c."<br><br>";
    ?>
</div>

<div>
    <?php
    echo "c/(b-a) = ".$c/($b-$a)."<br><br>";
    ?>
</div>

<div>
    <?php
    $p = "Программа";
    $b = "работает";
    $result = $p." ".$b;
    echo $result;
    ?>
</div>

<div>
    <?php
    $result .= " "."хорошо"."<br><br>";
    echo $result;
    ?>
</div>

<div>
    <?php
    $q = 5;
    $w = 7;
    echo "\r\n";
    echo "q = ".$q." ";
    echo "w = ".$w."<br>";
    $q = $q + $w;
    $w = $q - $w;
    $q = $q - $w;
    echo "Пересменка:<br>";
    echo "q = ".$q." ";
    echo "w = ".$w."<br><br>";
    ?>
</div>

<div>
    <?php
    for($i = 23;$i<=78;$i++){
        echo $i.' ';
    }
    echo "<br><br>";
    ?>
</div>

<div>
    <?php
    $array = array("red","orange","yellow","green","baby blue","blue","violet","black","grey","white");
    for($i=0;$i<count($array);$i++){
        echo "<li>".$array[$i]."</li>";
    }
    echo "<br>"
    ?>
</div>

<div>
    <?php
    echo "При помощи while:<br>";
    $j = 0;
    $rand_arr = array(100);
    while ($j<100){
        $rand_arr[$j] = rand(0,100);
        echo $rand_arr[$j]." ";
        $j++;
    }
    echo "<br><br>При помощи foreach:<br>";
    foreach ($rand_arr as $key){
        echo $key." ";
    }
    echo "<br><br>";
    ?>
</div>


<div>
    <?php
    $day = date('w');
    switch($day){
        case "1":
            echo "Понедельник";
            break;
        case "2":
            echo "Вторник";
            break;
        case "3":
            echo "Среда";
            break;
        case "4":
            echo "Четверг";
            break;
        case "5":
            echo "Пятница";
            break;
        case "6":
            echo "Суббота";
            break;
        case "0":
            echo "Воскресенье";
            break;
    }
    echo "<br><br>";
    ?>
</div>


<div>
    <form name="form" action="" method="get">
        <input type="number" name="subject" placeholder = "Введите число вау ёу">
    </form>
    <?php
    if (isset($_GET['subject'])) {
        if ($_GET['subject'] != NULL) {
            echo $_GET['subject'] . " -- то число, которое Вы ввели<br>";
            function getPlus10($arg1)
            {
                $arg1 += 10;
                echo $arg1 - 10 . " + 10 = " . $arg1;
            }

            echo getPlus10($_GET['subject']) . " -- произошла магия<br>Круто, да?<br><br>";
            unset($_GET['subject']);
        }
        else if ($_GET['subject'] == NULL) {
            echo "Ждёмс";
        }
    }
    else{
        echo "^^^^^^<br>Ждёмс<br><br>";
    }
    ?>
</div>

<a href="Lab5Inf.html">Просто ссылка обратно</a><br/>

</body>

</html>