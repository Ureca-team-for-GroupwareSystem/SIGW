package kr.co.ureca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.co.ureca.entity.vacation;

@Repository
public interface vacationRepository extends JpaRepository<vacation, Integer>{

}
