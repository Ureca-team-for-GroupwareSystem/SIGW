package kr.co.ureca.entity;


import java.time.LocalDateTime;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import jakarta.persistence.*;

@Entity
@Table(name= "approver")
@Getter
@Setter
@ToString
public class Approver {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ano; 
	
	@ManyToOne
    @JoinColumn(name = "vid", nullable = false)
    private vacation vid;  // 부서와의 관계 설정
	
	@ManyToOne
    @JoinColumn(name = "aemp", nullable = false)
    private employee employee;  // 부서와의 관계 설정
	
	@Column(name = "acnt",nullable= false)
	private int acnt;
	
	@Column(name = "astate",nullable= false)
	private approveType astate;
	
	
	
}


