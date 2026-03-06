import java.util.List;

public class NewsListApi extends PageApi {

    private final List<NewsItemModel> news;

    public NewsListApi() {
        this.news = List.of(
                new NewsItemModel("technology","Breaking News: Java 17 Released", "Java 17 has been officially released with new features and improvements.", "/images/news/java_17.png", "2024-06-01"),
                new NewsItemModel("technology","Tech Conference 2024 Announced", "The annual Tech Conference 2024 will take place in September, featuring industry leaders and innovators.", "/images/news/tech_2024.png", "2024-06-05"),
                new NewsItemModel("technology","AI Advancements in 2024", "Recent advancements in artificial intelligence are transforming various industries and applications.", "/images/news/ai_2024.png", "2024-06-10"),
                new NewsItemModel("headlines","Top 10 Strategic Technology Trends for 2026", "The 10 technology trends shaping the next five years", "/images/news/news-headlines/tech_trends_2026.png", "2026-02-01"),
                new NewsItemModel("headlines","Cyprus unveils Eurovision 2026 entry “JALLA” by Antigoni", "Cyprus has unveiled its Eurovision 2026 entry “JALLA” by Antigoni, with the music video featuring UK Cypriot stars including Stavros Flatley, Tonia Buxton, Sophia Hadjipanteli and comedian Cypriot Smurf.", "/images/news/news-headlines/cyprus_eurovision_2026.png", "2026-02-09"),
                new NewsItemModel("headlines","India Face Netherlands As T20 World Cup Group Stage Climaxes", "India aim to maintain their unbeaten run while Abhishek Sharma seeks form and the Netherlands look for a strong finish in Ahmedabad’s high-stakes T20 World Cup clash.", "/images/news/news-headlines/world_cup_2026.png", "2026-02-19")
        );
    }

    public List<NewsItemModel> getNews() {
        return news;
    }

    public List<NewsItemModel> getNewsByCategory(String category) {
        return this.news.stream().filter((NewsItemModel itemModel) -> category.equalsIgnoreCase(itemModel.getCategory())).toList();
    }

    @Override
    public String toJson() {
        StringBuilder json = new StringBuilder("[");
        for (int i = 0; i < this.news.size(); i++) {
            json.append(this.news.get(i).toJson());
            if (i < this.news.size() - 1) {
                json.append(",");
            }
        }
        json.append("]");
        return json.toString();
    }
}
