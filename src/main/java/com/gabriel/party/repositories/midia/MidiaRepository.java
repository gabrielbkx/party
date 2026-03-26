package com.gabriel.party.repositories.midia;

import com.gabriel.party.model.midia.Midia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MidiaRepository extends JpaRepository<Midia, UUID> {
    Page<Midia> findAllByAtivoTrue(Pageable pageable);
    Optional<Midia> findByIdAndAtivoTrue(UUID id);
}
