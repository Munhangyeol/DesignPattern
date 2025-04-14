---

## 개념

Factory Method 패턴을 통해서 객체의 생성로직을 위임한 바 있다. 

당시의 로직에서는 

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

처럼 하나의 팩토리가 하나의 제품만 생성한다.

하지만 만약 각 팩토리에서 제품군 즉, 여러 제품들을 생성해야 한다면 어떨까?

그리고 이 제품군은 다른 공장에서도 생성된다면? 어떻게 로직을 작성할 수 있을까?

이러한 경우에는 아래의 구조처럼 하나의 Factory에서 두개 이상의 제품을 생성하는 메서드를 만들어두면 될 것이다. 

![](https://velog.velcdn.com/images/msw0909/post/a73c05d4-8bec-4911-8138-4ff68202fdab/image.png)


이렇게 각 팩토리가 여러 **제품군의 집합**을 만들 수 있는 패턴이 **Abstract Factory** 패턴이다. 

Factory Method와 Abstract Method 패턴은 둘 다, 객체 생성 과정을 추상화한 인터페이스를 제공하고, 이 인터페이스를 구현한 클래스에서 DIP를 지켜서, 추상 객체에 의존해서 느슨한 결합 구조를 표방한다.

 

하지만 위에서 본 바와 같이 Factory Method와는 각 Factory가 생성하는 객체의 갯수의 차이가 존재한다.

전자는 이름 그대로 하나의 메서드가 하나의 팩토리 역할을 하며, 후자는 클래스 레벨에서 팩토리 역할을 한다. 

이것이 의미하는 건 다음과 같다.

- 팩토리 클래스 내부에서 하는 생성 과정이 복잡하다면, 팩토리 메서드 패턴을 이용해서, 책임을 줄일 수 있고,
- 크게 복잡하지 않고, 각 팩토리가 가지는 제품군을 생성하고 싶다면, 추상 팩토리 패턴을 이용하는 것이 옳다.

-----



## 구조 및 구현

![](https://velog.velcdn.com/images/msw0909/post/952072b7-099f-45d6-9176-084b53863bcf/image.png)


위처럼 Factory1에서 생산되는 제품군 A,B와,

Factory2에서 생산되는 제품군 A,B을 즉, **서로 호환되는 제품군**을 묶어서 생성하는 패턴

**→ 추상 팩토리 패턴의 전형적인 구조이다.**

### 팩토리 코드

```java
public interface AbstractFactory {

    IProductA createProductA();
    IProductB createProductB();
} 

public class Factory1 implements AbstractFactory{

    @Override
    public IProductA createProductA() {
        return new ProductA1();
    }

    @Override
    public IProductB createProductB() {
        return new ProductB1();
    }

    
    
}

public class Factory2 implements AbstractFactory{

    @Override
    public IProductA createProductA() {
        return new ProductA2();
    }

    @Override
    public IProductB createProductB() {
       return new ProductB2();
    }

    
}
```

AbstractFactory를 구현하는 Factory1,2를 만들고, 이 내부에서 제품 A,B를 만드는 메서드를 구현한다.

### Product 코드

```java
public interface IProductA {
    
}

public class ProductA1 implements IProductA{
    
}
public class ProductA2 implements IProductA{
    
}

```

B도 위와 마찬가지로 설정 가능하다. 

이러한 구조를 통해서 ProductA3를 생성시, 팩토리 코드에서의 코드를 수정하지 않고, AbstractFactory를 구현하는 Factory3를 생성하는 것을 통해서 구현이 가능하다.

즉 OCP를 위배하지 않고 코드를 짤 수 있어서 보다 나은 유지보수성을 고수 할 수 있다. 


------


## 응용

기존의 코드를 통해서 한번 살펴보자

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

기존의 코드는 A공장에서 A만 생산하니까 크게 문제가 되지않는다.

A공장에서 a를 생산하고자한다.→ 여기서 선택할 수 있는 것이 바로 추상 팩토리로의 확장이다.

```java
public abstract class Factory {
    protected abstract IProduct createProductLarge(String name, int price);
    protected abstract IProduct createProductSmall(String name, int price);
}
```

Factory를 위와 같이 리팩토링하여서, 하나의 팩토리에서 여러 제품군을 생성할 수 있게 설계한다. 

```java
public class FactoryA extends Factory{
     @Override
    protected IProduct createProductLarge(String name, int price) {
        name = "[A-Type Large] " + name;
        price += 1000;
        System.out.println("FactoryA 전처리 완료: 이름 수정, 가격 +1000");
        return new ProductA(name, price);
    }

    @Override
    protected IProduct createProductSmall(String name, int price) {
        name = "[A-Type Small] " + name;
        price += 500;
        System.out.println("FactoryA 전처리 완료: 이름 수정, 가격 +500");
        return new Producta();
    }
}

```

이렇게 A,a 객체를 생성하는 FactoryA로 리팩토링할 수 있다. 

또한 각 팩토리를 전략패턴을 통해서도 할당이 가능할 것이다.

상황에 따라서 사용하는 factory를 달리해서 아래와 같이도 사용이 가능하다.

```java
public class Main {

    public static void main(String[] args) {

        
        AbstractFactory factory=null;
        //공장 1에서 productA를 생산하자.
        factory=FactorySelector.selectFactory(FactoryType.EFACTORY1);
        System.out.println(factory.createProductA().getClass().getName());

        //공장 2에서 proudctB를 생산하자.
        factory=FactorySelector.selectFactory(FactoryType.EFACTORY2);
        factory=new Factory2();
        System.out.println(factory.createProductB().getClass().getName());  
    }

    
    
}

public class FactorySelector {
    public static AbstractFactory  selectFactory(FactoryType type){
           switch (type) {
            case EFACTORY1:
                return new Factory1();
            case EFACTORY2:
                return new Factory2();
            default:
                throw new IllegalArgumentException("Unknown factory type: " + type);
        }
    }
    
}

```

FactorySelector 가 Factory 생성 책임을 가지게 되면서, Main 클래스에서는 공장 선택 로직이 사라지고 필요한 제품군만 요청하면 되는 구조가 된다.

이렇게 하면 Main 은 생성 과정에 대해 몰라도 되고, FactorySelector 가 전략적으로 적절한 Factory 를 선택하여 제공하므로 **관심사 분리가 잘 이루어진 설계**가 된다.

결과적으로 코드의 유지보수성이 좋아지고, 새로운 Factory 가 추가되어도 Main 코드를 수정하지 않고 확장할 수 있어 **OCP (Open-Closed Principle)** 도 만족하는 깔끔한 구조가 완성된다.


#### 참고 자료
https://inpa.tistory.com/entry/GOF-%F0%9F%92%A0-%EC%B6%94%EC%83%81-%ED%8C%A9%ED%86%A0%EB%A6%ACAbstract-Factory-%ED%8C%A8%ED%84%B4-%EC%A0%9C%EB%8C%80%EB%A1%9C-%EB%B0%B0%EC%9B%8C%EB%B3%B4%EC%9E%90![](https://velog.velcdn.com/images/msw0909/post/a7801965-0f43-4651-9653-f1c9d14bf8d5/image.png)
