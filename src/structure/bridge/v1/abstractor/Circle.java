package src.structure.bridge.v1.abstractor;

import src.structure.bridge.v1.implementor.Color;

public class Circle extends Shape {

    public Circle(Color color) {
        super(color);

    }

    @Override
    void draw() {
        System.out.println("Draw Circle with");
        color.applyColor();
    }

}
