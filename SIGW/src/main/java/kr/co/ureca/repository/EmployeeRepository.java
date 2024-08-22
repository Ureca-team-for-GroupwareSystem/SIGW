package kr.co.ureca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import kr.co.ureca.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
	List<Employee> findAllByOrderByDeptno_DeptnoAscPositionAsc();
}