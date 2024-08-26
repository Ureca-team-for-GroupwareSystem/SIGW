package kr.co.ureca.service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.ureca.entity.Employee;
import kr.co.ureca.entity.EmpvCntId;
import kr.co.ureca.entity.Empvcnt;
import kr.co.ureca.entity.Vacation;
import kr.co.ureca.entity.VacationState;
import kr.co.ureca.entity.VacationType;
import kr.co.ureca.repository.EmployeeRepository;
import kr.co.ureca.repository.EmpvCntRepository;
import kr.co.ureca.repository.VacationRepository;

@Service
public class ApplyServiceImpl implements ApplyService {
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	EmpvCntRepository empvCntRepository;
	
	@Autowired
	VacationRepository vacationRepository;

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
        	
        	vacationRepository.save(vacationApply);
        	
        	// 결재선 계산 (사원 번호 받아서 int값으로 찾아내서 approver 데이터베이스에 저장)
        	handleApproverProcessing(vacationApply, employee, approvers);
        	
        	return true;
        } else if (empvcnt.getVremain() > daysBetween) { // 휴가 종류가 연차 월차면 날짜 비교 처리
        	// 가능하면 휴가 데이터베이스에 값 저장
        	Vacation vacationApply = new Vacation();
        	vacationApply.setEmployee(employee);
        	vacationApply.setVtype(vacationType);
        	vacationApply.setVstart(Date.valueOf(startDate));
        	vacationApply.setVend(Date.valueOf(endDate));
        	vacationApply.setVstate(VacationState.PENDING);
        	
        	vacationRepository.save(vacationApply);
			
        	// 결재선 계산 (사원 번호 받아서 int값으로 찾아내서 approver 데이터베이스에 저장)
        	handleApproverProcessing(vacationApply, employee, approvers);
        	return true;
        }
        return false;
	}

	private void handleApproverProcessing(Vacation vacationApply, Employee employee, String[] approvers) {
		
		
	}

}
