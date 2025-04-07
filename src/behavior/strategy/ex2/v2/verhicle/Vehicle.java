package behavior.strategy.ex2.v2.verhicle;

import behavior.strategy.ex2.v2.shape.ShapeStrategy;

public interface Vehicle {

    void move();
    void setShape(ShapeStrategy shape);
    
} 