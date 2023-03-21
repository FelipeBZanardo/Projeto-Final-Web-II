package tech.ada.apicaixa.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import tech.ada.apicaixa.model.dto.Resultado;

@FeignClient(value = "quinaRestClient", url = "https://servicebus2.caixa.gov.br/portaldeloterias/api/quina/")
public interface QuinaRestClient {

    @GetMapping
    Resultado getUltimoResultado();

    @GetMapping("/{numero}")
    Resultado getResultadoByNumeroSorteio(@PathVariable Integer numero);
}