package kr.co.ureca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.co.ureca.entity.EmpvCntId;
import kr.co.ureca.entity.empvcnt;

@Repository
public interface EmpvCntRepository extends JpaRepository<empvcnt, EmpvCntId>{

}
