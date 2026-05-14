package consulta_cep.entity;

import consulta_cep.dto.ViaCepResponseDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ViaCep {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;
    private String estado;
    private String regiao;
    private String ddd;

    public ViaCep() {
    }

    public ViaCep(ViaCepResponseDTO viaCepResponseDTO) {
        this.cep = viaCepResponseDTO.cep().replaceAll("[^0-9]", "");
        this.logradouro = viaCepResponseDTO.logradouro();
        this.complemento = viaCepResponseDTO.complemento();
        this.bairro = viaCepResponseDTO.bairro();
        this.localidade = viaCepResponseDTO.localidade();
        this.uf = viaCepResponseDTO.uf();
        this.estado = viaCepResponseDTO.estado();
        this.regiao = viaCepResponseDTO.regiao();
        this.ddd = viaCepResponseDTO.ddd();
    }

    public Long getId() {
        return id;
    }

    public String getCep() {
        return cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public String getLocalidade() {
        return localidade;
    }

    public String getUf() {
        return uf;
    }

    public String getEstado() {
        return estado;
    }

    public String getRegiao() {
        return regiao;
    }

    public String getDdd() {
        return ddd;
    }
}
