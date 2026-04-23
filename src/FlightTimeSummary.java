
import components.itinerary.Itinerary;
import components.itinerary.Itinerary.Leg;
import components.itinerary.Itinerary1L;

/**
 * Demonstrates generating a travel summary using Itinerary.
 *
 * Use Case 2: Travel analytics system calculates total flight time and produces
 * a formatted summary of the trip.
 *
 * @author Edward Ji
 */
public final class FlightTimeSummary {

    private FlightTimeSummary() {
        // no instantiation
    }

    public static void main(String[] args) {

        Itinerary itinerary = new Itinerary1L();

        Leg first = new Leg("CMH", "DEN", 100, 200);
        Leg second = new Leg("DEN", "SFO", 285, 400);

        itinerary.addToEnd(first);
        itinerary.addToEnd(second);

        int totalFlight = itinerary.totalFlightTime();
        int layover = itinerary.layoverTime();

        System.out.println("Travel Summary");
        System.out.println("-------------------");
        System.out.println("Route: " + itinerary.origin() + " → "
                + itinerary.destination());
        System.out.println("Total Flight Time: " + totalFlight + " minutes");
        System.out.println("Layover Time: " + layover + " minutes");
        System.out.println("Detail: " + itinerary.toString());
    }
}