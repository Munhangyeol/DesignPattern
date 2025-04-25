package structure.adapter.v1;

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
