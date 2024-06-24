package com.petid.domain.location;

import java.util.List;

public interface SigunguRepository {
    List<Sigungu> findAllBySidoId(long sidoId);
}
