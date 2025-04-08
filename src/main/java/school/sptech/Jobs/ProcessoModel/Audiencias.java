package school.sptech.Jobs.ProcessoModel;

public class Audiencias {
//                          "data": "26/02/2025",
//                          "audiencia": "Instrução, Interrogatório, Debates e Julgamento",
//                          "situacao": "Realizada",
//                          "quantidade_pessoas": "1"
//  @Many to one (fk_processo)
    private Integer id;
    private String data;
    private String audiencia;
    private String situacao;
    private String quantidadePessoas;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getAudiencia() {
        return audiencia;
    }

    public void setAudiencia(String audiencia) {
        this.audiencia = audiencia;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getQuantidadePessoas() {
        return quantidadePessoas;
    }

    public void setQuantidadePessoas(String quantidadePessoas) {
        this.quantidadePessoas = quantidadePessoas;
    }
}
