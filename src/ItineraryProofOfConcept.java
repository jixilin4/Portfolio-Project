import java.util.LinkedList;
import java.util.Objects;

/**
 * Proof-of-concept: Itinerary with one connection (two legs), using
 * assemble/disassemble access.
 *
 * @author Edward Ji
 */
public final class ItineraryProofOfConcept {

    /**
     * Baggage handling policy for connection checks.
     */
    public enum BaggagePolicy {
        THROUGH_CHECK, RECHECK_REQUIRED
    }

    /**
     * A single flight leg (immutable for aliasing safety).
     */
    public static final class Leg {

        private final String fromAirport;
        private final String toAirport;
        private final int departMinute;
        private final int arriveMinute;

        public Leg(String fromAirport, String toAirport, int departMinute,
                int arriveMinute) {
            this.fromAirport = requireNonBlank(fromAirport, "fromAirport");
            this.toAirport = requireNonBlank(toAirport, "toAirport");
            assert departMinute >= 0 : "Violation of: departMinute >= 0";
            assert arriveMinute >= 0 : "Violation of: arriveMinute >= 0";
            assert arriveMinute >= departMinute : "Violation of: arriveMinute >= departMinute";
            this.departMinute = departMinute;
            this.arriveMinute = arriveMinute;
        }

        public String fromAirport() {
            return this.fromAirport;
        }

        public String toAirport() {
            return this.toAirport;
        }

        public int departMinute() {
            return this.departMinute;
        }

        public int arriveMinute() {
            return this.arriveMinute;
        }

        public int flightMinutes() {
            return this.arriveMinute - this.departMinute;
        }

        @Override
        public String toString() {
            return this.fromAirport + "->" + this.toAirport + " ("
                    + this.departMinute + "→" + this.arriveMinute + ")";
        }
    }

    /**
     * Representation: ordered list of legs.
     */
    private final LinkedList<Leg> rep;

    /**
     * Constructs an empty itinerary.
     */
    public ItineraryProofOfConcept() {
        this.rep = new LinkedList<>();
    }

    /* Kernel style (assemble/disassemble from ends). */

    public void addToFront(Leg x) {
        assert x != null : "Violation of: x is not null";
        this.rep.addFirst(x);
    }

    public Leg removeFromFront() {
        assert this.length() > 0 : "Violation of: this is not empty";
        return this.rep.removeFirst();
    }

    public void addToEnd(Leg x) {
        assert x != null : "Violation of: x is not null";
        this.rep.addLast(x);
    }

    public Leg removeFromEnd() {
        assert this.length() > 0 : "Violation of: this is not empty";
        return this.rep.removeLast();
    }

    public int length() {
        return this.rep.size();
    }

    public void clear() {
        this.rep.clear();
    }

    /* Secondary style (assume exactly two legs for one-connection analysis). */

    public String origin() {
        if (this.length() == 0) {
            return "";
        }

        Leg first = this.removeFromFront();
        this.addToFront(first);

        return first.fromAirport();
    }

    public String destination() {
        if (this.length() == 0) {
            return "";
        }

        Leg last = this.removeFromEnd();
        this.addToEnd(last);

        return last.toAirport();
    }

    /**
     * Returns layover time between the first and second leg.
     *
     * @return layover minutes
     */
    public int layoverTime() {
        assert this.length() == 2 : "Violation of: this has exactly 2 legs";

        Leg first = this.removeFromFront();
        Leg second = this.removeFromFront();

        int layover = second.departMinute() - first.arriveMinute();

        this.addToEnd(first);
        this.addToEnd(second);

        return layover;
    }

    public boolean isConnectionValid(int minLayoverMinutes) {
        assert minLayoverMinutes >= 0 : "Violation of: minLayoverMinutes >= 0";
        return this.layoverTime() >= minLayoverMinutes;
    }

    public boolean isConnectionValid(BaggagePolicy policy, int minThrough,
            int minRecheck) {
        assert policy != null : "Violation of: policy is not null";
        assert minThrough >= 0 : "Violation of: minThrough >= 0";
        assert minRecheck >= 0 : "Violation of: minRecheck >= 0";

        int threshold = (policy == BaggagePolicy.THROUGH_CHECK) ? minThrough
                : minRecheck;
        return this.isConnectionValid(threshold);
    }

    public static void main(String[] args) {
        ItineraryProofOfConcept it = new ItineraryProofOfConcept();

        it.addToEnd(new Leg("CMH", "ORD", 7 * 60 + 20, 8 * 60 + 35));
        it.addToEnd(new Leg("ORD", "SFO", 10 * 60, 12 * 60 + 55));

        System.out.println("Leg count: " + it.length());
        System.out.println("Origin: " + it.origin());
        System.out.println("Destination: " + it.destination());
        System.out.println("Layover time (min): " + it.layoverTime());

        int minThrough = 60;
        int minRecheck = 120;

        System.out.println("Valid (THROUGH_CHECK, min=" + minThrough + ")? "
                + it.isConnectionValid(BaggagePolicy.THROUGH_CHECK, minThrough,
                        minRecheck));
        System.out.println("Valid (RECHECK_REQUIRED, min=" + minRecheck + ")? "
                + it.isConnectionValid(BaggagePolicy.RECHECK_REQUIRED,
                        minThrough, minRecheck));
    }

    private static String requireNonBlank(String s, String name) {
        Objects.requireNonNull(s, name + " must not be null");
        String t = s.trim();
        assert !t.isEmpty() : "Violation of: " + name + " is not blank";
        return t.toUpperCase();
    }
}