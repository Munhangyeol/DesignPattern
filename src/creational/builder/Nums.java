package creational.builder;


//점층적 생성자 패턴
public class Nums {
   private int num1;
   private int num2;
   private int num3;
   private int num4;
   private int num5;

   private int num6;
   private int num7;

    public Nums(int num1,int num2,int num3,int num4,int num5){
        
        this.num1=num1;
        this.num2=num2;
        this.num3=num3;
        this.num4=num4;
        this.num5=num5;
    }
    public Nums(int num1,int num2,int num3,int num4,int num5,int num6,int num7){
        
        this.num1=num1;
        this.num2=num2;
        this.num3=num3;
        this.num4=num4;
        this.num5=num5;
        this.num6=num6;
        this.num7=num7;
    }
    public Nums(int num1,int num2,int num3,int num4,int num5,int num6){
        
        this.num1=num1;
        this.num2=num2;
        this.num3=num3;
        this.num4=num4;
        this.num5=num5;
        this.num6=num6;


    }

    
}
