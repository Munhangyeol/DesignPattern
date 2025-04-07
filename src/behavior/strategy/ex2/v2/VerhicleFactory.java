package behavior.strategy.ex2.v2;

import behavior.strategy.ex2.v2.verhicle.Bus;
import behavior.strategy.ex2.v2.verhicle.Car;
import behavior.strategy.ex2.v2.verhicle.Train;
import behavior.strategy.ex2.v2.verhicle.Vehicle;

public class VerhicleFactory {

    public static Vehicle createVehicle (int select){
        switch (select) {
            case 1:
                return new Car();
            case 2:
                return new Bus();
            case 3:
                return new Train();
                
        }
        throw new RuntimeException("존재하지 않는 이동 수단입니다.");
    }
    
}
