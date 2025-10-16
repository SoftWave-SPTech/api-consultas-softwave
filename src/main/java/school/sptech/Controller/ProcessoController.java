package school.sptech.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.DTO.*;
import school.sptech.Service.ProcessoService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/processos")
public class ProcessoController {

    private final ProcessoService processoService;

    public ProcessoController(ProcessoService processoService) {
        this.processoService = processoService;
    }

    @PostMapping("/consulta-oab")
    public ResponseEntity<List<ProcessoResponse>> consultarPorOab(@RequestBody CredenciaisOABRequest request) {
        try {
            List<ProcessoResponse> resposta = processoService.consultarPorOab(request.getOab());
            return ResponseEntity.ok(resposta);
        } catch (IOException  e) {
            e.printStackTrace(); // opcional: log em vez de print
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

    @PostMapping("/consulta-numero")
    public ResponseEntity<List<ProcessoResponse>> consultarPorNumeroProcesso(@RequestBody CredenciaisNumProcessoRequest request) {
        try {
            List<ProcessoResponse> resposta = processoService.consultarPorNumeroProcesso(request.getNumeroProcesso());
            return ResponseEntity.ok(resposta);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }
}
