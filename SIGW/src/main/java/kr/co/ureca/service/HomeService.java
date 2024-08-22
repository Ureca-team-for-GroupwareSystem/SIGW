package kr.co.ureca.service;

import java.util.List;
import java.util.Optional;

import kr.co.ureca.entity.Employee;
import kr.co.ureca.entity.Empvcnt;

public interface HomeService {

	Optional<Employee> getEmployeeById(int empno);

	List<Empvcnt> getEmpVCntsByEmpno(int empno);

}
