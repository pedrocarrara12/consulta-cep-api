package consulta_cep.repository;

import consulta_cep.entity.ViaCep;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ViaCepReposity extends JpaRepository<ViaCep,Long> {

    Optional<ViaCep> findByCep(String cep);
}
