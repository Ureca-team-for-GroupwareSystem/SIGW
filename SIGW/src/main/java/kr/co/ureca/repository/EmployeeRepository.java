package kr.co.ureca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.co.ureca.entity.employee;

@Repository
public interface EmployeeRepository extends JpaRepository<employee, Integer>{
	
}