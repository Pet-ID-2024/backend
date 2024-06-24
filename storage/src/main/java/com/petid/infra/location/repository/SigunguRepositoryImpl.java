package com.petid.infra.location.repository;

import com.petid.domain.location.Sigungu;
import com.petid.domain.location.SigunguRepository;
import com.petid.infra.location.entity.SigunguEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class SigunguRepositoryImpl implements SigunguRepository {

    private final SigunguJpaRepository jpaRepository;

    @Override
    public List<Sigungu> findAllBySidoId(
            long sidoId
    ) {
        return jpaRepository.findAllBySidoId(sidoId)
                .stream()
                .map(SigunguEntity::toDomain)
                .toList();
    }
}
