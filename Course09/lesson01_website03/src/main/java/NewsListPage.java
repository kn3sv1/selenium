public class NewsListPage {
    private String title;
    private String menu;

    public NewsListPage(String title, String menu) {
        this.title = title;
        this.menu = menu;
    }

    public String toHtml() {

        return """
                <html>
                    <head>
                        <title>%s</title>
                        <link rel="stylesheet" href="basic.css">
                    </head>
                    <body>
                        %s
                        <h1>%s</h1>
                        <p>List of news will be here...</p>
                        <div class="news-list">
                            <div class="news-item">
                                <h2>News title 1</h2>
                                <p>News description 1. Refactor me to repository of news like menu</p>
                            </div>
                            <div class="news-item">
                                <h2>News title 2</h2>
                                <p>News description 2</p>
                            </div>
                            <div class="news-item">
                                <h2>News title 3</h2>
                                <p>News description 3</p>
                            </div>
                        </div>
                    </body>
                </html>
                """.formatted(this.title, this.menu, this.title);
    }
}
