package behavior.strategy.ex2.v2.verhicle;

import behavior.strategy.ex2.v2.shape.ShapeStrategy;

public class Train implements Vehicle{

    private ShapeStrategy shape;
    @Override
    public void move() {
        System.out.println("기차가 "+shape.getShape()+" 모양의 바퀴로 움직입니다!");
    }
   
    @Override
    public void setShape(ShapeStrategy shape) {       
        this.shape=shape;
    }
    
}
