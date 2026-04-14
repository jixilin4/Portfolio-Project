package components.itinerary;

import components.sequence.Sequence;
import components.sequence.Sequence1L;

/**
 * {@code Itinerary} kernel implementation using a {@code Sequence}.
 *
 * Representation: private Sequence<Leg> legs;
 *
 * Convention: legs is not null 0 <= legs.length() <= 2
 *
 * Correspondence: this = the sequence of legs stored in legs, in the same
 * order.
 *
 * @author Edward Ji
 */
public class Itinerary1L extends ItinerarySecondary {

    /**
     * Sequence of legs representing this itinerary.
     */
    private Sequence<Leg> legs;

    /**
     * Creates a new empty representation.
     */
    private void createNewRep() {
        this.legs = new Sequence1L<>();
    }

    /**
     * No-argument constructor.
     */
    public Itinerary1L() {
        this.createNewRep();
    }

    @Override
    public final void addToFront(Leg x) {
        assert x != null : "Violation of: x is not null";
        assert this.length() < 2 : "Violation of: at most 2 legs";

        this.legs.add(0, x);
    }

    @Override
    public final Leg removeFromFront() {
        assert this.length() > 0 : "Violation of: this is not empty";

        return this.legs.remove(0);
    }

    @Override
    public final void addToEnd(Leg x) {
        assert x != null : "Violation of: x is not null";
        assert this.length() < 2 : "Violation of: at most 2 legs";

        this.legs.add(this.legs.length(), x);
    }

    @Override
    public final Leg removeFromEnd() {
        assert this.length() > 0 : "Violation of: this is not empty";

        return this.legs.remove(this.legs.length() - 1);
    }

    @Override
    public final int length() {
        return this.legs.length();
    }

    @Override
    public final Itinerary newInstance() {
        return new Itinerary1L();
    }

    @Override
    public final void clear() {

        this.createNewRep();
    }

    @Override
    public final void transferFrom(Itinerary source) {
        assert source != null : "Violation of: source is not null";
        assert source != this : "Violation of: source is not this";
        assert source instanceof Itinerary1L : "Violation of: source must be Itinerary1L";

        Itinerary1L localSource = (Itinerary1L) source;
        this.legs = localSource.legs;
        localSource.createNewRep();
    }
}