package src;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.time.Instant;
import java.util.ArrayList;

public class OrderManager {

   
    private static ArrayList<Order> history = new ArrayList<>();
    private ArrayList<Order> currentOrders = new ArrayList<>();
    private DBManager db;

    public OrderManager(){
        db = new DBManager();
    }

    public void addOrder(Order order){
        db.addOrder(order);
    }
    
    public void clearOrder(Order order){
        db.clearOrder(order);
        addOrderToHistory(order);
    }
    
    public void addOrderToHistory(Order order){
        db.addOrderToHistory(order);
    }
    
    public ArrayList<Order> getOrders(){      
        return db.getOrders();
    }

    public ArrayList<Order> getOrderHistory(){
        return db.getOrderHistory();
    }
    
    
    public String printOrder(Order order){
        String print = "";
        DecimalFormat df = new DecimalFormat("#.00"); 
        if(order != null){
            String table = Integer.toString(order.getTableNo());
            String items = order.getItemsPrinted();

            Timestamp ts = order.getTimeStamp();
            Instant inst = ts.toInstant();
            String time = inst.toString();

            print += "Stamp: " + time + "\n";
            print += "Table No: " + table + "\n\nItems: \n";
            print += items;
            print += "\nAfter reduction: " + df.format(order.getCost());
            print += "\n\nRequest: \n" + order.getRequest();
        }
        
        return print;
    }
    
}
