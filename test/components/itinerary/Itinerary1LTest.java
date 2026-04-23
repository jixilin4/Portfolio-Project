package components.itinerary;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import components.itinerary.Itinerary.Leg;

/**
 * JUnit test fixture for {@code Itinerary1L}'s kernel and Standard methods.
 *
 * @author Edward Ji
 */
public class Itinerary1LTest {

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
    public void testConstructor() {
        Itinerary i = new Itinerary1L();
        assertEquals(0, i.length());
    }

    @Test
    public void testAddToFront() {
        Itinerary i = new Itinerary1L();
        Leg a = new Leg("CMH", "SFO", 100, 200);

        i.addToFront(a);

        assertEquals(1, i.length());
        assertEquals(a, i.removeFromFront());
        assertEquals(0, i.length());
    }

    @Test
    public void testAddToEnd() {
        Itinerary i = new Itinerary1L();
        Leg a = new Leg("CMH", "SFO", 100, 200);

        i.addToEnd(a);

        assertEquals(1, i.length());
        assertEquals(a, i.removeFromEnd());
        assertEquals(0, i.length());
    }

    @Test
    public void testRemoveFromFront() {
        Itinerary i = this.createTwoLegItinerary();

        Leg first = i.removeFromFront();

        assertEquals("CMH", first.fromAirport());
        assertEquals(1, i.length());
    }

    @Test
    public void testRemoveFromEnd() {
        Itinerary i = this.createTwoLegItinerary();

        Leg last = i.removeFromEnd();

        assertEquals("SFO", last.toAirport());
        assertEquals(1, i.length());
    }

    @Test
    public void testLength() {
        Itinerary i = new Itinerary1L();
        assertEquals(0, i.length());

        i.addToEnd(new Leg("A", "B", 0, 10));
        assertEquals(1, i.length());

        i.addToEnd(new Leg("B", "C", 20, 30));
        assertEquals(2, i.length());
    }

    @Test
    public void testClear() {
        Itinerary i = this.createTwoLegItinerary();

        i.clear();

        assertEquals(0, i.length());
    }

    @Test
    public void testTransferFrom() {
        Itinerary source = this.createTwoLegItinerary();
        Itinerary target = new Itinerary1L();

        target.transferFrom(source);

        assertEquals(2, target.length());
        assertEquals(0, source.length());
    }

    @Test
    public void testNewInstance() {
        Itinerary i = new Itinerary1L();
        Itinerary newI = i.newInstance();

        assertTrue(newI instanceof Itinerary1L);
        assertEquals(0, newI.length());
    }
}