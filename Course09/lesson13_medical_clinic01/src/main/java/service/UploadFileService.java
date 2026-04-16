package service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class UploadFileService {
    private String uploadDir =  "public\\upload";


    public String getURL(String path) {
        if (path == null) {
            return null;
        }

        String url = path.replace("\\", "/")
                .replaceAll("^public/upload/", "");

        return  "/upload/" + url;
    }

    public String getPath(String url) {
        if (url == null) {
            return null;
        }

        String path = url.replaceAll("^/upload/", "")
                .replace("/", "\\");

        return  this.uploadDir + "\\" + path;
    }

    public void deleteFile(String url) throws IOException {
        String path = this.getPath(url);
        if (path == null) {
            return;
        }

        java.io.File file = new java.io.File(path);
        if (file.exists()) {
            file.delete();
        }
    }
}
