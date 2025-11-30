import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class NewsController extends AbstractController {
    public void list(HttpExchange exchange) throws IOException {
        TemplateService templateService = new TemplateService();
        Path file = Path.of("templates/news/news.html");
        HashMap<String, String> map = new HashMap<>();
        //map.put("%NEWS_LIST%", "NEWS1|NEWS2|NEWS3|NEWS4");
        // generate html for each item separately
        ArrayList<String> htmlAll = new ArrayList<>();
        for (TemplateItem item : this.getNewsItems()) {
            String html = templateService.renderTemplate(item.getPath(), item.getMap());
            htmlAll.add(html);
        }
        map.put("%NEWS_LIST%", String.join("", htmlAll));
        // generate general page
        String response = templateService.renderTemplate(file, map);

        this.sendHTMLResponse(exchange, response);
    }

    /**
     * What data Structure should be?
     */
    private ArrayList<TemplateItem> getNewsItems() {
        ArrayList<TemplateItem> news = new ArrayList<>();

        Path file1 = Path.of("templates/news/news-item.html");
        HashMap<String, String> news1 = new HashMap<>();
        news1.put("%TITLE%", "title1");
        news1.put("%DESCRIPTION%", "description1");
        news1.put("%DATE%", "date1");
        news1.put("%IMAGE%", "/images/news/politics1.png");
        news.add(new TemplateItem(file1, news1));

        Path file2 = Path.of("templates/news/news-item-hot.html");
        HashMap<String, String> news2 = new HashMap<>();
        news2.put("%TITLE%", "title2");
        news2.put("%DESCRIPTION%", "description2");
        news2.put("%DATE%", "date2");
        news2.put("%IMAGE%", "/images/news/politics1.png");
        news.add(new TemplateItem(file2, news2));

        Path file3 = Path.of("templates/news/news-item.html");
        HashMap<String, String> news3 = new HashMap<>();
        news3.put("%TITLE%", "title3");
        news3.put("%DESCRIPTION%", "description3");
        news3.put("%DATE%", "date3");
        news3.put("%IMAGE%", "/images/news/politics1.png");
        news.add(new TemplateItem(file3, news3));

        return news;
    }
}
