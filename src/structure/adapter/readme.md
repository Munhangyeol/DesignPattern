## 개념
어댑터란 우리가 실생활에서 usb 어댑터, 충전 어댑터 등등으로 호환 되지 않는 제품을 사용하기 위해서 연결역할을 해주는 것을 의미한다. 
즉 호환되지 않는 제품을 **호환시켜주는** 개념이다.

이를 코딩의 관점으로 생각해본다면, 클라이언트에서 직접 사용할 수 없는, 객체 및 클래스를 사용할 수 있게 호환시켜주는 패턴이라고 생각하면 편할 것 같다.



## 객체 vs 클래스 어댑터
어댑터 패턴에는 크게 어댑티를 객체로 의존시켜서 구현하는 **객체 어댑터 패턴**, 어댑티를 상속하는 어댑터를 구현해서 만드는 **클래스 어댑터 패턴**이 존재한다.

### 객체 어댑터

- 객체가 객체를 의존 시켜서, 즉 **합성**시켜서 어댑터 패턴을 이용하는 방식이다.

- 합성을 이용했기 때문에, 런타임 시점에 Adaptee가 결정되어서 유연하게 코드를 짤 수 있다.(테스트 코드에서도 마찬가지이다)
  - 런타임 시점에 객체를 교체 가능하다는 점에서 약간 전략 패턴이랑 비슷한거 같기도 하다.
  - 하지만 목적이 전략 패턴은 행위 즉 메서드가 하는 일을 변경 시키려는 것이고, 어댑터는 인터페이스를 변환하는데 있다.
```java
// Main에서 직접사용할 수 없는 Service클래스를 Adapter클래스를 시용해서
// 사용할 수 있도록 일종의 연결 장치임.

public class Adapter implements Target {

    Service adaptee;

    public Adapter(Service adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void method(int data) {
        // TODO Auto-generated method stub
        adaptee.specificMethod(data);
    }

}

// Main에서 사용하고 싶은 클래스이지만, 현재는 호환이 안되어서 직접 사용할 수 없는 클래스
public class Service {

    void specificMethod(int data) {
        System.out.println("기존 서비스 기능 호출 + " + data);
    }
}


// 어댑터의 추상화 모듈
public interface Target {

    void method(int data);

}

```

