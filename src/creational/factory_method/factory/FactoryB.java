package creational.factory_method.factory;

import creational.factory_method.product.IProduct;
import creational.factory_method.product.ProductB;

public class FactoryB extends Factory{
    static class FactoryBHelper{
        static FactoryB instance=new FactoryB();
    }

    static public FactoryB getInstance(){
        return FactoryBHelper.instance;
    }
    @Override
    protected IProduct creatConcreteProduct(String name, int price) {
        name = name + " (B 전용)";
        price -= 1000;
        price *= 0.9; // 10% 할인
        if (price < 0) throw new IllegalArgumentException("가격은 0 이상이어야 합니다.");
        System.out.println("FactoryB 전처리 완료: 이름 수정, 가격 -1000 및 할인 적용");
        return new ProductB(name, price);
    }
    
}
