
import components.itinerary.Itinerary;
import components.itinerary.Itinerary.BaggagePolicy;
import components.itinerary.Itinerary.Leg;
import components.itinerary.Itinerary1L;

/**
 * Demonstrates validating a flight connection using Itinerary.
 *
 * Use Case 1: Airline system checks whether a passenger's connection satisfies
 * minimum layover requirements under different baggage policies.
 *
 * @author Edward Ji
 */
public final class ItineraryValidatorDemo {

    private ItineraryValidatorDemo() {
        // no instantiation
    }

    public static void main(String[] args) {

        Itinerary itinerary = new Itinerary1L();

        Leg first = new Leg("CMH", "DEN", 100, 200);
        Leg second = new Leg("DEN", "SFO", 285, 400);

        itinerary.addToEnd(first);
        itinerary.addToEnd(second);

        System.out.println("Origin: " + itinerary.origin());
        System.out.println("Destination: " + itinerary.destination());
        System.out.println(
                "Layover time: " + itinerary.layoverTime() + " minutes");

        boolean validThrough = itinerary
                .isConnectionValid(BaggagePolicy.THROUGH_CHECK, 60, 120);

        boolean validRecheck = itinerary
                .isConnectionValid(BaggagePolicy.RECHECK_REQUIRED, 60, 120);

        System.out.println("Valid (Through Check)? " + validThrough);
        System.out.println("Valid (Recheck Required)? " + validRecheck);
    }
}