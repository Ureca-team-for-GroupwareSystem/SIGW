package kr.co.ureca.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
	
	@GetMapping("/vacation/myapply")
	public String vacationMyApply() {
		return "vacation_myapply"; 
	}
	
	@GetMapping("/vacation/approval")
	public String vacationApproval() {
		return "vacation_approval"; 
	}
}
