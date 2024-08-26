package kr.co.ureca.entity;

public enum VacationState {
    PENDING("대기 중"),   // 대기 중
    APPROVED("승인됨"),  // 승인됨
    REJECTED("거부됨");  // 거부됨

    private final String stateName;

    VacationState(String stateName) {
        this.stateName = stateName;
    }

    @Override
    public String toString() {
        return stateName;
    }

    // 문자열로부터 Enum을 찾는 메서드 (선택적)
    public static VacationState fromString(String stateName) {
        for (VacationState state : VacationState.values()) {
            if (state.stateName.equals(stateName)) {
                return state;
            }
        }
        throw new IllegalArgumentException("Unknown state: " + stateName);
    }
}

