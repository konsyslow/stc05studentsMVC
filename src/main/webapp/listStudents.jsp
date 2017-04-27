
<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 19.04.2017
  Time: 18:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>List</title>
</head>
<body>
<% String message = (String) request.getAttribute("value");%>
<h1>
    <%=message%>
</h1>
<form method="get">
    <td><input type="button" value="Добавить" onclick="location.href = '<%= request.getContextPath() %>/student'"></td>

 <table border="1">
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Age</th>
            <th>Group</th>
        </tr>
        </thead>
        <tbody>
    <c:forEach items="${listStudents}" var="student">
        <tr>
            <td><c:out value="${student.id}"></c:out></td>
            <td><c:out value="${student.name}"></c:out></td>
            <td><c:out value="${student.age}"></c:out></td>
            <td><c:out value="${student.groupId}"></c:out></td>
        </tr>
    </c:forEach>
        <tbody/>
</table>
</form>
</body>
</html>

