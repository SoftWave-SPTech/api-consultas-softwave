package school.sptech.DTO;

import java.util.List;

public class ProcessoResponse {
    private String numeroProcesso;
    private String classe;
    private String assunto;
    private String foro;
    private String vara;
    private String juiz;
    private String apensado;
    private String distribuicao;
    private String controle;
    private String area;
    private String valorAcao;
    private Double normalizadoValorAcao;
    private String descricao;
    private String autor;
    private String executado;
    private String requerente;
    private String requerido;
    private String indiciado;
    private List<UltimasMovimentacoesResponse> ultimasMovimentacoes;

    // Getters e setters
    public String getNumeroProcesso() { return numeroProcesso; }
    public void setNumeroProcesso(String numeroProcesso) { this.numeroProcesso = numeroProcesso; }
    public String getClasse() { return classe; }
    public void setClasse(String classe) { this.classe = classe; }
    public String getAssunto() { return assunto; }
    public void setAssunto(String assunto) { this.assunto = assunto; }
    public String getForo() { return foro; }
    public void setForo(String foro) { this.foro = foro; }
    public String getVara() { return vara; }
    public void setVara(String vara) { this.vara = vara; }
    public String getJuiz() { return juiz; }
    public void setJuiz(String juiz) { this.juiz = juiz; }
    public String getApensado() { return apensado; }
    public void setApensado(String apensado) { this.apensado = apensado; }
    public String getDistribuicao() { return distribuicao; }
    public void setDistribuicao(String distribuicao) { this.distribuicao = distribuicao; }
    public String getControle() { return controle; }
    public void setControle(String controle) { this.controle = controle; }
    public String getArea() { return area; }
    public void setArea(String area) { this.area = area; }
    public String getValorAcao() { return valorAcao; }
    public void setValorAcao(String valorAcao) { this.valorAcao = valorAcao; }
    public Double getNormalizadoValorAcao() { return normalizadoValorAcao; }
    public void setNormalizadoValorAcao(Double normalizadoValorAcao) { this.normalizadoValorAcao = normalizadoValorAcao; }
    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public String getExecutado() { return executado; }
    public void setExecutado(String executado) { this.executado = executado; }
    public String getRequerente() { return requerente; }
    public void setRequerente(String requerente) { this.requerente = requerente; }
    public String getRequerido() { return requerido; }
    public void setRequerido(String requerido) { this.requerido = requerido; }
    public String getIndiciado() { return indiciado; }
    public void setIndiciado(String indiciado) { this.indiciado = indiciado; }
    public List<UltimasMovimentacoesResponse> getUltimasMovimentacoes() { return ultimasMovimentacoes; }
    public void setUltimasMovimentacoes(List<UltimasMovimentacoesResponse> ultimasMovimentacoes) { this.ultimasMovimentacoes = ultimasMovimentacoes; }
}