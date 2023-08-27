import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VentField {
    private final List<Vent> vents;

    public VentField(InputStreamReader inputStreamReader) throws IOException {
        vents = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(inputStreamReader)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] coordinates = line.split("->");
                String[] fromCoords = coordinates[0].split(",");
                String[] toCoords = coordinates[1].split(",");

                int fromX = Integer.parseInt(fromCoords[0].trim());
                int fromY = Integer.parseInt(fromCoords[1].trim());
                int toX = Integer.parseInt(toCoords[0].trim());
                int toY = Integer.parseInt(toCoords[1].trim());

                Vent vent = new Vent(new Point(fromX, fromY), new Point(toX, toY));
                vents.add(vent);
            }

        }
    }

    public Map<Point, Integer> coverage() {
        Map<Point, Integer> coverageMap = new HashMap<>();

        for (Vent vent : vents) {
            vent.pointsBetween().forEach(point -> coverageMap.merge(point, 1, Integer::sum));
        }
        return coverageMap;
    }

    public BufferedImage createImage() {
        int maxWidth = vents.stream()
                .mapToInt(v -> Math.max(v.from().xCoord(), v.to().xCoord()))
                .max()
                .orElse(0);
        int maxHeight = vents.stream()
                .mapToInt((v -> Math.max(v.from().yCoord(), v.to().yCoord())))
                .max()
                .orElse(0);

        BufferedImage image = new BufferedImage(maxWidth + 1, maxHeight + 1, BufferedImage.TYPE_INT_RGB);

        vents.stream()
                .flatMap(Vent::pointsBetween)
                .toList()
                .forEach(point -> {
                    int color = Color.RED.getRGB();
                    image.setRGB(point.xCoord(), point.yCoord(), color);
                });
        return image;
    }
}
