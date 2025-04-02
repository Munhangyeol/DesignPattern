package creational.factory;

abstract class Factory {

    final IProduct createProduct(){
      IProduct iProduct=creatConcreteProduct();
      iProduct.initilize();
      return iProduct;

    }
    abstract protected IProduct creatConcreteProduct();
    
}
