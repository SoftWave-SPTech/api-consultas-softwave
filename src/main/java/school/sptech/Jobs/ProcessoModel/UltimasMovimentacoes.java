package school.sptech.Jobs.ProcessoModel;

public class UltimasMovimentacoes {
//    {
//            "data": "28/03/2025",
//            "movimento": "Petição Juntada Nº Protocolo: WJMJ.25.40720318-9 Tipo da Petição: Petições Diversas Data: 28/03/2025 17:12"
//    },

    // @Many to one (fk_processo)
    private Integer id;
    private String data;
    private String movimento;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMovimento() {
        return movimento;
    }

    public void setMovimento(String movimento) {
        this.movimento = movimento;
    }
}
