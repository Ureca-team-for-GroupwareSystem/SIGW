package kr.co.ureca.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import kr.co.ureca.entity.Employee;
import kr.co.ureca.entity.Empvcnt;
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
	
	@GetMapping("/{pathEmpno}") // 로그인 기능 구현시 수정될 part 1
	public String getEmployeeInfo(HttpSession session, @PathVariable int pathEmpno, Model model) {
		session.setAttribute("empno", pathEmpno); // 로그인 기능 구현시 수정될 part 2
		int empno = (int) session.getAttribute("empno");
		
		Optional<Employee> employee = homeService.getEmployeeById(empno);
		List<Empvcnt> vacations = homeService.getEmpVCntsByEmpno(empno);
		
		if(employee.isPresent()) {
			model.addAttribute("empno", empno);
			model.addAttribute("employee", employee.get());
			model.addAttribute("vacations", vacations);
		} else {
			return "home";
//			return "login"; // 페이지 구현 필요 (로그인 페이지)
		}
		return "home"; 
	}
}
