package kr.co.ureca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.ureca.entity.employee;

public interface EmployeeRepository extends JpaRepository<employee, Integer>{
	
}