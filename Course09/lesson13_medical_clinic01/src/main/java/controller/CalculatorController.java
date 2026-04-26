package controller;

import com.sun.net.httpserver.HttpExchange;
import service.CalculatorService;
import utils.HttpResponse;
import utils.QueryParser;
import validator.calculator.CalculatorValidator;

import java.io.IOException;
import java.util.Map;

public class CalculatorController {
    private CalculatorService service;
    private CalculatorValidator validator = new CalculatorValidator();

    public CalculatorController() {
        this.service = new CalculatorService();
    }

    public void calculate(HttpExchange exchange, HttpResponse response) throws IOException {
        QueryParser queryParser = new QueryParser();
        Map<String, String> query = queryParser.parse(exchange);
        // here should be validation for query parameters.
        // http://localhost:8080/calculator?a=30&b=5&operation=-
        Map<String, String> errors = validator.validate(query);
        if (!errors.isEmpty()) {
            response.sendJSONMap(exchange, 400, Map.of(
                    "message", "validation errors",
                    "errors", errors
            ));
            return;
        }

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
        } else if (query.get("operation").equals("*")) {
            int result = service.multiply(num1, num2);
            response.sendJSONMap(exchange, 200, Map.of(
                    "a", num1,
                    "b", num2,
                    "result", result
            ));
            return;
        } else if (query.get("operation").equals("/")) {
            int result = service.divide(num1, num2);
            response.sendJSONMap(exchange, 200, Map.of(
                    "a", num1,
                    "b", num2,
                    "result", result
            ));
            return;
        }
        // response.sendHtmlResponse(exchange, 404, "<h1>operation not found</h1>");

        // should be JSON error. will never execute because validation will catch it before.
        // it will catch if we miss in controller: +, -, *, / in validation,
        // but if we miss in validation and put here, it will catch here.
        response.sendJSONMap(exchange, 400, Map.of(
                "error", "operation doesn't exist."
        ));
    }
}
