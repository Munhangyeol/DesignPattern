package behavior.strategy.ex1;

public class Context {
    IStrategy strategy;

    void strategy(IStrategy strategy){
        this.strategy=strategy;
    }
    void play(){
        this.strategy.play();
    }
    
}
