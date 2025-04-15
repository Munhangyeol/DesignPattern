import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import creational.builder.Nums;
import creational.singleton.MySingleton_V3;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws NumberFormatException, IOException {

       //Num에서 정의 한 생성자들을 이용해서 new 하자
       Nums num1=new Nums(1, 2, 3, 4, 5);
       
       Nums num2=new Nums(1, 2, 3, 4, 5,6);

       Nums num3=new Nums(1, 2, 3, 4, 5,6,7);

       Nums num4=new Nums(1,2,3,4,5,6,0);

       Nums num5=new Nums(1,2,3,4,5,0,0);

       Nums num6=new Nums();
       num6.setNum1(1);
       num6.setNum2(2);
       num6.setNum3(3);

       System.out.println(num1);
       System.out.println(num2);
       System.out.println(num3);
       System.out.println(num4);
       System.out.println(num5);
       System.out.println(num6);




    }

}
