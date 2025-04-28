# Prototype pattern

## 개념

Spring 컨테이너를 이용하면 기본적으로 빈(Bean) 객체는 싱글톤(Singleton)으로 관리된다. 이 경우 하나의 객체 인스턴스를 여러 곳에서 공유하게 되어, 객체가 고유한 상태(state)를 가질 수 없다.

만약 각 객체가 별도로 생성되어 독립적인 상태를 가질 수 있도록 하고 싶다면, Prototype 패턴을 적용할 수 있다. Prototype 패턴은 객체를 복제하거나 새로 생성하여 매번 다른 인스턴스를 제공함으로써, 싱글톤과는 반대되는 특성을 가지게 해준다.

여기서 @Scope(prototype) 을 통해서 적용해 볼 수 있다

## 기본 코드

프로토타입은 기본적으로 아래와 같이 객체 내부에 복제를 하는 코드를 통해서 구현이 가능하다.

```java
public class Circle implements Cloneable {

    int x, y, r;

    public Circle(int x, int y, int r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }

    public Circle createClone() {
        Circle c;
        try {
            c = (Circle) clone();
        } catch (CloneNotSupportedException ex) {
            throw new RuntimeException(ex);
        }
        return c;
    }

    @Override
    public String toString() {
        return "Circle [x=" + x + ", y=" + y + ", r=" + r + "]";
    }

}

  public static void main(String[] args) throws NumberFormatException, IOException {

        Circle c = new Circle(1, 1, 3);
        Circle cloneC = c.createClone();
        System.out.println(c);
        System.out.println(cloneC);
    }

```

여기서 `Cloneable` 인터페이스를 구현해서 `clone()`메서드를 이용할 수 있다.
`clone` 메서드는 위처럼 필드의 변수를 복사해서, 새로운 `Object`를 만들어준다.
하지만 clone메서드는 얕은 복사가 일어나므로 Circle클래스에서 가지고 있는 변수가 참조가 되는 경우에는 완전하게 복사가
되지 않는다.

따라서 이러한 경우에는 직접 구현해서 prototype을 구현해야한다.

```java

public class Circle implements Cloneable {

    int x, y, r;

        private List<Integer> points;


    public Circle(int x, int y, List<Integer> points) {
        this.x = x;
        this.y = y;
        this.points = points;
    }

    public Circle createClone() throws CloneNotSupportedException{
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

```

즉 위처럼 new ArrayList()를 통해서 새로운 List를 반환해서 지정해주면 된다.
이를 통해서 완전한 prototype 패턴을 구현할 수 있다.

## why use?

- new로 생성하는 비용이 크거나 복잡할 때 → 복제해서 빠르게 생성하고 싶을 때

- 각 객체가 독립적인 상태를 가져야 할 때![](https://velog.velcdn.com/images/msw0909/post/fb76cbfd-3bf6-4eb1-a959-c8c4a7a45605/image.png)

- 객체를 매번 새로 생성하지 않고 기존 상태를 복제해서 사용하고 싶을 때

## Gof Prototype

Gof에서의 프로토타입은 아래처럼 구체프로토타입 클래스가 프로토타입 인터페이스를 구현해서 사용 가능하다.
![](https://velog.velcdn.com/images/msw0909/post/b17d3e82-ab79-4954-bacb-76a728de87de/image.png)

```java
public interface Shape extends Cloneable {
    public Shape clone();
}

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

```

이를 통해, Shape 인터페이스를 구현하는 Circle, Rectangle, Triangle 등 다양한 도형 클래스들이 각자의 clone 메서드를 구현할 수 있으며,
새로운 도형이 추가되더라도 기존 코드를 수정할 필요 없이(OCP, Open-Closed Principle) 기능을 확장할 수 있게 된다.
