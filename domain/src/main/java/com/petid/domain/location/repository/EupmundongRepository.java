package com.petid.domain.location.repository;

import com.petid.domain.location.model.Eupmundong;

import java.util.List;

public interface EupmundongRepository {
    List<Eupmundong> findAllBySigunguId(long sigunguId);

    List<Eupmundong> findAll();
}
