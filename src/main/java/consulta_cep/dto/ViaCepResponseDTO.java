package consulta_cep.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ViaCepResponseDTO(
    String cep,
    String logradouro,
    String complemento,
    
) {
}
