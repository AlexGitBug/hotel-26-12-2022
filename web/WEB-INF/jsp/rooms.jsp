<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Комната</title>
</head>
<body>
<form action="/roominfo" method="post">
  <label for="number">Номер комнаты:
    <select name="number" id="number">
      <c:forEach var="number" items="${requestScope.numbers}">
        <option value="${number}">${number}</option>
      </c:forEach>
    </select>
  </label><br>
  <label for="floor">Этаж:
    <select name="floor" id="floor">
      <c:forEach var="floor" items="${requestScope.floors}">
        <option value="${floor}">${floor}</option>
      </c:forEach>
    </select>
  </label><br>Стоимость аренды:
  <label for="dayprice">
    <input type="text" name="dayprice" id="dayprice">
  </label><br>
  <label for="status">Статус:
    <select name="status" id="status">
      <c:forEach var="status" items="${requestScope.statuses}">
        <option value="${status}">${status}</option>
      </c:forEach>
    </select>
  </label><br>
  <button type="submit">Выбрать комнату</button>
  <label><br>
    <br>
    Список всех комнат:
  <ul><c:forEach var="room" items="${requestScope.roomlist}">
      <li>
        <a href="/room?id=${room.id}">Описание(номер комнаты, статус): ${room.number} - ${room.status}</a>
      </li>
    </c:forEach>
  </ul>
  </label><br>
</form>
</body>
</html>