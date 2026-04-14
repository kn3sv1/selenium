package view.doctors;

import model.DoctorModel;
import repository.DoctorRepository;

import java.util.*;

public class DoctorsPage {
    private DoctorRepository repository;
    private String title;
    private ArrayList<DoctorModel> doctorModels;

    public DoctorsPage(DoctorRepository repository, String title, ArrayList<DoctorModel> doctorModels) {
        this.repository = repository;
        this.title = title;
        this.doctorModels = doctorModels;
    }

    public String doctorsListToHtml() {
        StringBuilder html = new StringBuilder("<div class=\"doctor-list\"><ul>");
        for (DoctorModel doctor : doctorModels) {
            html.append("""
                    <li>
                        <h2>Name: %s %s</h2>
                        <h3>Specialization: %s </h3>
                        <a href="%s"><img src="%s" alt="%s"></a>
                    </li>
                    """.formatted(doctor.getFirstName(), doctor.getLastName(), doctor.getProfession(), doctor.getPhoto(), doctor.getPhoto(), doctor.getFirstName()));
        }
        html.append("</ul></div>");

        return html.toString();
    }

    public String toHtml() {

        return """
                <html>
                    <head>
                        <title>%s</title>
                        <link rel="stylesheet" href="basic.css">
                    </head>
                    <body>
                        <h1>%s</h1>
                        <div>%s</div>
                        <div>%s</div>
                    </body>
                </html>
                """.formatted(this.title, this.title, this.getDynamicOrderedCategories(), this.doctorsListToHtml());
    }

    public String getCategories() {
        return """
                <a href="/doctors">All doctors</a>&nbsp;
                <a href="/doctors?profession=General">General</a>&nbsp;
                <a href="/doctors?profession=Neurosurgery">Neurosurgery</a>&nbsp;
                <a href="/doctors?profession=Pediatric">Pediatric</a>&nbsp;
                <a href="/doctors?profession=Cardiothoracic">Cardiothoracic</a>&nbsp;
                """;
    }

    /**
     * not ordered, and it is problem because we have different professions, and we can have duplicates.
     * It is not good solution but it is simple. We can use HashSet to remove duplicates, but it will be not ordered.
     * We can use LinkedHashSet to remove duplicates and keep order.
     */
    public String getDynamicCategories() {
        // we use hashMap to replace duplicates by key (profession).
        Map<String, String> menu = new HashMap<>();
        menu.put("<a href=\"/doctors\">All doctors</a>&nbsp;", "All doctors");
        for (DoctorModel doctor : this.repository.getDoctors()) {
            String link = "/doctors?profession=%s".formatted(doctor.getProfession());
            menu.put(
               "<a href=\"%s\">%s</a>&nbsp;".formatted(link, doctor.getProfession()), doctor.getProfession()
            );
        }
        StringBuilder html = new StringBuilder();
        // we have everything in key - full link we don't need any value. But you can make if you want save in key only link
        // and then use value as well. It will be even more correct.
        for (String link : menu.keySet()) {
            html.append(link);
        }

        return html.toString();
    }

    /**
     * because we don't use value from HashMap we can use ArrayList because it has ordered items.
     * But we need unique so we can use LinkedHashSet. It is ordered and unique.
     */
    public String getDynamicOrderedCategories() {
        // we use hashMap to replace duplicates by key (profession).
        Set<String> menu = new LinkedHashSet<>();
        menu.add("<a href=\"/doctors\">All doctors</a>&nbsp;");
        for (DoctorModel doctor : this.repository.getDoctors()) {
            String link = "/doctors?profession=%s".formatted(doctor.getProfession());
            menu.add(
                    "<a href=\"%s\">%s</a>&nbsp;".formatted(link, doctor.getProfession())
            );
        }
        StringBuilder html = new StringBuilder();
        // we have everything in key - full link we don't need any value. But you can make if you want save in key only link
        // and then use value as well. It will be even more correct.
        for (String link : menu) {
            html.append(link);
        }

        return html.toString();
    }
}
