package creational.singleton;

public class MySingleton_V1 {
    private static final MySingleton_V1 instance=new MySingleton_V1();

    private MySingleton_V1(){

    }

    public synchronized MySingleton_V1 getInstance() {
        return instance;
    }

}
