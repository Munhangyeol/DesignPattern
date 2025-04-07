package creational.factory_method.factory;

import creational.factory_method.product.IProduct;
import creational.factory_method.product.ProductA;

public class FactoryA extends Factory{

    static class FactoryAHelper{
        static FactoryA instance=new FactoryA();
    }

    public static FactoryA getInstance(){
        return FactoryAHelper.instance;
    }

   @Override
    protected IProduct creatConcreteProduct(String name, int price) {
        name = "[A-Type] " + name;
        price += 1000;
        System.out.println("FactoryA 전처리 완료: 이름 수정, 가격 +1000");
        return new ProductA(name, price);
    }
}
