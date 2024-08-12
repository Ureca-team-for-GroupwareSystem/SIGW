package kr.co.ureca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.ureca.entity.department;

public interface DepartmentRepository extends JpaRepository<department, Integer>{

}