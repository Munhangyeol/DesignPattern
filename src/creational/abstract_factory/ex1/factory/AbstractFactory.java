package creational.abstract_factory.ex1.factory;

import creational.abstract_factory.ex1.product.IProductA;
import creational.abstract_factory.ex1.product.IProductB;


public interface AbstractFactory {

    IProductA createProductA();
    IProductB createProductB();
    
} 
