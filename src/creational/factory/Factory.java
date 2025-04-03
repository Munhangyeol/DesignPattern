package creational.factory;

abstract class Factory {




    final IProduct createProduct(String name,int price){
      IProduct iProduct=creatConcreteProduct(name,price);
      iProduct.initilize();
      return iProduct;

    }
    abstract protected IProduct creatConcreteProduct(String name,int price);
    
}
