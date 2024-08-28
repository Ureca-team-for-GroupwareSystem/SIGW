package kr.co.ureca.entity;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.sql.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import jakarta.persistence.*;

@Entity
@Table(name= "vacation")
@Getter
@Setter
@ToString
public class Vacation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int vid; 
	
	@ManyToOne
    @JoinColumn(name = "vemp", nullable = false)
    private Employee employee;  // 부서와의 관계 설정
	
	@Column(name = "vtype",nullable= false)
	private VacationType vtype;
	
	@Column(name = "vdate",nullable= false)
	private Date vdate;
	
	@Column(name = "vstart",nullable= false)
	private Date vstart;
	
	@Column(name = "vend",nullable= false)
	private Date vend;
	
	@Column(name = "vstate",nullable= false)
	private VacationState vstate;
	
	@OneToMany(mappedBy = "vacation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Approver> approvers;
	
	@PrePersist
    public void prePersist() {
        // 현재 날짜를 vdate에 설정
        if (vdate == null) {
            vdate = Date.valueOf(LocalDate.now());
        }
    }
	
}


