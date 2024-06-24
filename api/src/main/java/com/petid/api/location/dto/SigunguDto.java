package com.petid.api.location.dto;

import com.petid.domain.location.Sigungu;

public record SigunguDto() {

    public record Response(
          long id,
          String name
    ) {
        public static Response from(Sigungu sigungu) {
            return new Response(
                    sigungu.id(),
                    sigungu.name()
            );
        }
    }
}
