package toolkit;

import java.util.Arrays;
import java.util.HashMap;

public class Plane {
    boolean[][] plotted;

    public Plane(int length, int height) {
        plotted = new boolean[height][length];
    }

    public Plane graphLine(Line line) {

        // Shortcut to speed up rendering lines with a linear slope of zero.
        if (line.a == 0 && line.b == 0) {
            for (int i = 0; i < this.plotted[0].length; i++) {
                this.setSlotAt(i, (int) line.c, true);
            }
            return this;
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0 /* x axis */; i < plotted[0].length; i++) {
            int[] placement = {i, (int) line.calculateY(i)};

            if (placement[1] >= plotted.length) {
                // Fill in missing chars
                for (int j = Math.min((int) line.calculateY(i - 1), plotted.length); j < plotted.length; j++) {
                    this.setSlotAt(i - 1, j, true);
                }
            }

            // Do not attempt to render points outside of the frame.
            if (!(fitsWithinRange(placement[1], 0, plotted.length)
                    && fitsWithinRange(placement[0], 0, plotted[0].length))) {
                continue;
            }

            // Store point data.
            map.put(placement[0], placement[1]);

            // Draw from bottom of visible border to first rendered point.
            if (line.calculateY(i) > 0 && line.calculateY(i + 1) < 0) {
                for (int j = Math.max((int) line.calculateY(i + 1), 0); j < line.calculateY(i); j++) {
                    this.setSlotAt(i, j, true);
                }
            }

//            if (placement[1] > 0 && line.calculateY(i - 1) < 0) {
//                int j = Math.min(Math.max((int) line.calculateY(i - 1), 0), placement[1]);
//                while (j < Math.max(Math.max((int) line.calculateY(i - 1), 0), placement[1])) {
//                    this.setSlotAt(i - 1, j, true);
//                    j++;
//                }
//            }

            // Render missing chars to visually connect two points with a (slope < 1).
            if (placement[1] > 0 && line.b != 0 && i > 0) {
                int j = Math.min(Math.max((int) line.calculateY(i - 1), 0), placement[1]);
                while (j < Math.max(Math.max((int) line.calculateY(i - 1), 0), placement[1])) {
                    if (j >= this.plotted.length) {
                        j++;
                        continue;
                    }
                    this.setSlotAt(i - 1, j, true);
                    j++;
                }
            }
            this.setSlotAt(placement[0], placement[1], true);
        }
        return this;
    }

    /**
     * Used for debugging.
     */
    public void print() {
        StringBuilder buffer = new StringBuilder();
        Arrays.stream(this.plotted).forEach((n) -> {
            for (boolean b : n) {
                buffer.append(b ? Grid.Symbols.ROAD.representation : Grid.Symbols.EMPTY.representation);
            }
            buffer.append("\n");
        });
        System.out.print(buffer);
    }

    private void setSlotAt(int x, int y, boolean replacement) throws IndexOutOfBoundsException {
        this.plotted[this.plotted.length - y - 1][x] = replacement;
    }

    private boolean fitsWithinRange(int checked, int min, int max) {
        return checked >= min && checked < max;
    }
}
