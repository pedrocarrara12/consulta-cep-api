package consulta_cep.service;

import consulta_cep.client.ConsultaCepClient;
import consulta_cep.dto.ViaCepResponseDTO;
import consulta_cep.dto.ViaCepRetornoDTO;
import consulta_cep.entity.ViaCep;
import consulta_cep.repository.ViaCepReposity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ViaCepService  {
    private final ViaCepReposity viaCepReposity;
    private final ConsultaCepClient client;


    public ViaCepService(ViaCepReposity viaCepReposity, ConsultaCepClient client) {
        this.viaCepReposity = viaCepReposity;
        this.client = client;
    }
    public ViaCepRetornoDTO buscarCep(String cep) {
        String cepLimpo =validarELimparCep(cep);
        Optional<ViaCep> viaCepOptional = viaCepReposity.findByCep(cepLimpo);
        if (viaCepOptional.isPresent()) {
            ViaCep viaCep = viaCepOptional.get();
            return new ViaCepRetornoDTO(viaCep);
        }
        ViaCepResponseDTO viaCepResponseDTO  = client.buscarCep(cepLimpo);
        if (viaCepResponseDTO == null) {
            throw new RuntimeException("Erro ao consultar o ViaCEP.");
        }
        ViaCep viaCep = new ViaCep(viaCepResponseDTO);
        ViaCep viaCepSalvo = viaCepReposity.save(viaCep);
        ViaCepRetornoDTO viaCepRetornoDTO = new ViaCepRetornoDTO(viaCepSalvo);
        return viaCepRetornoDTO;

        







    }
    private String validarELimparCep(String cep) {
        String cepLimpo = cep.replaceAll("\\D", "");
        if (!cepLimpo.matches("\\d{8}")) {
            throw new IllegalArgumentException("CEP inválido. Informe exatamente 8 números.");
        }

        return cepLimpo;


    }


}
