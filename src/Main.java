
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import structure.adapter.v1.Adapter;
import structure.adapter.v1.Service;
import structure.adapter.v1.Target;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws NumberFormatException, IOException {

        System.out.println("!!!");

        Target adapter = new Adapter(new Service());
        adapter.method(10);
    }

}
