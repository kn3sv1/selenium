import java.util.ArrayList;
import java.util.List;

public class ChildrenRepository {
    private ArrayList<Child> children;

    public ChildrenRepository() {
        this.children = new ArrayList<Child>();
        this.populate();
    }

    private void populate() {
        this.children.add(new CypriotChild(5548, "Maria", 10, "cyprus", "kebab"));
        this.children.add(new RussianChild(5001, "Tania", 8, "russia", "borsch"));
        this.children.add(new AfricanChild(5435, "Happiness", 11, "africa", "fried chicken"));
        this.children.add(new IndianChild(4458, "Diya", 10, "india", "very spicy"));;
    }

    public String toJsonArray() {
        List<String> json = new ArrayList<String>();
        for (Child child : this.children) {
            json.add(child.toJson());
        }

        return "[" + String.join(", ", json) + "]";
    }


    public Child findById(int id) {
        Child child = null;
        for (Child ch : this.children) {
            if (ch.getId() == id) {
                child = ch;
                break;
            }
        }
        return child;
    }

    public void addChildToArray(int id) {
        this.children.add(new NewChild(id, "New kid", 18, "America", "coding"));
    }


}
