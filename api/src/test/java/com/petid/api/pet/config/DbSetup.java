package com.petid.api.pet.config;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import jakarta.transaction.Transactional;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Transactional
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@DbUnitConfiguration(databaseConnection = "dbUnitDatabaseConnection")
@DatabaseSetup(value = {"user.xml"}, type = DatabaseOperation.CLEAN_INSERT)
@DatabaseTearDown(value = {"user.xml"}, type = DatabaseOperation.DELETE_ALL)
public @interface DbSetup {
}
