package school.sptech.Jobs.ProcessoModel;

public class PeticoesDiversas {
//    {
//        "data": "29/11/2024",
//         "tipo": "Contestação com Reconvenção"
//    },
//   @Many to one (fk_processo)
    private Integer id;
    private String data;
    private String tipo;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
