# 🧑🏻‍💻 [SIGW] 그룹웨어시스템

## 휴가결재 파트 구현

### 프로젝트 소개
- Spring을 활용하여 그룹웨어시스템의 휴가결재에 대해 간단하게 구현해보았습니다.
- 개인이 휴가 신청서를 작성할 수 있습니다.
- 신청하는 휴가에 대한 결재선을 지정할 수 있습니다.
- 휴가 결재는 순차적으로 이루어지며 부서번호가 낮고 직급이 낮은 순부터 결재가 진행됩니다.
- 최종 결재가가 승인하면 휴가 신청이 승인됩니다.
- 로그인 기능은 구현하지 않아 홈 URL Path variable에 유저 id를 추가하면 <br>이후 세션에 저장되어 프로세스가 진행됩니다.

<홈 화면>
![image](https://github.com/user-attachments/assets/3ae8c7fa-3919-4680-9a11-453f95c75995)
<br>

<휴가신청 화면>
![image](https://github.com/user-attachments/assets/25d76d90-e34d-4c5d-ba78-3227f147b2a3)
<br>

<신청현황 화면>
![image](https://github.com/user-attachments/assets/c93e01f5-5407-48e8-8391-dc6e9f5031a1)
<br>

<직원휴가결재 화면>
![image](https://github.com/user-attachments/assets/af70e54a-6a4c-4233-a513-f2e51c83e169)
<br>


### 개발환경
- Back: Spring Boot, Spring JPA, Mysql
- Front: html, css, jsp
- 협업툴: Notion, Slack

### 개발 기간
2024/8/9 ~ 2024/8/29

### ERD
<img width="893" alt="image" src="https://github.com/user-attachments/assets/a49dbcf2-9fb5-48f7-a1ba-01c637defa5b">

### 주요 고민거리
- 결재선 처리 로직에 대한 고민이 많았습니다.
- N명의 사람을 선택해도 원하는 순서대로 결재선이 구성되어야 했습니다.
- DB에서는 결재 상태와 순번 컬럼을 두었습니다.
- 휴가 신청시 다음과 같은 과정을 거쳤습니다.
```java
// 부서 번호 및 직급 순서로 정렬
approverList.sort((a1, a2) -> {
  ...
});

// acnt: 결재순번, astate: 결재상태
int acnt = 0;
for (Approver approver : approverList) {
    approver.setAcnt(acnt);
    if (acnt == approverList.size()-1) {
        approver.setAstate(ApproveType.MYTURN);
    } else {
        approver.setAstate(ApproveType.NOTYET);
    }
    acnt++;
}
```
- 이후 순번을 따라서 결재 상태를 바꿔주는 처리를 해주어 결재선을 구현했습니다.
