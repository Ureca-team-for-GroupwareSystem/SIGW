package kr.co.ureca.entity;


import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import jakarta.persistence.*;

@Entity
@Table(name= "employee")
@Getter
@Setter
@ToString
public class employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int empno; 
	
	@ManyToOne
    @JoinColumn(name = "deptno", nullable = false)
    private department deptno;  // 부서와의 관계 설정
	
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
    private List<empvcnt> empvcnts;
    
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<vacation> vacations;
    
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Approver> approverlist;
	
	
}

