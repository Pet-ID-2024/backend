package com.petid.api.location.dto;

import com.petid.domain.location.model.Eupmundong;
import com.petid.domain.location.model.Sido;
import com.petid.domain.location.model.Sigungu;

public record LocationDto() {

    public record Response(
          long id,
          String name
    ) {
        public static Response from(Sigungu sigungu) {
            return new Response(
                    sigungu.id(),
                    sigungu.showName()
            );
        }

        public static Response from(Eupmundong eupmundong) {
            return new Response(
                    eupmundong.id(),
                    eupmundong.showName()
            );
        }

        public static Response from(Sido sido) {
            return new Response(
                    sido.id(),
                    sido.showName()
            );
        }
    }
}
