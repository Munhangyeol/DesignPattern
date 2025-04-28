package structure.adapter.v2;


// Main에서 사용하고 싶은 클래스이지만, 현재는 호환이 안되어서 직접 사용할 수 없는 클래스
public class Service {

    void specificMethod(int data) {
        System.out.println("기존 서비스 기능 호출 + " + data);
    }
}
