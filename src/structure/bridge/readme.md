# 개념
Bridge패턴은 구현하려고 하는 클래스와 이 클래스가 수행하는 기능을 **계층화**하기 위해서, 기능을 다른 추상화된 클래스로 위임하여, 구현 클래스와 기능 클래스를 연결해주는, 즉 bridge의 형태로 나타내는 디자인 패턴이다.

## 구조

![](https://velog.velcdn.com/images/msw0909/post/3b2884c8-d1fb-4b5a-a043-819ded2e76a0/image.png)

Bridge 패턴의 구조는 위처럼 기능 계층(Abstraction)이 구현클래스(Implementor)를 의존해서 구현할 수 있다.

이를 통해서 다음과 같은 이점을 가질 수 있다.
- 각 기능에 대한 구체적인 내용을 구체 클래스로 분리하면서 **SRP**를 지킬 수 있다.
- 기능 계층의 클래스에서 구체 클래스를 추상화한 인터페이스에 의존하므로, **DIP**를 지키므로, **OCP**를 지킬 수 있다.
- 기능 클래스에서 구체 클래스가 의존 될 때, 두 클래스 모두 각각 추상 클래스,인터페이스 형태로 추상화되어 있으므로, 각각 독립적으로 확장이 가능하다.
    - 이를 통해서 클래스간의 결합도를 훨씬 낮출 수 있다.


## 기본 구조 클래스

```Shape```라는 기능을 하는 추상 클래스에서 색상을 의미하는 ```Color```라는 구체적인 기능의 인터페이스를 의존한다고 해보자
```java

public abstract class Shape {

    protected Color color;

    public Shape(Color color) {
        this.color = color;
    }

    abstract void draw();

}

public class Circle extends Shape {

    public Circle(Color color) {
        super(color);

    }

    @Override
    void draw() {
        System.out.println("Draw Circle with");
        color.applyColor();
    }

}

public interface Color {

    void applyColor();

}

public class Green implements Color {

    @Override
    public void applyColor() {
        // TODO Auto-generated method stub
        System.out.println("Green Color");
    }

}

```
- ```Shape```을 추상클래스로 선언한 이유

    - 생성자 중복 제거: ```Color```를 공통 필드로 가지며, 이를 **주입받는 로직**을 상위 클래스에서 일괄 처리할 수 있다.

    - 상속받는 하위 클래스(Circle, Rectangle 등)는 draw()만 오버라이딩하면 되므로 코드 중복 최소화할 수 있다.

- Color는 인터페이스로 두어 다양한 색 구현체를 동적으로 확장 가능하게 구성

- Shape와 Color는 독립적으로 변경/확장이 가능하므로 브릿지 패턴의 장점을 충실히 구현

### Bridge 패턴과 전략 패턴의 차이
처음에는 구조적으로 보았을 때, 의존하고 있는 객체에 대해서 계층 구조를 가져서 추상화된 객체에 의존할 수 있다는 점에서 전략 패턴과의 차이점에 대해 명확히 이해하지 못했다.
하지만 두 가지 패턴은 사용 목적과 계층 구조의 확장성 측면에서 분명한 차이를 가진다.

- Bridge 패턴은 **기능 계층(Abstraction)**과 **구현 계층(Implementation)**을 독립적으로 분리하여 확장할 수 있도록 설계된다. 즉, 기능을 수행하는 클래스 계층과 그 기능을 실제로 구현하는 클래스 계층을 각각 독립적으로 확장할 수 있게 하는 구조적 패턴이다.

- 반면, 전략 패턴은 알고리즘을 캡슐화하고 동적으로 교체할 수 있도록 하기 위한 행위(behavioral) 패턴이다. 하나의 기능(예: 정렬, 경로 탐색 등)에 대해 여러 알고리즘 중 선택적으로 사용할 수 있게 하며, 전략 객체는 보통 단일 계층 구조로 존재한다.

따라서 Bridge 패턴은 클래스 계층 구조를 분리하고 확장하기 위해,전략 패턴은 행위를 런타임에 유연하게 변경하기 위해 사용된다는 점에서 큰 차이가 있다.


## 예시2
```java
// Renderer.java (Implementor)
public interface Renderer {
    void renderButton(String label);
    void renderCheckbox(boolean checked);
}

// AndroidRenderer.java (ConcreteImplementor)
public class AndroidRenderer implements Renderer {
    public void renderButton(String label) {
        System.out.println("Android Button: " + label);
    }
    public void renderCheckbox(boolean checked) {
        System.out.println("Android Checkbox: " + (checked ? "Checked" : "Unchecked"));
    }
}

// UIComponent.java (Abstraction)
public abstract class UIComponent {
    protected Renderer renderer;
    public UIComponent(Renderer renderer) {
        this.renderer = renderer;
    }
    public abstract void draw();
}

// Button.java (RefinedAbstraction)
public class Button extends UIComponent {
    private String label;
    public Button(Renderer renderer, String label) {
        super(renderer);
        this.label = label;
    }

    public void draw() {
        renderer.renderButton(label);
    }
}

// Checkbox.java (RefinedAbstraction)
public class Checkbox extends UIComponent {
    private boolean checked;
    public Checkbox(Renderer renderer, boolean checked) {
        super(renderer);
        this.checked = checked;
    }

    public void draw() {
        renderer.renderCheckbox(checked);
    }
}
```
이를 통해서 UI계층과 Render 계층을 나누어서 관리할 수 있다.

다음과 같은 이점을 가진다.
- 플랫폼 독립적인 UI 구성 가능
  → iOSRenderer, WindowsRenderer 등을 추가하더라도 UIComponent 계층은 수정 필요 없음.(srp를 지켜서 파급효과를 줄인다.)

- 새 UI 요소 확장 용이
  → Slider, RadioButton 같은 새 UIComponent 확장이 간단.

- 렌더링과 UI 기능의 분리
  → UI의 역할(버튼, 체크박스)과 출력 방식(Android, iOS 등)을 완전히 분리함으로써 결합도를 낮춤.




### Bridge 패턴의 한계와 고려사항
- 설계 복잡도 증가
  기능과 구현을 분리하고 계층화하기 때문에, 소규모 시스템에서는 오히려 **과도한 추상화로 코드가 복잡**해질 수 있다. 적절한 규모의 프로젝트에서 사용하는 것이 효과적이다.

- 인터페이스의 일관성 유지 필요
  구현체 인터페이스(Renderer 등)의 변경이 자주 일어난다면, 인터페이스의 변경이 **파급 효과**를 줄 수 있으므로 인터페이스를 안정적으로 설계해야 한다.

- 의존성 주입 설계 중요
  구현체(Renderer)는 보통 외부에서 주입되므로, DI(의존성 주입) 환경(Spring 등)과 함께 사용하면 유연하고 테스트 가능한 구조로 만들 수 있다.

### 언제 Bridge 패턴을 고려해야 하나?
- 하나의 기능 계층에 여러 플랫폼, 렌더링 방식, 저장 방식 등 다양한 구현이 존재할 때

- 기능과 구현이 독립적으로 자주 변경될 가능성이 있을 때

- 구현 클래스들이 공통 인터페이스를 공유하고, 이를 활용해 기능 클래스에서 동적으로 구현체를 교체하고 싶을 때