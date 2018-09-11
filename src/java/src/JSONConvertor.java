package src;

import java.sql.Timestamp;
import java.util.ArrayList;

public class JSONConvertor {

    private static DBManager db = new DBManager();
    
    public static Order toOrder(String string){
        ArrayList<Item> items = db.getItems();
        String[] s = string.split("'");
        int tableNo = Integer.parseInt(s[0]);
        String itemString = s[1];
        String request = s[2];
        String[] token = itemString.split(";");
                
        ArrayList<Item> orderItems = new ArrayList<>();
        
        for(Item i : items){
            for(int n = 0; n < token.length; n++){
                if(token[n].equals(Integer.toString(i.getId()))){
                    orderItems.add(i);
                }
            }
        }
        
        double cost = 0.00;
        for(Item i : orderItems){
            cost += i.getCost();
        }
        
        ArrayList<Order> orders = db.getOrders();
        int id = 0;
        for(Order o : orders){
            if(o.getId()>id){
                id = o.getId();
            }
        }
        id++;
        
        Timestamp timeStamp = new Timestamp(System.currentTimeMillis());

        Order o = new Order(id,tableNo,orderItems,cost,timeStamp,request);
        System.out.println("JSONConvertor printing order " + o.toString());
        return o;
    }
    
    
}
