public class Payment {

    private PaymentStatus status;
    private Person person;

    public Payment(Person person) {
        this.status = PaymentStatus.CREATED;
        this.person = person;
    }
    public Person getPerson() {
        return person;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void pay() {
        this.status = PaymentStatus.PAID;
    }

    public void cancel() {
        this.status = PaymentStatus.CANCELED;
    }

    public void fail() {
        this.status = PaymentStatus.FAILED;
    }

    public void showInfo() {
        System.out.println("The amount of: " + this.person.getTotalAmount() + " is being transferred to " + this.person.getName() + "'s" + " account.");
        System.out.println("Status: " + status);
    }
}

