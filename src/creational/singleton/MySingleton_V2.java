package creational.singleton;

public class MySingleton_V2 {
    private static MySingleton_V2 instance;

    private MySingleton_V2(){

    }
    public synchronized MySingleton_V2  getInstance(){
        if(instance==null)instance=new MySingleton_V2();
        return instance;
    }
    
}
