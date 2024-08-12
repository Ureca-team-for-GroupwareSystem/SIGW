package kr.co.ureca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.co.ureca.entity.department;

@Repository
public interface DepartmentRepository extends JpaRepository<department, Integer>{

}