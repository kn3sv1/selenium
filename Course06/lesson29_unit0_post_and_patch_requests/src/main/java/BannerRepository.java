import java.util.ArrayList;

public class BannerRepository {

    private ArrayList banners;


    public BannerRepository() {
        this.banners = new ArrayList<>();
        this.populate();
    }

    public ArrayList getBanners() {
        return banners;
    }

    private void populate() {
        this.banners.add(new Banner("/reception.html", "/images/doctors/banner1.png"));
        this.banners.add(new Banner("/news.html", "/images/news/banner2.png"));
    }
}


