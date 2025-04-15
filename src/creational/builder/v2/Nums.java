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

   public Nums(){

   }



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




    public void setNum1(int num1) {
        this.num1 = num1;
    }
    public void setNum2(int num2) {
        this.num2 = num2;
    }
    public void setNum3(int num3) {
        this.num3 = num3;
    }
    public void setNum4(int num4) {
        this.num4 = num4;
    }
    public void setNum5(int num5) {
        this.num5 = num5;
    }
    public void setNum6(int num6) {
        this.num6 = num6;
    }
    public void setNum7(int num7) {
        this.num7 = num7;
    }

    @Override
    public String toString() {
        return "Nums [num1=" + num1 + ", num2=" + num2 + ", num3=" + num3 + ", num4=" + num4 + ", num5=" + num5
                + ", num6=" + num6 + ", num7=" + num7 + "]";
    }

    

    
}
