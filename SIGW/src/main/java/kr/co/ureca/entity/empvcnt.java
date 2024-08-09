package kr.co.ureca.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "empvcnt")
@Getter
@Setter
@ToString
public class empvcnt {

    @EmbeddedId
    private EmpvCntId id;

    @MapsId("empno")
    @ManyToOne
    @JoinColumn(name = "empno", nullable = false)
    private employee employee; // 부서와의 관계 설정

    @Column(name = "vremain", nullable = false)
    private int vremain;

    @Column(name = "vused", nullable = false)
    private int vused;
    
    // 다른 필요한 메서드들
}


