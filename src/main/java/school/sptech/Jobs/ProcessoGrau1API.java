package school.sptech.Jobs;
//

import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;
import school.sptech.Jobs.ProcessoModel.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

////mvnrepository.com/artifact/org.json/json

public class ProcessoGrau1API{

    static Logger logger = Logger.getLogger(String.valueOf(ProcessoGrau1API.class));

    public static void getApiParams() throws IOException {

        HttpPost httppost = new HttpPost("https://api.infosimples.com/api/v2/consultas/tribunal/tjsp/primeiro-grau");
//  "0000005-27.2025.8.26.0008";
//  "509556";
//  "hdVPC0gzW8u6f9cb6cvCC75d-G6Q1brCLjy_NWJG";
//  "600";
        List<NameValuePair> params = new ArrayList<>(2);
        params.add(new BasicNameValuePair("processo", ParametrosAPI.getParametroProcesso()));
        params.add(new BasicNameValuePair("parte", ParametrosAPI.getParametroParte()));
        params.add(new BasicNameValuePair("cpf", ParametrosAPI.getParametroCpf()));
        params.add(new BasicNameValuePair("cnpj", ParametrosAPI.getParametroCnpj()));
        params.add(new BasicNameValuePair("rg", ParametrosAPI.getParametroRg()));
        params.add(new BasicNameValuePair("advogado", ParametrosAPI.getParametroAdvogado()));
        params.add(new BasicNameValuePair("oab", ParametrosAPI.getParametroOab()));
        params.add(new BasicNameValuePair("carta_precatoria", ParametrosAPI.getParametroCartaPrecatoria()));
        params.add(new BasicNameValuePair("documento_delegacia", ParametrosAPI.getParametroDocumentoDelegacia()));
        params.add(new BasicNameValuePair("cda", ParametrosAPI.getParametroCda()));
        params.add(new BasicNameValuePair("pagina", ParametrosAPI.getParametroPagina()));
        params.add(new BasicNameValuePair("token", ParametrosAPI.getTOKEN()));
        params.add(new BasicNameValuePair("timeout", ParametrosAPI.getTIMEOUT()));
        httppost.setEntity(new UrlEncodedFormEntity(params));
        logger.info("Processando dados");
        getDadosAPi(
                httppost
        );

    }

