package behavior.strategy.ex2.v1;

public class Train implements Vehicle{

    @Override
    public void move() {
        System.out.println("기차가 움직입니다!");
    }
    
}
