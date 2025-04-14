## 개념

**팩토리 메소드** 패턴은 이름 객체의 생성을 담당하는 팩토리 클래스를 도입해서, 객체의 생성의 책임을 new가 아닌 이 팩토리에게 **위임한다.**

이를 통해서 객체가 생성될 때 해야 하는 전처리와 후처리를 팩토리 클래스에게 위임하면서,

 기존에는 생성될 객체의 클래스가 가지고 있어야 할 책임을 분리 시킬 수 있다.

이를 통해서 srp,ocp를 지키면서 객체를 생성할 때  훨씬 다양하고 유연하게 처리할 수 있다.

---

## 구현

코드를 먼저 보고 구조를 봐야 이해가 더 잘 될 것 같았다.

우선 아래처럼 Factory라는 추상 클래스를 만들어서, 공통된 기능인 ``제품을 만드는 기능``을  이곳에서 정의 및 구현한다.

구체적인 제품은 Factory를 구현하는 구체 클래스에서 구현하도록 책임을 위임한다. 

```java
package creational.factory;

abstract class Factory {

    final IProduct createProduct(String name,int price){
      IProduct iProduct=creatConcreteProduct(name,price);
      iProduct.initilize();
      return iProduct;

    }
    abstract protected IProduct creatConcreteProduct(String name,int price);
    
}

```

```java
public interface IProduct {
    void initilize();
}

```

Product역시 인터페이스로 선언해서 Factory 추상 클래스내의 `createProduct` 에서 어떤 제품인지를 판단하지 않아도 되어서, 책임의 분리가 보다 명확해 짐을 알 수 있다.

또한 Factory클래스의 변경없이 ProductC,ProductD또한 만들 수 있으므로, OCP를 잘 지킬 수 있음을 알 수 있다.

```java
public class ProductA implements IProduct{
    String name;
    int price;
    String category;

    public ProductA(String name,int price){
        this.name=name;
        this.price=price;

    }

    @Override
    public void initilize() {

        category="A";
    }
    
    
}

```

```java
public class ProductB implements IProduct{
    
    String name;
    int price;
    String category;

    public ProductB(String name,int price){
        this.name=name;
        this.price=price;
    }
    @Override
    public void initilize() {
        category="B";
    }
    
    
}

```

객체를 생성해주는 Factory 추상 클래스를 만들고, 이를 구체적으로 생성하는 구현을 통해서 각 제품의 생성에 관한 응집성을 높일 수 있다. 또한 추상클래스 이므로, 확장성 또한 높은 코드를 작성 할 수 있다.

A의 생성과 관련된 코드는 Factory A에만 있고, B의 생성과 관련된 코드는 Factory B에만 있다.

또한 Factory C를 만들때 기존의 코드를 바꾸지 않고, Factory 추상클래스를 상속받아서 가능하다.

```java
package creational.factory;

public class Main {
    public static void main(String[] args) {
        Factory[] factories={new FactoryA(),new FactoryB()};
        IProduct productA=factories[0].creatConcreteProduct("선풍기", 10000);
        IProduct productB= factories[1].creatConcreteProduct("짜장면", 30000);
    }
    
}
```

  팩토리 메서드를 이용해서 Main에서는 위처럼 사용이 가능하다. 

