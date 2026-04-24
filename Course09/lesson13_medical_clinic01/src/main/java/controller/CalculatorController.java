package controller;

import com.sun.net.httpserver.HttpExchange;
import service.CalculatorService;
import utils.HttpResponse;
import utils.QueryParser;

import java.io.IOException;
import java.util.Map;

public class CalculatorController {
    private CalculatorService service;

    public CalculatorController() {
        this.service = new CalculatorService();
    }

    public void calculate(HttpExchange exchange, HttpResponse response) throws IOException {
        QueryParser queryParser = new QueryParser();
        Map<String, String> query = queryParser.parse(exchange);

        int num1 = Integer.parseInt(query.get("a"));
        int num2 = Integer.parseInt(query.get("b"));

        if (query.get("operation").equals("+")) {
            int result = service.add(num1, num2);
            response.<String, Integer>sendJSONMap(exchange, 200, Map.of(
                    "a", num1,
                    "b", num2,
                    "result", result
            ));
            //response.sendHtmlResponse(exchange, 200, "<h1>" + num1 + "+" + num2 + "=" + result + "</h1>");
            return;
        } else if (query.get("operation").equals("-")) {
            int result = service.subtract(num1, num2);
            response.sendJSONMap(exchange, 200, Map.of(
                    "a", num1,
                    "b", num2,
                    "result", result
            ));
            //response.sendHtmlResponse(exchange, 200, "<h1>" + num1 + "-" + num2 + "=" + result + "</h1>");
            return;
        }
        // response.sendHtmlResponse(exchange, 404, "<h1>operation not found</h1>");

        // should be JSON error
        response.sendJSONMap(exchange, 400, Map.of(
                "error", "operation doesn't exist."
        ));
    }
}
