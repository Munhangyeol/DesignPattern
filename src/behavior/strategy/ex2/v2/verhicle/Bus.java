package behavior.strategy.ex2.v2.verhicle;

import behavior.strategy.ex2.v2.shape.ShapeStrategy;

public class Bus implements Vehicle{

    private ShapeStrategy shape;
    

    @Override
    public void move() {
        // TODO Auto-generated method stub
        System.out.println("버스가 "+shape.getShape()+" 모양의 바퀴로 움직입니다!");
    }


    @Override
    public void setShape(ShapeStrategy shape) {       
        this.shape=shape;

    }
    
}
