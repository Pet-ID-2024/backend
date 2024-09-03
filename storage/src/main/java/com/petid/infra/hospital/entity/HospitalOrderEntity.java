package com.petid.infra.hospital.entity;

import com.petid.domain.hospital.model.Hospital;
import com.petid.domain.hospital.model.HospitalOrder;
import com.petid.domain.hospital.type.OrderStatus;
import com.petid.domain.member.model.Member;
import com.petid.infra.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Getter
@AllArgsConstructor
@Table(name = "hospital_order")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HospitalOrderEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private long memberId;
    private long hospitalId;

    private Instant date;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public static HospitalOrderEntity from(
            HospitalOrder hospitalOrder,
            Member member,
            Hospital hospital
    ) {
        return new HospitalOrderEntity(
                hospitalOrder.id(),
                member.id(),
                hospital.id(),
                hospitalOrder.date(),
                hospitalOrder.status()
        );
    }

    public HospitalOrder toDomain(
            String uid
    ) {
        return new HospitalOrder(
                id,
                uid,
                hospitalId,
                date,
                status
        );
    }
}
