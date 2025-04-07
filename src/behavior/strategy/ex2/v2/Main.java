package behavior.strategy.ex2.v2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import behavior.strategy.ex2.v2.verhicle.Vehicle;

public class Main {

    static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    static Vehicle vehicle;
    public static void main(String[] args) throws NumberFormatException, IOException {
        vehicle=VerhicleFactory.createVehicle(Integer.parseInt(br.readLine()));
        vehicle.move();
        
    }
}
