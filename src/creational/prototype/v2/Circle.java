package creational.prototype.v2;

import java.util.ArrayList;
import java.util.List;

public class Circle implements Shape {

    private int x, y;
    private List<Integer> points;

    public Circle(int x, int y, List<Integer> points) {
        this.x = x;
        this.y = y;
        this.points = points;
    }

    @Override
    public Shape clone() {
        List<Integer> copiedPoints = new ArrayList<>(this.points); // 깊은 복사
        return new Circle(this.x, this.y, copiedPoints);
    }

    public void addPoint(int p) {
        points.add(p);
    }

    public void show() {
        System.out.println("x=" + x + ", y=" + y + ", points=" + points);
    }
}
