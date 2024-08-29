package kr.co.ureca.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import kr.co.ureca.entity.Employee;
import kr.co.ureca.entity.EmpvCntId;
import kr.co.ureca.entity.Empvcnt;
import kr.co.ureca.entity.VacationType;

@Repository
public interface EmpvCntRepository extends JpaRepository<Empvcnt, EmpvCntId>{
	
	Optional<Empvcnt> findByEmployee(Employee employee);
	
	@Query("SELECT e FROM Empvcnt e WHERE e.id.empno = :empno AND e.id.vtype = :vtype")
    Optional<Empvcnt> findByEmpnoAndVtype(@Param("empno") int empno, @Param("vtype") VacationType vtype);
	
	List<Empvcnt> findByEmployeeEmpno(int empno);
}
