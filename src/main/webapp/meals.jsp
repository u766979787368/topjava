<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ru">
<head>
    <title>Meals</title>
    <style type="text/css">
        TABLE {

            border-collapse: collapse; /* Убираем двойные линии между ячейками */
        }
        TD, TH {
            padding: 3px; /* Поля вокруг содержимого таблицы */
            border: 1px solid black; /* Параметры рамки */
        }

    </style>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>

<table>
    <tr>
        <th >Date</th>
        <th width="400">Description</th>
        <th >Calories</th>
        <th width="100"></th>
        <th width="100"></th>

    </tr>
<c:forEach items="${mealToList}" var="d" >
    <c:set var="color" value="green"/>
    <c:if test="${d.excess}">
        <c:set var="color" value="red"/>
    </c:if>

    <tr style="color: ${color}">

        <td >${d.dateTime}</td>
        <td>${d.description}</td>
        <td>${d.calories}</td>
        <td></td>
        <td></td>
    </tr>
</c:forEach>
</table>
</body>
</html>
