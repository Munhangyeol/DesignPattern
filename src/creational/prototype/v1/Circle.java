package creational.prototype.v1;

import java.util.ArrayList;
import java.util.List;

public class Circle implements Cloneable {

    int x, y, r;

    private List<Integer> points;

    public Circle(int x, int y, List<Integer> points) {
        this.x = x;
        this.y = y;
        this.points = points;
    }

    public Circle createClone() throws CloneNotSupportedException {
        Circle cloned = (Circle) super.clone();
        // 깊은 복사: 참조 타입 필드도 새로 복제
        cloned.points = new ArrayList<>(this.points);
        return cloned;
    }

    @Override
    public String toString() {
        return "Circle [x=" + x + ", y=" + y + ", r=" + r + "]";
    }

}
