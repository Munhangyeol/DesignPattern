package creational.abstract_factory.ex1.factory;

import creational.factory_method.product.IProduct;

public abstract class Factory {
    protected abstract IProduct createProductLarge(String name, int price);
    protected abstract IProduct createProductSmall(String name, int price);
}
