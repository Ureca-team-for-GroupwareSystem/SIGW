package kr.co.ureca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.co.ureca.entity.Vacation;

@Repository
public interface VacationRepository extends JpaRepository<Vacation, Integer>{

}
