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
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

////mvnrepository.com/artifact/org.json/json

public class ProcessoGrau1API{
    public static void main(String args[]) throws IOException {
        HttpClient httpclient = HttpClients.createDefault();
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

        try (CloseableHttpResponse httpResponse = (CloseableHttpResponse) httpclient.execute(httppost)) {
            String body = EntityUtils.toString(httpResponse.getEntity());
            JSONObject response = new JSONObject(body);

            if (response.getInt("code") == 200) {
                System.out.println("Retorno com sucesso: \n" + response.get("data"));
                // Pegando o primeiro objeto do array

                JSONArray dataArray = response.getJSONArray("data");
                JSONObject firstDataObject = dataArray.getJSONObject(0); // Pega o primeiro objeto dentro de "data"
                JSONArray processosObject = firstDataObject.getJSONArray("processos"); // Obtém o valor de "processos"
                System.out.println(processosObject);

                for (int i = 0; i < processosObject.length(); i++) {
                    JSONObject processo = processosObject.getJSONObject(i);

                    // Extraindo os dados filtrados
                    String numeroProcesso = processo.getString("processo");
                    String classe = processo.getString("classe");
                    String juiz = processo.getString("juiz");
                    String vara = processo.getString("vara");
                    String requerido = processo.optString("reqdo", "Não informado");
                    String requerente = processo.optString("reqte", "Não informado");
                    JSONArray ultimasMovimentacoes = processo.getJSONArray("ultimas_movimentacoes");


                    System.out.println("Processo: " + numeroProcesso);
                    System.out.println("Classe: " + classe);
                    System.out.println("Juiz: " + juiz);
                    System.out.println("Vara: " + vara);
                    System.out.println("Requerido: " + requerido);
                    System.out.println("Requerente: " + requerente);
                    System.out.println("Ultimas Movimentações:");
                    for (int j = 0; j < ultimasMovimentacoes.length(); j++) {
                        JSONObject ultimasMovimentacoesObject = ultimasMovimentacoes.getJSONObject(j);
                        String dataMovimentacao = ultimasMovimentacoesObject.getString("data");
                        String movimento = ultimasMovimentacoesObject.getString("movimento");
                        System.out.println("Data: " + dataMovimentacao);
                        System.out.println("Movimento:" + movimento);
                    }

                    System.out.println("------------------------");
                }
            } else if (response.getInt("code") >= 600 && response.getInt("code") <= 799) {
                String mensagem = "Um erro aconteceu. Leia para saber mais:\n";
                mensagem += "Código: " + response.get("code") + " (" + response.get("code_message") + ")\n";
                if (!response.isNull("errors")) {
                    mensagem += response.getJSONArray("errors").join("; ");
                }
                System.out.println(mensagem);
            }
//System.out.println("Cabeçalho da consulta:\n" + response.get("header"));
//System.out.println("URLs com arquivos de visualização (HTML/PDF):\n" +
//response.getJSONArray("site_receipts").join("; "));
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }
}