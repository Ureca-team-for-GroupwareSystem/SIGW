package kr.co.ureca.entity;


import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import jakarta.persistence.*;

@Entity
@Table(name= "employee")
@Getter
@Setter
@ToString(exclude = {"deptno", "empvcnts", "vacations", "approverlist"})
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int empno; 
	
	@ManyToOne
    @JoinColumn(name = "deptno", nullable = false)
    private Department deptno;  // 부서와의 관계 설정
	
	@Column(name = "empname", length = 100,nullable= false)
	private String empname;	
	
	@Column(name = "position",nullable= false)
	private int position;	
	
	@Column(name = "phonenum", length = 100,nullable= false)
	private String phonenum;	
	
	@Column(name = "address", length = 255,nullable= false)
	private String address;	
	
	@Column(nullable= false)
	private Date hiredate;
	
	@Column(nullable= false)
	private Date birthdate;
	
	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Empvcnt> empvcnts;
    
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Vacation> vacations;
    
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Approver> approverlist;
	
	
}

