public enum PaymentStatus {
    CREATED,   // payment created but not paid yet
    PAID,      // money received
    CANCELED,  // payment canceled
    FAILED     // payment attempt failed
}
