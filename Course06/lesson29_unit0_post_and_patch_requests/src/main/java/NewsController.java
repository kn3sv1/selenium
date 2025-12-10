import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class NewsController extends AbstractController {
    public void list(HttpExchange exchange) throws IOException {
        String banner = this.getBanner(exchange);
        String menu = this.getMenu(exchange);
        String footer = this.getFooter(exchange);

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
        map.put("%BANNER%", banner);
        map.put("%MENU-ITEMS%", menu);
        map.put("%NEWS_LIST%", String.join("", htmlAll));
        map.put("%FOOTER%", footer);
        // generate general page
        String response = templateService.renderTemplate(file, map);

        this.sendHTMLResponse(exchange, response);
    }

    /**
     * What data Structure should be?
     */
    private ArrayList<TemplateItem> getNewsItems() {
        // We should always use Repository class and newsItem as model class for this
        // task because on news page we show all news, on main page we show 3 most popular news
        // and on doctor's page we show most popular and related to doctors so this means we can reuse
        // these classes in different files/controllers

        // separate classes should exist for example "NewsTemplate" that accepts array of models news
        // and returns bellow this type:"ArrayList<TemplateItem>" because we can reuse this class in many
        // places and provide different file templates: for mobile or different design
        // the more classes we have means the more flexibility we can have

        // code bellow we cannot take just part of news - not flexible. We cannot reuse tis code in another
        // controller because It's hardcoded all here - we need to move to separate classes:
        // news to repository and models, mapping of models and view to another class  "NewsTemplate".
        // The more classes the better we can reuse different combination in different places to satisfy different
        // business requirements
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
        news2.put("%TITLE%", "Barbie");
        news2.put("%DESCRIPTION%", "Movie - Comedy");
        news2.put("%DATE%", "Released 2023");
        news2.put("%IMAGE%", "/images/news/show_biz3.png");
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
