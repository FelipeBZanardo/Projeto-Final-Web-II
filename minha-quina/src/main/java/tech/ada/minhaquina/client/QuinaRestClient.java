package tech.ada.minhaquina.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import tech.ada.minhaquina.api.resultado.ResultadoResponse;

@FeignClient(value = "quinaRestClient", url = "https://servicebus2.caixa.gov.br/portaldeloterias/api/quina/")
public interface QuinaRestClient {

    @GetMapping
    ResultadoResponse getUltimoResultado();

    @GetMapping("/{numero}")
    ResultadoResponse getResultadoByNumeroSorteio(@PathVariable Integer numero);
}