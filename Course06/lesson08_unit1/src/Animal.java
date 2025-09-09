public abstract class Animal {
    private String food = "";

    public abstract void speak();
    public abstract String getName();

    public void eat(String food) {
        this.food = this.food + "," + food;
    }

    public void printFood() {
        System.out.print("Animal with name: " + this.getName() + ". Ate: ");
        System.out.println(this.food);
    }

    public void printAnimal() {
        //for each class this will be its own this
        System.out.println(this.toString());
    }
}
