package tech.ada.apicaixa.service;

import org.springframework.stereotype.Service;
import tech.ada.apicaixa.client.QuinaRestClient;
import tech.ada.apicaixa.model.dto.Resultado;

@Service
public class ResultadoService {

    private final QuinaRestClient quinaRestClient;

    public ResultadoService(QuinaRestClient quinaRestClient) {
        this.quinaRestClient = quinaRestClient;
    }

    public Resultado getUltimoResultado() {
        return quinaRestClient.getUltimoResultado();
    }

    public Resultado getResultadoByNumeroSorteio(Integer numero) {
        return quinaRestClient.getResultadoByNumeroSorteio(numero);
    }
}
