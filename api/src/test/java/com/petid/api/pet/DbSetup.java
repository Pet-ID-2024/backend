package com.petid.api.pet;

import jakarta.transaction.Transactional;
import org.springframework.test.context.jdbc.Sql;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Transactional
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Sql({
        "classpath:data/test_member_data.sql",
        "classpath:data/test_member_auth_data.sql",
        "classpath:data/test_pet_data.sql",
        "classpath:data/test_pet_appearance.sql"
})
public @interface DbSetup {
}
