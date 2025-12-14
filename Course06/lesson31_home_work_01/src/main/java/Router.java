import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.nio.file.Path;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

//        if (cleanPath.matches("^/doctor/show-appointments/edit/[0-9a-fA-F-]{36}$")) {
//            // lets extract UUID
//            Pattern pattern = Pattern.compile("^/doctor/show-appointments/edit/([0-9a-fA-F-]{36})$");
//            Matcher matcher = pattern.matcher(cleanPath);
//
//            if (matcher.find()) {
//                String uuid = matcher.group(1);
//                System.out.println(uuid);
//            }
//
//            this.doctorAppointmentController.update(exchange);
//            return;
//        }
//        if (cleanPath.matches("^/doctor/show-appointments/delete/[0-9a-fA-F-]{36}$")) {
//            this.doctorAppointmentController.delete(exchange);
//            return;
//        }

        // this version less code and less duplication
        // lets extract UUID.
        Pattern pattern = Pattern.compile("^/doctor/show-appointments/edit/([0-9a-fA-F-]{36})$");
        Matcher matcher = pattern.matcher(cleanPath);
        if (matcher.find()) {
            String uuid = matcher.group(1);
            System.out.println(uuid);
            this.doctorAppointmentController.update(exchange, uuid);
            return;
        }

        pattern = Pattern.compile("^/doctor/show-appointments/delete/([0-9a-fA-F-]{36})$");
        matcher = pattern.matcher(cleanPath);
        if (matcher.find()) {
            String uuid = matcher.group(1);
            System.out.println(uuid);
            this.doctorAppointmentController.delete(exchange, uuid);
            return;
        }

        if (cleanPath.startsWith("/doctor/show-appointments")) {
            this.doctorAppointmentController.showAppointments(exchange);
            return;
        }

        // /api - prefix for JSON
        if (cleanPath.startsWith("/api/doctor/show-appointments")) {
            this.doctorAppointmentController.showAppointmentsJSON(exchange);
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
