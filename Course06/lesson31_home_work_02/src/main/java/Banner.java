import java.util.Date;
import java.util.List;

public class Banner {
    private String id;
    private String name;
    private String photo;
    private List<String> pages;
    private Date dateFrom;
    private Date dateTo;
    private String place;
    private Integer position;

    public Banner() {

    }

    public Banner(String id, String name, String photo, List<String> pages, Date dateFrom, Date dateTo, String place, Integer position) {
        this.id = id;
        this.name = name;
        this.photo = photo;
        this.pages = pages;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.place = place;
        this.position = position;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public List<String> getPages() {
        return pages;
    }

    public void setPages(List<String> pages) {
        this.pages = pages;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }
}
