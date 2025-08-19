import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class HttpResponse {
    private final HttpExchange exchange;
    // we minimize amount of arguments to method if we move them to constructor
    public HttpResponse(HttpExchange exchange) {
        this.exchange = exchange;
    }

    public HttpExchange getExchange() {
        return exchange;
    }

    /**
     * This method we use for file
     */
    public void sendFileResponse(String mime, byte[] fileBytes) throws IOException {
        this.exchange.getResponseHeaders().add("Content-Type", mime + "; charset=UTF-8");
        this.exchange.sendResponseHeaders(200, fileBytes.length);
        try (OutputStream os = this.exchange.getResponseBody()) {
            os.write(fileBytes);
        }
    }

    public void sendHtmlResponse(int code, String msg) {
        this.sendMimeResponse(code, msg, "text/html");
    }

    public void sendJsonResponse(int code, String msg) {
        this.sendMimeResponse(code, msg, "application/json");
    }

    /**
     * This method we use for text
     */
    public void sendMimeResponse(int code, String msg, String mime) {
        byte[] bytes = msg.getBytes(StandardCharsets.UTF_8);
        this.exchange.getResponseHeaders().add("Content-Type", mime + "; charset=UTF-8");
        OutputStream os = null;

        try {
            this.exchange.sendResponseHeaders(code, bytes.length);

            os = this.exchange.getResponseBody();   // get the response stream
            os.write(bytes);                   // send the response
            // like toilet flushes
            os.flush();                        // make sure everything is written
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (os != null) {
                try {
                    // you close toilet lid
                    os.close();                // always close when done
                } catch (IOException e) {
                    e.printStackTrace();       // simple error handling
                }
            }
        }
    }
}
