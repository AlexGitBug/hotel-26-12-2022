<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
  <title>Регистрация</title>
</head>
<body>
<%@include file="header.jsp" %>
<img width="252" height="252" src="${pageContext.request.contextPath}/images/users/test1.jpg" alt="User image">
<%--<img width="252" src="https://i.ytimg.com/vi/o5s5LLyQa6c/maxresdefault.jpg">--%>

<form action="${pageContext.request.contextPath}/registration" method="post" enctype="multipart/form-data">
  <label for="name"><fmt:message key="page.registration.name"/>
    <input type="text" name="name" id="name" required>
  </label><br>
  <label for="lastname"><fmt:message key="page.registration.lastname"/>
    <input type="text" name="lastname" id="lastname" required>
  </label><br>
  <label for="birthday"><fmt:message key="page.registration.birthday"/>
    <input type="date" name="birthday" id="birthday" required>
  </label><br>
  <label for="imageid"><fmt:message key="page.registration.image"/>
    <input type="file" name="image" id="imageid" required>
  </label><br>
  <label for="telephoneNumber"><fmt:message key="page.registration.telephoneNumber"/>
    <input type="text" name="telephoneNumber" id="telephoneNumber" required>
  </label><br>
  <label for="email"><fmt:message key="page.registration.email"/>
    <input type="text" name="email" id="email" required>
  </label><br>
  <label for="password"><fmt:message key="page.registration.password"/>
    <input type="password" name="password" id="password" required>
  </label><br><fmt:message key="page.registration.role"/>
  <select name="role" id="role">
    <c:forEach var="role" items="${requestScope.roles}">
      <option value="${role.id}">${role.rank}</option>
    </c:forEach>
  </select><br>
  <button type="submit"><fmt:message key="page.registration.submit.button"/></button>
</form>
</body>
</html>
