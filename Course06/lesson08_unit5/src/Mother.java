public class Mother {
    private String name;
    private int birth;
    private Child child = null; // we pass default value null to have the option of not having
    // a value for this property and so we don't have to overload the constructor.
    private int catFoodSupply;

    /*
    public Mother(String name, int birth, Child child) {
        this.name = name;
        this.birth = birth;
        this.child = child;
        if (this.child != null) {
            // we say to child - now I am your mother officially and I own you
            this.child.setMother(this);
        }
    }
    */
    public Mother(String name, int birth, int catFoodSupply) {
        this.name = name;
        this.birth = birth;
        this.catFoodSupply = catFoodSupply;
    }

    // setter dependency injection is better in this situation because child should be optional
    public void setChild(Child child) {
        this.child = child;
        this.child.setMother(this);
    }

    public String getName() {
        return this.name;
    }

    public void showFamily() {
        System.out.println("name: " + this.name + " .Date of birth: " + this.birth);
        if (this.child != null) {
            //System.out.println("child: " + this.child.getName());
            this.child.printInfo();
        }
    }

    public void childWantsToEat(String food) {
        System.out.println("I'm mother object. I'm now on TikTok. I will feed after with: " + food + " in 5 minutes!");
        this.feedingChild(food);
    }

    public void feedingChild(String food) {
        // here mother starts to feed child by calling methods on child
        this.child.eat("porridge");
        this.child.eat("frappe");
        this.child.eat(food);
    }

    public void supplyCatFood() {
        this.catFoodSupply -= 5;
        int catFood = this.child.getCatFood();
        catFood += 5;
        System.out.println(this.name + " is supplying " + this.child.getName() + " with cat food...");
        System.out.println(this.name + " now has " + this.catFoodSupply + " units of cat food left.");
        System.out.println(this.child.getName() + " now has " + catFood + " units of cat food.");
    }
}
