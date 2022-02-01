package com.example.oppg2.controller;

import oppgave1.ConsoleApp;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import static oppgave1.ConsoleApp.API_KEY;

@RestController
@CrossOrigin(origins = {"*"})
public class CurrencyExchangeController {

    @GetMapping("/ce/v1")
    public String getCurrencyExchange(@RequestParam String from,
                                      @RequestParam String to,
                                      @RequestParam int ammount,
                                      @RequestParam(required = false) String ceDate) {

        if (ceDate == null) {
            ceDate = "latest";
        }

        String requestUrl = "http://data.fixer.io/api/" +
                ceDate +
                "?access_key=" + API_KEY +
                "&base=EUR" +
                "&symbols=" + from.toUpperCase() + "," + to.toUpperCase();

        JSONObject resultFromFixerApi = ConsoleApp.doRequest(requestUrl);

        return resultFromFixerApi.toString();
    }
}
