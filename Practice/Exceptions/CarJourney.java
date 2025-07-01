// Custom Exceptions
class EngineStartException extends Exception {
    public EngineStartException(String message) {
        super(message);
    }
}

class FlatTireException extends Exception {
    public FlatTireException(String message) {
        super(message);
    }
}

class OverheatException extends Exception {
    public OverheatException(String message) {
        super(message);
    }
}

public class CarJourney {

    public static void main(String[] args) {
        try {
            startCar();           // Might throw EngineStartException
            driveToDestination(); // Might throw FlatTireException or OverheatException
        } catch (EngineStartException e) {
            System.out.println("ERROR: " + e.getMessage());
            System.out.println("Calling mechanic...");
        } catch (FlatTireException e) {
            System.out.println("ERROR: " + e.getMessage());
            System.out.println("Changing the tire...");
        } catch (OverheatException e) {
            System.out.println("ERROR: " + e.getMessage());
            System.out.println("Pulling over to cool down...");
        } finally {
            System.out.println("Trip ended. Logging journey details...");
        }
    }

    static void startCar() throws EngineStartException {
        boolean engineFails = Math.random() < 0.3; // 30% chance of failure
        if (engineFails) {
            throw new EngineStartException("Engine failed to start.");
        }
        System.out.println("Engine started successfully.");
    }

    static void driveToDestination() throws FlatTireException, OverheatException {
        double randomEvent = Math.random();
        if (randomEvent < 0.2) {
            throw new FlatTireException("You got a flat tire.");
        } else if (randomEvent < 0.4) {
            throw new OverheatException("Engine is overheating.");
        }
        System.out.println("You reached your destination safely.");
    }
}

