import java.util.List;

public class NewsRepository {
    private final List<NewsItem> news;

    public NewsRepository() {
        this.news = List.of(
            new NewsItem(1,"technology", "Breaking News: Java 17 Released", "Java 17 has been officially released with new features and improvements.", "/images/news/java_17.png", "2024-06-01", "/news/item/1"),
            new NewsItem(2,"technology", "AI Advancements in 2024", "Recent advancements in artificial intelligence are transforming various industries and applications.", "/images/news/ai_2024.png", "2024-06-10", "/news/item/2"),
            new NewsItem(3,"technology", "Top 10 Strategic Technology Trends for 2026", "The 10 technology trends shaping the next five years", "/images/news/news-headlines/tech_trends_2026.png", "2026-02-01", "/news/item/3"),
            new NewsItem(4,"headlines","Top 10 Strategic Technology Trends for 2026", "The 10 technology trends shaping the next five years", "/images/news/news-headlines/tech_trends_2026.png", "2026-02-01", "/news/item/4"),
            new NewsItem(5,"headlines","Cyprus unveils Eurovision 2026 entry “JALLA” by Antigoni", "Cyprus has unveiled its Eurovision 2026 entry “JALLA” by Antigoni, with the music video featuring UK Cypriot stars including Stavros Flatley, Tonia Buxton, Sophia Hadjipanteli and comedian Cypriot Smurf.", "/images/news/news-headlines/cyprus_eurovision_2026.png", "2026-02-09", "/news/item/5"),
            new NewsItem(6,"headlines","India Face Netherlands As T20 World Cup Group Stage Climaxes", "India aim to maintain their unbeaten run while Abhishek Sharma seeks form and the Netherlands look for a strong finish in Ahmedabad’s high-stakes T20 World Cup clash.", "/images/news/news-headlines/world_cup_2026.png", "2026-02-19", "/news/item/6")
        );
    }

    public List<NewsItem> getNews () {
        return news;
    }

    public List<NewsItem> getNewsByCategory(String category) {
        return news.stream()
                   .filter(newsItem -> newsItem.getCategory().equalsIgnoreCase(category))
                   .toList();
    }

    public NewsItem findObjectById(int id) {
        NewsItem news = null;
        for (NewsItem n : this.news) {
            if(n.getId() == id) {
                news = n;
                break;
            }
        }
        return news;
    }
}
