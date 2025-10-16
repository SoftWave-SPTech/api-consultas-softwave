package school.sptech.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import school.sptech.Config.ProcessoGrau1API;
import school.sptech.DTO.ProcessoResponse;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.util.List;

@Service
public class ProcessoService {

    @Value("${api.principal.url}")
    private String apiPrincipalUrl;

    private final RestTemplate restTemplate;

    public ProcessoService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<ProcessoResponse> consultarPorOab(String oab) throws IOException {
        school.sptech.Config.ParametrosAPI.setParametroOab(oab);
        List<ProcessoResponse> processos = ProcessoGrau1API.consultarProcessos();
        enviarParaApiPrincipal(processos);
        return processos;
    }

    public List<ProcessoResponse> consultarPorNumeroProcesso(String numeroProcesso) throws IOException {
        school.sptech.Config.ParametrosAPI.setParametroProcesso(numeroProcesso);
        List<ProcessoResponse> processos = ProcessoGrau1API.consultarProcessos();
        enviarParaApiPrincipal(processos);
        return processos;
    }

    private void enviarParaApiPrincipal(List<ProcessoResponse> processos) {
        try {
            restTemplate.postForEntity(apiPrincipalUrl, processos, Void.class);
            System.out.println("Processos enviados com sucesso para a API principal (" + processos.size() + ")");
        } catch (Exception e) {
            System.err.println("Falha ao enviar processos para a API principal: " + e.getMessage());
        }
    }
}
