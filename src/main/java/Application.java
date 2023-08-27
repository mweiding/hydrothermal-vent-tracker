import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

public class Application {

    public static void main(String[] args) throws IOException {
        VentField vents = new VentField(new InputStreamReader(VentField.class.getClassLoader().getResourceAsStream("input1.txt")));
        final Map<Point, Integer> coverage = vents.coverage();

        try {
            File file = new File("Output.png");
            ImageIO.write(vents.createImage(), "png", file);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

}
