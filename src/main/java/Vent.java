import java.util.stream.IntStream;
import java.util.stream.Stream;

public record Vent(Point from, Point to) {

    public Vent {
        assert from.xCoord() == to.xCoord() || from.yCoord() == to.yCoord() || Math.abs(from.xCoord() - to.xCoord()) == Math.abs(from.yCoord() - to.yCoord())
                : "Invalid Coordinates for Vent";
    }

    public Stream<Point> pointsBetween() {
        int startX = from.xCoord();
        int startY = from.yCoord();
        int endX = to.xCoord();
        int endY = to.yCoord();

        int stepX = Integer.compare(endX, startX);
        int stepY = Integer.compare(endY, startY);

        int deltaX = Math.abs(endX - startX);
        int deltaY = Math.abs(endY - startY);
        int maxSteps = Math.max(deltaX, deltaY);

        return IntStream.rangeClosed(0, maxSteps)
                .mapToObj(i -> new Point(startX + i * stepX, startY + i * stepY));
    }
}
