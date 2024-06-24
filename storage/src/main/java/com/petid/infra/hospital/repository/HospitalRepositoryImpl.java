package com.petid.infra.hospital.repository;

import com.petid.domain.hospital.model.Hospital;
import com.petid.domain.hospital.repository.HospitalRepository;
import com.petid.infra.hospital.entity.HospitalEntity;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class HospitalRepositoryImpl implements HospitalRepository {

    private final JdbcTemplate jdbcTemplate;
    private final HospitalJpaRepository jpaRepository;
    private final QHospitalRepository qRepository;

    @Override
    public Optional<Hospital> findById(
            Long id
    ) {
        return jpaRepository.findById(id)
                .map(HospitalEntity::toDomain);
    }

    @Override
    @Transactional
    public void saveAllBulk(
            List<Hospital> hospitals
    ) {
        String sql = """
                INSERT INTO hospital (sigungu_id, address, name, tel, vet)
                VALUES (?, ?, ?, ?, ?)
                """;

        jdbcTemplate.batchUpdate(sql,
                hospitals,
                hospitals.size(),
                (PreparedStatement ps, Hospital hospital) -> {
                    ps.setLong(1, hospital.sigunguId());
                    ps.setString(2, hospital.address());
                    ps.setString(3, hospital.name());
                    ps.setString(4, hospital.tel());
                    ps.setString(5, hospital.vet());
                });
    }

    @Override
    public List<Hospital> findAllBySigunguId(
            int sidoId, 
            int sigunguId, 
            List<Integer> eupmundongIds
    ) {
        return qRepository.findAllByLocation(sidoId, sigunguId, eupmundongIds)
                .stream()
                .map(HospitalEntity::toDomain)
                .toList();
    }
}
