import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class BannerRepository {
    private List<Banner> banners = new ArrayList<>();
    public BannerRepository() {
        this.populate();
    }
    public void addBanner(Banner banner) {
        banners.add(banner);
    }
    public List<Banner> getAllBanners() {
        return banners;
    }

    public List<Banner> findBy(Instant currentTime) {
        List<Banner> activeBanners = new ArrayList<>();
        for (Banner banner : this.banners) {
            if (banner.getActive() &&
                    (banner.getShowFrom().equals(currentTime) || banner.getShowFrom().isBefore(currentTime)) &&
                    (banner.getShowTo().equals(currentTime) || banner.getShowTo().isAfter(currentTime))) {
                activeBanners.add(banner);
            }
        }
        return activeBanners;
    }

    public void populate() {
        // add some sample banners
        Banner banner1 = new Banner(
                "Spring Sale",
                Instant.parse("2024-03-01T00:00:00Z"),
                Instant.parse("2024-03-31T23:59:59Z"),
                "<h1>Spring Sale - Up to 50% Off!</h1>",
                true,
                Instant.now(),
                Instant.now()
        );
        Banner banner2 = new Banner(
                "Summer Collection",
                Instant.parse("2024-06-01T00:00:00Z"),
                Instant.parse("2024-06-30T23:59:59Z"),
                "<h1>Check Out Our New Summer Collection!</h1>",
                true,
                Instant.now(),
                Instant.now()
        );
        Banner banner3 = new Banner(
                "Winter Clearance",
                Instant.parse("2024-12-01T00:00:00Z"),
                Instant.parse("2024-12-31T23:59:59Z"),
                "<h1>Winter Clearance - Everything Must Go!</h1>",
                false,
                Instant.now(),
                Instant.now()
        );
        Banner banner4 = new Banner(
                "Black Friday Deals",
                Instant.parse("2024-11-25T00:00:00Z"),
                Instant.parse("2024-11-29T23:59:59Z"),
                "<h1>Black Friday Deals - Don't Miss Out!</h1>",
                true,
                Instant.now(),
                Instant.now()
        );
        Banner banner5 = new Banner(
                "New Year Specials",
                Instant.parse("2024-12-31T00:00:00Z"),
                Instant.parse("2025-01-05T23:59:59Z"),
                "<h1>Celebrate the New Year with Special Offers!</h1>",
                true,
                Instant.now(),
                Instant.now()
        );
        Banner banner6 = new Banner(
                "Back to School",
                Instant.parse("2024-08-15T00:00:00Z"),
                Instant.parse("2026-09-15T23:59:59Z"),
                "<h1>Back to School - Save on Supplies!</h1>",
                true,
                Instant.now(),
                Instant.now()
        );
        this.addBanner(banner1);
        this.addBanner(banner2);
        this.addBanner(banner3);
        this.addBanner(banner4);
        this.addBanner(banner5);
        this.addBanner(banner6);
    }
}
