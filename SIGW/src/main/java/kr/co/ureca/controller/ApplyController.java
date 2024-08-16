package kr.co.ureca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class ApplyController {
	
//	@Autowired
//	private ApplyService applyService;
	
	@GetMapping("/vacation/apply")
	public String vacationApply(HttpSession session, Model model) {
		int empno = (int) session.getAttribute("empno");
		model.addAttribute("empno", empno);
		
		
		return "vacation_apply"; 
	}

}
