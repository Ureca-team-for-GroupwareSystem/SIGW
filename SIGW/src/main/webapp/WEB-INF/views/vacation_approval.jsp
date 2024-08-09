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
        <h2>직원 휴가 관리</h2>
		 <ul class="list-group list-group-flush">
            <c:forEach var="each-vacation" items="${vacation}">
                <li class="list-group-item d-flex justify-content-between align-items-center">
                    <div class="d-flex" style="width: 15%;">
                        <span class="mr-5">${each-vacation.vid}</span>
                        <span>${each-vacation.vemp}</span>
                    </div>
                    <div class="d-flex flex-column" style="width: 20%;">
                        <span>${each-vacation.vtype}</span>
                    </div>
                    <div class="d-flex flex-column" style="width: 25%;">
                        <span>${each-vacation.vstart} ~ ${each-vacation.vend}</span>
                    </div>
                   <div class="d-flex" style="width: 20%; justify-content: flex-end; gap: 5px;">
				    <form action="/approveVacation" method="post" class="d-inline">
				        <input type="hidden" name="requestId" value="${each-vacation.vid}">
				        <button type="submit" name="action" value="approve" class="btn btn-success btn-sm">승인</button>
				    </form>
				    <form action="/rejectVacation" method="post" class="d-inline">
				        <input type="hidden" name="requestId" value="${each-vacation.vid}">
				        <button type="submit" name="action" value="reject" class="btn btn-danger btn-sm">거절</button>
				    </form>
				</div>
                </li>
            </c:forEach>
      
        </ul>
	</div>
	
	<%@ include file="./footer.jsp" %>
	
</body>
</html>