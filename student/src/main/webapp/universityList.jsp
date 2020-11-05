<%@ page import="ru.unclediga.saburov.student.domain.University" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <title>Unuversity List</title>
</head>
<body>
<H2>List of universities ${today}</H2>
<%
    final List<University> universities = (List<University>) request.getAttribute("universities");
    for (University university : universities) {
        out.print(university.getUniversityName());
        out.print("<br/>");
    }
%>
</body>
</html>
