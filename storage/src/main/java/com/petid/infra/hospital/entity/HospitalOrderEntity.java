package com.petid.infra.hospital.entity;

import com.petid.domain.hospital.model.Hospital;
import com.petid.domain.hospital.model.HospitalOrder;
import com.petid.domain.hospital.type.OrderStatus;
import com.petid.domain.member.model.Member;
import com.petid.infra.common.BaseEntity;
import com.petid.infra.member.entity.MemberEntity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity
@Getter
@AllArgsConstructor
@Table(name = "hospital_order")
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HospitalOrderEntity extends BaseEntity {
    public HospitalOrderEntity(Long id, long memberId, HospitalEntity hospital, Instant date, OrderStatus status) {
        this.id = id;
        this.memberId = memberId;
        this.hospital = hospital;
        this.date = date;
        this.status = status;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "member_id")
    private long memberId;
    private long hospitalId;

    private Instant date;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "member_id", referencedColumnName = "id", insertable = false, updatable = false)
    private MemberEntity member;

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

    public HospitalOrder toDomain(
    ) {
        String uid = (member != null) ? member.getUid() : null;
        return new HospitalOrder(
                id,
                uid,
                hospital.getId(),
                date,
                status
        );
    }
}
