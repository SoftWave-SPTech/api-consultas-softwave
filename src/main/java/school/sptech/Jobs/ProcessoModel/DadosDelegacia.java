package school.sptech.Jobs.ProcessoModel;

public class DadosDelegacia {

//            "documento": "Inquérito Policial",
//             "numero": "2053985/2024",
//             "distrito_policial": "02º D.P. CARAPICUIBA",
//             "municipio": "Carapicuíba-SP"
    private String documento;
    private String numero;
    private String distritoPolicial;
    private String municipio;

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getDistritoPolicial() {
        return distritoPolicial;
    }

    public void setDistritoPolicial(String distritoPolicial) {
        this.distritoPolicial = distritoPolicial;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }
}
