<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="ko" ng-app="Anajo">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Anajo</title>
	<link rel="shortcut icon" href="${contextPath}/favicon.ico" />
	<!-- bootstrap css -->
	<link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet" media="screen">
	<link href="${contextPath}/resources/css/bootstrap-theme.min.css" rel="stylesheet" media="screen">
	<link href="${contextPath}/resources/css/sticky-footer-navbar.css" rel="stylesheet" media="screen">
	<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!--[if lt IE 9]>
	      <script src="${contextPath}/resources/js/lib/html5shiv.min.js"></script>
	      <script src="${contextPath}/resources/js/lib/respond.min.js"></script>
    <![endif]-->
	
	<!-- anajo custom css -->
	<link href="${contextPath}/resources/css/anajo.css" rel="stylesheet" media="screen">
	
	<script src="${contextPath}/resources/js/lib/jquery.js"></script>
	<script src="${contextPath}/resources/js/lib/angular.js"></script>
	<script src="${contextPath}/resources/js/lib/angular-route.js"></script>
	<script src="${contextPath}/resources/js/lib/angular-resource.js"></script>
	
	<!-- bootstrap js -->
	<script src="${contextPath}/resources/js/lib/bootstrap.min.js"></script>
	
	<!-- image lazy load jquery plugin -->
	<script src="${contextPath}/resources/js/lib/jquery.lazyload.min.js"></script>
	
	<!-- Angularjs App -->
	<script src="${contextPath}/resources/js/Anajo.js"></script>
	<!-- Controllers -->	
	<script src="${contextPath}/resources/js/controller/MainController.js"></script>
	<script src="${contextPath}/resources/js/controller/HomeController.js"></script>
	<script src="${contextPath}/resources/js/controller/resource/ResourceController.js"></script>
	<script src="${contextPath}/resources/js/controller/resource/ResourceCreateController.js"></script>
	<script src="${contextPath}/resources/js/controller/picture/PictureListController.js"></script>
	<script src="${contextPath}/resources/js/controller/chat/ChatController.js"></script>
</head>
<body ng-controller="MainController">
	<div id="wrap">
		
		<div class="container">
			<div ng-view></div>
		</div>
		
		<div id="footer">
			<div class="container">
				<p class="text-muted credit">
					ⓒ2012 <a href="mailto:jodoyoung36@gmail.com">jodoyoung</a>
				</p>
			</div>
		</div>
	</div>
</body>
</html>
