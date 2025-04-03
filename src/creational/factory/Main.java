package creational.factory;

public class Main {
    public static void main(String[] args) {
        Factory[] factories={new FactoryA(),new FactoryB()};
        IProduct productA=FactoryA.getInstance().creatConcreteProduct("선풍기", 10000);
        IProduct productB= FactoryB.getInstance().creatConcreteProduct("짜장면", 30000);
    }
    
}