### 클래스 어댑터
![](https://velog.velcdn.com/images/msw0909/post/592ac41f-5fe0-4e38-bf58-f71265fa0761/image.png)
객체 어댑터가 합성을 통해서 어댑터를 구현했다면 클래스 어댑터는 **상속**을 통해서 어댑터를 구현한다.

- DI를 하지 않아도, 어댑터를 구현할 수 있고, 객체 어댑터와 달리 컴파일 시점에서 어댑터의 관계가 결정된다.

- 객체 어댑터의 경우 여러 객체를 의존해서, 어댑터를 구현할 수 있지만, 클래스 어댑터의 경우, 클래스에 대한 다중 상속이 되지않는 자바의 특성때문에 불가능하다.
  
  
- 위의 두가지 특성 때문에 상대적으로 덜 유연한 어댑터 패턴이라고 여겨지므로, 객체 어댑터 패턴을 사용하는게 더 낫지 않나라고 생각한다. 


```java
// Main에서 직접사용할 수 없는 Service클래스를 Adapter클래스를 시용해서
// 사용할 수 있도록 일종의 연결 장치임.

public class Adapter extends Service implements Target {

    @Override
    public void method(int data) {
        // TODO Auto-generated method stub
        super.specificMethod(data);
    }

}

// Main에서 사용하고 싶은 클래스이지만, 현재는 호환이 안되어서 직접 사용할 수 없는 클래스
public class Service {

    void specificMethod(int data) {
        System.out.println("기존 서비스 기능 호출 + " + data);
    }
}

// 어댑터의 추상화 모듈
public interface Target {

    void method(int data);

}

```
## 실제 적용

어댑터 패턴은 호환이 안되는 객체들을 호환되게 해주는데 포커스를 맞춘다.
예를 들어서 다음과 같은 상황을 고려해보자.
```java
public class OlderPrinter {
    public void oldPrint(){
        System.out.println("old print");
    }
    
}
```
기존에 사용하던, ```OlderPrinter```를 사용할 때는 단순히, 이를 생성하고 사용하면 된다.
이 상황에서 ```NewPrinter```클래스들을 만들고, 이 클래스들이 가지고 있는 메서드인 ```newPrint```를 추상화해서, ```Printable```인터페이스를 만들자

```java
public interface Printable {

    void newPrint();
    
}

public class NewPrinter1 implements Printable{

    @Override
    public void newPrint() {
        // TODO Auto-generated method stub
        System.out.println("new print1");
    }
    
}

public class NewPrinter2 implements Printable{

    @Override
    public void newPrint() {
        // TODO Auto-generated method stub
        System.out.println("new print2");
    }
    
}
```

만든 ```newPrinter```들을 출력을 하자
```java

    public static void main(String[] args) throws NumberFormatException, IOException {

       List<Printable> printList=new ArrayList<>();
       printList.add(new NewPrinter1());
       printList.add(new NewPrinter2());

       for(Printable printer: printList){
        printer.newPrint();
       }
       
    }
```

여기서 ```olderPrinter```도 ```printList```넣어서 같이 출력을 하고자 한다면, 기존의 ```olderPrinter```는 레거시로써 ```Printable```을 구현하지 않았으므로, 호환되지 않아서 정상적으로 동작하지 않을 것이다.

이를 호환시키기 위해서 ```PrintAdapter```를 만들고 어댑터 패턴을 구현하자.
```java
public class PrintAdapter implements Printable{

    OlderPrinter printer;
    public PrintAdapter(OlderPrinter printer){
        this.printer=printer;

    }

    @Override
    public void newPrint() {
        // TODO Auto-generated method stub
        printer.oldPrint();
      
    }
    
}

    public static void main(String[] args) throws NumberFormatException, IOException {

       List<Printable> printList=new ArrayList<>();
       printList.add(new NewPrinter1());
       printList.add(new NewPrinter2());
       printList.add(new PrintAdapter(new OlderPrinter()));

       for(Printable printer: printList){
        printer.newPrint();
       }
       
    }
```
이를 통해서 기존의 코드를 변경하지 않고, 호환되게 바꿀 수 있어서 ```OCP```를 잘 지킬 수 있다.



## 어떨때 적용?

1. 레거시 코드와 새 코드가 인터페이스가 다를 때
	- 기존 시스템(Adaptee)을 바꾸지 않고 새 시스템(Target)에 끼워 맞춰야 할 때

	위의 예시처럼 기존에 printOld(String)을 쓰던 클래스를 Printable.print() 인터페이스에 맞춰야 할 때

	 "수정할 수 없는 코드에, 새 규칙을 강제해야 할 때"


2. 서드파티 라이브러리, 외부 API를 우리 인터페이스로 감쌀 때
	- 외부 라이브러리에서 제공하는 메서드와 우리의 시스템 구조가 다를 때

	- 우리가 정의한 인터페이스에 맞게 중간에 어댑터를 둠

	 예: GoogleMapAPI → MapService 인터페이스로 감싸기

3. 인터페이스를 바꾸지 않고 재사용성 확보하고 싶을 때
	- 기존 코드 재사용은 하고 싶지만, 기존 인터페이스를 바꾸면 의존성 문제가 생길 때

4. 런타임에 다른 객체를 유연하게 끼워 넣고 싶을 때 (객체 어댑터)
	- 객체 합성을 활용해 Adapter가 Adaptee를 바꿔 끼우는 구조

	(이건 전략 패턴처럼 유연한 설계가 필요할 때 자주 씀)

### 장점
- 비즈니스 로직에서 호환되는 코드를 분리할 수 있어서 SRP를 지킬 수 있다.
- 위의 코드 처럼 기존의 코드를 변경하지 않고 어댑터를 만들면 되므로, OCP를 만족한다.

### 단점
- 새로운 인터페이스와 어댑터 클래스를 만들어야 하므로, 코드의 복잡성이 증가하므로, 단순한 어뎁터는 그냥 비즈니스 로직에 넣어도 나쁘지않을 것 같다. 



#### 참고 자료
https://inpa.tistory.com/entry/GOF-%F0%9F%92%A0-%EC%96%B4%EB%8C%91%ED%84%B0Adaptor-%ED%8C%A8%ED%84%B4-%EC%A0%9C%EB%8C%80%EB%A1%9C-%EB%B0%B0%EC%9B%8C%EB%B3%B4%EC%9E%90![업로드중..](blob:https://velog.io/560d70a2-e5b0-478b-aec6-3e5e48338d52)
