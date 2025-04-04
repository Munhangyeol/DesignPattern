
## 🫛SingleTon Pattern

### 개념

싱글톤 패턴은 한 어플리케이션에서 하나의 객체만 생성하게 해서 불필요한 리소스의 낭비를 줄일 수 있게 하는 패턴이다.

스프링 컨테이너는 객체들을 빈으로 관리하며, 이 빈을 싱글톤으로 관리한다.

스프링 컨테이너가 빈을 싱글톤으로 관리하므로 얻는 이점은 아래와 같다.

- 각 요청에 대해서 객체를 그 때 그 때 생성한다면, 메모리 사용량이 매우 올라갈 것인데, 이를 싱글톤으로 막아서 불필요한 리소스의 낭비를 줄인다.
- 또한 객체를 생성하고, 사용하지 않는 객체에 대해서는 GC가 동작하는데, 객체가 많아짐에 따라서 GC가 그만큼 많은 일을 해야한다.
    - 싱글톤은 이를 막아준다.
 


### 구현

```java

public class MySingleton {
    private static final MySingleton instance = new MySingleton();

    private MySingleton() {

    }

    public static MySingleton getInstance() {
        return instance;
    }

}

```

싱글톤은 3가지의 원칙을 지켜야한다.

- 생성자가 private으로 선언되어서, 외부에서 선언되지 못하게 한다.
- 멤버 해당 클래스인 멤버변수는 static으로 선언되어야한다.(전체에서 하나뿐이니까)
- static메서드인 getInstance를 통해서 인스턴스를 반환해서 인스턴스와의 접점을 만든다.

하지만 위의 구현은 인스턴스를 사용하기 이전에 미리 new MySingleton();를 통해서 생성하므로, 굳이 생성을 해둬야 하나 싶다.

그래서 아래처럼 ```Lazy Initiailization``` 전략을 취할 수 있다.

```java
package creational.singleton;

public class MySingleton {
    private static  MySingleton instance ;

    private MySingleton() {

    }

    public static MySingleton getInstance() {
        if(instance==null)instance=new MySingleton();
        return instance;
    }

}

```

이처럼 필요할 때만 instance를 생성해서 불필요한 리소스 낭비를 줄일 수 있다.

하지만 이렇게 되면 동시에  getInstance()를 호출하게 된다면, 하나의 인스턴스만 나오는 싱글톤이 위배될 수 있다.

그렇기에 

```java
package creational.singleton;

public class MySingleton {
    private static  MySingleton instance ;

    private MySingleton() {

    }

    public static syncronized MySingleton getInstance() {
        if(instance==null)instance=new MySingleton();
        return instance;
    }

}

```

syncronized 를 통해서 임계영역을 형성해서 오직 하나의 쓰레드만 접근 가능하게 해준다.

그러나 ```syncronized```의 경우 비용이 크기 때문에 getInstance가 있을 때마다 임계구역을 설정시 성능이 떨어진다.


### Bill Pugh Singleton Implementaion

inner class를 이용한 **Bill Pugh Singleton Implementaion** 방식을 이용하면 위 문제를 해결할 수 있다.

```java
public class MySingleton_V3 {

    private MySingleton_V3() {

    }

    private static class MySingletonHelper {
        private static MySingleton_V3 instance = new MySingleton_V3();
    }

    public static MySingleton_V3 getInstance() {
        return MySingletonHelper.instance;
    }

}

```

`static` 내부 클래스를 이용하면, `getInstance()`가 호출되기 전까지 `MySingletonHelper` 클래스는 메모리에 로드되지 않는다.

`getInstance()`가 처음 호출되는 순간 `MySingletonHelper`가 로드되며, 이 시점에 `MySingleton_V3` 인스턴스가 생성된다.

`MySingletonHelper`는 JVM에 의해 **클래스 로딩 시 단 한 번만 초기화되므로**, 이 초기화 과정은 **Thread-safe**하게 보장된다.

이게 가능한 것은 변수가 메모리에 올라가는 타이밍 때문이다.

클래스 변수(static)도 인스턴스 변수(non-static)도 "사용될 때" 메모리에 올라간다

이게 무슨 말이냐하면

인스턴스 변수일 때는 아래와 같이 new 시점에 heap메모리로 올라가고,

`Test t=new Test();`

static 변수 일 때는 위처럼 `MySingletonHelper.instance;` 로 접근할 때 static 영역에 올라가게 된다는 말이다.

## JVM+

```java
public class Test {
    static int a; // 초기값 없이 선언
}
```

```java
public class Main {
    public static void main(String[] args) {
        System.out.println(Test.a);
    }
}
```

이 경우에는 

**main()실행→ Test.a 접근 → 실제 a=0의 값이 static 영역에 올라감→ 출력** 순으로 실행된다.

JVM은 로딩, 링크 초기화의 과정을 통해서 클래스의 정보들을 읽는다.

로딩은 .class를 읽고, 링크는 값들에 초기값(int →0, boolean false)을 세팅해주고 클래스가 올바른지 검증한다. 초기화단계에서 비로소 실제 데이터를 올린다.

앞서 설명했듯이,

인스턴스 변수는 `new` 연산자를 통해 **객체를 생성**하는 시점에 초기화되며,

`static` 변수는 해당 클래스에 `static` 요소(변수/메서드 등)로 접근하는 순간 **클래스 초기화**가 트리거되어 초기화된다.


각 변수들이 실제로 메모리에 로드되는 시점을 알아두자


##### 참고
https://readystory.tistory.com/116

https://ittrue.tistory.com/550
