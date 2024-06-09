package com.petid.infra.pet.converter;

import com.petid.domain.type.Breed;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class BreedConverter implements AttributeConverter<Breed, String> {

    @Override
    public String convertToDatabaseColumn(Breed breed) {
        if (breed == null) {
            return null;
        }
        return breed.getDisplayName();
    }

    @Override
    public Breed convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        return Breed.fromDisplayName(dbData);
    }
}
