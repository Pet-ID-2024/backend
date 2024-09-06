package com.petid.infra.hospital.entity;

import com.petid.domain.hospital.model.HospitalOrder;
import com.petid.domain.hospital.type.OrderStatus;
import com.petid.domain.member.model.Member;
import com.petid.infra.common.BaseEntity;
import com.petid.infra.member.entity.MemberEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity(name="hospital_order")
@Getter
@AllArgsConstructor
@Table(name = "hospital_order")
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HospitalOrderEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Instant date;
    private long hospitalId;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "member_id", referencedColumnName = "id")
    private MemberEntity member;


    public static HospitalOrderEntity from(
            HospitalOrder hospitalOrder,
            Member member
    ) {
        return new HospitalOrderEntity(
                hospitalOrder.id(),
                hospitalOrder.date(),
                hospitalOrder.hospitalId(),
                hospitalOrder.status(),
                MemberEntity.from(member)
        );
    }

    public HospitalOrder toDomain() {
        return new HospitalOrder(
                id,
                date,
                hospitalId,
                status,
                member.getId()
        );
    }
}
