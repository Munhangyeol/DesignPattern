## 개념

전략 패턴(Strategy Pattern)은 런타임 중에  전략(알고리즘, 동작 방식)을 동적으로 선택하여 객체의 동작을 변경할 수 있는 디자인 패턴이다.

여기서 전략이란, **객체가 특정 메서드를 통해 어떤 방식으로 동작할지를 결정하는 알고리즘**을 의미한다.

전략 패턴을 사용하면 새로운 기능이 추가될 때 **객체가 사용할 전략 객체만 교체**하면 되므로, 클라이언트 코드를 수정하지 않고도 동작을 변경할 수 있다. 이를 통해 OCP(개방-폐쇄 원칙)을 준수할 수 있다.

또한, 각 객체의 상세한 동작 방식을 유사한 역할을 하는 여러 클래스로 분산해서 관리하지 않고, **전략 클래스를 분리해 관리**함으로써 중복 코드를 줄이고, SRP(단일 책임 원칙)를 지킬 수 있다.

이러한 구조는 유지보수가 쉬우며, 새로운 전략을 추가하거나 기존 전략을 수정하기도 용이하다.

## 구조
![](https://velog.velcdn.com/images/msw0909/post/be3f4b6c-390b-4df0-9122-ce0e7096f401/image.png)


기본 구조는 위처럼 전략을 사용하는, Context클래스, 전략 인터페이스, 인터페이스를 구체적으로 구현한 StrategyA,StrategyB가 있다.

```java
public interface IStrategy {
    void play();
}
```

```java
public class Context {
    IStrategy strategy;

    void setStrategy(IStrategy strategy){
        this.strategy=strategy;
    }
    void play(){
        this.strategy.play();
    }
    
}
```

```java

public class AStrategy implements Istrategy{

    @Override
    public void play() {
        System.out.println("Play A");
    }
    
}

```

```java
public class BStrategy implements IStrategy{

    @Override
    public void play() {
        // TODO Auto-generated method stub
        System.out.println("play B");
    }
    
}
```

위의 구현을 통해서 런타임 시점에서 A전략을 사용할 지 B전략을 사용할 지를 결정 후, 이를 Context에 주입 후 사용이 가능하다. C전략을 추가하는 경우에도 인터페이스를 구현하는 형태로 손쉽게 구현이 가능하고, OCP를 지킬 수 있다. 

------

## setter vs생성자 주입

Spring 같은 DI컨테이너의 경우 필드의 불변성과 의존성이 명확해서 생성자 주입을 이용해서 전략 패턴을 구현한다.

```java
public interface DiscountPolicy {
    int discount(int price);
}
```

```java
@Component("rateDiscountPolicy")
public class RateDiscountPolicy implements DiscountPolicy {
    @Override
    public int discount(int price) {
        return (int)(price * 0.1); // 10% 할인
    }
}
```

```java
@Component("fixDiscountPolicy")
public class FixDiscountPolicy implements DiscountPolicy {
    @Override
    public int discount(int price) {
        return 1000; // 고정 1000원 할인
    }
}
```

```java
public class OrderService {
    private final DiscountPolicy discountPolicy;

    public OrderService(DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }
    
    public int calculateDiscount(int price) {
        return discountPolicy.discount(price);
    }
}
```

위와 같은 코드는 일반적으로 OCP(개방-폐쇄 원칙)를 잘 지킨 전략 패턴의 구현 예시이다.

이를 통해 다양한 `DiscountPolicy` 구현체를 추가하더라도, 기존 코드를 수정할 필요 없이 주입만으로 기능을 확장할 수 있다.

하지만 Spring 프레임워크처럼 **불변성과 의존성 주입(Dependency Injection)을 중시하는 환경**에서는,

런타임 시점에 전략을 유연하게 변경하는 전략 패턴의 장점을 **완전히 활용하기에는 제약**이 존재한다.

이는 객체 생성과 주입이 대부분 **애플리케이션 시작 시점에 완료**되며,

그 이후에는 전략을 동적으로 바꾸기 어려운 구조이기 때문이다.

(근본적으로 setter를 잘 열어두지 않으니…)

----

## 응용

현재 아래와 같은 클래스 다이어그램의 코드가 존재한다.

![](https://velog.velcdn.com/images/msw0909/post/359bd9bc-847d-4342-93db-c2bbb8b18542/image.png)

```java

public interface Vehicle {
    void move();  
} 

public class Train implements Vehicle{
    @Override
    public void move() {
        System.out.println("기차가 원형 바퀴로 움직입니다!");
    }
    
}

public class Car implements Vehicle{

    @Override
    public void move() {
        System.out.println("자동차가 원형 바퀴로 움직입니다!");
    }
    
}

public class Bus implements Vehicle{

    @Override
    public void move() {
        // TODO Auto-generated method stub
        System.out.println("버스가 원형 바퀴로 움직입니다!");
    }
    
}
```

위처럼 이동수단을 구현하는 Train,Car,Bus를 구체 클래스로 만들고 이에 따라서 move메서드를 정의해야한다.

이 구조 속에서, 숫자 1,2,3을 입력 받고, 각각 Car,Bus,Train에 대한 move()를 실행한다고 가정해보자.

Main클래스에서는 다음과 같은 구조로 코드를 짤 수 있다.

```java
public class Main {

    static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    static Vehicle vehicle;
    public static void main(String[] args) throws NumberFormatException, IOException {
        decideVehicle(Integer.parseInt(br.readLine()));
       
        vehicle.move();
        
    }
    static void decideVehicle(int decideNum){
        switch (decideNum) {
            case 1:
                vehicle=new Car();
                break;
            case 2:
                vehicle=new Bus();
                break;
            case 3:
                vehicle=new Train();
                break;
        }

    }
    
} 
```

  언뜻 보면 매우 괜찮은 코드 처럼 보인다. 하지만 다음과 같은 문제점을 내포한다.

1. Main 클래스에서 입력받고, 출력하는 책임 이외에, 어떤 이동수단을 탈 것 인지를 고르는 책임을 같이 가지고 있다.
2. Main 클래스내에서 구체 클래스를 의존하고 있다. 
    
    → 1,2: 새로운 이동 수단이 추가되었을 때 Main 클래스의 코드를 변경해야한다. (OCP를 위반하는 행위)
    
3. 새로운 기능이 추가되었을 때, 클래스의 갯수가 많아진다.
    1. 예를 들어서 원형 바퀴가 아닌 별모양 바퀴, 세모 모양 바퀴, 네모 모양 바퀴가 추가된다면 이에 맞게 클래스를 새로 구현해야한다.(3x4)

위의 문제들은 모두 전략 패턴과 팩토리 메서드 패턴을 통해서 해결이 가능하다.

먼저 1의 이동수단을 어떤 것으로 고를지에 대한 책임은 팩토리 메서드 패턴을 통해서 아래와 같이 만들 수 있다.

```java
public class VerhicleFactory {

    public static Vehicle creatVehicle (int select){
        switch (select) {
            case 1:
                return new Car();
            case 2:
                return new Bus();
            case 3:
                return new Train();
                
        }
        throw new RuntimeException("존재하지 않는 이동 수단입니다.");
    }
    
}
```

우리가 흔히 아는 Factory를 인터페이스로 만들고 이를 구현하는 구체 클래스 Factory에서 클래스를 만드는 방식이 아닌 위처럼 바로 팩토리 클래스를 만들어서 분기 처리하였다.

이 이유는 각 이동수단의 생성 전 처리,후 처리가 다르지 않고,거의 없기 때문이다.

```java
public class Main {

    static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    static Vehicle vehicle;
    public static void main(String[] args) throws NumberFormatException, IOException {
        vehicle=VerhicleFactory.creatVehicle(Integer.parseInt(br.readLine()));
        vehicle.move();
        
    }
}
```

Main 에서는 따라서 분기 처리 하지 않고 다음과 같이 간단하게 처리할 수 있다.

원형 바퀴가 아닌 별모양 바퀴,  네모 모양 바퀴가 추가된다면 아래처럼 전략 패턴을 구현할 수 있다.

```java
public interface ShapeStrategy {
    ShapeEnum getShape();
} 

public class SquareShapeStrategy implements ShapeStrategy{

    @Override
    public ShapeEnum getShape() {
        // TODO Auto-generated method stub
        return ShapeEnum.네모;
    }
    
}

public class Bus implements Vehicle{

    private ShapeStrategy shape;
    

    @Override
    public void move() {
        // TODO Auto-generated method stub
        System.out.println("버스가 "+shape.getShape()+" 모양의 바퀴로 움직입니다!");
    }

    @Override
    public void setShape(ShapeStrategy shape) {       
        this.shape=shape;

    }
    
}
```

이를 통해서 Shape에 대한 책임과 Vehicle에 대한 책임을 나누어서 유지보수 관점에서 훨씬 낫다.

또한 각 전략을 인터페이스로 필드를 정의해서, OCP또한 지킬 수 있고,

shape을 동적인 상황에 따라서 정할 수도 있게 했다.

구체적으로 설명하면 아래와 같다.

1. **`ShapeStrategy` 인터페이스**를 도입해서 **바퀴 모양 결정 책임을 분리**
    
    → Vehicle은 바퀴의 모양이 어떻게 생겼는지 몰라도 되고, 그냥 `shape.getShape()`만 호출
    
2. **전략 객체를 런타임에 주입 가능하게 설계**
    
    → `vehicle.setShape(new StarShapeStrategy())`처럼 **동적으로 바퀴 전략 변경 가능**
    
3. **OCP (Open-Closed Principle)** 만족
    - 새로운 바퀴 모양을 추가할 때도 기존 코드를 수정할 필요 없이,
    - `ShapeStrategy` 구현체만 새로 만들면 됨
    - 예: `StarShapeStrategy`, `TriangleShapeStrategy` 등

shape의 결정을 Factory에서 할까도 생각을 해봤지만, 동적으로 변화한다면, 객체의 생성시점이 아닌 다른 시점에서 주입해줘야 하므로,  구체적인 비즈니스 로직에 맞게 적용하면 될 것 같다.

----
## 결론

전략 패턴을 적용함으로써 SRP와 OCP를 지키며 유지보수에 유리한 구조를 만들 수 있고,

런타임 중 전략을 교체하는 등 동적인 상황에 유연하게 대처할 수 있다.

다만 전략을 동적으로 설정하는 구조에서는 setter를 통한 주입이 필요한데,

이 경우 전략이 의도대로 동작하도록 관리하는 책임이 추가로 필요하다.

만약 전략이 고정된 상황이라면, 생성 시점에 전략을 설정하는 팩토리 패턴과의 조합이 더 적합할 수 있다.


#### 참고자료
https://inpa.tistory.com/entry/GOF-%F0%9F%92%A0-%EC%A0%84%EB%9E%B5Strategy-%ED%8C%A8%ED%84%B4-%EC%A0%9C%EB%8C%80%EB%A1%9C-%EB%B0%B0%EC%9B%8C%EB%B3%B4%EC%9E%90

https://inpa.tistory.com/entry/JAVA-%E2%98%95-%EC%97%B4%EA%B1%B0%ED%98%95Enum-%ED%83%80%EC%9E%85-%EB%AC%B8%EB%B2%95-%ED%99%9C%EC%9A%A9-%EC%A0%95%EB%A6%AC![](https://velog.velcdn.com/images/msw0909/post/e3e17dcc-1fba-4394-b4b4-09a853ecb4c0/image.png)
