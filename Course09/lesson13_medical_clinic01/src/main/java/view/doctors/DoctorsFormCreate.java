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
                
                    <!--The image will be saved in the folder you specify with the name you specify. If the folder does not exist, it will be created. The file extension will be .jpg-->
                    <p>Choose a photo:</p>
                
                    <label for="folder">Folder on server. Use / for sub folder</label>
                    <input type="text" id="folder" name="folder" required>
                
                    <label for="filename">File name without extension</label>
                    <input id="filename" name="filename" required>
                
                    <label for="file">File</label>
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
