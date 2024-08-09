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
<script>
        function validateForm() {
            var startDate = document.getElementById('startDate').value;
            var endDate = document.getElementById('endDate').value;

            if (new Date(startDate) > new Date(endDate)) {
                alert('휴가 종료일은 시작일보다 커야 합니다.');
                return false; // 폼 제출을 막음
            }
            return true; // 폼 제출 허용
        }
    </script>
</head>
<body>
	<%@ include file="./header.jsp" %>
	<%@ include file="./horimenu.jsp" %>
	
	<div class="container mt-5">
        <h2>휴가 신청</h2>
		<form action="/submitVacationApply" method="post" onsubmit="return validateForm();">
		  <!-- 휴가 종류 선택 -->
            <div class="form-group">
                <label for="vtype">휴가 종류</label>
                <select class="form-control" id="vtype" name="vtype" required>
                    <option value="">선택하세요</option>
                    <option value="annual">연차 휴가</option>
                    <option value="monthly">월차 휴가</option>
                    <option value="sick">병가</option>
                    <option value="event">경조사</option>
                </select>
            </div>
            
             <!-- 휴가 시작일 -->
            <div class="form-group">
                <label for="startDate">휴가 시작일</label>
                <input type="date" class="form-control" id="startDate" name="startDate" required>
            </div>

            <!-- 휴가 종료일 -->
            <div class="form-group">
                <label for="endDate">휴가 종료일</label>
                <input type="date" class="form-control" id="endDate" name="endDate" required>
            </div>
            
			<!-- 결재선 사원 선택 -->
			<div class="form-group">			
			    <label for="approvers">결재선 사원 선택</label>
			    <div id="approvers">
			        <c:forEach var="each-employee" items="${employee}">
			            <div class="form-check">
			               <input class="form-check-input" type="checkbox" id="approver${each-employee.empno}" name="approvers" value="${each-employee.empno}">
			               <label class="form-check-label" for="approver${each-employee.empno}">${each-employee.empname}</label>
			            </div>
			        </c:forEach>		        
			    </div>
			</div>
		  <button type="submit" class="btn btn-secondary">신청하기</button>
		</form>
	</div>
	
	<%@ include file="./footer.jsp" %>
	
</body>
</html>