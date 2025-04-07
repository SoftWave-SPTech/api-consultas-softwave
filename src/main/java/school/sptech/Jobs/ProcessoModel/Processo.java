package school.sptech.Jobs.ProcessoModel;
/*
Classe para obterProcessos
 */
public class Processo {
//            "processo": "1192602-55.2024.8.26.0100",
    private String numeroProcesso;
//            "classe": "Despejo por Falta de Pagamento Cumulado Com Cobrança",
    private String classe;
//            "assunto": "Locação de Imóvel",
    private String assunto;
//            "foro": "Foro Central Cível",
    private String foro;
//            "vara": "28ª Vara Cível",
    private String vara;
//            "juiz": "FLAVIA POYARES MIRANDA",
    private String juiz;
//            "apensado": "",
    private String apensado;
//            "distribuicao": "04/12/2024 às 18:18 - Livre",
    private String distribuicao;
//            "controle": "2024/004133",
    private String controle;
//            "area": "Cível",
    private String area;
//            "valor_acao": "R$ 36.000,00",
    private String valorAcao;
//            "normalizado_valor_acao": 36000.0,
    private Double normalizadoValorAcao;
//            "outros_numeros": null,
    private String outrosNumeros;
//            "autor": null,
    private String autor;
//            "advogado": null,
    private String advogado;
//            "exectdo": null,
    private String executado;
//            "reqte": "Jarbas Alessandro Rocha Marqueze Advogado:  Felipe Lauriano Rocha Marqueze",
    private String requerente;
//            "reqdo": "Edgar Gualberto Quispe Flores",
    private String requerido;
//            "fiadterc": null,
    private String fiadterc;
//            "indiciado": null,
    private String indiciado;
//            "dados_da_delegacia": [],
    private DadosDelegacia dadosDaDelegacia;
//            "ultimas_movimentacoes": [],
    private UltimasMovimentacoes ultimasMovimentacoes;
//            "peticoes_diversas": [],
    private PeticoesDiversas peticoesDiversas;
//            "incidentes": [],
    private Incidentes incidentes;
//            "apensos": [],
    private Apensos apensos;
//            "audiencias": [],
    private Audiencias audiencias;
//            "historico_classes": []
    private HistoricoClasses historicoClasses;

    public String getNumeroProcesso() {
        return numeroProcesso;
    }

    public void setNumeroProcesso(String numeroProcesso) {
        this.numeroProcesso = numeroProcesso;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getForo() {
        return foro;
    }

    public void setForo(String foro) {
        this.foro = foro;
    }

    public String getVara() {
        return vara;
    }

    public void setVara(String vara) {
        this.vara = vara;
    }

    public String getJuiz() {
        return juiz;
    }

    public void setJuiz(String juiz) {
        this.juiz = juiz;
    }

    public String getApensado() {
        return apensado;
    }

    public void setApensado(String apensado) {
        this.apensado = apensado;
    }

    public String getDistribuicao() {
        return distribuicao;
    }

    public void setDistribuicao(String distribuicao) {
        this.distribuicao = distribuicao;
    }

    public String getControle() {
        return controle;
    }

    public void setControle(String controle) {
        this.controle = controle;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getValorAcao() {
        return valorAcao;
    }

    public void setValorAcao(String valorAcao) {
        this.valorAcao = valorAcao;
    }

    public Double getNormalizadoValorAcao() {
        return normalizadoValorAcao;
    }

    public void setNormalizadoValorAcao(Double normalizadoValorAcao) {
        this.normalizadoValorAcao = normalizadoValorAcao;
    }

    public String getOutrosNumeros() {
        return outrosNumeros;
    }

    public void setOutrosNumeros(String outrosNumeros) {
        this.outrosNumeros = outrosNumeros;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getAdvogado() {
        return advogado;
    }

    public void setAdvogado(String advogado) {
        this.advogado = advogado;
    }

    public String getExecutado() {
        return executado;
    }

    public void setExecutado(String executado) {
        this.executado = executado;
    }

    public String getRequerente() {
        return requerente;
    }

    public void setRequerente(String requerente) {
        this.requerente = requerente;
    }

    public String getRequerido() {
        return requerido;
    }

    public void setRequerido(String requerido) {
        this.requerido = requerido;
    }

    public String getFiadterc() {
        return fiadterc;
    }

    public void setFiadterc(String fiadterc) {
        this.fiadterc = fiadterc;
    }

    public String getIndiciado() {
        return indiciado;
    }

    public void setIndiciado(String indiciado) {
        this.indiciado = indiciado;
    }

    public DadosDelegacia getDadosDaDelegacia() {
        return dadosDaDelegacia;
    }

    public void setDadosDaDelegacia(DadosDelegacia dadosDaDelegacia) {
        this.dadosDaDelegacia = dadosDaDelegacia;
    }

    public UltimasMovimentacoes getUltimasMovimentacoes() {
        return ultimasMovimentacoes;
    }

    public void setUltimasMovimentacoes(UltimasMovimentacoes ultimasMovimentacoes) {
        this.ultimasMovimentacoes = ultimasMovimentacoes;
    }

    public PeticoesDiversas getPeticoesDiversas() {
        return peticoesDiversas;
    }

    public void setPeticoesDiversas(PeticoesDiversas peticoesDiversas) {
        this.peticoesDiversas = peticoesDiversas;
    }

    public Incidentes getIncidentes() {
        return incidentes;
    }

    public void setIncidentes(Incidentes incidentes) {
        this.incidentes = incidentes;
    }

    public Apensos getApensos() {
        return apensos;
    }

    public void setApensos(Apensos apensos) {
        this.apensos = apensos;
    }

    public Audiencias getAudiencias() {
        return audiencias;
    }

    public void setAudiencias(Audiencias audiencias) {
        this.audiencias = audiencias;
    }

    public HistoricoClasses getHistoricoClasses() {
        return historicoClasses;
    }

    public void setHistoricoClasses(HistoricoClasses historicoClasses) {
        this.historicoClasses = historicoClasses;
    }
}
