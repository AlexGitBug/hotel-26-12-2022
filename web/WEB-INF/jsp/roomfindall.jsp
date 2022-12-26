<<<<<<< HEAD

=======
>>>>>>> origin/master
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<<<<<<< HEAD
    <title>Категория комнат:</title>
=======
  <title>Категория комнат:</title>
>>>>>>> origin/master
</head>
<body>
<h1>Категория комнат:</h1>
<ul>
<<<<<<< HEAD
    <c:forEach var="room" items="${requestScope.roomlist}">
        <li>
            <a href="/room?id=${room.id}">${room.number} ${room.status}</a>
        </li>
    </c:forEach>
</ul>
</body>
</html>
=======
  <c:forEach var="room" items="${requestScope.roomlist}">
    <li>
     <a href="/room?id=${room.id}">${room.description}</a>
    </li>
  </c:forEach>
</ul>
</body>
</html>

>>>>>>> origin/master
