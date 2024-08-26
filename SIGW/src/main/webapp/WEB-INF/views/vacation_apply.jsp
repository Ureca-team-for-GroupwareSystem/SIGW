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
     
	window.onload = function() {
		var errorMessage = "${errorMessage}";
		if (errorMessage) {
		    alert(errorMessage); // 에러 메시지가 있을 경우 경고창 표시
		}
	}
 </script>
 <style>
 /* 테이블 셀 안의 체크박스를 중앙에 정렬하는 스타일 */
 .table td {
     vertical-align: middle; /* 수직 중앙 정렬 */
     text-align: center;     /* 수평 중앙 정렬 */
 }
</style>
</head>
<body>
	<%@ include file="./header.jsp" %>
	<%@ include file="./horimenu.jsp" %>
	
	<div class="container mt-5">
        <h2> 사원번호 ${empno}번님의 휴가 신청</h2>
        
		<form action="http://localhost:8080/ureca/submitVacationApply" method="post" onsubmit="return validateForm();">
		  <!-- 휴가 종류 선택 -->
            <div class="form-group">
                <label for="vtype">휴가 종류</label>
                <select class="form-control" id="vtype" name="vtype" required>
                    <option value="">선택하세요</option>
                    <option value="ANNUAL">연차 휴가</option>
                    <option value="MONTHLY">월차 휴가</option>
                    <option value="SICK">병가</option>
                    <option value="PERSONAL">경조사</option>
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
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>부서번호</th>
                            <th>직책번호</th>
                            <th>사원이름</th>
                            <th>선택</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="eachEmployee" items="${employee}">
                            <tr>
                           
                          		<td>${eachEmployee.deptno.deptno}</td>
                                <td>${eachEmployee.position}</td>
                                <td>${eachEmployee.empname}</td>
                                <td>
                                    <input class="form-check-input" type="checkbox" id="approver${eachEmployee.empno}" name="approvers" value="${eachEmployee.empno}">
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
		  <button type="submit" class="btn btn-secondary">신청하기</button>
		</form>
	</div>
	
	<%@ include file="./footer.jsp" %>
	
</body>
</html>