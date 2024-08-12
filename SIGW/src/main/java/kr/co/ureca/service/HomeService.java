package kr.co.ureca.service;

import java.util.List;
import java.util.Optional;

import kr.co.ureca.entity.employee;
import kr.co.ureca.entity.empvcnt;

public interface HomeService {

	Optional<employee> getEmployeeById(int empno);

	List<empvcnt> getEmpVCntsByEmpno(int empno);

}
