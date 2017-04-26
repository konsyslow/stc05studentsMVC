<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: PoGo
  Date: 20.04.2017
  Time: 10:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>
        <c:choose>
            <c:when test="${requestScope.student.id ne null }">
                Студент ID #<c:out value="${requestScope.student.id}" escapeXml="true"></c:out>
            </c:when>
            <c:otherwise>
                Новый студент
            </c:otherwise>
        </c:choose>
    </title>
</head>
<body>

<h2>
    <c:choose>
        <c:when test="${requestScope.student.id ne null }">
            Редактирование студента ID #<c:out value="${requestScope.student.id}" escapeXml="true"></c:out>
        </c:when>
        <c:otherwise>
            Новый студент
        </c:otherwise>
    </c:choose>
</h2>

<form method="POST" action="${pageContext.request.contextPath}/students/">
    <c:if test="${requestScope.student.id ne null }">
        <input type="hidden" name="id" value="${requestScope.student.id}" />
    </c:if>
    <p>ФИО: <input type="text" name="name" value="${requestScope.student.name}" /></p>
    <p>Возраст: <input type="text" name="age" value="${requestScope.student.age}" /></p>
    <p>Группа: <input type="text" name="group_id" value="${requestScope.student.groupId}" /></p>

    <h2>
        <c:choose>
            <c:when test="${requestScope.student.id ne null }">
                <button type="submit">Сохранить</button>
            </c:when>
            <c:otherwise>
                <button type="submit">Добавить</button>
            </c:otherwise>
        </c:choose>
    </h2>
</form>

</body>
</html>
