package com.uwl3.domain.dao;

import org.springframework.data.jpa.repository.JpaRepository;

public interface patientRepository extends JpaRepository<Patient, Integer> {
}
