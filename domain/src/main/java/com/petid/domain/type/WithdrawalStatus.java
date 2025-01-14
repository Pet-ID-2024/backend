package com.petid.domain.type;

public enum WithdrawalStatus {
    NORMAL,      // 일반 상태
    IN_PROGRESS, // 탈퇴 진행 중 상태 (복구 O)
    COMPLETED    // 탈퇴 완료 상태 (복구 X)
}
