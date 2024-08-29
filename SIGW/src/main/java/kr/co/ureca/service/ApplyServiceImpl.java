package kr.co.ureca.service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.ureca.entity.*;
import kr.co.ureca.repository.*;

@Service
public class ApplyServiceImpl implements ApplyService {
	
	private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	EmpvCntRepository empvCntRepository;
	
	@Autowired
	VacationRepository vacationRepository;
	
	@Autowired
	ApproverRepository approverRepository;

	@Override
	public List<Employee> getAllEmployees() {
		 return employeeRepository.findAllByOrderByDeptno_DeptnoAscPositionAsc();
	}

	@Override
	public boolean applyVacation(int empno, String vtype, String startDateStr, String endDateStr, String[] approvers) {
		
	    VacationType vacationType = VacationType.valueOf(vtype.toUpperCase());
	    EmpvCntId empvCntId = new EmpvCntId(vacationType, empno);
		Empvcnt empvcnt = empvCntRepository.findById(empvCntId).get();
		
		// 신청 휴가 일수
        LocalDate startDate = parseDate(startDateStr); 
        LocalDate endDate = parseDate(endDateStr); 
        long daysBetween =  calculateDaysBetween(startDate, endDate);
        
        Employee employee = employeeRepository.findById(empno).get();
        
        // 병가, 경조사 pass
        if(vacationType == VacationType.SICK || vacationType == VacationType.PERSONAL) {
        	processVacation(employee, vacationType, startDate, endDate, approvers);
        	return true;
        // 연차, 월차 잔여일 비교
        } else if (empvcnt.getVremain() > daysBetween) {
        	processVacation(employee, vacationType, startDate, endDate, approvers);
        	return true;
        }
        return false;
	}
	
    private LocalDate parseDate(String dateStr) {
        return LocalDate.parse(dateStr, DATE_FORMATTER);
    }

    private long calculateDaysBetween(LocalDate startDate, LocalDate endDate) {
        return ChronoUnit.DAYS.between(startDate, endDate) + 1;
    }
    
    private void processVacation(
    		Employee employee, 
    		VacationType vacationType, 
    		LocalDate startDate, 
    		LocalDate endDate, 
    		String[] approvers) {
    	
        Vacation vacation = createVacation(employee, vacationType, startDate, endDate);
        handleApproverProcessing(vacation, approvers);
    }
    
	private Vacation createVacation(
			Employee employee, 
			VacationType vacationType, 
			LocalDate startDate,
			LocalDate endDate) {
		
		Vacation vacationApply = new Vacation();
    	vacationApply.setEmployee(employee);
    	vacationApply.setVtype(vacationType);
    	vacationApply.setVstart(Date.valueOf(startDate));
    	vacationApply.setVend(Date.valueOf(endDate));
    	vacationApply.setVstate(VacationState.PENDING);
    	
		return vacationRepository.save(vacationApply);
	}

	private void handleApproverProcessing(Vacation vacation, String[] approvers) {
		// 결재자 객체 List
	    List<Approver> approverList = new ArrayList<>();
	 
	    for (String approverIdStr : approvers) {
	        int approverId = Integer.parseInt(approverIdStr);
	        Employee approverEmployee = employeeRepository.findById(approverId).get();

	        Approver approver = new Approver();
	        approver.setVacation(vacation);
	        approver.setEmployee(approverEmployee);
	        approverList.add(approver);
	    }
	    
	    // 부서 번호 및 직급 순서로 정렬
	    approverList.sort((a1, a2) -> {
	        int deptComparison = Integer.compare(
	            a1.getEmployee().getDeptno().getDeptno(), 
	            a2.getEmployee().getDeptno().getDeptno()
	        );
	        if (deptComparison != 0) {
	            return deptComparison;
	        } else {
	            return Integer.compare(a1.getEmployee().getPosition(), a2.getEmployee().getPosition());
	        }
	    });
	    
	    // acnt: 결재순번, astate: 결재상태
	    int acnt = 0;
	    for (Approver approver : approverList) {
	        approver.setAcnt(acnt);
	        if (acnt == approverList.size()-1) {
	            approver.setAstate(ApproveType.MYTURN);
	        } else {
	            approver.setAstate(ApproveType.NOTYET);
	        }
	        acnt++;
	    }
	    
	    approverRepository.saveAll(approverList);
	}
}