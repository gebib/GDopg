package oppgave1;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

public class Main {

    private final static String API_KEY = "privateAPIkey!";
    private static String fromCurrency = "";
    private static String toCurrency = "";
    private static int ammount = 0;


    private static String readConsoleValuesAndPrepareUrl() {
        String requestUrl = "";
        String currencyDate = "latest";
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("From currency:>");
            fromCurrency = scanner.next().toUpperCase();

            System.out.print("To currency:>");
            toCurrency = scanner.next().toUpperCase();

            System.out.print("Ammount in " + fromCurrency + ":>");
            ammount = scanner.nextInt();

            System.out.print("Add date? y/n :>");
            String option = scanner.next();
            if (option.equals("y")) {
                System.out.print("Optional currency date: yyyy-mm-dd:>");
                currencyDate = scanner.next();
            }
        } catch (InputMismatchException imme) {
            System.out.println("Input mis match, please try again with right data!");
        }

        requestUrl = "http://data.fixer.io/api/" +
                currencyDate +
                "?access_key=" + API_KEY +
                "&base=EUR" +
                "&symbols=" + fromCurrency + "," + toCurrency;

        return requestUrl;
    }


    private static JSONObject doRequest(String apiUrl) {
        StringBuffer sb = new StringBuffer();
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP Error code : "
                        + conn.getResponseCode());
            }
            InputStreamReader in = new InputStreamReader(conn.getInputStream());
            BufferedReader br = new BufferedReader(in);
            String output;
            while ((output = br.readLine()) != null) {
                sb.append(output);
            }
            conn.disconnect();

        } catch (Exception e) {
            System.out.println("Net client exception!" + e);
        }

        JSONObject responseObject = new JSONObject(
                sb.toString()
        );
        return responseObject;
    }


    public static void main(String[] args) {

        JSONObject responsObject = doRequest(readConsoleValuesAndPrepareUrl());

        double fromCurrencyRate = responsObject.getJSONObject("rates").getDouble(fromCurrency);
        double toCurrencyRate = responsObject.getJSONObject("rates").getDouble(toCurrency);
        double convertionRate = toCurrencyRate / fromCurrencyRate;

        System.out.println(ammount + " " + fromCurrency + " is " + ammount * convertionRate + " " + toCurrency);
    }
}
