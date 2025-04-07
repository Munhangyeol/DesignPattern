package creational.abstract_factory.ex1.factory;

import creational.abstract_factory.ex1.product.Producta;
import creational.factory_method.product.IProduct;
import creational.factory_method.product.ProductA;

public class FactoryA extends Factory{
     @Override
    protected IProduct createProductLarge(String name, int price) {
        name = "[A-Type Large] " + name;
        price += 1000;
        System.out.println("FactoryA 전처리 완료: 이름 수정, 가격 +1000");
        return new ProductA(name, price);
    }

    @Override
    protected IProduct createProductSmall(String name, int price) {
        name = "[A-Type Small] " + name;
        price += 500;
        System.out.println("FactoryA 전처리 완료: 이름 수정, 가격 +500");
        return new Producta();
    }
}
