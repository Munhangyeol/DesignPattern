
public class Rectangle extends Shape {

    public Rectangle(Color color) {
        super(color);
        //TODO Auto-generated constructor stub
    }

    @Override
    void draw() {
        // TODO Auto-generated method stub
        System.out.println("Draw Rectangle with");
        color.applyColor();
    }

}
