package kr.co.ureca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import kr.co.ureca.entity.ApproveType;
import kr.co.ureca.entity.Approver;
import kr.co.ureca.entity.Empvcnt;
import kr.co.ureca.entity.Vacation;
import kr.co.ureca.entity.VacationState;
import kr.co.ureca.repository.ApproverRepository;
import kr.co.ureca.repository.EmpvCntRepository;
import kr.co.ureca.repository.VacationRepository;

@Service
@Transactional
public class VacationApprovalService {
    @Autowired
    private ApproverRepository approverRepository;

    @Autowired
    private VacationRepository vacationRepository;

    @Autowired
    private EmpvCntRepository empvcntRepository; // EmpvcntRepository 추가

    public void approveVacation(int vid, int empno) {
        // 1. 요청된 휴가를 조회
        Vacation vacation = vacationRepository.findById(vid).orElseThrow(() -> new RuntimeException("Vacation not found"));

        // 2. 해당 휴가의 Approver 리스트를 가져옴
        List<Approver> approvers = approverRepository.findByVacation(vacation);

        // 3. Approver 리스트에서 승인할 Approver를 찾음
        Approver currentApprover = approvers.stream()
                .filter(a -> a.getEmployee().getEmpno() == empno)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Approver not found"));

        // 4. acnt가 0인지 확인
        if (currentApprover.getAcnt() == 0) {
            // 모든 Approver의 상태를 APPROVED로 설정
            for (Approver approver : approvers) {
                approver.setAstate(ApproveType.APPROVED);
                approverRepository.save(approver);
            }
            // 휴가 상태를 APPROVED로 설정
            vacation.setVstate(VacationState.APPROVED);
            vacationRepository.save(vacation);

            // 5. 휴가의 기간 계산
            int vacationDays = (int) (vacation.getVend().toLocalDate().toEpochDay() - vacation.getVstart().toLocalDate().toEpochDay() + 1);

            // 6. Empvcnt 엔티티를 조회하여 업데이트
            Empvcnt empvcnt = empvcntRepository.findByEmpnoAndVtype(vacation.getEmployee().getEmpno(),vacation.getVtype())
                    .orElseThrow(() -> new RuntimeException("Empvcnt not found"));

            // 7. 휴가 유형에 따라 `vremain`과 `vused` 값 조정
            switch (vacation.getVtype()) {
                case ANNUAL:
                case MONTHLY:
                    empvcnt.setVremain(empvcnt.getVremain() - vacationDays);
                    empvcnt.setVused(empvcnt.getVused() + vacationDays);
                    break;
                case SICK:
                case PERSONAL:
                    empvcnt.setVused(empvcnt.getVused() + vacationDays);
                    break;
            }

            empvcntRepository.save(empvcnt);
        } else {
            // 현재 Approver의 상태를 PENDING으로 설정
            currentApprover.setAstate(ApproveType.PENDING);
            approverRepository.save(currentApprover);
            // 다음 Approver의 상태를 MYTURN으로 설정
            Approver nextApprover = approvers.stream()
                    .filter(a -> a.getAcnt() == currentApprover.getAcnt() - 1)
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Next approver not found"));
            nextApprover.setAstate(ApproveType.MYTURN);
            approverRepository.save(nextApprover);
        }
    }

    public void rejectVacation(int vid) {
        // 1. 요청된 휴가를 조회
        Vacation vacation = vacationRepository.findById(vid).orElseThrow(() -> new RuntimeException("Vacation not found"));

        // 2. 해당 휴가의 Approver 리스트를 가져옴
        List<Approver> approvers = approverRepository.findByVacation(vacation);

        // 3. 모든 Approver의 상태를 REJECTED로 설정
        for (Approver approver : approvers) {
            approver.setAstate(ApproveType.REJECTED);
            approverRepository.save(approver);
        }
        // 휴가 상태를 REJECTED로 설정
        vacation.setVstate(VacationState.REJECTED);
        vacationRepository.save(vacation);

    }
}

