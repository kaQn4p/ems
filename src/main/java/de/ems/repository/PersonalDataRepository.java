package de.ems.repository;

import de.ems.model.PersonalData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface PersonalDataRepository extends JpaRepository<PersonalData, String> {
    PersonalData findByUsername(String username);
    
    @Transactional
    void deleteByUsername(String username);
    
}