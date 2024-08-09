package kr.co.ureca.entity;


import java.time.LocalDateTime;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import jakarta.persistence.*;

@Entity
@Table(name= "empvcnt")
@Getter
@Setter
@ToString
public class empvcnt {
	
	@Id
	private VacationType vtype; 
	
	@ManyToOne
    @JoinColumn(name = "empno", nullable = false)
    private employee employee;  // 부서와의 관계 설정
	
	@Column(name = "vremain",nullable= false)
	private int vremain;	
	
	@Column(name = "vused",nullable= false)
	private int vused;	
	
}


