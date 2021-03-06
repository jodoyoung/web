<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="/${pageContext.request.contextPath}" />
<html>
<head>
	<%@ include file="../common/header.jsp" %>
</head>
<body>
    <div id="wrapper">
        <section>
			<article>
				<div class="logo">
					<a href="${contextPath}"><img	src="${contextPath}resources/img/logo.png" alt="anajo" /></a>
				</div>
				<div class="loginBox">
					<form action="/login" method="POST">
						<input type="text" name="loginId" placeholder="아이디" autofocus /><br />
						<input type="password" name="password" placeholder="비밀번호" /><br />
						<br /> <br /> <input name="submit" type="submit" value="로그인" class="btnLogin" />
					</form>
				</div>
		    </article>
	    </section>
	    <%@ include file="../common/footer.jsp" %>
    </div>
</body>
</html>