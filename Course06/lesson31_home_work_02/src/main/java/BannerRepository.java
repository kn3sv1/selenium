import com.fasterxml.jackson.core.type.TypeReference;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class BannerRepository extends AbstractDatabaseRepository {
    private List<Banner> banners;
    private final Path file;

    public BannerRepository() {
        super();
        this.file = Path.of("./database/banners.json");
        // always load from disk when you create constructor of repository
        // otherwise you erase all of them from disk.
        this.banners = this.load(new TypeReference<List<Banner>>() {});
    }

    @Override
    protected Path getFile() {
        return this.file;
    }

    public List<Banner> getBanners() {
        return this.banners;
    }

    public Banner findById(String id) {
        //TODO: refactor after to lambda to have shorter code
        Banner entity = null;
        for(Banner e : this.banners) {
            if(e.getId().equals(id)) {
                entity = e;
            }
        }
        return entity;
    }
    public void add(Banner entity) {
        this.banners.add(entity);
        this.save(this.banners);
    }

    public void update(Banner entity) {
        for (int i = 0; i < this.banners.size(); i++) {
            if (this.banners.get(i).getId().equals(entity.getId())) {
                this.banners.set(i, entity);
            }
        }
        this.save(this.banners);
    }

    public void delete(String id) {
        for(Banner entity : this.banners) {
            if(entity.getId().equals(id)) {
                this.banners.remove(entity);
            }
        }
        this.save(this.banners);
    }
}
