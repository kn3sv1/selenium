public class AgeService {
    public String categorizeAge(int age) {
        if (age < 0) {
            return "Invalid age";
        } else if (age <= 12) {
            return "Child";
        } else if (age <= 19) {
            return "Teenager";
        } else if (age <= 64) {
            return "Adult";
        } else {
            return "Senior";
        }
    }
}
