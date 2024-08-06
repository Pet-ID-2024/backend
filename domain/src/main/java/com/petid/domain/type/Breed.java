package com.petid.domain.type;

public enum Breed {
	GOLDEN_RETRIEVER("Golden Retriever"),
    LABRADOR("Labrador Retriever"),
    BEAGLE("Beagle"),
    MALTESE("Maltese"),    
    BULLDOG("Bulldog"),
    POODLE("Poodle"),
    SIBERIAN_HUSKY("Siberian Husky"),
    GERMAN_SHEPHERD("German Shepherd"),
    DACHSHUND("Dachshund"),
    BOXER("Boxer"),
    CHIHUAHUA("Chihuahua"),
	DRAGON("Dragon");

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
