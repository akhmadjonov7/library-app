<%--
  Created by IntelliJ IDEA.
  User: oybek
  Date: 9/11/2022
  Time: 1:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <jsp:include page="../navbar.jsp"></jsp:include>
    <title>Record Form</title>
</head>
<body>
<div class="container text-center">
    <c:if test="${record==null}">
        <h1>======= ADD NEW RECORD ========</h1>
    </c:if>
    <c:if test="${record!=null}">
        <h1>======= EDIT RECORD ========</h1>
    </c:if>


    <div class="row">
        <div class="col-md-6 offset-3">
            <c:if test="${record==null}">
            <form action="/records" method="post">
                </c:if>
                <c:if test="${record!=null}">
                <form action="/records/edit" method="post">
                    <input type="hidden" value="${record.id}" name="id">
                    </c:if>
                        <div class="form-group">
                        <label for="booksIds">Books:</label>
                        <select id="booksIds"
                                class="selectpicker form-control"
                                multiple
                                aria-label="Please select books"
                                data-live-search="true"
                                name="booksId"
                                required
                        >
                            <c:forEach items="${bookList}" var="book">
                                <c:choose>
                                    <c:when test="${record.bookList.contains(book)}">
                                        <option selected value="${book.id}">${book.title}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${book.id}">${book.title}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                        </div>
                    <div class="form-group">

                        <label for="userId">User:</label>
                        <select id="userId"
                                class="selectpicker form-control"
                                aria-label="Please select user"
                                data-live-search="true"
                                name="userId"
                                required
                        >
                            <option disabled selected value="none">Choose User</option>
                            <c:forEach items="${userDtoList}" var="user">
                                <c:choose>
                                    <c:when test="${record.userId == user.id}">
                                        <option selected value="${user.id}">${user.fullName}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${user.id}">${user.fullName}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-primary">Save</button>
                </form>
        </div>
    </div>
</div>
</body>
</html>
