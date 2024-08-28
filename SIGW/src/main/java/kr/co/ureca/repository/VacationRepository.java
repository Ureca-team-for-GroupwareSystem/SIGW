package kr.co.ureca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.co.ureca.entity.Employee;
import kr.co.ureca.entity.Vacation;

@Repository
public interface VacationRepository extends JpaRepository<Vacation, Integer>{
	List<Vacation> findByEmployee(Employee employee);
	
	List<Vacation> findByVidIn(List<Integer> vidList);
}
