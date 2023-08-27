import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

import static org.junit.Assert.*;

public class VentFieldTest {
    @Test
    public void testCoverage() {
        // Reading in the test data from the "input1.txt" file
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("input1.txt");
        assertNotNull("Data not found", inputStream);

        try (InputStreamReader reader = new InputStreamReader(inputStream)) {
            VentField ventField = new VentField(reader);

            // Calling the coverage() method
            Map<Point, Integer> coverageMap = ventField.coverage();

            int expectedTotalPoints = 0;

            for (int count : coverageMap.values()) {
                if (count >= 2) {
                    expectedTotalPoints++;
                }
            }
            assertEquals(expectedTotalPoints, coverageMap.size());
        } catch (IOException e) {
            fail("Error reading the file: " + e.getMessage());
        }
    }

    @Test
    public void createImage() {
    }
}