![](https://velog.velcdn.com/images/msw0909/post/dc0a49ff-b8ab-4bf5-94d3-910d914011c0/image.png)


대략적으로 이런 형태로 구조를 짜서, OCP,SRP를 명확하게 지킬 수 있다.

---

## 그래서 왜 써?

Factory Method 패턴을 사용하는 이유는 보다 명확한 것 같다.

- 생성의 책임과 비즈니스 로직의 책임을 나눠서 결합도를 낮추고자 할 때,
- 코드가 각 객체의 유형과 종속성을 캡슐화를 통해 정보 은닉을 하고자 할때,
- 중복을 제거하고, 객체 생성 방식의 변화에 따른 파급효과를 줄여서 리소스를 절약하고자 할 때 사용한다.

정리하자면, 팩토리 메서드 패턴은 SRP,OCP를 지켜서 생성자와 구현 객체의 low coupling을 유지할 수 있어서,  유지보수의 관점에서  좋은 것이다.

### 단점

각 제품 구현체마다 팩토리 객체들을 다 구현해야 하므로, 구현체가 많아지면 전체 클래스의 수가 많아져서 오히려 관리하기 힘들다는 단점이 존재한다. 


### 예시
각 서브 클래스들마다의 전처리, 후처리가 다른 경우, Factory Method패턴을 이용하면 보다 효과적으로 사용이 가능하다.

 

- `ProductA`는:
    - 이름 앞에 접두사 `[A-Type]`을 붙임
    - 가격에 +1000
    - 로그 출력
- `ProductB`는:
    - 이름 끝에 접미사 `(B 전용)`을 붙임
    - 가격에 -1000
    - 할인율(10%) 적용
    - 가격이 0 미만이면 예외 발생

인 복잡한 요구사항이있을 때 

```java
public class FactoryA extends Factory {

    @Override
    protected IProduct creatConcreteProduct(String name, int price) {
        name = "[A-Type] " + name;
        price += 1000;
        System.out.println("FactoryA 전처리 완료: 이름 수정, 가격 +1000");
        return new ProductA(name, price);
    }
}
```

```java
public class FactoryB extends Factory {

    @Override
    protected IProduct creatConcreteProduct(String name, int price) {
        name = name + " (B 전용)";
        price -= 1000;
        price *= 0.9; // 10% 할인
        if (price < 0) throw new IllegalArgumentException("가격은 0 이상이어야 합니다.");
        System.out.println("FactoryB 전처리 완료: 이름 수정, 가격 -1000 및 할인 적용");
        return new ProductB(name, price);
    }
}
```

- 더욱 복잡한 요구도, 단순하게 Factory클래스에서의 변경을 통해서 구현이 가능하다.
- 이를 통해서 객체가 가지는 정보의 저장 책임과 전처리 후처리 책임을 분리할 수 있다.
- 제품 생성에 대한 정책 변경이 필요한 경우 Factory 서브 클래스만 수정하면 됨 → **OCP 원칙 충족**

물론 이렇게 싱글톤으로도 구현이 가능하다 

```java

public class FactoryA extends Factory{

    static class FactoryAHelper{
        static FactoryA instance=new FactoryA();
    }

    static FactoryA getInstance(){
        return FactoryAHelper.instance;
    }

   @Override
    protected IProduct creatConcreteProduct(String name, int price) {
        name = "[A-Type] " + name;
        price += 1000;
        System.out.println("FactoryA 전처리 완료: 이름 수정, 가격 +1000");
        return new ProductA(name, price);
    }
}



public class FactoryB extends Factory{
    static class FactoryBHelper{
        static FactoryB instance=new FactoryB();
    }

    static FactoryB getInstance(){
        return FactoryBHelper.instance;
    }
    @Override
    protected IProduct creatConcreteProduct(String name, int price) {
        name = name + " (B 전용)";
        price -= 1000;
        price *= 0.9; // 10% 할인
        if (price < 0) throw new IllegalArgumentException("가격은 0 이상이어야 합니다.");
        System.out.println("FactoryB 전처리 완료: 이름 수정, 가격 -1000 및 할인 적용");
        return new ProductB(name, price);
    }
    
}

```

위처럼 구현을 통해서 싱글톤을 동시성 문제와 성능 모두 잡아서 해결할 수 있다.

---
#### 참고
https://inpa.tistory.com/entry/GOF-%F0%9F%92%A0-%ED%8C%A9%ED%86%A0%EB%A6%AC-%EB%A9%94%EC%84%9C%EB%93%9CFactory-Method-%ED%8C%A8%ED%84%B4-%EC%A0%9C%EB%8C%80%EB%A1%9C-%EB%B0%B0%EC%9B%8C%EB%B3%B4%EC%9E%90
