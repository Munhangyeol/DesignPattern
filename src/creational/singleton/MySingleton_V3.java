package creational.singleton;

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
