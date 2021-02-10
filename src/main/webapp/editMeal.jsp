<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>EditMeal</title>

</head>

<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>EditMeal</h2>
<form method="POST" action='meals?id=${id}'>
    <table>
        <tr>
            <td width="100">DateTime :</td>
            <td><input type="date" name="date" value="${meal.date}"/> <input type="time" name="time"
                                                                             value="${meal.time}"/></td>
        </tr>
        <tr>
            <td>Description :</td>
            <td><input type="text" name="description" value="${meal.description}"/></td>
        </tr>
        <tr>
            <td>Calories :</td>
            <td><input type="text" name="calories" value="${meal.calories}"/></td>
        </tr>
    </table>

    <input type="submit" value="Save"/>

</form>
<form action="meals">
    <button>Cancel</button>
</form>

</body>
</html>