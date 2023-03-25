package tech.ada.minhaquina.api.resultado;

import org.springframework.stereotype.Service;
import tech.ada.minhaquina.client.QuinaRestClient;
import tech.ada.minhaquina.api.resultado.ResultadoResponse;

@Service
public class ResultadoService {

    private final QuinaRestClient quinaRestClient;

    public ResultadoService(QuinaRestClient quinaRestClient) {
        this.quinaRestClient = quinaRestClient;
    }

    public ResultadoResponse getUltimoResultado() {
        return quinaRestClient.getUltimoResultado();
    }

    public ResultadoResponse getResultadoByNumeroSorteio(Integer numero) {
        return quinaRestClient.getResultadoByNumeroSorteio(numero);
    }
}
