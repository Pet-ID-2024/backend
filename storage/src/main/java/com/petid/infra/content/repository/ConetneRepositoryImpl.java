package com.petid.infra.content.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import com.petid.domain.content.repository.ContentRepository;

@Repository
@RequiredArgsConstructor
public class ConetneRepositoryImpl implements ContentRepository {

    private final ContentJpaRepository ContentJpaRepository;
}
