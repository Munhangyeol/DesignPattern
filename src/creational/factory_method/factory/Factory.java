package creational.factory_method.factory;

import creational.factory_method.product.IProduct;

public abstract class Factory {




    public final IProduct createProduct(String name,int price){
      IProduct iProduct=creatConcreteProduct(name,price);
      iProduct.initilize();
      return iProduct;

    }
    abstract protected IProduct creatConcreteProduct(String name,int price);
    
}
