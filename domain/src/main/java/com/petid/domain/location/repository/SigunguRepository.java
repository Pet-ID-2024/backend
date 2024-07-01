package com.petid.domain.location.repository;

import com.petid.domain.location.model.Sigungu;

import java.util.List;
import java.util.Optional;

public interface SigunguRepository {
    List<Sigungu> findAllBySidoId(long sidoId);

    Optional<Sigungu> findById(long id);
}
