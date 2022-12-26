<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Комнат:</title>
</head>
<body>
<h1>Комната номер :</h1>
<ul>
    <c:forEach var="room" items="${requestScope.roomlist}">
        Номер комнаты: ${room.number}<br>
            Этаж: ${room.floor}<br>
            Стоимость за сутки: ${room.dayPrice}<br>

    </c:forEach>
</ul>
</body>
</html>
