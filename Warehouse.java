package my.accountingwindow;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.Scanner;
import javax.swing.JOptionPane;
import java.text.DecimalFormat;

public class Warehouse {

    private Item newItem;
    private TreeMap<String, Item> itemsInStock;
    private int totalStock;
    double totalPrice;
    int quantitySold;
    double change;
    double payment;
    Scanner keyboard = new Scanner(System.in);
    DecimalFormat df = new DecimalFormat("0.##");

    public Warehouse() {
        totalStock = 0;
        itemsInStock = new TreeMap<String, Item>();
        newItem = new Item();
    }

    public boolean addItem(String name) {

        String itemName;
        int numberOrdered;
        double priceOfItem;



        numberOrdered = Integer.parseInt(JOptionPane.showInputDialog("Please enter the quantity of items ordered: "));

        priceOfItem = Float.parseFloat(JOptionPane.showInputDialog("Please enter the price of the item: "));

        Item newestItem = new Item(name, numberOrdered, priceOfItem);
        itemsInStock.put(name, newestItem);

        totalStock++;
        return true;

    }

    public void printItems() throws FileNotFoundException,
            UnsupportedEncodingException, IOException {
        //create manifest file
        File manifestFile = new File("C:\\temp\\Warehouse Manifest.txt");
        //create parent directory and path
        manifestFile.getParentFile().mkdirs();
        //set up file for printing
        PrintWriter writer = new PrintWriter(manifestFile);
        //iterate over treemap
        for (Map.Entry entry : itemsInStock.entrySet()) {
            //print items in treemap to file
            writer.println("Item Name: " + entry.getKey()
                    + " ---> " + entry.getValue().toString());
        }
        //close file writer
        writer.close();
        //start runtime loader
        Runtime load = Runtime.getRuntime();
        //declare notepad as program to open
        String program = "C:\\WINDOWS\\system32\\notepad.exe";
        //declare pathname and filename of file to open
        String file = "C:\\temp\\Warehouse Manifest.txt";
        //open notepad and file
        load.exec(program + " " + file);

    }

    public boolean Checkout(String itemName) {
        //get Item name from user


        if (!itemsInStock.containsKey(itemName)) {
            JOptionPane.showMessageDialog(null, "Item does not exist in stock");
            return false;
        }
        //get item price
        double itemPrice = itemsInStock.get(itemName).price;
        //user enters how many of the item they want to buy
        quantitySold = Integer.parseInt(JOptionPane.showInputDialog("Please enter quantity to buy: "));
        //check stock
        if (quantitySold > itemsInStock.get(itemName).inStock) {
            JOptionPane.showMessageDialog(null, "There are only "
                    + itemsInStock.get(itemName).inStock + " "
                    + itemName + "(s) in stock, "
                    + "your order cannot be filled at this time.");
            return false;
        }

        this.totalPrice = (double) (itemPrice * quantitySold);

        do {

            payment = Double.parseDouble(JOptionPane.showInputDialog("Please enter Payment: $" + df.format(totalPrice)));
            if (payment < totalPrice) {
                JOptionPane.showMessageDialog(null, "Payment must equal or exceed total price, try again!");
            }
        } while (payment < totalPrice);
        if (payment >= totalPrice) {
            change = (double) (payment - totalPrice);
            JOptionPane.showMessageDialog(null, "Customer change is: " + df.format(change));
            //after payment has been processed, decrement total stock
            totalStock--;
            //decrement stock number of item by amount sold
            itemsInStock.get(itemName).inStock =
                    itemsInStock.get(itemName).inStock - quantitySold;

            if (itemsInStock.get(itemName).inStock <= 15) {
                JOptionPane.showMessageDialog(null, "Stock is low, please order more  " + itemName);
                return true;
            }
            if (itemsInStock.get(itemName).inStock == 0) {
                JOptionPane.showMessageDialog(null, "You are out of " + itemName + " Please order more");
                return true;
            }
            return true;
        }
        return true;
    }

    public void viewItem(String itemName) {
        JOptionPane.showMessageDialog(null, "Item Number: "
                + itemsInStock.get(itemName).number + "\n"
                + "Number in stock: " + itemsInStock.get(itemName).inStock + "\n"
                + "Price: $" + df.format(itemsInStock.get(itemName).price));

    }

    public boolean contains(String itemName) {
        if (itemsInStock.containsKey(itemName)) {
            JOptionPane.showMessageDialog(null, "Item already exists, please use View or Edit button below.");
            return true;
        } else {
            return false;
        }
     }
    public void editItem(String itemName){
        
        boolean checker = false;
        String itemNumber;
        String price;
        String stockNumber;
        
        do{
        checker = false;    
        itemNumber = JOptionPane.showInputDialog("Please enter a new item number: ");
        for(int i = 0; i<itemNumber.length(); i++){
        char firstChar = itemNumber.charAt(i);
        //if first character is a letter, input is invalid
        if(Character.isLetter(firstChar)){
            checker = true;
            JOptionPane.showMessageDialog(null, "Please enter in only numbers.");
        }
        }
        }while(checker==true);
        int iNum = Integer.parseInt(itemNumber);
        itemsInStock.get(itemName).number = iNum;
        
    }
}
