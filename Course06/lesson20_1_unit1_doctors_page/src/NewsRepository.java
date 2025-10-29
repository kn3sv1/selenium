import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NewsRepository {
    private List<NewsItem> news;

    public NewsRepository() {
        this.news =  new ArrayList<>();
        this.populate();
    }

    private void populate() {
        this.news.add(
                new NewsItem(
                        1,
                        "S Africa humble England to reach World Cup final after Wolvaardt and Kapp heroics",
                        "description1",
                        "South Africa captain Laura Wolvaardt smashed an epic 169 to lead her side into the final of the Women’s World Cup hammering England by 125 runs in the first semi-final on ...",
                        "/images/news/sports1.png",
                        LocalDateTime.now(),
                        11,
                        "sports"

                )
        );
        this.news.add(
                new NewsItem(
                        2,
                        "Cyprus Forum opens with call for reform and regional dialogue",
                        "description2",
                        "President Nikos Christodoulides focused on legal reform and the fight against corruption in his opening address at the opening of the annual Cyprus Forum on Wednesday night. “The effort for ...",
                        "/images/news/politics1.png",
                        LocalDateTime.now(),
                        13,
                        "politics"
                )
        );
        this.news.add(
                new NewsItem(
                        3,
                        "British royals shrug off speculation about Kate and King Charles",
                        "description3",
                        "A laughing Prince William was back at work on Tuesday, a day after the first footage emerged of his wife Kate since her surgery two months ago which showed the ...",
                        "/images/news/show_biz1.png",
                        LocalDateTime.now(),
                        20,
                        "show-biz"
                )
        );
        this.news.add(
                new NewsItem(
                        4,
                        "Eurovision 2023: Sweden’s Loreen wins for a second time",
                        "description4",
                        "Sweden’s Loreen won Eurovision 2023 with the song “Tattoo” in Liverpool, northern England, on Saturday, becoming the first woman to triumph twice in the contest. Finland’s Käärijä, a ...",
                        "/images/news/show_biz2.png",
                        LocalDateTime.now(),
                        21,
                        "show-biz"
                )
        );
        this.news.add(
                new NewsItem(
                        5,
                        "From Barbie to baddies",
                        "description5",
                        "2023 was a tumultuous year. Our writers look back on what they turned to to find some escape via books, music, films/TV and TikTok ALIX NORMAN READING: 2023 has been murder ...",
                        "/images/news/show_biz3.png",
                        LocalDateTime.now(),
                        10,
                        "show-biz"
                )
        );
    }

    public String findAlltoJsonArray() {
        List<String> json = new ArrayList<>();
        for (NewsItem n : this.news) {
            json.add(n.toJSON());
        }

        // now we just join all strings of Jsons for each news item
        return "[" + String.join(", ", json) + "]";
    }

    public String findById(String id) {
        int num = Integer.parseInt(id);
        NewsItem news = null;
        for (NewsItem n : this.news) {
            if(n.getId() == num) {
                news = n;
                break;
            }
        }
        return news.toJSON();
    }

    public NewsItem findObjectById(String id) {
        int num = Integer.parseInt(id);
        NewsItem news = null;
        for (NewsItem n : this.news) {
            if(n.getId() == num) {
                news = n;
                break;
            }
        }
        return news;
    }
}
