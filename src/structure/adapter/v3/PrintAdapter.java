package structure.adapter.v3;

public class PrintAdapter implements Printable{

    OlderPrinter printer;
    public PrintAdapter(OlderPrinter printer){
        this.printer=printer;

    }

    @Override
    public void newPrint() {
        // TODO Auto-generated method stub
        printer.oldPrint();
      
    }
    
}
