package kr.co.ureca.entity;

import jakarta.persistence.Embeddable;
import lombok.Getter;

import java.io.Serializable;

@Embeddable
@Getter
public class EmpvCntId implements Serializable {

    private static final long serialVersionUID = 1L;

    private VacationType vtype;

    private int empno;

    // 기본 생성자
    public EmpvCntId() {}

    // 매개변수가 있는 생성자
    public EmpvCntId(VacationType vtype, int empno) {
        this.vtype = vtype;
        this.empno = empno;
    }

    // equals와 hashCode 메서드는 복합 키 클래스에서 필수입니다.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmpvCntId that = (EmpvCntId) o;

        if (empno != that.empno) return false;
        return vtype == that.vtype;
    }

    @Override
    public int hashCode() {
        int result = vtype != null ? vtype.hashCode() : 0;
        result = 31 * result + empno;
        return result;
    }
    
    @Override
    public String toString() {
        return "EmpvCntId{" +
                "vtype=" + vtype +
                ", empno=" + empno +
                '}';
    }
    
}
