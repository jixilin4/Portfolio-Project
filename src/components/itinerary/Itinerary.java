package components.itinerary;

/**
 * Enhanced interface for {@code Itinerary}.
 *
 * @author Edward Ji
 */
public interface Itinerary extends ItineraryKernel {

    /**
     * Baggage handling policy for connection checks.
     */
    enum BaggagePolicy {
        THROUGH_CHECK, RECHECK_REQUIRED
    }

    /**
     * A single flight leg.
     */
    final class Leg {
        private final String from;
        private final String to;
        private final int depart;
        private final int arrive;

        public Leg(String from, String to, int depart, int arrive) {
            this.from = from;
            this.to = to;
            this.depart = depart;
            this.arrive = arrive;
        }

        public String fromAirport() {
            return this.from;
        }

        public String toAirport() {
            return this.to;
        }

        public int departMinute() {
            return this.depart;
        }

        public int arriveMinute() {
            return this.arrive;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Leg)) {
                return false;
            }
            Leg other = (Leg) obj;
            return this.from.equals(other.from) && this.to.equals(other.to)
                    && this.depart == other.depart
                    && this.arrive == other.arrive;
        }

        @Override
        public int hashCode() {
            return this.from.hashCode() + this.to.hashCode() + this.depart
                    + this.arrive;
        }

        @Override
        public String toString() {
            return this.from + "->" + this.to + " (" + this.depart + "-"
                    + this.arrive + ")";
        }
    }

    String origin();

    String destination();

    int layoverTime();

    boolean isConnectionValid(int minLayoverMinutes);

    boolean isConnectionValid(BaggagePolicy policy, int minThrough,
            int minRecheck);

    int totalFlightTime();
}