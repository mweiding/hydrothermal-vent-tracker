import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Stream;

@RunWith(Parameterized.class)
public class VentTest {
    private final int startX;
    private final int startY;
    private final int endX;
    private final int endY;
    private final int expectedCount;

    public VentTest(int startX, int startY, int endX, int endY, int expectedCount) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.expectedCount = expectedCount;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testCases() {
        return Arrays.asList(
                new Object[]{922, 902, 110, 90, 813},
                new Object[]{3, 7, 3, 2, 6}
        );
    }

    @Test
    public void pointsBetween() {
        Vent vent = new Vent(new Point(startX, startY), new Point(endX, endY));
        Stream<Point> points = vent.pointsBetween();
        int actualCount = (int) points.count();
        Assert.assertEquals(expectedCount, actualCount);
    }


}