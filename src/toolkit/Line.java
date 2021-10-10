package toolkit;

import java.util.Objects;

public class Line {
    private final boolean special;
    int exponentialBase = 2;
    double a;
    double b;
    double c;

    private final Double xIntercept;

    /**
     * Define logic needed to create a line able to be graphed.
     * @param a specifies the quadratic factor to a line, if present. If this line is linear, substitute with 0.
     * @param b specifies the linear slope rawValue, or <b><tt>m</tt/></b> of a line.
     * @param c specifies the y-intercept of the line.
     * @see <a href="https://www.desmos.com/calculator/zuaqvcvpbz">This helpful visualization</a> of how to
     * graph a quadratic function.
     */
    public Line(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.special = false;
        this.xIntercept = null;
    }

    /**
     * <p>
     *     Special constructor to initialize a {@link Line} with an equation of: <b><tt>x=?</tt></b>.
     * </p>
     * In other words, creates a straight, vertical line.
     * @param xIntercept the x-intercept of this line.
     */
    public Line(double xIntercept) {
        this.xIntercept = xIntercept;
        this.special = true;
    }

    public double calculateY(int x) {
        if (special)
            throw new IllegalStateException("This operation cannot be performed on a line with a slope of x=" + xIntercept);
        return (this.a * Math.pow(x, this.exponentialBase)) + (this.b * x) + this.c;
    }

    public double getXIntercept() {
        if (this.xIntercept == null)
            throw new IllegalStateException("This operation can only be performed on a line with a vertical slope.");
        return this.xIntercept;
    }

    public void setExponentialBase(int exponentialBase) {
        this.exponentialBase = exponentialBase;
    }

    public boolean isSpecial() {
        return special;
    }

    @Override
    public String toString() {
        return "Line{" +
                "vertical=" + special +
                ", exponentialBase=" + exponentialBase +
                ", quadraticMultiplier=" + a +
                ", linearSlopeMultiplier=" + b +
                ", yIntercept=" + c +
                ", xIntercept=" + xIntercept +
                '}';
    }
}

