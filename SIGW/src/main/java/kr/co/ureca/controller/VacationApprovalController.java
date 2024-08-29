package kr.co.ureca.controller;

import org.apache.hc.core5.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import kr.co.ureca.service.ApproverService;
import kr.co.ureca.service.VacationApprovalService;

@Controller
public class VacationApprovalController {


    @Autowired
    private VacationApprovalService vacationApprovalService;

    @PostMapping("/vacation/approve")
    public String approveVacation(@RequestParam("requestId") int vid, @RequestParam("action") String action, HttpSession session) {
        Integer empno = (Integer) session.getAttribute("empno");

        if ("approve".equals(action)) {
            vacationApprovalService.approveVacation(vid, empno);
        } else if ("reject".equals(action)) {
            vacationApprovalService.rejectVacation(vid);
        }
//        return "redirect:/home/{empno}"; // 오류 페이지로 리다이렉트
        return "redirect:/vacation/approval"; 
        
    }

    @PostMapping("/vacation/reject")
    public String rejectVacation(@RequestParam("requestId") int vid, @RequestParam("action") String action) {
    	if ("reject".equals(action)) {
    		vacationApprovalService.rejectVacation(vid);
    	}
    	
//        return "redirect:/home/{empno}";
    	return "redirect:/vacation/approval"; 
    	
    }
}