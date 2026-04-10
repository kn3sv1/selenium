package view.doctors;

import model.DoctorModel;

import java.util.ArrayList;
import java.util.List;

public class DoctorsPage {
    private String title;
    private ArrayList<DoctorModel> doctorModels;

    public DoctorsPage(String title, ArrayList<DoctorModel> doctorModels) {
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
                        %s
                    </body>
                </html>
                """.formatted(this.title, this.title, this.doctorsListToHtml());
    }
}
