package components.itinerary;

import components.standard.Standard;

/**
 * Kernel interface for {@code Itinerary}.
 *
 * @author Edward Ji
 */
public interface ItineraryKernel extends Standard<Itinerary> {

    /**
     * Adds {@code x} to the front of this.
     *
     * @param x
     *            the leg to add
     */
    void addToFront(Itinerary.Leg x);

    /**
     * Removes and returns the front leg.
     *
     * @return the front leg
     */
    Itinerary.Leg removeFromFront();

    /**
     * Adds {@code x} to the end of this.
     *
     * @param x
     *            the leg to add
     */
    void addToEnd(Itinerary.Leg x);

    /**
     * Removes and returns the end leg.
     *
     * @return the end leg
     */
    Itinerary.Leg removeFromEnd();

    /**
     * Returns the number of legs in this.
     *
     * @return the length of this
     */
    int length();
}
