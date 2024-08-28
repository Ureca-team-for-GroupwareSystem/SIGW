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

	// 유저가 휴가 제출하면 수행되는 비즈니스 로직
	// 유저의 휴가 상태 확인하고 (잔여일수) 실패면 바로 false 리턴
	// 가능하면 db 저장하고 결재선 로직 수행
	// 결재선 db 저장 
	@Override
	public boolean applyVacation(int empno, String vtype, String startDateStr, String endDateStr, String[] approvers) {
		
		// 문자열로 받아온 vtype을 VacationType enum으로 변환
	    VacationType vacationType = VacationType.valueOf(vtype.toUpperCase());
	    
	    // 복합 키 생성
	    EmpvCntId empvCntId = new EmpvCntId(vacationType, empno);
	    
	    // 해당 사원의 특정 휴가 타입에 대한 데이터 조회
	    Optional<Empvcnt> empvcntOptional = empvCntRepository.findById(empvCntId);
		Empvcnt empvcnt = empvcntOptional.get();
		
		
		// 휴가 신청 일수 계산
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate = LocalDate.parse(startDateStr, formatter);
        LocalDate endDate = LocalDate.parse(endDateStr, formatter);
        
        // 날짜 간의 차이 계산 (ChronoUnit.DAYS를 사용)
        long daysBetween = ChronoUnit.DAYS.between(startDate, endDate)+1;
        Employee employee = employeeRepository.findById(empno).get();
        
        // 휴가 종류가 병가 경조사면 바로 pass
        if(vacationType == VacationType.SICK || vacationType == VacationType.PERSONAL) {
        	
        	Vacation vacationApply = new Vacation();
        	vacationApply.setEmployee(employee);
        	vacationApply.setVtype(vacationType);
        	vacationApply.setVstart(Date.valueOf(startDate));
        	vacationApply.setVend(Date.valueOf(endDate));
        	vacationApply.setVstate(VacationState.PENDING);
        	
        	Vacation vacation = vacationRepository.save(vacationApply);
        	
        	// 결재선 계산 (사원 번호 받아서 int값으로 찾아내서 approver 데이터베이스에 저장)
        	handleApproverProcessing(vacation, approvers);
        	return true;
        } else if (empvcnt.getVremain() > daysBetween) { // 휴가 종류가 연차 월차면 날짜 비교 처리
        	// 가능하면 휴가 데이터베이스에 값 저장
        	Vacation vacationApply = new Vacation();
        	vacationApply.setEmployee(employee);
        	vacationApply.setVtype(vacationType);
        	vacationApply.setVstart(Date.valueOf(startDate));
        	vacationApply.setVend(Date.valueOf(endDate));
        	vacationApply.setVstate(VacationState.PENDING);
        	
        	Vacation vacation = vacationRepository.save(vacationApply);
			
        	// 결재선 계산 (사원 번호 받아서 int값으로 찾아내서 approver 데이터베이스에 저장)
        	handleApproverProcessing(vacation, approvers);
        	return true;
        }
        return false;
	}

	private void handleApproverProcessing(Vacation vacation, String[] approvers) {
		// 결재자 목록을 저장할 리스트 생성
	    List<Approver> approverList = new ArrayList<>();

	    // 주어진 approvers 배열에 있는 사원 ID를 통해 Employee 객체를 조회하고 리스트에 추가
	    for (String approverIdStr : approvers) {
	        int approverId = Integer.parseInt(approverIdStr);
	        Employee approverEmployee = employeeRepository.findById(approverId).get();

	        // Approver 객체 생성 및 리스트에 추가
	        Approver approver = new Approver();
	        approver.setVacation(vacation);
	        approver.setEmployee(approverEmployee);
	        approverList.add(approver);
	    }
	    
	    // Approver 리스트를 부서 번호 및 직급 순서로 정렬
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
	    
	    // 정렬된 순서대로 acnt 설정 및 astate 설정
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
	    
	 // Approver 리스트를 데이터베이스에 한 번에 저장
	    approverRepository.saveAll(approverList);
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