    public static void getDadosAPi(HttpPost httppost) throws IOException {
        HttpClient httpclient = HttpClients.createDefault();

        try (CloseableHttpResponse httpResponse = (CloseableHttpResponse) httpclient.execute(httppost)) {
            String body = EntityUtils.toString(httpResponse.getEntity());
            JSONObject response = new JSONObject(body);

            if (response.getInt("code") == 200) {
                System.out.println("Retorno com sucesso: \n" + response.get("data"));
                // Pegando o primeiro objeto do array

                JSONArray dataArray = response.getJSONArray("data");
                JSONObject firstDataObject = dataArray.getJSONObject(0); // Pega o primeiro objeto dentro de "data"
                JSONArray processosObject = firstDataObject.getJSONArray("processos"); // Obtém o valor de "processos"
                System.out.println("Quantidade de Processos encontrados: " + processosObject.length());
//                System.out.println(processosObject);


                for (int i = 0; i < processosObject.length(); i++) {
                    Processo novoProcesso = new Processo();
                    JSONObject processo = processosObject.getJSONObject(i);

                    // Extraindo os dados filtrados
                    String numeroProcesso = processo.getString("processo");
                    novoProcesso.setNumeroProcesso(numeroProcesso);

                    String classe = processo.getString("classe");
                    novoProcesso.setClasse(classe);

                    String assunto = processo.getString("assunto");
                    novoProcesso.setAssunto(assunto);

                    String foro = processo.getString("foro");
                    novoProcesso.setForo(foro);

                    String vara = processo.getString("vara");
                    novoProcesso.setVara(vara);

                    String juiz = processo.getString("juiz");
                    novoProcesso.setJuiz(juiz);

                    String apensado = processo.optString("apensado", "Não informado");
                    novoProcesso.setApensado(apensado);

                    String distribuicao = processo.getString("distribuicao");
                    novoProcesso.setDistribuicao(distribuicao);

                    String controle = processo.getString("controle");
                    novoProcesso.setControle(controle);

                    String area = processo.getString("area");
                    novoProcesso.setArea(area);

                    String valorAcao = processo.getString("valor_acao");
                    novoProcesso.setValorAcao(valorAcao);

                    Double valorAcaoNormalizado = processo.getDouble("normalizado_valor_acao");
                    novoProcesso.setNormalizadoValorAcao(valorAcaoNormalizado);

                    String autor = processo.optString("autor", "Não informado");
                    novoProcesso.setAutor(autor);

                    String advogado = processo.optString("advogado", "Não informado");
                    novoProcesso.setAdvogado(advogado);

                    String executado = processo.optString("executado", "Não informado");
                    novoProcesso.setExecutado(executado);

                    String requerido = processo.optString("reqdo", "Não informado");
                    novoProcesso.setRequerido(requerido);

                    String requerente = processo.optString("reqte", "Não informado");
                    novoProcesso.setRequerente(requerente);

                    String indiciado = processo.optString("indiciado", "Não indiciado");
                    novoProcesso.setIndiciado(indiciado);

                    // Agora aqui será a logica para criação de movimentações dentro da tabela de movimentações7
                    // que tera uma fk na tabela de processo
                    JSONArray ultimasMovimentacoes = processo.getJSONArray("ultimas_movimentacoes");
                    if (ultimasMovimentacoes.isEmpty()){
                        System.out.println("Ultimas movimentações não informadas");
                    } else {
                        for (Object obj : ultimasMovimentacoes) {
                            UltimasMovimentacoes novaMovimentacao = new UltimasMovimentacoes();
                            JSONObject ultimasMovimentacoesObject = (JSONObject) obj;
                            String dataMovimentacao = ultimasMovimentacoesObject.getString("data");
                            novaMovimentacao.setData(dataMovimentacao);
                            String movimento = ultimasMovimentacoesObject.getString("movimento");
                            novaMovimentacao.setMovimento(movimento);
                            // será um Save no repository das movimentações
                            // Adicionar ID do Processo
                        }
                    }
                   JSONArray peticoesDiversas = processo.getJSONArray("peticoes_diversas");
                   if (peticoesDiversas.isEmpty()) {
                       System.out.println("Peticoes diversas não informadas");
                   }else{
                       for (Object obj : peticoesDiversas) {
                           JSONObject peticoesDiversasObject = (JSONObject) obj;
                           PeticoesDiversas novaPeticao = new PeticoesDiversas();
                           String dataPeticao = peticoesDiversasObject.getString("data");
                           novaPeticao.setData(dataPeticao);
                           String tipoPeticao = peticoesDiversasObject.optString("tipo", "Não Informado");
                           novaPeticao.setTipo(tipoPeticao);
                           // adicionar ID do Processo
                       }
                   }
                   JSONArray apensos = processo.getJSONArray("apensos");
                   if (apensos.isEmpty()) {
                       System.out.println("Apensos não informados");
                   }else {
                       for (Object object : apensos) {
                           JSONObject apenso = (JSONObject) object;
                           Apensos novoApenso = new Apensos();
                           String numeroProcessoApenso = apenso.getString("numero");
                           novoApenso.setNumeroProcesso(numeroProcessoApenso);
                           String classeApenso = apenso.getString("classe");
                           novoApenso.setClasse(classeApenso);
                           String apensamento = apenso.getString("apensamento");
                           novoApenso.setApensamento(apensamento);
                           String motivo = apenso.getString("motivo");
                           novoApenso.setMotivo(motivo);
                           // Adicionar ID do Processo
                       }
                   }

                   JSONArray audiencias = processo.getJSONArray("audiencias");
                   if (audiencias.isEmpty()) {
                       System.out.println("Audiências não informadas");
                   }else {
                       for (Object obj : audiencias) {
                           JSONObject audienciaObject = (JSONObject) obj;
                           Audiencias audiencia = new Audiencias();
                           audiencia.setData(audienciaObject.getString("data"));
                           audiencia.setAudiencia(audienciaObject.getString("audiencia"));
                           audiencia.setSituacao(audienciaObject.getString("situacao"));
                           audiencia.setQuantidadePessoas(audienciaObject.getString("quantidade_pessoas"));
                           // Adicionar ID do Processo
                       }
                   }

                }

            } else if (response.getInt("code") >= 600 && response.getInt("code") <= 799) {
                String mensagem = "Um erro aconteceu. Leia para saber mais:\n";
                mensagem += "Código: " + response.get("code") + " (" + response.get("code_message") + ")\n";
                if (!response.isNull("errors")) {
                    mensagem += response.getJSONArray("errors").join("; ");
                }
                System.out.println(mensagem);
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}