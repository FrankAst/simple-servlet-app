<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<meta charset="UTF-8">
	<title>Library system</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>

<body>
	    <div class="container">
            <div class="info" style="text-align: center;"><h3>You are in system</h3></div>
			<h3>Hey, student!</h3>
			<button><a href="/book">Get all books!</a></button>

			<div class="newBooks">
				<ul>
					<c:forEach items="${books}" var="item">
						<li>Title: ${item.get("title")} | Author: ${item.get("author")}</li>
					</c:forEach>
				</ul>
			</div>
    	</div>
</body>
</html>