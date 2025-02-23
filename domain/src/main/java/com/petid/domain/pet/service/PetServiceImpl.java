package com.petid.domain.pet.service;

import com.petid.domain.exception.PetAlreadyExistsException;
import com.petid.domain.exception.PetNotFoundException;
import com.petid.domain.member.model.MemberAuthInfo;
import com.petid.domain.member.service.MemberService;
import com.petid.domain.pet.manager.PetManager;
import com.petid.domain.pet.model.Pet;
import com.petid.domain.pet.model.PetAppearance;
import com.petid.domain.pet.model.PetImage;
import com.petid.domain.pet.repository.PetAppearanceRepository;
import com.petid.domain.pet.repository.PetImageRepository;
import com.petid.domain.pet.repository.PetRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class PetServiceImpl implements PetService {

    private final PetRepository petRepo;
    private final PetAppearanceRepository petAppearanceRepo;
    private final PetImageRepository petImgRepo;
    private final MemberService memberService;
    private final PetManager petManager;

    @Override
    @Transactional
    public Pet createPet(Pet pet, MemberAuthInfo memberAuth) {
        if (petManager.isOwnerPetExists(memberAuth.memberId())) throw new PetAlreadyExistsException(memberAuth.memberId());
        
        PetAppearance savedPetAppearance = petAppearanceRepo.createPetAppearance(pet.appearance());
        Pet savedPet = petRepo.createPet(pet.updatePetAppearance(savedPetAppearance));
        petRepo.updatePet(savedPet);
        List<PetImage> petImages = pet.petImages();
        List<PetImage> newPetImages = new ArrayList<PetImage>();
        if (petImages != null && !petImages.isEmpty()) {
            petImages.forEach(image -> newPetImages.add(petImgRepo.createPetImage(savedPet.petId(), image)));
        }

        memberService.updateMemberAuth(memberAuth);
        return savedPet.updatePetAppearance(savedPetAppearance).updatePetImages(newPetImages);
    }

    @Override
    public Pet updatePet(
            long petId,
            Pet updatePetData
    ) {
        Pet targetPet = petManager.get(petId);
        Pet updatedPet = targetPet.update(updatePetData);

        return petRepo.save(updatedPet);
    }

    @Override
    @Transactional
    public void deletePet(Long petId) {
        petRepo.deletePet(petId);
    }

    @Override
    public Pet findPetById(Long petId) {
        return petManager.get(petId);
    }

    @Override
    public List<Pet> findAllPets() {
        return petRepo.findAllPets();
    }

    @Override
    @Transactional
    public PetImage createPetImage(PetImage petImage) {
        long petId = petImage.petId();
        if (!petManager.existByPetId(petId)) {
            throw new PetNotFoundException(petId);
        }
        return petImgRepo.createPetImage(petId, petImage);
    }

    @Override
    @Transactional
    public PetImage updatePetImage(PetImage petImage) {
        long petId = petImage.petId();
        if (!petManager.existByPetId(petId)) {
            throw new PetNotFoundException(petId);
        }

        return petImgRepo.updatePetImage(petImage);
    }

    @Override
    @Transactional
    public void deletePetImage(Long petImageId) {
        petImgRepo.deletePetImage(petImageId);
    }

    @Override
    public Optional<PetImage> findPetImageById(Long petId, Long petImageId) {
        if (!petRepo.findPetById(petId).isPresent()) {
            throw new RuntimeException("Pet not found for ID: " + petId);
        }
        return petImgRepo.findPetImageById(petImageId);
    }

    @Override
    public List<PetImage> findAllPetImages() {
        return petImgRepo.findAllPetImages();
    }

    @Override
    @Transactional
    public PetAppearance createPetAppearance(Long petId, PetAppearance petAppearance) {
        if (!petRepo.findPetById(petId).isPresent()) {
            throw new RuntimeException("Pet not found for ID: " + petId);
        }

        return petAppearanceRepo.createPetAppearance(petAppearance);
    }

    @Override
    @Transactional
    public PetAppearance updatePetAppearance(Long petId, PetAppearance petAppearance) {
        if (petRepo.findPetById(petId).isEmpty()) {
            throw new PetNotFoundException(petId);
        }

        return petAppearanceRepo.updatePetAppearance(petAppearance);
    }

    @Override
    @Transactional
    public void deletePetAppearance(Long petId) {
        petAppearanceRepo.deletePetAppearanceById(petId);
    }

    @Override
    public Optional<PetAppearance> findPetAppearanceById(Long appearanceId) {
        return petAppearanceRepo.findPetAppearanceById(appearanceId);
    }

    @Override
    public List<PetAppearance> findAllPetAppearances() {
        return petAppearanceRepo.findAllPetAppearances();
    }

    public void deletePetById(Long petId) {
        Pet pet = petRepo.findPetById(petId)
                .orElseThrow(() -> new PetNotFoundException(petId));

        // Delete PetAppearance associated with the pet
        if (pet.appearance() != null && pet.appearance().appearanceId() != null) {
            petAppearanceRepo.deletePetAppearanceById(pet.appearance().appearanceId());
        }
        // Delete PetImage associated with the pet
        petImgRepo.deletePetImageByPetId(petId);
        // Delete the pet
        petRepo.deletePet(petId);

    }

    public void deletePetImage(long petId, long imageId) {
        if (!petManager.existByPetId(petId)) {
            throw new PetNotFoundException(petId);
        }
        petImgRepo.deletePetImage(imageId);
    }
}
