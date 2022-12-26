<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 26.12.2022
  Time: 20:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Переход на главную страницу</title>
</head>
<body>
<c:if test="${not empty sessionScope.user}">
  <div id="main page">
    <form action="${pageContext.request.contextPath}/mainpage" method="post">
      <button type="submit">Main page</button>
    </form>
  </div>
</c:if>
</body>
</html>
