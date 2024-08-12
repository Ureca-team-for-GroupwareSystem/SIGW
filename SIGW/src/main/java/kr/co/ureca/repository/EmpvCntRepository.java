package kr.co.ureca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.ureca.entity.EmpvCntId;
import kr.co.ureca.entity.empvcnt;

public interface EmpvCntRepository extends JpaRepository<empvcnt, EmpvCntId>{

}
