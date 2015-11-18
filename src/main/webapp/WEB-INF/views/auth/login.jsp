<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="/${pageContext.request.contextPath}" />
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${contextPath}resources/css/style.css" type="text/css" />
    <link rel="shortcut icon" href="${contextPath}favicon.ico" />
</head>
<body>
    <div id="wrap">
        <section>
			<article class="login">
				<div class="logo">
					<a href="${contextPath}"><img	src="${contextPath}resources/img/logo.png" alt="anajo" /></a>
				</div>
				<div class="loginBox">
					<form action="/login" method='POST'>
						<input type="text" name="loginId" placeholder="아이디" autofocus /><br />
						<input type="password" name="password" placeholder="비밀번호" /><br />
						<br /> <br /> <input name="submit" type="submit" value="로그인" class="button" />
					</form>
				</div>
		    </article>
	    </section>
	    <footer>
	           ⓒ2012 <a href="mailto:jodoyoung36@gmail.com">jodoyoung</a>
	    </footer>
    </div>
</body>
</html>