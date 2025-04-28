package structure.adapter.v1;


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
