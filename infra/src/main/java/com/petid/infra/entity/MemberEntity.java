package com.petid.infra.entity;

import com.petid.domain.member.Member;
import com.petid.domain.type.Role;
import com.petid.infra.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "member")
public class MemberEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String uid;
    private String platform;
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

    public Member toDomain() {
        return new Member(
                uid,
                platform,
                email,
                role
        );
    }

    public static MemberEntity from(Member member) {
        return new MemberEntity(
                null,
                member.uid(),
                member.platform(),
                member.email(),
                member.role()
        );
    }
}
