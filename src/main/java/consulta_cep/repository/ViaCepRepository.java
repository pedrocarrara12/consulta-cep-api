package consulta_cep.repository;

import consulta_cep.entity.ViaCep;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ViaCepRepository extends JpaRepository<ViaCep,Long> {

    Optional<ViaCep> findByCep(String cep);

}
