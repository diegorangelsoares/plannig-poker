package com.diego.planningpoker.infrastructure.persistence;

import com.diego.planningpoker.domain.Historia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HistoriaRepository extends JpaRepository<Historia, Long> {

    Optional<Historia> findById(long id);


}

