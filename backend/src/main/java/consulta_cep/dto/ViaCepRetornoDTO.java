package consulta_cep.dto;

import consulta_cep.entity.ViaCep;

public record ViaCepRetornoDTO(
        Long id,
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
    public ViaCepRetornoDTO(ViaCep viaCep) {
        this(
                viaCep.getId(),
                viaCep.getCep(),
                viaCep.getLogradouro(),
                viaCep.getComplemento(),
                viaCep.getBairro(),
                viaCep.getLocalidade(),
                viaCep.getUf(),
                viaCep.getEstado(),
                viaCep.getRegiao(),
                viaCep.getDdd()
        );


    }
}