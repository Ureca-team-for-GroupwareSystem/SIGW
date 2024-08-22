package kr.co.ureca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.ureca.entity.Employee;
import kr.co.ureca.repository.EmployeeRepository;

@Service
public class ApplyServiceImpl implements ApplyService {
	
	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public List<Employee> getAllEmployees() {
		 return employeeRepository.findAllByOrderByDeptno_DeptnoAscPositionAsc();
	}

}
