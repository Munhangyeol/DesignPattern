package structure.adapter.v2;


// Main에서 직접사용할 수 없는 Service클래스를 Adapter클래스를 시용해서
// 사용할 수 있도록 일종의 연결 장치임.

public class Adapter extends Service implements Target {

    @Override
    public void method(int data) {
        // TODO Auto-generated method stub
        super.specificMethod(data);
    }

}
