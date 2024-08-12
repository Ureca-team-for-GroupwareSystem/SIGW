<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>* SI Group Ware *</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
    <style>
        .container {
            margin-top: 20px;
        }
        h2 {
            text-align: left;
            margin-bottom: 20px;
        }
        .row {
            display: flex;
            flex-wrap: nowrap;
        }
        .col-sm-4 {
            padding: 15px;
            border: 1px solid #dee2e6;
            margin-right: 20px;
        }
        .col-sm-4:last-child {
            margin-right: 0;
        }
        h3 {
            text-align: left;
            margin-bottom: 20px;
            height: 50px; /* 일정한 높이 설정 */
        }
        table th, table td {
            text-align: center;
            white-space: nowrap; /* 줄바꿈 없이 한 줄로 */
        }
        table {
            width: 100%;
        }
    </style>
</head>
<body>
    <%@ include file="./header.jsp" %>
    <%@ include file="./horimenu.jsp" %>
    
    <div class="container">
        <h2>${employee.empname}님의 정보</h2>
        <div class="row">
            <div class="col-sm-4">
                <h3>개인 정보</h3>
                <p>사원번호: ${employee.empno}</p>
                <p>부서번호: ${employee.deptno.deptno}</p>
                <p>직책: ${employee.position}</p>
                <p>전화번호: ${employee.phonenum}</p>
                <p>입사일: ${employee.hiredate}</p>
                <p>주소: ${employee.address}</p>
                <p>생년월일: ${employee.birthdate}</p>
            </div>
            <div class="col-sm-4">
                <h3>휴가 정보</h3>
                <div class="table-responsive">
	                <table class="table">
	                    <thead>
	                        <tr>
	                            <th>휴가종류</th>
	                            <th>보유개수</th>
	                            <th>사용개수</th>
	                        </tr>
	                    </thead>
	                    <tbody>
	                        <c:forEach items="${vacations}" var="vacation">
	                            <tr>
	                                <td>${vacation.id.vtype.name()}</td>
	                                <td>${vacation.vremain}</td>
	                                <td>${vacation.vused}</td>
	                            </tr>
	                        </c:forEach>
	                    </tbody>
	                </table>
                </div>
            </div>
        </div>
    </div>
		
		<%@ include file="./footer.jsp" %>
		
	</body>
</html>