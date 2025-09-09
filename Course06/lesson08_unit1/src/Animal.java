public abstract class Animal {
    private String food = "";

    public abstract void printAnimal();
    public abstract void speak();
    public abstract String getName();

    public void eat(String food) {
        this.food = this.food + "," + food;
    }

    public void printFood() {
        System.out.print("Animal with name: " + this.getName() + ". Ate: ");
        System.out.println(this.food);
    }
}
