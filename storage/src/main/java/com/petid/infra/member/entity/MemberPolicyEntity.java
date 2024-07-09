package com.petid.infra.member.entity;

import com.petid.domain.member.model.Member;
import com.petid.domain.member.model.MemberPolicy;
import com.petid.infra.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "member_policy")
public class MemberPolicyEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "member_id")
    @OneToOne(fetch = FetchType.LAZY)
    private MemberEntity member;

    private boolean advertisement;

    public MemberPolicy toDomain() {
        return new MemberPolicy(
                id,
                member.getId(),
                advertisement
        );
    }

    public static MemberPolicyEntity from(
            MemberPolicy policy,
            Member member
    ) {
        return new MemberPolicyEntity(
                policy.id(),
                MemberEntity.from(member),
                policy.advertisement()
        );
    }
}
