package app;

import annotations.RunningInstance;
import external.LogMessage;
import toolkit.Grid;
import toolkit.Line;
import toolkit.Plane;
import toolkit.RoadBuilder;

import java.util.Arrays;

public class Main {

    @RunningInstance
    public static void main(String[] args) {
        try {
            Grid g = new Grid(50, 15);
            Plane p = new RoadBuilder(g.getLength(), g.getHeight()).getRoad();
            g.apply(p);
            g.print();
//        g.apply(new Plane(g.getLength(), g.getHeight()).graphLine(new Line(10)));
//        g.print();
        } catch (Throwable e) {
            new LogMessage(e + " | " + Arrays.toString(e.getStackTrace()), 5, System.err);
        }
    }
}
