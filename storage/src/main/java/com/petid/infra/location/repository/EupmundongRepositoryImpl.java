package com.petid.infra.location.repository;

import com.petid.domain.location.model.Eupmundong;
import com.petid.domain.location.repository.EupmundongRepository;
import com.petid.infra.location.entity.EupmundongEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class EupmundongRepositoryImpl implements EupmundongRepository {

    private final EupmundongJpaRepository jpaRepository;

    @Override
    public List<Eupmundong> findAllBySigunguId(long sigunguId) {
        return jpaRepository.findAllBySigunguId(sigunguId)
                .stream()
                .map(EupmundongEntity::toDomain)
                .toList();
    }

    @Override
    public List<Eupmundong> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(EupmundongEntity::toDomain)
                .toList();
    }
}
