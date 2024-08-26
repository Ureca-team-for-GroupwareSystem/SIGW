<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Vacation Applications</title>
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
            <c:forEach var="eachVacation" items="${vacationList}">
                <li class="list-group-item d-flex justify-content-between align-items-center">
                    <span>${eachVacation.vid}. ${eachVacation.employee.empname}</span>
                    <span>${eachVacation.vtype}</span>
                    <span>${eachVacation.vstart} ~ ${eachVacation.vend}</span>
                    <span class="badge badge-${eachVacation.vstate.toString() == '승인됨' ? 'success' : eachVacation.vstate.toString() == '거부됨' ? 'danger' : 'warning'}">
                        ${eachVacation.vstate}
                    </span>
                </li>
            </c:forEach>    
        </ul>
    </div>

    <%@ include file="./footer.jsp" %>
    
</body>
</html>
