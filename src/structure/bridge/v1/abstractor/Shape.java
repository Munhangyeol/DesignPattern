package src.structure.bridge.v1.abstractor;

import src.structure.bridge.v1.implementor.Color;

public abstract class Shape {

    protected Color color;

    public Shape(Color color) {
        this.color = color;
    }

    abstract void draw();

}
