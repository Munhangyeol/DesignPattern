## 개념
**Builder 패턴**은 객체의 생성과정과 객체 자체를 분리해서, 다양한 구성을 가진 객체를 생성하는 패턴이다.
생성자에 들어갈 매개변수를 메서드를 통해서 주입하고 마지막에는 build 메서드를 통해서 객체를 생성하는 방식이다.

### 배경
빌더 패턴은 필드의 주입을 유연하게 받을 때 그 필요성이 증가된다.
필드의 주입을 유연하게 받기 위해서는 아래의 두가지 방식이 일반적으로 이용이 가능하다.
#### 1. 점층적 생성자를 사용한다.
생성자의 매개변수를 다양한 형태로 받는 **오버로딩**을 이용하는 방식이다.
```java
//점층적 생성자 패턴
public class Nums {
    private int num1;
    private int num2;
    private int num3;
    private int num4;
    private int num5;

    private int num6;
    private int num7;

    public Nums() {

    }

    public Nums(int num1, int num2, int num3, int num4, int num5) {

        this.num1 = num1;
        this.num2 = num2;
        this.num3 = num3;
        this.num4 = num4;
        this.num5 = num5;
    }

    public Nums(int num1, int num2, int num3, int num4, int num5, int num6, int num7) {

        this.num1 = num1;
        this.num2 = num2;
        this.num3 = num3;
        this.num4 = num4;
        this.num5 = num5;
        this.num6 = num6;
        this.num7 = num7;
    }

    public Nums(int num1, int num2, int num3, int num4, int num5, int num6) {

        this.num1 = num1;
        this.num2 = num2;
        this.num3 = num3;
        this.num4 = num4;
        this.num5 = num5;
        this.num6 = num6;

    }
    
       public Nums(int num1, int num2, int num3) {

        this.num1 = num1;
        this.num2 = num2;
        this.num3 = num3;

    }
    @Override
    public String toString() {
        return "Nums [num1=" + num1 + ", num2=" + num2 + ", num3=" + num3 + ", num4=" + num4 + ", num5=" + num5
                + ", num6=" + num6 + ", num7=" + num7 + "]";
    }
}

public class Main {
    public static void main(String[] args) throws NumberFormatException, IOException {

       //Num에서 정의 한 생성자들을 이용해서 new 하자
       Nums num1=new Nums(1, 2, 3, 4, 5);
       
       Nums num2=new Nums(1, 2, 3, 4, 5,6);

       Nums num3=new Nums(1, 2, 3, 4, 5,6,7);

       Nums num4=new Nums(1,2,3,4,5,6,0);

       Nums num5=new Nums(1,2,3,4,5,0,0);
}
```
위의 코드처럼 Nums의 생성자를 오버로딩해서, 필드의 주입을 유연하게 받을 수 있다. 


하지만 이러한 코드는 다음과 같은 문제점을 내포한다.

- Nums의 생성자를 무한정 늘릴 수 없다.
  - 클래스에 들어가는 필드의 수가 많아질 수도록 생성자를 계속 만들어야하는데, 이렇게 되면, 가독성, 유지보수 측면에서 그다지 좋지 못하다.

- 필드의 수가 많아질수록 생성시에 어떤 필드를 주입해야하는지 햇갈려서 실수하는 경우가 생긴다.

이러한 문제를 보완하기 위해서 ```Setter```를 이용한 자바 빈 패턴을 적용할 수 있다.

