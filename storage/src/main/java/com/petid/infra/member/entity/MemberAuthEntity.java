package com.petid.infra.member.entity;

import com.petid.domain.member.model.MemberAuth;
import com.petid.infra.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@Table(name = "member_auth")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberAuthEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "member_id")
    @OneToOne(fetch = FetchType.LAZY)
    private MemberEntity member;

    private String name;
    private String ssn;
    private String phone;
    private String address;

    public MemberAuth toDomain() {
        return new MemberAuth(
                id,
                member.getUid(),
                name,
                ssn,
                phone,
                address
        );
    }
}
