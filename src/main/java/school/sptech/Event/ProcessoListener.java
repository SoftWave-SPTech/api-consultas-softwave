package school.sptech.Event;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import school.sptech.DTO.ProcessoResponse;

import java.util.List;

@Component
public class ProcessoListener {

    private final RabbitTemplate rabbitTemplate;

    @Value("${broker.exchange.name}")
    private String exchangeName;

    @Value("${broker.routing.key}")
    private String routingKey;

    public ProcessoListener(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @EventListener
    public void onProcessoConsultado(ProcessoEvent event) {
        List<ProcessoResponse> processos = event.getProcessos();

        rabbitTemplate.convertAndSend(exchangeName, routingKey, processos);

        System.out.println("📤 Processos enviados ao microserviço principal via RabbitMQ: "
                + processos.size());
    }
}
