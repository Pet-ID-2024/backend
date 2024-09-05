package com.petid.batch.model;

public record LocationModel(
        Object address,
        String address_name,
        String address_type,
        Object road_address,
        Double x,
        Double y
) {
}
