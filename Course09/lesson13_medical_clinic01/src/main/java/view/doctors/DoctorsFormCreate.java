package view.doctors;

public class DoctorsFormCreate {
   private String title;

    public DoctorsFormCreate(String title) {
        this.title = title;
    }

    private String formToHtml() {
        return """
                <form action="/doctor-create" method="POST" class="contact-form" enctype="multipart/form-data">
                
                    <label for="name">First name:</label><br>
                    <input type="text" id="name" name="name"><br><br>
                
                    <label for="surname">Last name:</label><br>
                    <input type="text" id="surname" name="surname"><br><br>
                
                    <label for="spe">Specialization:</label><br>
                    <input type="text" id="spe" name="spe"><br><br>
                
                    <label for="file">Choose a photo:</label>
                    <input type="file" id="file" name="file" accept="image/*" required>
                
                    <button type="submit">Create doctor</button>
                
                </form>
                """;
    }

    public String bodyToHtml() {
        return """
                <html>
                    <head>
                        <title>%s</title>
                        <link rel="stylesheet" href="/basic.css">
                        <link rel="stylesheet" href="/forms.css">
                    </head>
                    <body>
                        <h1 style="margin-bottom: 40px;">%s</h1>
                            %s
                    </body>
                </html>
                """.formatted(this.title, this.title, this.formToHtml());

        }
}
