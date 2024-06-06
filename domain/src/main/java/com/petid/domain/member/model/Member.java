package com.petid.domain.member.model;

import com.petid.domain.type.Role;

public record Member(
<<<<<<< Updated upstream
        Long id,
        String uid,
        String platform,
        String email,
        Role role
=======
    String uid,
    String platform,
    String email,
    Role role
>>>>>>> Stashed changes
) {
}
