package com.gabriel.party.repositories.prestador;

import com.gabriel.party.model.prestador.Prestador;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PrestadorRepository extends JpaRepository<Prestador, UUID> {
    boolean existsByEmailIgnoreCase(String email);

    Page<Prestador> findAllByAtivoTrue(Pageable pageable);

    Optional<Prestador> findByIdAndAtivoTrue(UUID id);
}
