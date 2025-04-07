package creational.factory_method;

import creational.factory_method.factory.*;
import creational.factory_method.product.IProduct;

public class Main {
    public static void main(String[] args) {
        Factory[] factories={new FactoryA(),new FactoryB()};
        IProduct productA=FactoryA.getInstance().createProduct("선풍기", 10000);
        IProduct productB= FactoryB.getInstance().createProduct("짜장면", 30000);
    }
    
}
