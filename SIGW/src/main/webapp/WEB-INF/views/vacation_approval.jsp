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
        <c:choose>
            <c:when test="${empty vacationlist}">
                <p>결재할 휴가 요청이 없습니다.</p>
             
            </c:when>
            <c:otherwise>
				 <ul class="list-group list-group-flush">
				  <!-- 헤더 추가 -->
					    <li class="list-group-item d-flex justify-content-between align-items-center font-weight-bold">
					        <div class="d-flex" style="width: 10%;">
					            <span>휴가번호</span>
					        </div>
					        <div class="d-flex" style="width: 20%;">
					            <span>사원명</span>
					        </div>
					        <div class="d-flex" style="width: 20%;">
					            <span>휴가종류</span>
					        </div>
					        <div class="d-flex" style="width: 25%;">
					            <span>휴가기간</span>
					        </div>
					        <div class="d-flex" style="width: 25%; justify-content: flex-end;">
					            <span></span>
					        </div>
					    </li>
				 
				    <c:forEach var="eachvacation" items="${vacationlist}">
				        <li class="list-group-item d-flex justify-content-between align-items-center">
				            <!-- vid와 empname을 분리하여 각각의 div에 배치 -->
				            <div class="d-flex" style="width: 10%;">
				                <span>${eachvacation.vid}</span>
				            </div>
				            <div class="d-flex" style="width: 20%;">
				                <span>${eachvacation.employee.empname}</span>
				            </div>
				            <!-- vtype -->
				            <div class="d-flex" style="width: 20%;">
				                <span>${eachvacation.vtype}</span>
				            </div>
				            <!-- vstart와 vend -->
				            <div class="d-flex" style="width: 25%;">
				                <span>${eachvacation.vstart} ~ ${eachvacation.vend}</span>
				            </div>
				            <!-- 버튼들 -->
				            <div class="d-flex" style="width: 25%; justify-content: flex-end; gap: 5px;">
				                <form action="/ureca/vacation/approve" method="post" class="d-inline">
				                    <input type="hidden" name="requestId" value="${eachvacation.vid}">
				                    <button type="submit" name="action" value="approve" class="btn btn-success btn-sm">승인</button>
				                </form>
				                <form action="/ureca/vacation/reject" method="post" class="d-inline">
				                    <input type="hidden" name="requestId" value="${eachvacation.vid}">
				                    <button type="submit" name="action" value="reject" class="btn btn-danger btn-sm">거절</button>
				                </form>
				            </div>
				        </li>
				    </c:forEach>
				</ul>

            </c:otherwise>
        </c:choose>
	</div>
	
	<%@ include file="./footer.jsp" %>
		
</body>
</html>
