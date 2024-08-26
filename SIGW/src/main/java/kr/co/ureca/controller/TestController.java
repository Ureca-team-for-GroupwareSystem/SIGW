package kr.co.ureca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;
import kr.co.ureca.entity.Employee;
import kr.co.ureca.entity.Vacation;
import kr.co.ureca.service.EmployeeService;
import kr.co.ureca.service.VacationService;

import java.util.List;

@Controller
public class TestController {

    @Autowired
    private VacationService vacationService;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/vacation/myapply")
    public String vacationMyApply(HttpSession session, Model model) {
        // 세션에서 empno 가져오기
        Integer empno = (Integer) session.getAttribute("empno");
        
        if (empno == null) {
            // empno가 null이면 로그인되지 않은 상태일 수 있음
            return "redirect:/login"; // 로그인 페이지로 리다이렉트
        }

        // empno를 이용하여 Employee 객체를 조회
        Employee employee = employeeService.findById(empno);
        if (employee == null) {
            // Employee가 데이터베이스에 존재하지 않는 경우 처리
            return "error"; // 오류 페이지로 리다이렉트
        }

        // Vacation 목록을 조회
        List<Vacation> vacationList = vacationService.getVacationsByEmployee(employee);
        model.addAttribute("vacationList", vacationList);
        return "vacation_myapply"; 
    }
    @GetMapping("/vacation/approval")
    public String vacationApproval() {
        return "vacation_approval"; 
    }
}
