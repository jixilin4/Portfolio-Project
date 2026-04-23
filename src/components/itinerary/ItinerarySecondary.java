package components.itinerary;

import java.util.Objects;

/**
 * Secondary abstract class for {@code Itinerary}.
 *
 * @author Edward Ji
 */
public abstract class ItinerarySecondary implements Itinerary {

    @Override
    public final String origin() {
        if (this.length() == 0) {
            return "";
        }
        Leg first = this.removeFromFront();
        this.addToFront(first);
        return first.fromAirport();
    }

    @Override
    public final String destination() {
        if (this.length() == 0) {
            return "";
        }
        Leg last = this.removeFromEnd();
        this.addToEnd(last);
        return last.toAirport();
    }

    @Override
    public final int layoverTime() {
        assert this.length() == 2 : "Violation of: this has exactly 2 legs";

        Leg first = this.removeFromFront();
        Leg second = this.removeFromFront();

        int layover = second.departMinute() - first.arriveMinute();

        this.addToFront(second);
        this.addToFront(first);

        return layover;
    }

    @Override
    public final boolean isConnectionValid(int minLayoverMinutes) {
        assert minLayoverMinutes >= 0 : "Violation of: minLayoverMinutes >= 0";
        return this.layoverTime() >= minLayoverMinutes;
    }

    @Override
    public final boolean isConnectionValid(BaggagePolicy policy, int minThrough,
            int minRecheck) {
        assert policy != null : "Violation of: policy is not null";
        assert minThrough >= 0 : "Violation of: minThrough >= 0";
        assert minRecheck >= 0 : "Violation of: minRecheck >= 0";

        int threshold;
        if (policy == BaggagePolicy.THROUGH_CHECK) {
            threshold = minThrough;
        } else {
            threshold = minRecheck;
        }
        return this.isConnectionValid(threshold);
    }

    @Override
    public final int totalFlightTime() {
        assert this.length() == 2 : "Violation of: this has exactly 2 legs";

        Leg first = this.removeFromFront();
        Leg second = this.removeFromFront();

        int total = (first.arriveMinute() - first.departMinute())
                + (second.arriveMinute() - second.departMinute());

        this.addToFront(second);
        this.addToFront(first);

        return total;
    }

    @Override
    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Itinerary(");
        int n = this.length();

        for (int i = 0; i < n; i++) {
            Leg leg = this.removeFromFront();
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(leg.toString());
            this.addToEnd(leg);
        }

        sb.append(")");
        return sb.toString();
    }

    @Override
    public final int hashCode() {
        return this.toString().hashCode();
    }

    @Override
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Itinerary)) {
            return false;
        }

        Itinerary other = (Itinerary) obj;

        if (this.length() != other.length()) {
            return false;
        }

        boolean same = true;
        int n = this.length();

        for (int i = 0; i < n; i++) {
            Leg a = this.removeFromFront();
            Leg b = other.removeFromFront();

            if (!Objects.equals(a, b)) {
                same = false;
            }

            this.addToEnd(a);
            other.addToEnd(b);
        }

        return same;
    }
}