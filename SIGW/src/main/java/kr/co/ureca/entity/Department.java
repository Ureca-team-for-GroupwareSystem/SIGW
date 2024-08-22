package kr.co.ureca.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import jakarta.persistence.*;

@Entity
@Table(name= "department")
@Getter
@Setter
@ToString
public class Department {
	
	@Id
	private int deptno; 
	
	// 직원들과의 관계 설정
    @OneToMany(mappedBy = "deptno", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Employee> employees;
}
