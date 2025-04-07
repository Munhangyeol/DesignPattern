package creational.abstract_factory;

import creational.abstract_factory.ex1.FactoryType;
import creational.abstract_factory.ex1.factory.AbstractFactory;
import creational.abstract_factory.ex1.factory.Factory1;
import creational.abstract_factory.ex1.factory.Factory2;

public class Main {

    public static void main(String[] args) {

        
        AbstractFactory factory=null;
        //공장 1에서 productA를 생산하자.
        factory=FactorySelector.selectFactory(FactoryType.EFACTORY1);
        System.out.println(factory.createProductA().getClass().getName());

        //공장 2에서 proudctB를 생산하자.
        factory=FactorySelector.selectFactory(FactoryType.EFACTORY2);
        factory=new Factory2();
        System.out.println(factory.createProductB().getClass().getName());  
    }

    
    
}
