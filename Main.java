package my.accountingwindow;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class Main extends JFrame 
                  implements ListSelectionListener{
    
    DefaultListModel listModel;
   JPanel mainPanel;
   JList manifestList;
   JScrollPane scroll;
   JPanel buttonPanel; 
   JButton checkoutButton;
   JButton printButton;
   JButton exitButton;
   JButton editButton;
   JButton viewButton;
   JTextField addText;
   JButton addButton;
   JPanel textPanel;
   JPanel listPanel;
   Warehouse newWarehouse = new Warehouse();
   
   public Main(){
       setTitle("Welcome to Warehouse!");
       setSize(500,475);
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       buttonListener bl = new buttonListener();
       setLocationRelativeTo(null);
       
       //create panels
       mainPanel = new JPanel();
       buttonPanel = new JPanel();
       textPanel = new JPanel();
       listPanel = new JPanel();
       
       //create buttons
       checkoutButton = new JButton("Checkout");
       printButton = new JButton("Print Manifest");
       addButton = new JButton("Add");
       exitButton = new JButton("Exit");
       viewButton = new JButton("View");
       editButton = new JButton("Edit");
       
       //create list
       listModel = new DefaultListModel();
       manifestList = new JList(listModel);
       manifestList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
       manifestList.addListSelectionListener(this);
       manifestList.setSelectedIndex(0);
       scroll = new JScrollPane(manifestList);
       
       //create text field
       addText = new JTextField(15);
       
       //create border
       Border textborder = BorderFactory.createTitledBorder("Enter an item here");
       Border buttonBorder = BorderFactory.createTitledBorder("Print Manifest or Checkout");
       Border ListBorder = BorderFactory.createTitledBorder("Items Currently in Stock");
       
       //set borders on panels and add items to panels
       buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
       textPanel.setBorder(textborder);
       listPanel.setBorder(ListBorder);
       
       buttonPanel.add(printButton);
       buttonPanel.add(checkoutButton);
       buttonPanel.add(exitButton);
       
       textPanel.add(addText);
       textPanel.add(addButton);
       textPanel.add(viewButton);
       
       listPanel.add(scroll);
       listPanel.add(viewButton);
       listPanel.add(editButton);
       
       mainPanel.add(textPanel);
    
       mainPanel.add(listPanel);
        mainPanel.add(buttonPanel);
       
       //add event listeners to buttons
       checkoutButton.addActionListener(bl);
       printButton.addActionListener(bl);
       addButton.addActionListener(bl);
       exitButton.addActionListener(bl);
       viewButton.addActionListener(bl);
       editButton.addActionListener(bl);
       add(mainPanel);
       
       setVisible(true);  
       
   }
   
   
   public class buttonListener implements ActionListener{
       public void actionPerformed(ActionEvent e){
           if(e.getSource()==addButton){
               
               //get item name
               String itemName = addText.getText();
               if(!newWarehouse.contains(itemName)){
               newWarehouse.addItem(itemName);    
               listModel.addElement(addText.getText());
               addText.setText("");
               }
               else
               //clear text
               addText.setText("");
               
           }
           if(e.getSource()==exitButton){
               System.exit(0);
           }
           if(e.getSource()==checkoutButton){
               String itemName = (String)manifestList.getSelectedValue();
               newWarehouse.Checkout(itemName);
           }
           if(e.getSource()==printButton){
               
                   try {
                       newWarehouse.printItems();
                   } catch (IOException ex) {
                       Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                   }
               }
           if(e.getSource()== viewButton){
                   String itemName = (String)manifestList.getSelectedValue();
                   newWarehouse.viewItem(itemName);
               }
           if(e.getSource()==editButton){
                String itemName = (String)manifestList.getSelectedValue();
                newWarehouse.editItem(itemName);
            }  
               }
            
               
           }
   
       public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {
 
            if (manifestList.getSelectedIndex() == -1) {
            //No selection, 
               viewButton.setEnabled(false);
 
            } else {
            //Selection, 
                viewButton.setEnabled(true);
            }
        }
    }    
           
           
       
   
    
    
    
    public static void main(String[] args) {
        //ask for name
        Main main;
        main = new Main();
        
}
}