####  2. ```Setter```를 이용
```java

//setter 패턴
public class Nums {
    private int num1;
    private int num2;
    private int num3;
    private int num4;
    private int num5;

    private int num6;
    private int num7;

    public Nums() {

    }



    public void setNum1(int num1) {
        this.num1 = num1;
    }

    public void setNum2(int num2) {
        this.num2 = num2;
    }

    public void setNum3(int num3) {
        this.num3 = num3;
    }

    public void setNum4(int num4) {
        this.num4 = num4;
    }

    public void setNum5(int num5) {
        this.num5 = num5;
    }

    public void setNum6(int num6) {
        this.num6 = num6;
    }

    public void setNum7(int num7) {
        this.num7 = num7;
    }

    @Override
    public String toString() {
        return "Nums [num1=" + num1 + ", num2=" + num2 + ", num3=" + num3 + ", num4=" + num4 + ", num5=" + num5
                + ", num6=" + num6 + ", num7=" + num7 + "]";
    }

}

public class Main{
    public static void main(String[] args) throws NumberFormatException, IOException {
       
       Nums num6=new Nums();
       num6.setNum1(1);
       num6.setNum2(2);
       num6.setNum3(3);


}


}

```
위처럼 기본 생성자를 만들고, setter를 만들어서, 객체 생성시 주입이 아닌 생성 후에 원하는 필드에 주입할 수 있다.


이를 통해서 점층적 생성자 패턴에서의 문제인 생성자를 무한으로 생성해야하는 문제와 어떤 필드로 주입을 해야하는지에 대한 문제를 해결할 수 있다. 

하지만 이 패턴 역시 두가지 정도의 문제점을 내포하고 있다.

- 일관성 문제
  - 필수적으로 주입을 해줘야 하는 필드의 경우 반드시 넣어줘야하는데, ```setter```를 이용하면 필수적으로 필드에 주입하지 않아도, 객체를 생성할 수 있다.
  - 따라서 필수적으로 주입을 해줘야하는 필드를 주입하지 않고 사용할 가능성이 있으므로, 객체의 **일관성**이 무너질 수 있다. 

- 불변성 문제
  - setter를 지양해야 하는 이유중 가장 큰것은 변경의 가능성이 있다는 것이다.
  - 싱글톤 컨테이너인 스프링은 stateless한 통신,객체를 보통 유지하므로, 필드의 가변성은 그다지 좋은 선택지가 아니다.
 
 
유연성있는 필드 주입과 위의 문제들을 해결하기 위해서 ```Builder``` 패턴을 이용할 수 있다.



## 구현 

Builder패턴은 기존의 클래스의 필드를 복사해서 Builder클래스의 필드에 넣고 아래와 같이 메서드를 구현할 수 있다.
```java
public class NumBuilder {

    private int num1;
    private int num2;
    private int num3;
    private int num4;
    private int num5;
 
    private int num6;
    private int num7;

    public NumBuilder num1(int num1){
        this.num1=num1;
        return this;
    }
    
    public NumBuilder num2(int num2){
        this.num2=num2;
        return this;
    }
    
    public NumBuilder num3(int num3){
        this.num3=num3;
        return this;
    }
    
    public NumBuilder num4(int num4){
        this.num4=num4;
        return this;
    }
    
    public NumBuilder num5(int num5){
        this.num5=num5;
        return this;
    }
    
    public NumBuilder num6(int num6){
        this.num6=num6;
        return this;
    }
    public NumBuilder num7(int num6){
        this.num6=num6;
        return this;
    }

    public Nums build(){
        return new Nums(num1,num2,num3,num4,num5,num6,num7);
    }

    @Override
    public String toString() {
        return "Nums [num1=" + num1 + ", num2=" + num2 + ", num3=" + num3 + ", num4=" + num4 + ", num5=" + num5
                + ", num6=" + num6 + ", num7=" + num7 + "]";
    }

    
}

public class Main {


    public static void main(String[] args) throws NumberFormatException, IOException {

       Nums numFromBuilder=new NumBuilder().num1(1).num2(2).num3(3).build();
       System.out.println(numFromBuilder);


   }
 }
```
```num``` 메서드를 통해 각 필드를 유연하게 주입한 후, ```build``` 메서드를 통해 최종 객체를 생성한다.

이 방식은 생성자의 매개변수를 줄이면서도, 필요한 필드만 선택적으로 설정할 수 있어 객체 생성에 유연성을 더한다. 또한 객체 생성 전까지 모든 필드의 설정이 완료되므로 **객체의 일관성**을 유지할 수 있으며, 각 필드 설정 메서드(num1, num2, num3, num4, num5, num6)는 numBuilder 자신을 반환하도록 설계되어 **메서드 체이닝**이 가능하고, 이를 통해 불변성도 유지할 수 있다.

