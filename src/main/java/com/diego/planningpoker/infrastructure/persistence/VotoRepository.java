package com.diego.planningpoker.infrastructure.persistence;

import com.diego.planningpoker.domain.Historia;
import com.diego.planningpoker.domain.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VotoRepository extends JpaRepository<Voto, Long> {

    Optional<Voto> findById(long id);

    List<Voto> findVotoByHistoria(Historia historia);

    void deleteAllByHistoria(Historia historia);


}

