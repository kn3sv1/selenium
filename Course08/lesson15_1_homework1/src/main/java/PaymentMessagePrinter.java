public class PaymentMessagePrinter {

    public static void printMessage(PaymentStatus status) {
        switch (status) {
            case PAID:
                System.out.println("Payment completed successfully.");
                break;
            case FAILED:
                System.out.println("Payment failed. Please try again.");
                break;
            case CANCELED:
                System.out.println("Payment was canceled.");
                break;

            default:
                System.out.println("Transaction in progress... Please wait.");
        }
    }
}
