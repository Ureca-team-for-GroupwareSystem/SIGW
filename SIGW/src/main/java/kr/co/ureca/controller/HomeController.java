package kr.co.ureca.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.ureca.entity.employee;
import kr.co.ureca.entity.empvcnt;
import kr.co.ureca.service.HomeService;

@Controller
@RequestMapping("/home")
public class HomeController {
	
	@Autowired
	private HomeService homeService;
	
	@GetMapping("")
	public String home() {
		return "home";
	}
	
	@GetMapping("/{empno}")
	public String getEmployeeInfo(@PathVariable int empno, Model model) {
		Optional<employee> employee = homeService.getEmployeeById(empno);
		List<empvcnt> vacations = homeService.getEmpVCntsByEmpno(empno);
		if(employee.isPresent()) {
			model.addAttribute("employee", employee.get());
			model.addAttribute("vacations", vacations);
		} else {
			return "home";
		}
		return "home"; 
	}
}
