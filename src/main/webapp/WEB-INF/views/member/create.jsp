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
				ID: <input type="text" id="loginId" /> <br/>
				Password: <input type="password" id="password" /> <br/>
				Name: <input type="text" id="name" /> <br/>
				E-mail: <input type="text" id="email" /> <br/>
				<input type="button" value="등록" onclick="javascript:create();" />
		    </article>
	    </section>
	    <%@ include file="../common/footer.jsp" %>
    </div>
<script lang="javascript" type="text/javascript">
function create() {
	$.ajax({
		url: '/public/member/create',
		data: {
			loginId: $('#loginId').val(),
			password: $('#password').val(),
			name: $('#name').val(),
			email: $('#email').val()
		},
		success: function() {
			alert('success');
			location.href = '/';
		}
	});
}
</script>
</body>
</html>