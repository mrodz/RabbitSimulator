package toolkit;

import java.util.HashMap;

public enum RoadConstants {
    STRAIGHT_X(1),
    STRAIGHT_Y(2),
    LINEAR_POSITIVE(3),
    LINEAR_NEGATIVE(4),
    QUADRATIC_POSITIVE(5),
    QUADRATIC_NEGATIVE(6);

    private static final HashMap<Integer, RoadConstants> mapped = new HashMap<>();
    static {
        for (RoadConstants r : RoadConstants.values()) {
            mapped.put(r.id, r);
        }
    }

    int id;
    RoadConstants(int id) {
        this.id = id;
    }

    public static RoadConstants getRoadFromId(int id) {
        return mapped.get(id);
    }
}
