package com.petid.infra.hospital.repository;

import com.petid.domain.hospital.model.Hospital;
import com.petid.domain.hospital.repository.HospitalRepository;
import com.petid.infra.hospital.entity.HospitalEntity;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.io.WKTWriter;
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
    private final GeometryFactory geometryFactory;

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
        String insertHospitalQuery = """
                INSERT INTO hospital (sido_id, sigungu_id, eupmundong_id, address, name, tel, vet, location)
                VALUES (?, ?, ?, ?, ?, ?, ?, ST_GeomFromText(?))
                """;

        jdbcTemplate.batchUpdate(insertHospitalQuery,
                hospitals,
                hospitals.size(),
                (PreparedStatement ps, Hospital hospital) -> {
                    Coordinate coordinate = (hospital.location().lon() != null && hospital.location().lat() != null)
                            ? new Coordinate(hospital.location().lon(), hospital.location().lat())
                            : null;

                    ps.setLong(1, hospital.sidoId());
                    ps.setLong(2, hospital.sigunguId());
                    ps.setLong(3, hospital.eupmundongId());
                    ps.setString(4, hospital.address());
                    ps.setString(5, hospital.name());
                    ps.setString(6, hospital.tel());
                    ps.setString(7, hospital.vet());
                    ps.setString(8, new WKTWriter().write(geometryFactory.createPoint(coordinate)));
                });
    }

    @Override
    public List<Hospital> findAllByLocationIds(
            long sidoId,
            long sigunguId,
            List<Long> eupmundongIds
    ) {
        return qRepository.findAllByLocation(sidoId, sigunguId, eupmundongIds)
                .stream()
                .map(HospitalEntity::toDomain)
                .toList();
    }

    @Override
    public List<Hospital> findAllByLocationIdsOrderByLocation(
            int sidoId,
            int sigunguId,
            List<Long> eupmundongIds,
            double lat,
            double lon
    ) {
        return qRepository.findAllByLocationIdsOrderByLocation(sidoId, sigunguId, eupmundongIds, lat, lon)
                .stream()
                .map(HospitalEntity::toDomain)
                .toList();
    }
}
