package kr.co.ureca.service;

import java.util.List;

import kr.co.ureca.entity.Employee;
import kr.co.ureca.entity.Vacation;

public interface ApplyService {

	public List<Employee> getAllEmployees();

	public boolean applyVacation(int empno, String vtype, String startDate, String endDate, String[] approvers);
}
