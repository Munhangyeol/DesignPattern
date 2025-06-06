## 개념

어떻게 하면 유지보수성이 높은 코드를 잘 짤 수 있을까 에 대한 고민은 개발자들이라면 당연히 고민해볼것이다.

이때 여러가지 패턴을 통해서 이러한 고민을 해결할 수 있는 것이 바로 **디자인 패턴**이다.

기본적으로 디자인 패턴은 객체지향의 4대 원칙인 캡슐화 다형성 상속 추상화를 **이용**하며, SOLID원칙을 **지키는 방향**으로 문제를 해결한다.

디자인 패턴을 이용하면 다음과 같은 장점이 있다.

- 유지보수성: 각 기능의 모듈들의 응집성을 올리고 결합도를 낮춰서, 기능 수정 시, 다른 기능까지 수정해야 하는 파급효과를 줄일 수 있다.
- 확장성: 기능을 확장시에 기존의 기능을 많이 변경하지 않고, 추가가 가능하다.
- 가독성: 관련이 있는 기능들 끼리만 모여져 있으므로, 가독성이 뛰어나다.
- 재사용성: 일종의 패턴을 이용해서 문제를 해결하는 것이므로, 유사한 상황 시에, 코드를 재사용해서 문제를 해결할 수 있다.

## 분류

디자인 패턴에는 크게 생성,구조,행위 패턴이 존재한다.

### 생성 패턴

이름 그대로 객체나 인스턴스, 인터페이스를 **생성 하는 패턴이다.**

- [**Singleton**](https://github.com/Munhangyeol/DesignPattern/blob/main/src/creational/singleton/readme.md)
- [**Factory Method**](https://github.com/Munhangyeol/DesignPattern/tree/main/src/creational/factory_method)
- [**Abstract Factory**](https://github.com/Munhangyeol/DesignPattern/tree/main/src/creational/abstract_factory)
- [**Builder**](https://github.com/Munhangyeol/DesignPattern/tree/main/src/creational/builder)
- [**Prototype**](https://github.com/Munhangyeol/DesignPattern/blob/main/src/creational/prototype/readme.md)

### **구조 패턴**

유지 보수성을 위해서 기존의 구조를 변경하는 패턴이다.

- [**Adapter**](https://github.com/Munhangyeol/DesignPattern/blob/main/src/structure/adapter/readme.md)
- [**Bridge**](https://github.com/Munhangyeol/DesignPattern/tree/main/src/structure/bridge)
- **Composite**
- **Decorator**
- **Facade**
- **Flyweight**
- **Proxy**

### 행위 패턴

객체가 하는 행위에 대해서 결합도를 낮추기 위해 사용하는 패턴이다.

- **Observer**
- [**Strategy**](https://github.com/Munhangyeol/DesignPattern/blob/main/src/behavior/strategy/readme.md)
- **Command**
- **State**
- **Chain of Responsibility**
- **Visitor**
- **Interpreter**
- **Memento**
- **Mediator**
- **Template Method**
- **Iterator**

사실은 해당 패턴에 대한 의미를 적었었는데 아직 이해가 잘 안돼서, 하나하나 공부를 하면서 정의해볼까한다.

---
