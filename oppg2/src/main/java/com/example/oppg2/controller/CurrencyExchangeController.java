package com.example.oppg2.controller;

import oppgave1.ConsoleApp;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import static oppgave1.ConsoleApp.API_KEY;

@RestController
public class CurrencyExchangeController {

    @GetMapping("/ce/v1/{from}/{to}/{ammount}/{ceDate}")
    public String getCurrencyExchange(@PathVariable String from,
                                      @PathVariable String to,
                                      @PathVariable int ammount,
                                      @PathVariable String ceDate) {

        String requestUrl = "http://data.fixer.io/api/" +
                ceDate +
                "?access_key=" + API_KEY +
                "&base=EUR" +
                "&symbols=" + from.toUpperCase() + "," + to.toUpperCase();

        JSONObject resultFromFixerApi = ConsoleApp.doRequest(requestUrl);

        return resultFromFixerApi.toString();
    }
}
