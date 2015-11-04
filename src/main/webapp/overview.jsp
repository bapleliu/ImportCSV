<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <title>List</title>
</head>
<body>
<table border="1" cellpadding="5" cellspacing="5">
    <tr>
        <th>Contact ID</th>
        <th>Name</th>
        <th>Surname</th>
        <th>Login</th>
        <th>E-mail</th>
        <th>Phone number</th>
    </tr>

    <c:forEach var="contact" items="${contacts}">
        <tr>
            <td>${contact.id}</td>
            <td>${contact.name}</td>
            <td>${contact.surname}</td>
            <td>${contact.login}</td>
            <td>${contact.email}</td>
            <td>${contact.phoneNumber}</td>
        </tr>
    </c:forEach>
</table>

<%--Displaying link to previous. --%>
<c:if test="${pageNumber gt 1}">
    <td><a href="overview.do?page=${pageNumber-1}">Previous</a></td>
</c:if>

<%--Displaying number of page.--%>
<table border="1" cellpadding="5" cellspacing="5">
    <tr>
        <c:forEach begin="1" end="${numberOfPages}" var="i">
            <c:choose>
                <c:when test="${pageNumber eq i}">
                    <td>${i}</td>
                </c:when>
                <c:otherwise>
                    <td><a href="/overview.do?page=${i}">${i}</a></td>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </tr>
</table>

<%--Displaying link to next --%>
<c:if test="${pageNumber lt numberOfPages}">
    <td><a href="overview.do?page=${pageNumber + 1}">Next</a></td>
</c:if>
</body>
</html>
