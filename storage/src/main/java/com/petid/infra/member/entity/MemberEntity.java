package com.petid.infra.member.entity;

import com.petid.domain.member.model.Member;
import com.petid.domain.type.Role;
import com.petid.domain.type.WithdrawalStatus;
import com.petid.infra.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "member")
public class MemberEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String uid;
    @Column(nullable = false)
    private String platform;
    private String fcmToken;
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private WithdrawalStatus status;
    private LocalDate statusUpdateDate;

    public Member toDomain() {
        return new Member(
                id,
                uid,
                platform,
                fcmToken,
                email,
                role,
                status,
                statusUpdateDate
        );
    }

    public static MemberEntity from(Member member) {
        return new MemberEntity(
                member.id(),
                member.uid(),
                member.platform(),
                member.fcmToken(),
                member.email(),
                member.role(),
                member.status(),
                member.statusUpdateDate()
        );
    }
}
