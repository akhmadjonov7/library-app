<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <jsp:include page="../navbar.jsp"></jsp:include>
    <title>Author Form</title>
</head>
<body>
<div class="container text-center">
    <h1>======= ADD NEW AUTHOR ========</h1>

    <div class="row">
        <div class="col-md-6 offset-3">
            <form action="/authors" method="post">
                <div class="mb-3">
                    <label for="FullName" class="form-label">FullName</label>
                    <input name="fullName" type="text" class="form-control" id="FullName">
                </div>
                <div class="mb-3">
                    <label for="authorBiography" class="form-label">Biography</label>
                    <input name="biography" type="text" class="form-control" id="authorBiography">
                </div>
                <button type="submit" class="btn btn-primary">Save</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
