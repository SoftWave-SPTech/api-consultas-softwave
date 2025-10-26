package school.sptech.Service;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import school.sptech.Config.ProcessoGrau1API;
import school.sptech.DTO.ProcessoResponse;
import school.sptech.Event.ProcessoEvent;

import java.io.IOException;
import java.util.List;

@Service
public class ProcessoService {

    private final ApplicationEventPublisher publisher;


    public ProcessoService(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    public List<ProcessoResponse> consultarPorOab(String oab) throws IOException {
        school.sptech.Config.ParametrosAPI.setParametroOab(oab);
        List<ProcessoResponse> processos = ProcessoGrau1API.consultarProcessos();
        publisher.publishEvent(new ProcessoEvent(this, processos));
        return processos;
    }

    public List<ProcessoResponse> consultarPorNumeroProcesso(String numeroProcesso) throws IOException {
        school.sptech.Config.ParametrosAPI.setParametroProcesso(numeroProcesso);
        List<ProcessoResponse> processos = ProcessoGrau1API.consultarProcessos();
        publisher.publishEvent(new ProcessoEvent(this, processos));
        return processos;
    }

}
