package school.sptech.Config;

import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import school.sptech.DTO.ProcessoResponse;
import school.sptech.DTO.UltimasMovimentacoesResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProcessoGrau1API {

    public static List<ProcessoResponse> consultarProcessos() throws IOException, ParseException {
        HttpPost httppost = new HttpPost("https://api.infosimples.com/api/v2/consultas/tribunal/tjsp/primeiro-grau");

        // Configura os parâmetros da API
        List<NameValuePair> params = new ArrayList<>();
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

        return getDadosApi(httppost);
    }

    private static List<ProcessoResponse> getDadosApi(HttpPost httppost) throws IOException, ParseException {
        List<ProcessoResponse> processosList = new ArrayList<>();
        HttpClient httpclient = HttpClients.createDefault();

        try (CloseableHttpResponse httpResponse = (CloseableHttpResponse) httpclient.execute(httppost)) {
            String body = EntityUtils.toString(httpResponse.getEntity());
            JSONObject response = new JSONObject(body);

            if (response.getInt("code") != 200) {
                throw new RuntimeException("Erro na API: código " + response.optInt("code") + ", mensagem: " + response.optString("code_message"));
            }

            JSONArray dataArray = response.getJSONArray("data");
            if (dataArray.isEmpty()) return processosList;

            JSONArray processosJson = dataArray.getJSONObject(0).getJSONArray("processos");

            for (int i = 0; i < processosJson.length(); i++) {
                JSONObject processoJson = processosJson.getJSONObject(i);
                ProcessoResponse processoResponse = mapProcessoResponse(processoJson);

                // Mapear últimas movimentações
                JSONArray ultimasMovimentacoesJson = processoJson.optJSONArray("ultimas_movimentacoes");
                if (ultimasMovimentacoesJson != null) {
                    List<UltimasMovimentacoesResponse> movimentacoesList = new ArrayList<>();
                    for (int j = 0; j < ultimasMovimentacoesJson.length(); j++) {
                        JSONObject movJson = ultimasMovimentacoesJson.getJSONObject(j);
                        UltimasMovimentacoesResponse mov = new UltimasMovimentacoesResponse();
                        mov.setData(LocalDate.parse(movJson.getString("data"))); // assumindo formato YYYY-MM-DD
                        mov.setMovimento(movJson.getString("movimento"));
                        movimentacoesList.add(mov);
                    }
                    processoResponse.setUltimasMovimentacoes(movimentacoesList);
                }

                processosList.add(processoResponse);
            }

        }

        return processosList;
    }

    private static ProcessoResponse mapProcessoResponse(JSONObject processoJson) {
        ProcessoResponse p = new ProcessoResponse();
        p.setNumeroProcesso(processoJson.optString("processo", ParametrosAPI.getParametroProcesso()));
        p.setClasse(processoJson.optString("classe", "Não informado"));
        p.setAssunto(processoJson.optString("assunto", "Não informado"));
        p.setForo(processoJson.optString("foro", "Não informado"));
        p.setVara(processoJson.optString("vara", "Não informado"));
        p.setJuiz(processoJson.optString("juiz", "Não informado"));
        p.setApensado(processoJson.optString("apensado", "Não informado"));
        p.setDistribuicao(processoJson.optString("distribuicao", "Não informado"));
        p.setControle(processoJson.optString("controle", "Não informado"));
        p.setArea(processoJson.optString("area", "Não informado"));
        p.setValorAcao(processoJson.optString("valor_acao", "Não informado"));
        p.setNormalizadoValorAcao(processoJson.optDouble("normalizado_valor_acao", 0.0));
        p.setAutor(processoJson.optString("autor", "Não informado"));
        p.setAdvogado(processoJson.optString("advogado", "Não informado"));
        p.setExecutado(processoJson.optString("executado", "Não informado"));
        p.setRequerente(processoJson.optString("reqte", "Não informado"));
        p.setRequerido(processoJson.optString("reqdo", "Não informado"));
        p.setIndiciado(processoJson.optString("indiciado", "Não indiciado"));
        return p;
    }
}
