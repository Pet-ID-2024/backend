package com.petid.infra.member.entity;

import com.petid.domain.member.MemberAuth;
import com.petid.infra.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "member_auth")
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
                member.getId(),
                name,
                ssn,
                phone,
                address
        );
    }
}
