package behavior.strategy.ex2.v1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    static Vehicle vehicle;
    public static void main(String[] args) throws NumberFormatException, IOException {
        decideVehicle(Integer.parseInt(br.readLine()));
       
        vehicle.move();
        
    }
    static void decideVehicle(int decideNum){
        switch (decideNum) {
            case 1:
                vehicle=new Car();
                break;
            case 2:
                vehicle=new Bus();
                break;
            case 3:
                vehicle=new Train();
                break;
        }

    }
    
}
