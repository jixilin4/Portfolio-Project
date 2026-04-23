package components.itinerary;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import components.itinerary.Itinerary.BaggagePolicy;
import components.itinerary.Itinerary.Leg;

/**
 * JUnit test fixture for {@code Itinerary}'s secondary methods.
 *
 * Tests ensure: 1) Correct return values 2) Object state is preserved after
 * method calls
 *
 * @author Edward Ji
 */
public class ItineraryTest {

    /**
     * Helper method to create a sample two-leg itinerary.
     */
    private Itinerary createTwoLegItinerary() {
        Itinerary i = new Itinerary1L();
        Leg a = new Leg("CMH", "DEN", 100, 200);
        Leg b = new Leg("DEN", "SFO", 285, 400);

        i.addToEnd(a);
        i.addToEnd(b);
        return i;
    }

    @Test
    public void testOrigin() {
        Itinerary i = this.createTwoLegItinerary();
        Itinerary copy = this.createTwoLegItinerary();

        String origin = i.origin();

        assertEquals("CMH", origin);
        assertEquals(copy, i);
    }

    @Test
    public void testDestination() {
        Itinerary i = this.createTwoLegItinerary();
        Itinerary copy = this.createTwoLegItinerary();

        String destination = i.destination();

        assertEquals("SFO", destination);
        assertEquals(copy, i);
    }

    @Test
    public void testLayoverTime() {
        Itinerary i = this.createTwoLegItinerary();
        Itinerary copy = this.createTwoLegItinerary();

        int layover = i.layoverTime();

        assertEquals(85, layover);
        assertEquals(copy, i);
    }

    @Test
    public void testIsConnectionValidInt() {
        Itinerary i = this.createTwoLegItinerary();
        Itinerary copy = this.createTwoLegItinerary();

        assertTrue(i.isConnectionValid(60));
        assertEquals(copy, i);
    }

    @Test
    public void testIsConnectionValidPolicy() {
        Itinerary i = this.createTwoLegItinerary();
        Itinerary copy = this.createTwoLegItinerary();

        boolean validThrough = i.isConnectionValid(BaggagePolicy.THROUGH_CHECK,
                60, 120);
        boolean validRecheck = i
                .isConnectionValid(BaggagePolicy.RECHECK_REQUIRED, 60, 120);

        assertTrue(validThrough);
        assertEquals(false, validRecheck);

        assertEquals(copy, i);
    }

    @Test
    public void testTotalFlightTime() {
        Itinerary i = this.createTwoLegItinerary();
        Itinerary copy = this.createTwoLegItinerary();

        int totalFlight = i.totalFlightTime();

        int expected = (200 - 100) + (400 - 285);
        assertEquals(expected, totalFlight);
        assertEquals(copy, i);
    }

    @Test
    public void testToString() {
        Itinerary i = this.createTwoLegItinerary();
        Itinerary copy = this.createTwoLegItinerary();

        String s = i.toString();

        assertTrue(s.contains("CMH"));
        assertTrue(s.contains("SFO"));
        assertEquals(copy, i);
    }

    @Test
    public void testEquals() {
        Itinerary a = this.createTwoLegItinerary();
        Itinerary b = this.createTwoLegItinerary();

        assertTrue(a.equals(b));
        assertTrue(b.equals(a));
    }
}