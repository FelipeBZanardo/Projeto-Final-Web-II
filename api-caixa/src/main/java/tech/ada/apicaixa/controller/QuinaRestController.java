package tech.ada.apicaixa.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.ada.apicaixa.client.QuinaRestClient;
import tech.ada.apicaixa.model.ResultQuina;

@RestController
@RequestMapping("/quina")
public class QuinaRestController {

    private final QuinaRestClient quinaRestClient;

    public QuinaRestController(QuinaRestClient quinaRestClient) {
        this.quinaRestClient = quinaRestClient;
    }

    @GetMapping("/latestResult")
    public ResultQuina getLatestResult(){
        return quinaRestClient.getLatestResult();
    }
}
