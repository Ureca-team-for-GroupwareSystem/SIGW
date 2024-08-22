package kr.co.ureca.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.ureca.entity.Employee;
import kr.co.ureca.entity.Empvcnt;
import kr.co.ureca.repository.EmployeeRepository;
import kr.co.ureca.repository.EmpvCntRepository;

@Service
public class HomeServiceImpl implements HomeService {
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	EmpvCntRepository empvCntRepository;

	@Override
	public Optional<Employee> getEmployeeById(int empno) {
		Optional<Employee> emp = employeeRepository.findById(empno);
		return emp;
	}

	@Override
	public List<Empvcnt> getEmpVCntsByEmpno(int empno) {
		List<Empvcnt> empvcntList = empvCntRepository.findByEmployeeEmpno(empno);
		return empvcntList;
	}

}
