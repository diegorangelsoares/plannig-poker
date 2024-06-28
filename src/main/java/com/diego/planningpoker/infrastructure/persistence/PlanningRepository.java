package com.diego.planningpoker.infrastructure.persistence;

import com.diego.planningpoker.domain.Planning;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlanningRepository extends JpaRepository<Planning, Long> {

    Optional<Planning> findById(long id);


}

