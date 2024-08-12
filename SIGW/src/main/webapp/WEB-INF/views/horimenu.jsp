<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <nav class="navbar navbar-expand-sm bg-dark navbar-dark mb-5">
	  <!-- Brand -->
	  <a class="navbar-brand" href="">URECA</a>
	
	  <!-- Links -->
	  <ul class="navbar-nav">
	    <li class="nav-item">
	      <a class="nav-link" href="http://localhost:8080/ureca/home/${employee.empno}">메인화면</a>
	    </li>
	
	    <!-- Dropdown -->
	    <li class="nav-item dropdown">
	      <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
	        휴가 신청 및 관리
	      </a>
	      <div class="dropdown-menu">
	        <a class="dropdown-item" href="http://localhost:8080/ureca/vacation/apply">휴가 신청</a>
	        <a class="dropdown-item" href="http://localhost:8080/ureca/vacation/myapply">신청 상태</a>
	        <a class="dropdown-item" href="http://localhost:8080/ureca/vacation/approval">직원 휴가 관리</a>
	      </div>
	    </li>
	  </ul>
	</nav>