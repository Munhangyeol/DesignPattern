import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import creational.singleton.MySingleton_V3;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws NumberFormatException, IOException {
        int a = Integer.parseInt(br.readLine());
        MySingleton_V3.getInstance();


    }

}
