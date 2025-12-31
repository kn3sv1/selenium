import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.nio.file.Path;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Router implements HttpHandler {
    private final Path root;
    private final DoctorAppointmentController doctorAppointmentController;
    private final DoctorController doctorController;
    private final StaticFileController staticFileController;
    private final PatientController patientController;
    private final BannerController bannerController;

    public Router(String rootDir) {
        this.root = Path.of(rootDir).toAbsolutePath();
        // all controllers bellow will have different BannerService and BannerView class in memory.
        // we know that we have bug that other pages will not show correct banners.
        // all time when we add new banner or edited we have to restart all server to see changes on other pages
        // in the future we create dependency container and use ApplicationContext class that provides us single instance of any service/repository/class
        this.staticFileController = new StaticFileController();
        this.doctorAppointmentController = new DoctorAppointmentController();
        this.doctorController = new DoctorController();
        this.patientController = new PatientController();
        this.bannerController = new BannerController();
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            String requestedPath = exchange.getRequestURI().getPath();
            String cleanPath = this.removeLastSlash(requestedPath);
            if (cleanPath.isEmpty()) {
                cleanPath = "/";
            }

            if (cleanPath.startsWith("/doctor/appointment/show-form")) {
                this.doctorAppointmentController.showForm(exchange);
                return;
            }

            if (cleanPath.startsWith("/doctor/create-doctor")) {
                this.doctorController.createAction(exchange);
                return;
            }

//        if (cleanPath.startsWith("/doctor/create-doctor")) {
//            this.doctorController.create(exchange);
//            return;
//        }


            if (cleanPath.startsWith("/doctor/show-doctors")) {
                this.doctorController.listAction(exchange);
                return;
            }

            if (cleanPath.startsWith("/doctor/appointment/create-appointment")) {
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
            Pattern pattern;
            Matcher matcher;

            pattern = Pattern.compile("/doctor/edit/([0-9a-fA-F-]{36})$");
            matcher = pattern.matcher(cleanPath);
            if (matcher.find()) {
                String uuid = matcher.group(1);
                this.doctorController.updateAction(exchange, uuid);
                return;
            }

            pattern = Pattern.compile("/doctor/delete/([0-9a-fA-F-]{36})$");
            matcher = pattern.matcher(cleanPath);
            if (matcher.find()) {
                String uuid = matcher.group(1);
                this.doctorController.deleteAction(exchange, uuid);
                return;
            }

            pattern = Pattern.compile("/customer/edit/([0-9a-fA-F-]{36})$");
            matcher = pattern.matcher(cleanPath);
            if (matcher.find()) {
                String uuid = matcher.group(1);
                this.patientController.updateAction(exchange, uuid);
                return;
            }

            pattern = Pattern.compile("/customer/delete/([0-9a-fA-F-]{36})$");
            matcher = pattern.matcher(cleanPath);
            if (matcher.find()) {
                String uuid = matcher.group(1);
                this.patientController.deleteAction(exchange, uuid);
                return;
            }

            pattern = Pattern.compile("^/doctor/show-appointments/edit/([0-9a-fA-F-]{36})$");
            matcher = pattern.matcher(cleanPath);
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

//        pattern = Pattern.compile("/doctor/edit/([0-9a-fA-F-]{36})$");
//        matcher = pattern.matcher(cleanPath);
//        if (matcher.find()) {
//            String uuid = matcher.group(1);
//            this.patientController.updateAction(exchange, uuid);
//            return;
//        }


            if (cleanPath.startsWith("/doctor/show-appointments")) {
                this.doctorAppointmentController.showAppointments(exchange);
                return;
            }

            // /api - prefix for JSON
            if (cleanPath.startsWith("/api/doctor/show-appointments")) {
                this.doctorAppointmentController.showAppointmentsJSON(exchange);
                return;
            }

            if (cleanPath.startsWith("/customer/show-patients")) {
                this.patientController.showCustomers(exchange);
            }

            if (cleanPath.startsWith("/customer/create-patient")) {
                this.patientController.createAction(exchange);
            }


            //------------------BEGIN BANNER-------------------
            if (cleanPath.startsWith("/banner/list")) {
                this.bannerController.listAction(exchange);
                return;
            }

            if (cleanPath.startsWith("/banner/create")) {
                this.bannerController.createAction(exchange);
                return;
            }

            pattern = Pattern.compile("/banner/edit/([0-9a-fA-F-]{36})$");
            matcher = pattern.matcher(cleanPath);
            if (matcher.find()) {
                String uuid = matcher.group(1);
                this.bannerController.updateAction(exchange, uuid);
                return;
            }

            pattern = Pattern.compile("/banner/delete/([0-9a-fA-F-]{36})$");
            matcher = pattern.matcher(cleanPath);
            if (matcher.find()) {
                String uuid = matcher.group(1);
                this.bannerController.deleteAction(exchange, uuid);
                return;
            }
            //------------------END BANNER-------------------

            pattern = Pattern.compile("/doctor/page/([0-9a-fA-F-]{36})$");
            matcher = pattern.matcher(cleanPath);
            if (matcher.find()) {
                String uuid = matcher.group(1);
                this.doctorController.PageAction(exchange, uuid);
                return;
            }

            this.staticFileController.getFile(exchange, this.root);
        } catch (Exception e) {
            // Log error in console
            System.err.println("SERVER ERROR: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private String removeLastSlash(String url) {
        if(url.endsWith("/")) {
            return url.substring(0, url.lastIndexOf("/"));
        } else {
            return url;
        }
    }
}
