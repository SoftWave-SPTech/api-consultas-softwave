package school.sptech.Config;

import org.json.JSONArray;
import org.json.JSONObject;
import school.sptech.DTO.ProcessoResponse;
import school.sptech.DTO.UltimasMovimentacoesResponse;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProcessoGrau1API {

    public static List<ProcessoResponse> consultarProcessos() throws IOException {
        // URL da API
        String urlStr = "https://api.infosimples.com/api/v2/consultas/tribunal/tjsp/primeiro-grau";
        URL url = new URL(urlStr);

        // Configura a conexão HTTP
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");

        // Parâmetros da API
        Map<String, String> params = new HashMap<>();
        params.put("processo", ParametrosAPI.getParametroProcesso());
        params.put("parte", ParametrosAPI.getParametroParte());
        params.put("cpf", ParametrosAPI.getParametroCpf());
        params.put("cnpj", ParametrosAPI.getParametroCnpj());
        params.put("rg", ParametrosAPI.getParametroRg());
        params.put("advogado", ParametrosAPI.getParametroAdvogado());
        params.put("oab", ParametrosAPI.getParametroOab());
        params.put("carta_precatoria", ParametrosAPI.getParametroCartaPrecatoria());
        params.put("documento_delegacia", ParametrosAPI.getParametroDocumentoDelegacia());
        params.put("cda", ParametrosAPI.getParametroCda());
        params.put("pagina", ParametrosAPI.getParametroPagina());
        params.put("token", ParametrosAPI.getTOKEN());
        params.put("timeout", ParametrosAPI.getTIMEOUT());

        // Converte os parâmetros para formato form-urlencoded
        StringBuilder postData = new StringBuilder();
        for (Map.Entry<String, String> param : params.entrySet()) {
            if (postData.length() != 0) postData.append('&');
            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
            postData.append('=');
            postData.append(URLEncoder.encode(param.getValue(), "UTF-8"));
        }

        // Envia os parâmetros
        try (OutputStream os = connection.getOutputStream()) {
            byte[] postDataBytes = postData.toString().getBytes("UTF-8");
            os.write(postDataBytes);
            os.flush();
        }

        // Lê a resposta
        int status = connection.getResponseCode();
        InputStream inputStream = (status >= 200 && status < 300)
                ? connection.getInputStream()
                : connection.getErrorStream();

        String responseBody;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            StringBuilder responseBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                responseBuilder.append(line);
            }
            responseBody = responseBuilder.toString();
        }

        connection.disconnect();

        // Processa o JSON da resposta
        return parseResponse(responseBody);
    }

    private static List<ProcessoResponse> parseResponse(String body) throws IOException {
        List<ProcessoResponse> processosList = new ArrayList<>();
        JSONObject response = new JSONObject(body);

        if (response.optInt("code") != 200) {
            throw new IOException("Erro na API: código " + response.optInt("code") +
                    ", mensagem: " + response.optString("code_message"));
        }

        JSONArray dataArray = response.optJSONArray("data");
        if (dataArray == null || dataArray.isEmpty()) return processosList;

        JSONArray processosJson = dataArray.getJSONObject(0).optJSONArray("processos");
        if (processosJson == null) return processosList;


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (int i = 0; i < processosJson.length(); i++) {
            JSONObject processoJson = processosJson.getJSONObject(i);
            ProcessoResponse processoResponse = mapProcessoResponse(processoJson);

            JSONArray ultimasMovimentacoesJson = processoJson.optJSONArray("ultimas_movimentacoes");
            if (ultimasMovimentacoesJson != null) {
                List<UltimasMovimentacoesResponse> movimentacoesList = new ArrayList<>();
                for (int j = 0; j < ultimasMovimentacoesJson.length(); j++) {
                    JSONObject movJson = ultimasMovimentacoesJson.getJSONObject(j);
                    UltimasMovimentacoesResponse mov = new UltimasMovimentacoesResponse();

                    String dataTexto = movJson.getString("data");
                    try {
                        mov.setData(LocalDate.parse(dataTexto, formatter));
                    } catch (Exception e) {
                        // fallback para formato ISO (caso venha yyyy-MM-dd)
                        mov.setData(LocalDate.parse(dataTexto));
                    }

                    mov.setMovimento(movJson.getString("movimento"));
                    movimentacoesList.add(mov);
                }
                processoResponse.setUltimasMovimentacoes(movimentacoesList);
            }

            processosList.add(processoResponse);
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
