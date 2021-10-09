package toolkit;

public class Line {
    int exponentialBase = 2;
    double a;
    double b;
    double c;

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
    }

    /**
     * Define logic needed to create a line able to be graphed.
     * @param a specifies the quadratic factor to a line, if present. If this line is linear, substitute with 0.
     * @param b specifies the linear slope rawValue, or <b><tt>m</tt/></b> of a line.
     * @param c specifies the y-intercept of the line.
     * @param exponentialBase defines the exponential base to be used when calculating a step in
     *                        {@link #calculateY(int)}.
     * @see <a href="https://www.desmos.com/calculator/zuaqvcvpbz">This helpful visualization</a> of how to
     * graph a quadratic function.
     */
    public Line(double a, double b, double c, int exponentialBase) {
        this(a, b, c);
        this.exponentialBase = exponentialBase;
    }

    public double calculateY(int x) {
        return (this.a * Math.pow(x, this.exponentialBase)) + (this.b * x) + this.c;
    }
}

