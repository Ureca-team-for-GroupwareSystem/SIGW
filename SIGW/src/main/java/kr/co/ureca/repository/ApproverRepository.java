package kr.co.ureca.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import kr.co.ureca.entity.ApproveType;
import kr.co.ureca.entity.Approver;
import kr.co.ureca.entity.Employee;
import kr.co.ureca.entity.Vacation;

@Repository
public interface ApproverRepository extends JpaRepository<Approver, Integer>{
	List<Approver> findByEmployee(Employee employee);
	
	@Query("SELECT a.vacation FROM Approver a WHERE a.employee.empno = :empno AND a.astate = :astate")
    List<Vacation> findVacationsByEmployee(@Param("empno") int empno , @Param("astate") ApproveType astate);
	    

	List<Approver> findByEmployeeEmpnoAndAstate(int empno, ApproveType astate);
}
