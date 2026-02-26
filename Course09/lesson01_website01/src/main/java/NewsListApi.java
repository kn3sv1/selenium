import java.util.List;

public class NewsListApi extends PageApi {

    @Override
    public String toJson() {
        List<NewsItemModel> news = List.of(
                new NewsItemModel("Breaking News: Java 17 Released", "Java 17 has been officially released with new features and improvements.", "2024-06-01"),
                new NewsItemModel("Tech Conference 2024 Announced", "The annual Tech Conference 2024 will take place in September, featuring industry leaders and innovators.", "2024-06-05"),
                new NewsItemModel("AI Advancements in 2024", "Recent advancements in artificial intelligence are transforming various industries and applications.", "2024-06-10")
        );
        StringBuilder json = new StringBuilder("[");
        for (int i = 0; i < news.size(); i++) {
            json.append(news.get(i).toJson());
            if (i < news.size() - 1) {
                json.append(",");
            }
        }
        json.append("]");
        return json.toString();
    }
}
