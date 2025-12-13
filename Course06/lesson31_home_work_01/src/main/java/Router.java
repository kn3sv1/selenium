import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.nio.file.Path;

import java.io.IOException;

public class Router implements HttpHandler {
    private final Path root;
    private final DoctorAppointmentController doctorAppointmentController;
    private final StaticFileController staticFileController;

    public Router(String rootDir) {
        this.root = Path.of(rootDir).toAbsolutePath();
        this.staticFileController = new StaticFileController();
        this.doctorAppointmentController = new DoctorAppointmentController();
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String requestedPath = exchange.getRequestURI().getPath();
        String cleanPath = this.removeLastSlash(requestedPath);
        if (cleanPath.isEmpty()) {
            cleanPath = "/";
        }

        if (cleanPath.startsWith("/doctor/show-form")) {
            this.doctorAppointmentController.showForm(exchange);
            return;
        }

        if (cleanPath.startsWith("/doctor/create-appointment")) {
            this.doctorAppointmentController.create(exchange);
            return;
        }

        if (cleanPath.startsWith("/doctor/update-appointment")) {
            this.doctorAppointmentController.update(exchange);
            return;
        }

        if (cleanPath.startsWith("/doctor/delete-appointment")) {
            this.doctorAppointmentController.delete(exchange);
            return;
        }

        this.staticFileController.getFile(exchange, this.root);

    }

    private String removeLastSlash(String url) {
        if(url.endsWith("/")) {
            return url.substring(0, url.lastIndexOf("/"));
        } else {
            return url;
        }
    }
}
