package de.ems.repository;

import de.ems.model.PersonalData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalDataRepository extends JpaRepository<PersonalData, Long> {
    PersonalData findByUsername(String username);
}