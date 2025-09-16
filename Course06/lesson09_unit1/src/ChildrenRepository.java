import java.util.ArrayList;
import java.util.List;

public class ChildrenRepository {
    private ArrayList<Child> children;

    public ChildrenRepository() {
        this.children = new ArrayList<Child>();
        this.populate();
    }

    private void populate() {
        this.children.add(new CypriotChild(5548, "Maria", 10, "Cyprus", "kebab"));
        this.children.add(new RussianChild(5001, "Tania", 8, "Russia", "borsch"));
        this.children.add(new AfricanChild(5435, "Happiness", 11, "Africa", "fried chicken"));
        this.children.add(new IndianChild(4458, "Diya", 10, "India", "very spicy"));
    }

    public String toJsonArray() {
        List<String> json = new ArrayList<String>();
        for(Child child : this.children) {
            json.add(child.toJson());
        }

        return "[" + String.join(", ", json) + "]";
    }
}
