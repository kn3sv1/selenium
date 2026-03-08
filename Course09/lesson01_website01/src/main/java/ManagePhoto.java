public class ManagePhoto extends Page{
    @Override
    public String getBody() {
        return """
                <form action="/manage-photo" method="POST" class="contact-form" enctype="multipart/form-data">
                
                    <h2>Photo manager form</h2>
                
                    <label for="folder">Folder on server. Use / for sub folder</label>
                    <input type="text" id="folder" name="folder" required>

                    <label for="filename">File name without extension</label>
                    <input id="filename" name="filename" required>

                    <label for="file">File</label>
                    <input type="file" id="file" name="file" accept="image/*" required>

                    <label for="message">Message in Chinese and emoji</label>
                    <input id="message" name="message" required>

                    <button type="submit">Upload file</button>
                
                </form>
                """;
    }

    @Override
    public String getTitle() {
        return "";
    }

    @Override
    public String getHeader() {
        return "";
    }

    @Override
    public String getFooter() {
        return "";
    }
}
