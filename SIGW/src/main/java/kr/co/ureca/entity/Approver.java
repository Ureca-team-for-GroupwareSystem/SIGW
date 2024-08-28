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
	private int ano; // 결제 ID 
	
	@ManyToOne
    @JoinColumn(name = "vid", nullable = false)
    private Vacation vacation;  // 신청할 휴가의 번호 
	
	@ManyToOne
    @JoinColumn(name = "aemp", nullable = false)
    private Employee employee;  // 결제자의 번호 
	
	@Column(name = "acnt",nullable= false)
	private int acnt; // 결제순서 높은번호부터 결제를 시작하며 0번이 최종결제자가 된다. 
	
	@Column(name = "astate",nullable= false)
	private ApproveType astate; // 결제 상태 설정 . DEFAULT, NOTYET, MYTURN, PENDING,REJECTED,APPROVED 으로 구성되어 있다.
	//내 차례면 MYTURN, 아직이면 NOTYET , 내 차례가 지나면 PENDING, 0번 결제자 최종승인시 APPROVED , 거절 시 REJECTED 로 구성된다.  
	
	
	
}


