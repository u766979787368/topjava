<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>AddMeal</title>

</head>

<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>AddMeal</h2>
<form method="POST" action='meals'>
    <table>
        <tr>
            <td width="100">DateTime :</td>
            <td><input type="date" name="date"/> <input type="time" name="time"/></td>
        </tr>
        <tr>
            <td>Description :</td>
            <td><input type="text" name="description"/></td>
        </tr>
        <tr>
            <td>Calories :</td>
            <td><input type="text" name="calories"/></td>
        </tr>
    </table>

    <input type="submit" value="Save"/>

</form>
<form action="meals">
    <button>Cancel</button>
</form>

</body>
</html>