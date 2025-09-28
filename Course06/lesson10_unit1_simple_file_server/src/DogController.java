import java.io.IOException;

public class DogController {
    private Dog chino;
    private Dog rusty;

    public DogController() {
        this.chino = new Dog("chino", "white", 15, "chino.png");
        this.rusty = new Dog("rusty", "dark ginger", 5, "rusty.png");
    }

    public void chinoHTML(Browser browser) throws IOException {
        browser.sendHTML(this.chino.toHTML());
    }

    public void rustyHTML(Browser browser) throws IOException {
        browser.sendHTML(this.rusty.toHTML());
    }

    public void dogListHTML(Browser browser) throws IOException {
        DogList list = new DogList(new Dog[]{chino, rusty});
        browser.sendHTML(list.toHTML(true));
    }
}
