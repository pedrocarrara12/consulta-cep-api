package consulta_cep.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import consulta_cep.entity.ViaCep;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ViaCepResponseDTO(
    String cep,
    String logradouro,
    String complemento,
    String bairro,
    String localidade,
    String uf,
    String estado,
    String regiao,
    String ddd

    
) {
    public ViaCepResponseDTO(ViaCep viaCep) {
        this(
                viaCep.getCep(),
                viaCep.getLogradouro(),
                viaCep.getComplemento(),
                viaCep.getBairro(),
                viaCep.getLocalidade(),
                viaCep.getUf(),
                viaCep.getEstado(),
                viaCep.getRegiao(),
                viaCep.getDdd());


}}
