package kr.co.ureca.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import kr.co.ureca.entity.Employee;
import kr.co.ureca.entity.Vacation;
import kr.co.ureca.service.ApplyService;

@Controller
public class ApplyController {
	
	@Autowired
	private ApplyService applyService;
	
	@GetMapping("/vacation/apply")
	public String vacationApply(HttpSession session, Model model) {
		int empno = (int) session.getAttribute("empno");
		model.addAttribute("empno", empno);
		
		List<Employee> employee= applyService.getAllEmployees();
		model.addAttribute("employee", employee);
		
		return "vacation_apply";
	}
	
	// @PostMapping() // 사용할 것
	@PostMapping("/submitVacationApply")
	public String submitVacationApply(
            @RequestParam("vtype") String vtype,
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate,
            @RequestParam(value = "approvers", required = false) String[] approvers,
            RedirectAttributes redirectAttributes,
            HttpSession session) {
		
		System.out.println("===== ApplyController submitVacationApply() START =====");
		
		// 입력 데이터 확인 로그 START
		System.out.println("휴가 종류: " + vtype);
        System.out.println("휴가 시작일: " + startDate);
        System.out.println("휴가 종료일: " + endDate);
        if (approvers != null) {
            for (String approver : approvers) {
                System.out.println("선택된 결재선 사원 ID: " + approver);
            }
        }
        // 확인 로그 END
		
        int empno = (int) session.getAttribute("empno");
        // 서비스에 파라미터 전달
        boolean isSuccess = applyService.applyVacation(empno, vtype, startDate, endDate, approvers);
        
        if(!isSuccess) {
            // 실패 시 에러 메시지 리다이렉션에 전달
        	redirectAttributes.addFlashAttribute("errorMessage", "잔여 휴가일이 부족합니다.");
        	 
        	System.out.println("===== ApplyController submitVacationApply() END =====");
            return "redirect:/vacation/apply"; // 실패 시, GET 요청으로 리다이렉트하여 폼을 다시 로드
        }
		
//		  해당 부분 myapply 구현 필요
//        List<Vacation> vacation= applyService.getMyVacation();
//        redirectAttributes.addFlashAttribute("vacation", vacation);
		
		System.out.println("===== ApplyController submitVacationApply() END =====");
		return "redirect:/vacation/myapply";
	}

}