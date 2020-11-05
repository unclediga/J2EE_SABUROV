<%@ page import="ru.unclediga.saburov.student.domain.University" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Unuversity List</title>
</head>
<body>
<H2>List of universities ${today}</H2>
<table border="1">
    <tbody>
    <jsp:useBean id="universities" scope="request" type="java.util.List"/>
    <c:forEach var="university" items="${universities}" varStatus="status">
        <c:if test="${status.count%2 == 1}">
            <tr style="background-color: yellow">
        </c:if>
        <c:if test="${status.count%2 == 0}">
            <tr style="background-color: greenyellow">
        </c:if>
        <td>${status.count}</td>
        <td>${university.universityId}</td>
        <td>${university.universityName}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
