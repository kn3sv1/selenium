public class NewsItemHtml {
    private NewsItem item;

    public NewsItemHtml(NewsItem item) {
        this.item = item;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("<!DOCTYPE html>");
        sb.append("<html lang='en'>");
        sb.append("<head>");
        sb.append("<meta charset='UTF-8'>");
        sb.append("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        sb.append("<title>").append(item.getTitle()).append("</title>");
        sb.append("<style>");
        sb.append("body { font-family: Arial, sans-serif; max-width: 800px; margin: 2rem auto; }");
        sb.append(".news-img { width: 100%; border-radius: 10px; }");
        sb.append(".likes { color: #666; font-size: 0.9rem; }");
        sb.append("</style>");
        sb.append("</head>");
        sb.append("<body>");

        sb.append("<h1>").append(item.getTitle()).append("</h1>");
        sb.append("<p><b>Description:</b> ").append(item.getDescription()).append("</p>");
        sb.append("<img class='news-img' src='").append(item.getImage()).append("' alt='news image'>");
        sb.append("<p>").append(item.getText()).append("</p>");
        sb.append("<p class='likes'>Likes: ").append(item.getLikes()).append("</p>");
        sb.append("<p><b>Category:</b> ").append(item.getCategory()).append("</p>");
        sb.append("<a href='/'>← Back to all news</a>");


        sb.append("</body>");
        sb.append("</html>");

        return sb.toString();
    }
}
