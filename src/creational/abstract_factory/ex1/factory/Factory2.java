package creational.abstract_factory.ex1.factory;

import creational.abstract_factory.ex1.product.IProductA;
import creational.abstract_factory.ex1.product.IProductB;
import creational.abstract_factory.ex1.product.ProductA2;
import creational.abstract_factory.ex1.product.ProductB2;
import creational.factory_method.product.ProductA;
import creational.factory_method.product.ProductB;

public class Factory2 implements AbstractFactory{

    @Override
    public IProductA createProductA() {
        // TODO Auto-generated method stub
        return new ProductA2();
    }

    @Override
    public IProductB createProductB() {
        // TODO Auto-generated method stub
       return new ProductB2();
    }


    
}
