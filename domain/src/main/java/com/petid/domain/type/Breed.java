package com.petid.domain.type;

public enum Breed {
    GOLDEN_RETRIEVER("Golden Retriever"),
    LABRADOR("Labrador"),
    BEAGLE("Beagle"),
    MALTESE("Maltese");    

    private final String displayName;

    Breed(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static Breed fromDisplayName(String displayName) {
        for (Breed breed : Breed.values()) {
            if (breed.getDisplayName().equalsIgnoreCase(displayName)) {
                return breed;
            }
        }
        throw new IllegalArgumentException("Unknown display name: " + displayName);
    }
}
