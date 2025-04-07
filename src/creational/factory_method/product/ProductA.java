package creational.factory_method.product;

public class ProductA implements IProduct{
    String name;
    int price;
    String category;

    public ProductA(String name,int price){
        this.name=name;
        this.price=price;

    }

    @Override
    public void initilize() {

        category="A";
    }
    
    
}
