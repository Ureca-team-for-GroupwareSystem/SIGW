package kr.co.ureca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.co.ureca.entity.EmpvCntId;
import kr.co.ureca.entity.Empvcnt;

@Repository
public interface EmpvCntRepository extends JpaRepository<Empvcnt, EmpvCntId>{

	
	List<Empvcnt> findByEmployeeEmpno(int empno);
}
