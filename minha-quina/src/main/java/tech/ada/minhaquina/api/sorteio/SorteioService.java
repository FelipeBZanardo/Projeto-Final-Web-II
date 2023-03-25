package tech.ada.minhaquina.api.sorteio;

import org.springframework.stereotype.Service;
import tech.ada.minhaquina.client.QuinaRestClient;
import tech.ada.minhaquina.client.SorteioDTO;

@Service
public class SorteioService {

    private final QuinaRestClient quinaRestClient;

    public SorteioService(QuinaRestClient quinaRestClient) {
        this.quinaRestClient = quinaRestClient;
    }

    public SorteioDTO getUltimoResultado() {
        return quinaRestClient.getUltimoSorteio();
    }

    public SorteioDTO getResultadoByNumeroSorteio(Integer numero) {
        return quinaRestClient.getSorteioByNumeroSorteio(numero);
    }
}
