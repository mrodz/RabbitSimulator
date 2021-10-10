package toolkit;

import java.util.Arrays;
import java.util.function.UnaryOperator;

public class Grid {
    enum Symbols {
        EMPTY(' '),
        ROAD('#'),
        RABBIT('@'),
        RABBIT_BABY('^'),
        COYOTE('&');

        char representation;
        Symbols(char representation) {
            this.representation = representation;
        }
    }

    int length;
    int height;

    private final char[][] grid;

    public Grid(int length, int height) {
        this.length = length;
        this.height = height;
        this.grid = ((UnaryOperator<char[][]>) (matrix) -> {
            for (char[] chars : matrix) {
                Arrays.fill(chars, Symbols.EMPTY.representation);
            }
            return matrix;
        }).apply(new char[height][length]);

    }

    public String rawValue() {
        StringBuilder buffer = new StringBuilder();
        Arrays.stream(grid).forEach((n) -> {
            for (char c : n) {
                buffer.append(c);
            }
            buffer.append("\n");
        });
        return buffer.deleteCharAt(buffer.length() - 1).toString();
    }

    public String boxedValue() {
        String value = rawValue();

        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < value.replaceAll("[\n].*", "").length() + 2; i++)
            buffer.append('-');

        return String.format("%s%n|%s|%n%s", buffer, value.replaceAll("[\n]", "|\n|"), buffer);
    }

    public void print() {
        System.out.println(boxedValue());
    }

    public void apply(Plane plane) {
        for (int i = 0; i < plane.plotted.length; i++) {
            for (int j = 0; j < plane.plotted[i].length; j++) {
                if (plane.plotted[i][j]) this.grid[i][j] = Symbols.ROAD.representation;
            }
        }
    }

    /**
     * Set a char at a specific index using standard graphing notation (0,0 is the bottom left of a conceptualized
     * array, instead of the top left).
     * @param x the x index rawValue
     * @param y the y index rawValue
     * @param replacement the character to be inserted into said position.
     */
    public void setCharAt(int x, int y, char replacement) throws IndexOutOfBoundsException {
        this.grid[this.grid.length - y][x] = replacement;
    }

    /**
     * Get this grid's size in the format [x, y] using standard graphing notation.
     * @return the correct dimension.
     */
    public int[] getDimensions() {
        return new int[] {this.grid[0].length, this.grid.length};
    }

    public int getLength() {
        return length;
    }

    public int getHeight() {
        return height;
    }
}