package com.petid.infra.location.repository;

import com.petid.domain.location.model.Sigungu;
import com.petid.domain.location.repository.SigunguRepository;
import com.petid.infra.location.entity.SigunguEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Optional<Sigungu> findById(
            long id
    ) {
        return jpaRepository.findById(id)
                .map(SigunguEntity::toDomain);
    }
}
