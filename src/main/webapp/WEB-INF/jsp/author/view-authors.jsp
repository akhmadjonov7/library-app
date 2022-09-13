<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <jsp:include page="../navbar.jsp"></jsp:include>
    <title>Authors</title>
</head>
<body>
<div class="container text-center">
    <h1>======= AUTHORS ========</h1>
    <h1 style="color: red">${error}</h1>
    <a class="btn btn-outline-primary my-4" href="authors/get-add-form">+ Add new author</a>
    <div class="row">
        <div class="col-md-6 offset-3">
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">FullName</th>
                    <th scope="col">Actions</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${authors}" var="author" varStatus="loop">
                    <tr>
                        <th scope="row">${loop.index+1}</th>
                        <td>
                            <a href="/authors/${author.authorId}">${author.authorFullName}</a>
                        </td>
                        <td>
                            <a class="btn btn-warning" href="/authors/get-edit-form/${author.authorId}">EDIT</a>
                            <a class="btn btn-danger" href="/authors/delete/${author.authorId}">DELETE</a>
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
