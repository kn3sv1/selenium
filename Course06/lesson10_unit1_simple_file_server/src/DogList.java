public class DogList {
    private Dog[] dogs;

    public DogList(Dog[] dogs) {
        this.dogs = dogs;
    }

    public String toHTML(boolean isShort) {
        String result = "";
        for (Dog d : this.dogs) {
            result = result +  (isShort? d.toHTMLShort() : d.toHTML());
        }

        return result;
    }

}
