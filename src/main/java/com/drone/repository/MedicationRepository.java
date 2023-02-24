package com.drone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.drone.model.Medication;

@Repository
public interface MedicationRepository extends JpaRepository<Medication, String> {

}
