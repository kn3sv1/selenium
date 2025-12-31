import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class BannerService {
    private final BannerRepository repository;

    public BannerService(BannerRepository repository) {
        this.repository = repository;
    }

    public List<Banner> findAll() {
        return  this.repository.getBanners();
    }

    public Banner findById(String uuid) {
        return this.repository.findById(uuid);
    }

    public List<Banner> findByPage(String page, String place) {
        return this.repository.findByPage(page, place);
    }

    /**
     * service responsibility is to not only hide repository from controller and work as proxy.
     * but also to transform data from controller to entity that repository understands.
     * the repository only saves entity but doesn't apply business rules before saving.
     */
    public void create(Map<String, String> data) {
        String[] lines = data.get("pages").trim().split("\n");
        List<String> pages = new ArrayList<>(List.of(lines));
        Banner entity = new Banner(
                UUID.randomUUID().toString(),
                data.get("name"),
                data.get("photo"),
                pages,
                this.parseDate(data.get("date_from")),
                this.parseDate(data.get("date_to")),
                data.get("place"),
                Integer.parseInt(data.get("position"))
        );
        this.repository.add(entity);
    }

    public void update(String uuid, Map<String, String> data) {
        Banner entity = this.repository.findById(uuid);
        String[] lines = data.get("pages").trim().split("\n");
        List<String> pages = new ArrayList<>(List.of(lines));

        entity.setName(data.get("name"));
        // we check if not empty otherwise we take new photo that was submitted otherwise we get what we had.
        // if not submitted it will be null.
        //entity.setPhoto(data.get("photo") != null && !data.get("photo").isEmpty() ? data.get("photo") : entity.getPhoto());
        if (data.get("photo") != null && !data.get("photo").isEmpty()) {
            entity.setPhoto(data.get("photo"));
        }
        entity.setPages(pages);
        entity.setDateFrom(this.parseDate(data.get("date_from")));
        entity.setDateTo(this.parseDate(data.get("date_to")));
        entity.setPlace(data.get("place"));
        entity.setPosition(Integer.parseInt(data.get("position")));

        this.repository.update(entity);
    }

    public void removeById(String uuid) {
        this.repository.delete(uuid);
    }

    private Date parseDate(String input) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(input);
            //System.out.println("Parsed: " + date);
            return date;
        } catch (ParseException e) {
            System.err.println("Wrong format!");
            throw new RuntimeException(e);
        }
    }
}
