package behavior.strategy.ex2.v1;

public class Car implements Vehicle{

    @Override
    public void move() {
        System.out.println("자동차가 움직입니다!");
    }
    
}
