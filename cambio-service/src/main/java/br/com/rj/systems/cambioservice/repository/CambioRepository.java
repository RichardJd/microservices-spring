package br.com.rj.systems.cambioservice.repository;

import br.com.rj.systems.cambioservice.model.Cambio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CambioRepository extends JpaRepository<Cambio, Long> {

    Optional<Cambio> findByFromAndTo(String from, String to);
}
