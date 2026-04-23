package components.itinerary;

import components.sequence.Sequence;
import components.sequence.Sequence1L;

/**
 * Layered implementation of Itinerary.
 */
public final class Itinerary1L extends ItinerarySecondary {

    /**
     * Representation: sequence of legs.
     */
    private Sequence<Leg> legs;

    /**
     * Constructor.
     */
    public Itinerary1L() {
        this.legs = new Sequence1L<>();
    }

    @Override
    public void addToFront(Leg x) {
        this.legs.add(0, x);
    }

    @Override
    public Leg removeFromFront() {
        return this.legs.remove(0);
    }

    @Override
    public void addToEnd(Leg x) {
        this.legs.add(this.legs.length(), x);
    }

    @Override
    public Leg removeFromEnd() {
        return this.legs.remove(this.legs.length() - 1);
    }

    @Override
    public int length() {
        return this.legs.length();
    }

    @Override
    public void clear() {
        this.legs.clear();
    }

    @Override
    public Itinerary newInstance() {
        return new Itinerary1L();
    }

    @Override
    public void transferFrom(Itinerary source) {
        Itinerary1L local = (Itinerary1L) source;
        this.legs.transferFrom(local.legs);
    }
}
