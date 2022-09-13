<%--
  Created by IntelliJ IDEA.
  User: oybek
  Date: 9/11/2022
  Time: 2:14 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <jsp:include page="../navbar.jsp"></jsp:include>
    <title>Records</title>
</head>
<body>

<div class="container text-center">

    <h1>======= RECORDS =======</h1>
    <h1 style="color: red">${error}</h1>
    <a class="btn btn-outline-primary my-4" href="/records/get-form">+ Add new record</a>
    <div class="row">
        <div class="col-md-8 offset-2">
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Book Title</th>
                    <th scope="col">Returned</th>
                    <th scope="col">Date</th>
                    <th scope="col">User</th>
                    <th scope="col">Actions</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${records}" var="record" varStatus="loop">
                    <tr>
                        <th scope="row">${loop.index+1}</th>
                        <td><c:forEach items="${record.bookList}" var="book">
                            <a href="/books/${book.id}">${book.title}</a>
                        </c:forEach></td>
                        <td>${record.returned}</td>
                        <td>
                            <fmt:parseDate value="${ record.dateTime }" pattern="yyyy-MM-dd'T'HH:mm"
                                           var="parsedDateTime" type="both"/>
                            <fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${ parsedDateTime }"/></td>
                        <td><a href="/users/${record.userId}">${record.userFullName}</a></td>
                        <td>
                            <c:choose>
                                <c:when test="${record.returned==false}">
                            <a class="btn btn-outline-success" href="/records/return/${record.id}">✔️</a>
                                </c:when>
                                <c:otherwise>
                            <a class="btn btn-outline-danger" href="/records/not-return/${record.id}">❌</a>
                                </c:otherwise>
                            </c:choose>
                            <a class="btn btn-warning" href="/records/edit/${record.id}">EDIT</a>
                            <a class="btn btn-danger" href="/records/delete/${record.id}">DELETE</a>
                        </td>
                    </tr>
                </c:forEach>

                </tbody>
            </table>

        </div>
    </div>
</div>
</body>
</html>
