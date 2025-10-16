package school.sptech.DTO;

import java.time.LocalDate;

public class UltimasMovimentacoesResponse {
    private LocalDate data;
    private String movimento;

    // Getters e setters
    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }
    public String getMovimento() { return movimento; }
    public void setMovimento(String movimento) { this.movimento = movimento; }
}