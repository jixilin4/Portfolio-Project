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
     *
     * (Implementation detail: may be immutable to avoid aliasing.)
     */
    interface Leg {

        String fromAirport();

        String toAirport();

        int departMinute();

        int arriveMinute();
    }

    /**
     * Reports the origin airport code, or "" if empty.
     *
     * @return origin airport code or ""
     */
    String origin();

    /**
     * Reports the destination airport code, or "" if empty.
     *
     * @return destination airport code or ""
     */
    String destination();

    /**
     * Returns the layover time between the first and second leg. This secondary
     * method is intended for the one-connection (two-leg) case.
     *
     * @return layover minutes
     */
    int layoverTime();

    /**
     * Reports whether the connection layover is at least
     * {@code minLayoverMinutes}.
     *
     * @param minLayoverMinutes
     *            minimum required layover time
     * @return true iff the connection is valid
     */
    boolean isConnectionValid(int minLayoverMinutes);

    /**
     * Reports whether the connection layover satisfies the threshold implied by
     * {@code policy}.
     *
     * @param policy
     *            baggage policy
     * @param minThrough
     *            minimum layover for through-check
     * @param minRecheck
     *            minimum layover for recheck-required
     * @return true iff the connection is valid
     */
    boolean isConnectionValid(BaggagePolicy policy, int minThrough,
            int minRecheck);
}
