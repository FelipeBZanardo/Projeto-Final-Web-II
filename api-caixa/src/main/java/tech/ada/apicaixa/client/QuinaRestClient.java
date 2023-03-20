package tech.ada.apicaixa.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import tech.ada.apicaixa.model.ResultQuina;

@FeignClient(value = "quinaRestClient", url = "https://servicebus2.caixa.gov.br/portaldeloterias/api/quina/")
public interface QuinaRestClient {

    @GetMapping
    ResultQuina getLatestResult();
}