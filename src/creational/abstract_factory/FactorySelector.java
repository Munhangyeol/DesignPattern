package creational.abstract_factory;

import creational.abstract_factory.ex1.FactoryType;
import creational.abstract_factory.ex1.factory.AbstractFactory;
import creational.abstract_factory.ex1.factory.Factory;
import creational.abstract_factory.ex1.factory.Factory1;
import creational.abstract_factory.ex1.factory.Factory2;

public class FactorySelector {
    public static AbstractFactory  selectFactory(FactoryType type){
           switch (type) {
            case EFACTORY1:
                return new Factory1();
            case EFACTORY2:
                return new Factory2();
            default:
                throw new IllegalArgumentException("Unknown factory type: " + type);
        }
    }
    
}
