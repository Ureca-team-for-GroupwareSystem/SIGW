package kr.co.ureca.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.ureca.entity.employee;
import kr.co.ureca.entity.empvcnt;
import kr.co.ureca.repository.EmployeeRepository;
import kr.co.ureca.repository.EmpvCntRepository;

@Service
public class HomeServiceImpl implements HomeService {
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	EmpvCntRepository empvCntRepository;

	@Override
	public Optional<employee> getEmployeeById(int empno) {
		Optional<employee> emp = employeeRepository.findById(empno);
		return emp;
	}

	@Override
	public List<empvcnt> getEmpVCntsByEmpno(int empno) {
		List<empvcnt> empvcntList = empvCntRepository.findByEmployeeEmpno(empno);
		return empvcntList;
	}

}
