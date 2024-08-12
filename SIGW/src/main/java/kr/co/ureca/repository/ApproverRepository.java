package kr.co.ureca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.co.ureca.entity.Approver;

@Repository
public interface ApproverRepository extends JpaRepository<Approver, Integer>{

}
