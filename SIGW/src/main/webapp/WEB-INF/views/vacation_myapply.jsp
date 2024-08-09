<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>* SI Group Ware *</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<%@ include file="./header.jsp" %>
	<%@ include file="./horimenu.jsp" %>
	
	
	<div class="container mt-5">
        <h2>휴가 신청 상태</h2>
        <ul class="list-group list-group-flush">
            <c:forEach var="each-vacation" items="${vacation}">
                <li class="list-group-item d-flex justify-content-between align-items-center">
                    <span>${each-vacation.vid}. ${each-vacation.vemp}</span>
                    <span>${each-vacation.vtype}</span>
                    <span>${each-vacation.vstart} ~ ${each-vacation.vend}</span>
                    <span class="badge badge-${each-vacation.vstate == '승인됨' ? 'success' : each-vacation.vstate == '거절됨' ? 'danger' : 'warning'}">
                        ${each-vacation.status}
                    </span>
                </li>
            </c:forEach>	
        </ul>
    </div>

	
	<%@ include file="./footer.jsp" %>
	
</body>
</html>