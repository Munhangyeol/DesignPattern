package src.structure.bridge.v1.abstractor;

import src.structure.bridge.v1.implementor.Color;

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
