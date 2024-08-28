package kr.co.ureca.service;

import kr.co.ureca.entity.ApproveType;
import kr.co.ureca.entity.Approver;
import kr.co.ureca.entity.Employee;
import kr.co.ureca.entity.Vacation;
import kr.co.ureca.repository.ApproverRepository;
import kr.co.ureca.repository.VacationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ApproverService {

    private final ApproverRepository approverRepository;
    private final VacationRepository vacationRepository;

    public ApproverService(ApproverRepository approverRepository, VacationRepository vacationRepository) {
        this.approverRepository = approverRepository;
        this.vacationRepository = vacationRepository;
    }

    public List<Vacation> getVacationsByEmployee(Employee employee) {
        int empno = employee.getEmpno();
        System.out.println(empno);
        // 지정된 직원(empno)에 대해 모든 관련 휴가 목록을 가져옴
        return approverRepository.findVacationsByEmployee(empno,ApproveType.MYTURN);
    }
}

//