<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
  <title>Заказы</title>
</head>
<body>

<%@include file="header.jsp" %>

<form action="/order" method="post">
    <label for="userinfoid">User:
      <select name="userinfoid" id="userinfoid">
        <c:forEach var="userinfoid" items="${requestScope.userinfoid}">
          <option value="${userinfoid.id}">${userinfoid.firstName} ${userinfoid.lastName}</option>
        </c:forEach>
      </select>
    </label><br>
  <label>Rooms:
    <select name="roomid" id="roomid">
      <c:forEach var="roomid" items="${requestScope.roomid}">
        <option value="${roomid.id}">${roomid.number}</option>
      </c:forEach>
    </select>
  </label><br>
  <label for="begintime">Begin Time:
    <input type="date" name="begintime" id="begintime">
  </label><br>
  <label for="endtime">End Time:
    <input type="date" name="endtime" id="endtime">
  </label><br>
    <label for="conditions">Condition:
      <select name="conditions" id="conditions">
      <c:forEach var="conditions" items="${requestScope.conditions}">
        <option value="${conditions}">${conditions}</option>
      </c:forEach>
      </select>
    </label><br>Message:
  <label for="message">
    <input type="text" name="message" id="message">
  </label><br>
  <button type="submit">Send</button><br>

</form>

<%@include file="footer.jsp"%>

</body>
</html>