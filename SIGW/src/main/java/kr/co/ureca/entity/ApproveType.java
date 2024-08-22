package kr.co.ureca.entity;

public enum ApproveType {
	DEFAULT,  //기본
	NOTYET,   //내 차례 아님 
	MYTURN,  // 내 차례임 
	PENDING,  //결제중
	REJECTED, //거절
	APPROVED  //승인
}
