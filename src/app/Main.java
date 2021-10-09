package app;

import annotations.RunningInstance;
import toolkit.Grid;
import toolkit.Line;
import toolkit.Plane;

public class Main {

    @RunningInstance
    public static void main(String[] args) {
        Grid g = new Grid(140, 25);
        Plane p = new Plane(g.getLength(), g.getHeight());
        p.graphLine(new Line(0, 1, 0));
        p.graphLine(new Line(3, 5, -90));
        p.graphLine(new Line(0, 0, 5)); // x = 0
        p.graphLine(new Line(0, -0.2, g.getHeight() - 1));
        p.graphLine(new Line(0.25, 2, 3));
        p.graphLine(new Line(-2, 1, 50));
        g.apply(p);
        g.print();

    }
}
