package toolkit;

import external.LogMessage;

import java.util.Random;

public class RoadBuilder {
    Plane road;
    RoadConstants type;

    public RoadBuilder(int length, int height) {
        this.road = new Plane(length, height);
        Random rng = new Random();

        int selection = rng.nextInt(RoadConstants.values().length) + 1;
        this.type = RoadConstants.getRoadFromId(selection);
        if (this.type == null) {
            new LogMessage(String.format("type is null {selection=%d, type=%s}", selection, null), 5, System.err);
        }
    }

    public Plane getRoad() {
        Random rng = new Random();
        int maximumQuadrilateralPower = 4;
        boolean integerSlope = rng.nextInt(2) == 0;
        boolean integerQuadrilateral = rng.nextInt(2) == 0;
        float scaleFactor = 1.5f;

        Line toBeApplied;

        switch (this.type) {
            case STRAIGHT_X:
                toBeApplied = new Line(0, 0, rng.nextInt(this.road.getHeight()));
                break;
            case STRAIGHT_Y:
                toBeApplied = new Line(rng.nextInt(this.road.getLength()));
                break;
            case LINEAR_NEGATIVE:
                toBeApplied = new Line(0, -rng.nextFloat(), rng.nextInt(this.road.getHeight() / 2) + this.road.getHeight() / 2f);
                break;
            case LINEAR_POSITIVE:
                toBeApplied = new Line(0, +rng.nextFloat(), rng.nextInt(this.road.getHeight() / 2));
                break;
            case QUADRATIC_POSITIVE:
                toBeApplied = new Line(integerQuadrilateral
                        ? rng.nextInt(maximumQuadrilateralPower + 1) + 2
                        : rng.nextFloat(), integerSlope
                                ? rng.nextInt(this.road.getHeight() / 3)
                                : rng.nextFloat(),
                        -rng.nextInt((int) (this.road.getHeight() * scaleFactor  * 100))
                );
                break;
            case QUADRATIC_NEGATIVE:
                toBeApplied = new Line(-(integerQuadrilateral
                        ? rng.nextInt(maximumQuadrilateralPower + 1) + 2
                        : rng.nextFloat()), -(integerSlope
                                ? rng.nextInt(this.road.getHeight() / 3)
                                : rng.nextFloat()),
                        rng.nextInt((int) (this.road.getHeight() * scaleFactor /* * 100*/))
                );
                break;
            default:
                throw new IllegalStateException("Assertion = false");
        }

        new LogMessage(String.format("Graphing a %s line >> %s >> on %s", this.type, toBeApplied, this.road), 1);
        return this.road.graphLine(toBeApplied);
    }
}
