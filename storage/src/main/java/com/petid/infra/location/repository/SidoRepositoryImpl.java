package com.petid.infra.location.repository;

import com.petid.domain.location.model.Eupmundong;
import com.petid.domain.location.model.Sido;
import com.petid.domain.location.repository.EupmundongRepository;
import com.petid.domain.location.repository.SidoRepository;
import com.petid.infra.location.entity.EupmundongEntity;
import com.petid.infra.location.entity.SidoEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class SidoRepositoryImpl implements SidoRepository {

    private final SidoJpaRepository jpaRepository;

    @Override
    public List<Sido> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(SidoEntity::toDomain)
                .toList();
    }
}
