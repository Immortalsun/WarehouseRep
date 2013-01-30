package my.accountingwindow;
import java.text.DecimalFormat;

public class Item{

     int number;
     String name;
    double price;
    int inStock;
    
    public Item(String n, int ordered, double p){
      this.name = n;
      this.inStock = ordered;
      this.price = p;
      this.number = (int)(Math.random()*9999);         
}
    public Item(){
          this.name= "";
          this.price = 0.00;
          this.inStock = 0;
          this.number = 0000;
      } 
    
    public void setName(String name){
        this.name = name;
    }
    
    public void setPrice(double price){
        this.price = price;
    }
    
    public void setStock(int stock){
        this.inStock = stock;
    }
    
    public void setNumberManually(int numb){
    
        this.number= numb;
    }
    
    public int setNumberAuto(){
        this.number= (int)(Math.random()*9999);   
        
        return number;
    }

    @Override
    public String toString(){
        DecimalFormat df = new DecimalFormat("0.##");
        String output = "";
        output = "Item Number: "+number+"  Item Price: $"+df.format(price)+"  Number in Stock: "+ inStock;
        return output;
    }
    
}