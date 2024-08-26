package kr.co.ureca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.ureca.entity.Employee;
import kr.co.ureca.repository.EmployeeRepository;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee findById(int empno) {
        return employeeRepository.findById(empno).orElse(null);
    }
}
