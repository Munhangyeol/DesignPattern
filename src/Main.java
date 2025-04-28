
import creational.prototype.v2.Circle;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws NumberFormatException, IOException {

        List<Integer> initialPoints = new ArrayList<>();
        initialPoints.add(1);
        initialPoints.add(2);

        Circle original = new Circle(10, 20, initialPoints);
        Circle cloned = (Circle) original.clone();

        cloned.addPoint(3);

        original.show(); // x=10, y=20, points=[1, 2]
        cloned.show();   // x=10, y=20, points=[1, 2, 3]
    }

}