### 장점& 단점 

#### 장점 
정리하자면, 빌더 패턴은 다음과 같은 장점이 있다.

- 매개변수가 많아짐에 따라 생성자 사용시 가독성이 떨어져서 실수할 가능성이 높은데, 빌더 패턴을 이용하면,  
주입하는 메서드를 필드 이름으로 무어서, 어떤 데이터에 **어떤 값이 설정되는지 한눈에 파악할 수 있다**.

- 필수적 매개변수와 선택적 매개변수를 구별이 더욱 명확하게 분리할 수 있다.
  - 필수적인 매개변수는 builder의 생성자 매개변수로 넘기면 이를 필수적이라고 명시할 수 있다. 
  ```java
      public NumBuilder(int num1){
        this.num1=num1;
    }

  ```

- 초기화 검증을 각 멤버별로 분리해서 유지보수성을 용이하게 할 수 있다.

- 멤버에 대한 변경 가능성 최소화를 추구한다. 
  - Builder로 객체의 생성을 위임하니까 이것이 가능한 것이다.
  
#### 단점
- 코드 복잡성 증가
  - 각 클래스 별로 빌더 클래스를 만들어야 하므로 구현해야하는 코드의 수가 증가하고 복잡해진다.(물론 @Builder를 이용하면 편하긴 하다)

- 당연하게도 Builder객체를 거쳐서 객체가 생성되므로 기존의 생성자보다 성능이 떨어진다.


### Simple Builder Pattern
Simple Builder 패턴은 구현 객체 내부에 static inner class로 builder를 구현하는 패턴이다.

