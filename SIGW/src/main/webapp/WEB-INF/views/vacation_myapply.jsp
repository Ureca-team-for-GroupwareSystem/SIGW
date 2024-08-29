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
		    
		    <!-- 각 항목의 제목을 포함한 헤더 추가 -->
		    <div class="list-group-item d-flex justify-content-between align-items-center font-weight-bold">
		        <div style="width: 15%;">
		            <span>휴가번호</span>
		        </div>
		        <div style="width: 30%;">
		            <span>휴가종류</span>
		        </div>
		        <div style="width: 35%;">
		            <span>휴가기간</span>
		        </div>
		        <div style="width: 20%; text-align: right;">
		            <span>처리상태</span>
		        </div>
		    </div>
		
		    <ul class="list-group list-group-flush">
		        <c:forEach var="eachVacation" items="${vacationList}">
		            <li class="list-group-item d-flex justify-content-between align-items-center">
		                <!-- VID와 EmpName -->
		                <div style="width: 15%;">
		                    <span>${eachVacation.vid}</span>
		                </div>
		                <!-- Vacation Type -->
		                <div style="width: 30%;">
		                    <span>${eachVacation.vtype}</span>
		                </div>
		                <!-- Vacation Period -->
		                <div style="width: 35%;">
		                    <span>${eachVacation.vstart} ~ ${eachVacation.vend}</span>
		                </div>
		                <!-- Status -->
		                <div style="width: 20%; text-align: right;">
		                    <span class="badge badge-${eachVacation.vstate.toString() == '승인됨' ? 'success' : eachVacation.vstate.toString() == '거부됨' ? 'danger' : 'warning'}">
		                        ${eachVacation.vstate}
		                    </span>
		                </div>
		            </li>
		        </c:forEach>    
		    </ul>
		</div>

	
    <%@ include file="./footer.jsp" %>
    
</body>
</html>
