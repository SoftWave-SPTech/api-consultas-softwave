package school.sptech.Event;

import school.sptech.DTO.ProcessoResponse;
import org.springframework.context.ApplicationEvent;
import java.util.List;

public class ProcessoEvent extends ApplicationEvent {
    private final List<ProcessoResponse> processos;

    public ProcessoEvent(Object source, List<ProcessoResponse> processos) {
        super(source);
        this.processos = processos;
    }

    public List<ProcessoResponse> getProcessos() {
        return processos;
    }
}