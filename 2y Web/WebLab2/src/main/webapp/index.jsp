<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<style><%@include file="stylesheets.css"%></style>
<meta charset="utf-8">
<head>
    <meta charset="UTF-8">
    <title>ЛР1</title>
    <link href="stylesheets.css" rel="stylesheet">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>

<body>

<header title="header">
    <h1 class="info">Дмитришен К.Р., группа Р3224, вариант 408547</h1>
</header>

<main id="layout">

    <div class="form-n-canvas">
        <form class="info-block" id="info-block" action="controller" method="GET">
            <label class="label">Координата X:</label>
            <div class="coord-header">
                <label class="coord-picker"><input type="radio" name="x" value="-5" required>-5</label>
                <label class="coord-picker"><input type="radio" name="x" value="-4">-4</label>
                <label class="coord-picker"><input type="radio" name="x" value="-3">-3</label>
                <label class="coord-picker"><input type="radio" name="x" value="-2">-2</label>
                <label class="coord-picker"><input type="radio" name="x" value="-1">-1</label>
                <label class="coord-picker"><input type="radio" name="x" value="0">0</label>
                <label class="coord-picker"><input type="radio" name="x" value="1">1</label>
                <label class="coord-picker"><input type="radio" name="x" value="2">2</label>
                <label class="coord-picker"><input type="radio" name="x" value="3">3</label>
            </div>

            <label class="label">Координата Y:</label>
            <div class="coord-header">
                <label for="y"></label><input class="input-box" type="text" name="y" id="y" placeholder="{-5...5}"
                                              maxlength="10" required>
            </div>

            <label class="label">Радиус R:</label>
            <div class="coord-header">
                <label for="r"></label><input class="input-box" type="text" name="r" id="r" placeholder="{1...4}"
                                              maxlength="10" required>
            </div>

            <button type="submit" class="table-buttons">Отправить данные</button>

        </form>

        <figure class="canvas">
            <svg height="600" width="600" xmlns="http://www.w3.org/2000/svg">

                <rect x="200" y="100" width="100" height="200" fill="#3399ff" fill-opacity="1" stroke="#3399ff"></rect>

                <polygon fill="#3399ff" fill-opacity="1" points="500,300 300,200 300,300" stroke="#3399ff"></polygon>

                <path d="M 200 300 A 100 100, 1, 0, 0, 300 400 L 300 300 Z" fill="#3399ff" fill-opacity="1"
                      stroke="#3399ff"></path>

                <line stroke="black" x1="0" x2="600" y1="300" y2="300"></line>
                <line stroke="black" x1="300" x2="300" y1="0" y2="600"> </line>
                <polygon fill="black" points="300,0 288,30 312,30" stroke="white"></polygon>
                <polygon fill="black" points="600,300 585,312 585,288" stroke="white"></polygon>

                <line stroke="gray" x1="400" x2="400" y1="305" y2="295"></line>
                <line stroke="gray" x1="500" x2="500" y1="305" y2="295"></line>
                <line stroke="gray" x1="100" x2="100" y1="305" y2="295"></line>
                <line stroke="gray" x1="200" x2="200" y1="305" y2="295"></line>
                <line stroke="gray" x1="305" x2="295" y1="200" y2="200"></line>
                <line stroke="gray" x1="305" x2="295" y1="100" y2="100"></line>
                <line stroke="gray" x1="305" x2="295" y1="400" y2="400"></line>
                <line stroke="gray" x1="305" x2="295" y1="500" y2="500"></line>

                <text fill="black" x="395" y="290">R/2</text>
                <text fill="black" x="498" y="290">R</text>
                <text fill="black" x="80" y="290">-R</text>
                <text fill="black" x="190" y="290">-R/2</text>
                <text fill="black" x="310" y="205">R/2</text>
                <text fill="black" x="310" y="105">R</text>
                <text fill="black" x="310" y="405">-R/2</text>
                <text fill="black" x="310" y="505">-R</text>
                <text fill="black" x="310" y="10">Y</text>
                <text fill="black" x="590" y="290">X</text>

                <circle cx="300" cy="300" id="target-dot" r="0" stroke="black" fill="black"></circle>
            </svg>
        </figure>
    </div>


    <div class="table-block">
        <table class="table-text" cellspacing="6" cellpadding="15" border="1">
            <caption class="label"><b>Результаты</b></caption>
            <tbody>
            <tr>
                <th>Координата X</th>
                <th>Координата Y</th>
                <th>Радиус R</th>
                <th>Факт попадания в область</th>
            </tr>
            <jsp:include page="table.jsp"/>
            </tbody>
        </table>
    </div>
</main>
<script><%@include file="script.js"%></script>
</body>

</html>