package consulta_cep.service;

import consulta_cep.client.ConsultaCepClient;
import consulta_cep.dto.ViaCepResponseDTO;
import consulta_cep.dto.ViaCepRetornoDTO;
import consulta_cep.entity.ViaCep;
import consulta_cep.exceptions.CepNaoEncontrado;
import consulta_cep.repository.ViaCepRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ViaCepService {
    private final ViaCepRepository viaCepRepository;
    private final ConsultaCepClient client;


    public ViaCepService(ViaCepRepository viaCepReposity, ConsultaCepClient client) {
        this.viaCepRepository = viaCepReposity;
        this.client = client;
    }

    public ViaCepRetornoDTO buscarCep(String cep) {
        String cepLimpo = validarELimparCep(cep);
        Optional<ViaCep> viaCepOptional = viaCepRepository.findByCep(cepLimpo);
        if (viaCepOptional.isPresent()) {
            ViaCep viaCep = viaCepOptional.get();
            return new ViaCepRetornoDTO(viaCep);
        }
        ViaCepResponseDTO viaCepResponseDTO = client.buscarCep(cepLimpo);
        if (viaCepResponseDTO == null) {
            throw new RuntimeException("Erro ao consultar o ViaCEP.");
        }
        ViaCep viaCep = new ViaCep(viaCepResponseDTO);
        ViaCep viaCepSalvo = viaCepRepository.save(viaCep);
        ViaCepRetornoDTO viaCepRetornoDTO = new ViaCepRetornoDTO(viaCepSalvo);
        return viaCepRetornoDTO;
    }
    public List<ViaCepRetornoDTO> listarHistorico () {
        List<ViaCep> viaCeps = viaCepRepository.findAll();
        List<ViaCepRetornoDTO> viaCepRetornoDTOS = viaCeps.stream().map(
                ViaCepRetornoDTO::new).collect(Collectors.toList());
        return viaCepRetornoDTOS;

    }
    public void deletarHistoricoPorId(Long id) {
        if (!viaCepRepository.existsById(id)) {
            throw new CepNaoEncontrado("O CEP com o ID informado não foi encontrado.");
        }

        viaCepRepository.deleteById(id);
    }


    private String validarELimparCep(String cep) {
        String cepLimpo = cep.replaceAll("\\D", "");
        if (!cepLimpo.matches("\\d{8}")) {
            throw new IllegalArgumentException("CEP inválido. Informe exatamente 8 números.");
        }

        return cepLimpo;


    }


}