```java

public class Nums {
    private int num1;
    private int num2;
    private int num3;
    private int num4;
    private int num5;

    private int num6;
    private int num7;

    public static class Builder {
        private int num1;
        private int num2;
        private int num3;
        private int num4;
        private int num5;

        private int num6;
        private int num7;

        public Builder num2(int num2) {
            this.num2 = num2;
            return this;
        }

        public Builder num3(int num3) {
            this.num3 = num3;
            return this;
        }

        public Builder num4(int num4) {
            this.num4 = num4;
            return this;
        }

        public Builder num5(int num5) {
            this.num5 = num5;
            return this;
        }

        public Builder num6(int num6) {
            this.num6 = num6;
            return this;
        }

        public Builder num7(int num6) {
            this.num6 = num6;
            return this;
        }

        public Nums build() {
            return new Nums(this);
        }
    }

    private  Nums(Builder builder) {

        this.num1 = builder.num1;
        this.num2 = builder.num2;
        this.num3 = builder.num3;
        this.num4 = builder.num4;
        this.num5 = builder.num5;
        this.num6 = builder.num6;
        this.num7 = builder.num7;

    }

    @Override
    public String toString() {
        return "Nums [num1=" + num1 + ", num2=" + num2 + ", num3=" + num3 + ", num4=" + num4 + ", num5=" + num5
                + ", num6=" + num6 + ", num7=" + num7 + "]";
    }

}


```
  ![](https://velog.velcdn.com/images/msw0909/post/5ed9dfce-b1c5-428a-9cfc-7827a3962902/image.png)
- ``` Nums``` 클래스는 ```static inner class```로 선언되었고, 생성자는 ```private```으로 설정되어 외부에서 직접 객체를 생성할 수 없다.

- ```Nums``` 객체는 오직 ```Builder``` 클래스 내부에서만 생성할 수 있도록 하여 객체 생성을 ```Builder``` 패턴으로만 제한한다.

- ```static``` 키워드를 통해 외부 클래스의 인스턴스 없이도 ```Builder```에서 ```Nums``` 객체를 생성할 수 있어, 불필요한 외부 참조를 줄이고 메모리 효율성을 높일 수 있다.

약간 싱글톤 내부에서 inner class랑 비슷한 느낌이지 않나 싶다



### Director Builder Pattern
![](https://velog.velcdn.com/images/msw0909/post/f6d6b8f7-b45e-4489-8b13-7f48ce81e4df/image.png)
뭔가 일반적인 ```Builder```와는 다르다.

```Builder```가 구체적인 ```Builder```로 구현되어서, 어떤 전략을 사용할지를 정해지는데, 이것은 **전략 패턴**이며, ```Builder```를 사용하는 ```Director```는 ```Facade```와 ```Template Method ```역할을 한다고 하는데, 아직 이부분까지는 이해하지 못했다.

코드를 먼저 보자
```java
public class Nums {
    private int num1;
    private int num2;
 

    public Nums() {

    }


    public Nums(int num1, int num2) {

        this.num1 = num1;
        this.num2 = num2;
       
    }

 

    public int getNum1() {
        return num1;
    }


    public int getNum2() {
        return num2;
    }




    @Override
    public String toString() {
        return "Nums [num1=" + num1 + ", num2=" + num2 + "]";
    }

}

public class Director   {
    private NumBuilder builder;

    public Director(NumBuilder builder){
        this.builder=builder;
    }

    public String build(){
        return String.valueOf(builder.calculate());
    }


    
}

public abstract class NumBuilder {

     protected Nums num;

     public NumBuilder(Nums num){
        this.num=num;
     }
     public abstract int calculate();
    
}


public class PlusBuilder extends NumBuilder{

    public PlusBuilder(Nums num) {
        super(num);
       
    }

    @Override
    public int calculate() {
        // TODO Auto-generated method stub
        return num.getNum1()+num.getNum2();
    }
    
}


public class MinusBuilder extends NumBuilder{

    public MinusBuilder(Nums num) {
        super(num);
        //TODO Auto-generated constructor stub
    }

    @Override
    public int calculate() {
        // TODO Auto-generated method stub
        return num.getNum1()-num.getNum2();
    }
    
}


public class Main {


    public static void main(String[] args) throws NumberFormatException, IOException {

       Nums nums=new Nums(1, 2);
   
       NumBuilder builder=new PlusBuilder(nums);
       Director director=new Director(builder);
       String result=director.build();
       System.out.println(result);

       NumBuilder builder2=new MinusBuilder(nums);
       Director director2=new Director(builder2);
       String result2=director2.build();
       System.out.println(result2);
        
}
}
```

String형으로 결과를 반환하는 과정에서 build를 이용했다. 즉 전략을 결정하는 과정에서 빌더를 이용했다고 볼 수 있다.

```Builder``` 패턴은 단순히 객체를 "어떻게 만들까"에 집중하지만,
여기선 전략(계산 방식)을 **Builder**로 추상화하고, 이를 **Director**가 조합해서 실행하는 구조다.


전략패턴을 같이 도입해서 보다 더 유연성있는 코드를 작성할 수 있다. 


## @Builder

Lombock 라이브러리는 ```@Builder```를 이용해서 빌더를 직접 구현하지 않고 사용이 가능하다.
```java
public class Nums {
    private int num1;
    private int num2;

    @Builder
    public Nums(int num1, int num2) {
        this.num1 = num1;
        this.num2 = num2;
    }
}
```
이렇게 사용이 가능하다.
```@Builder```는 심플 빌더 패턴을 구현해준다.

이를 통해서 
```java
Nums nums = Nums.builder()
                .num1(10)
                .num2(20)
                .build();
```
이렇게 사용이 가능하다.
빌더를 이용해서만 객체를 생성시키려면 위처럼 생성자를 ```public```으로 선언하는것이 아닌 ```private```으로 선언하는것이 바람직한것 같다. 


#### 참고 
https://inpa.tistory.com/entry/GOF-%F0%9F%92%A0-%EB%B9%8C%EB%8D%94Builder-%ED%8C%A8%ED%84%B4-%EB%81%9D%ED%8C%90%EC%99%95-%EC%A0%95%EB%A6%AC![](https://velog.velcdn.com/images/msw0909/post/db0975ca-d31c-4f74-91cb-7d7b3ec0de9c/image.png)
