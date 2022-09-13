<%--
  Created by IntelliJ IDEA.
  User: oybek
  Date: 9/8/2022
  Time: 11:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <jsp:include page="../navbar.jsp"></jsp:include>
    <title>Author Form</title>
</head>
<body>

<div class="container text-center">
    <c:if test="${author==null}">
        <h1>======= ADD NEW USER ========</h1>
    </c:if>
    <c:if test="${author!=null}">
        <h1>======= EDIT USER ========</h1>
    </c:if>
    <h1 style="color: red">${error}</h1>
    <div class="row">
        <div class="col-md-6 offset-3">
            <c:if test="${user==null}">
            <form action="/users" method="post">
                </c:if>
                <c:if test="${user!=null}">
                <form action="/users/edit" method="post">
                    <input type="hidden" value="${user.id}" name="id">
                    </c:if>
                    <div class="mb-3">

                        <label for="FullName" class="form-label">FullName</label>
                        <input name="fullName" type="text" value="${user.fullName}" class="form-control"
                               id="FullName" required>
                    </div>
                    <div class="mb-3">
                        <label for="PhoneNumber" class="form-label">Phone Number</label>
                        <input name="phoneNumber" type="text" value="${user.phoneNumber}" class="form-control"
                               id="PhoneNumber" required>
                    </div>
                    <c:if test="${user!=null}">
                    <div class="mb-3">
                        <input name="isAdmin" type="radio" value="true" id="Admin" <c:if test="${user.isAdmin==true}">checked="checked"</c:if> required>
                        <label for="Admin" >Admin</label>
                        <input name="isAdmin" type="radio" value="false" id="User" <c:if test="${user.isAdmin==false}">checked="checked"</c:if> required>
                        <label for="User" >User</label>
                    </div>
                    </c:if>
                    <button type="submit" class="btn btn-primary">Save</button>
                </form>
        </div>
    </div>
</div>
</body>
</html>
