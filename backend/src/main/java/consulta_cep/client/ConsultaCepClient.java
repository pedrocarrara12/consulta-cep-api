package consulta_cep.client;

import consulta_cep.dto.ViaCepResponseDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class ConsultaCepClient {
    private final RestClient restClient;

    public ConsultaCepClient() {
        this.restClient = RestClient.builder()
                .baseUrl("https://viacep.com.br/ws")
                .build();
    }
    public ViaCepResponseDTO buscarCep(String cep) {
        return restClient
                .get()
                .uri("/{cep}/json", cep)
                .retrieve()
                .body(ViaCepResponseDTO.class);
    }


}
