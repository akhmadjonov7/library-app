<%--
  Created by IntelliJ IDEA.
  User: oybek
  Date: 9/9/2022
  Time: 3:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Author</title>
  <jsp:include page="../navbar.jsp"></jsp:include>
</head>
<body>
<h1 class="text-center">${author.fullName}</h1><br>
<h2>${author.biography}</h2>
</body>
</html>
