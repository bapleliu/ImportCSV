<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <title>List</title>
</head>
<body>
<table border="1" cellpadding="5" cellspacing="5">
    <tr>
        <td colspan="2" bgcolor="#D3EDF6" align="center"><a href="index.jsp"> Menu:</a></td>
        <td colspan="2" bgcolor="#D3EDF6" valign="top" align="center"><a href="overview.do">List overview</a></td>
        <td colspan="2" bgcolor="#D3EDF6" valign="top" align="center"><a href="import.jsp">Import CSV</a></td>
    </tr>
    <tr>
        <th><a href="overview.do?page=${pageNumber}&sort=${sortStyle}&column=id">Contact ID</a></th>
        <th><a href="overview.do?page=${pageNumber}&sort=${sortStyle}&column=name">Name</a></th>
        <th><a href="overview.do?page=${pageNumber}&sort=${sortStyle}&column=surname">Surname</a></th>
        <th><a href="overview.do?page=${pageNumber}&sort=${sortStyle}&column=login">Login</a></th>
        <th><a href="overview.do?page=${pageNumber}&sort=${sortStyle}&column=email">E-mail</a></th>
        <th><a href="overview.do?page=${pageNumber}&sort=${sortStyle}&column=phoneNumber">Phone number</a></th>
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
    <td><a href="overview.do?page=${pageNumber-1}&sort=${sortStyle}&column=${columnName}">Previous</a></td>
</c:if>

<%--Displaying pages.--%>
<table border="1" cellpadding="5" cellspacing="5">
    <tr>
        <c:forEach begin="1" end="${numberOfPages}" var="i">
            <c:choose>
                <c:when test="${pageNumber eq i}">
                    <td>${i}</td>
                </c:when>
                <c:otherwise>
                    <td><a href="/overview.do?page=${i}&sort=${sortStyle}&column=${columnName}">${i}</a></td>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </tr>
</table>

<%--Displaying link to next --%>
<c:if test="${pageNumber lt numberOfPages}">
    <td><a href="overview.do?page=${pageNumber + 1}&sort=${sortStyle}&column=${columnName}">Next</a></td>
</c:if>
</body>
</html>
