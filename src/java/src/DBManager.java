package src;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;

public class DBManager {
    
    private JDBCHandler handler;
    
    public DBManager(){
        handler = new JDBCHandler();
    }
    
    public void addItem(Item item){
        String query = "INSERT INTO items (id,name,description,cost,allergens,tags) VALUES ('" + Integer.toString( item.getId()) + "','"
                                                                                    + item.getName() + "','" 
                                                                                    + item.getDesc() + "','" 
                                                                                    + item.getCost() + "','" 
                                                                                    + item.getAllergensCSV() + "','" 
                                                                                    + item.getTagsCSV() + "');";
        System.out.println(query);
        handler.executeQuery(query);
    }
    
    public void removeItem(Item item){
        String query = "DELETE FROM Items WHERE id = " + item.getId();
        handler.executeQuery(query);
    }
    
    public void addMenu(Menu menu) {
        String query = "INSERT INTO Menus (name, items, discounts, active) VALUES('" + menu.getName() + "','" + menu.getItemsCSV() + "','" + menu.getDiscountsCSV() + "','" + menu.isActiveInt() + "')";
        handler.executeQuery(query);
    }

    public void removeMenu(Menu menu){
        String query = "DELETE FROM Menus WHERE name = '" + menu.getName() + "'";
        handler.executeQuery(query);
    }
    
    public void updateMenu(Menu menu){
        String query = "UPDATE Menus SET name = '" + menu.getName() + "', items = '" + menu.getItemsCSV() + "', active = '" + menu.isActive() + "' WHERE name = '" + menu.getName() + "'";
        handler.executeQuery(query);
    }
    
    public void activateMenu(Menu menu){
        String query = "UPDATE Menus SET active = '0'";
        handler.executeQuery(query);
        query = "UPDATE Menus SET active = '1' WHERE name = '" + menu.getName() + "'";
        handler.executeQuery(query);
    }
    
    public ArrayList<Menu> getMenus() {
        String query = "SELECT * FROM menus";
        ArrayList<HashMap<String, Object>> resultMap = handler.queryToHashMapList(query);
        ArrayList<Menu> menus = new ArrayList<>();
        for (HashMap<String, Object> row : resultMap) {
            String name = (String)row.get("name");
            boolean active = (boolean)row.get("active");
            Menu m = new Menu(name,active);
            ArrayList<Item> menuItems = getMenuItems(m);
            m.setItems(menuItems);
            menus.add(m);
        }
        return menus;
    }

    public ArrayList<Item> getItems(){
        String query = "SELECT * FROM items";
        ArrayList<HashMap<String, Object>> resultMap = handler.queryToHashMapList(query);
        ArrayList<Item> items = new ArrayList<>();
        for (HashMap<String, Object> row : resultMap) {
            
            int id = (int) row.get("id");
            String name = (String) row.get("name");
            String desc = (String) row.get("description");
            
            String costString = (String) row.get("cost");
            double cost = Double.parseDouble(costString);
            
            String allergensString = (String) row.get("allergens");
            String[] allergens = allergensString.split(";");
            
            String tagsString = (String) row.get("tags");
            String[] tags = tagsString.split(";");
            
            Item i = new Item(id,name,desc,cost,allergens,tags);

            items.add(i);
        }
        return items;
    }
    
    public ArrayList<Item> getMenuItems(Menu menu){
        String[] tokens = null;
        
        String query = "SELECT * FROM menus WHERE name = '" + menu.getName() + "'";
        ArrayList<HashMap<String, Object>> resultMap = handler.queryToHashMapList(query);
        
        for (HashMap<String, Object> row : resultMap) {
            String itemString = (String) row.get("items");
            tokens = itemString.split(";");
        }

        ArrayList<Item> allItems = getItems();
        ArrayList<Item> items = new ArrayList<>();
        for(Item i : allItems){
            if(tokens != null){
                for(int n = 0; n < tokens.length; n++){                  
                    if(Integer.toString(i.getId()).equals(tokens[n])){
                       items.add(i);
                    }
                }
            }
        }
        
        menu.setItems(items);
        return items;
    }

    public void updateMenuItem(Menu menu, String updatedId){
        String query = "UPDATE Menus SET items = '" + updatedId + "' WHERE name = '" + menu.getName() + "'";
        handler.executeQuery(query);
    }

    public ArrayList<Discount> getDiscounts(){
        String query = "SELECT * FROM discounts";
        ArrayList<HashMap<String, Object>> resultMap = handler.queryToHashMapList(query);
        ArrayList<Discount> discounts = new ArrayList<>();
        for (HashMap<String, Object> row : resultMap) {
            
            int id = (int) row.get("id");
            String firstTag = (String) row.get("firstTag");
            String secondTag = (String) row.get("secondTag");
            BigDecimal reductionBD = (BigDecimal) row.get("reduction");
            double reduction = reductionBD.doubleValue();


            Discount d = new Discount(id,firstTag,secondTag,reduction);

            discounts.add(d);
        }
        return discounts;
    }
    
    public ArrayList<Discount> getMenuDiscounts(Menu menu){
        String[] tokens = null;
        
        String query = "SELECT * FROM menus WHERE name = '" + menu.getName() + "'";
        ArrayList<HashMap<String, Object>> resultMap = handler.queryToHashMapList(query);
        
        for (HashMap<String, Object> row : resultMap) {
            String itemString = (String) row.get("discounts");
            tokens = itemString.split(";");
        }

        ArrayList<Discount> allDiscounts = getDiscounts();
        ArrayList<Discount> discounts = new ArrayList<>();
        for(Discount d : allDiscounts){
            if(tokens != null){
                for(int n = 0; n < tokens.length; n++){                  
                    if(Integer.toString(d.getId()).equals(tokens[n])){
                       discounts.add(d);
                    }
                }
            }
        }
        
        menu.setDiscounts(discounts);
        return discounts;
        
    }
    
    public void addDiscount(Discount discount){
        String query = "INSERT INTO discounts (firstTag,secondTag,reduction) VALUES ('" + discount.getFirstTag() + "','" 
                                                                                    + discount.getSecondTag() + "','" 
                                                                                    + discount.getReduction() + "');";
        handler.executeQuery(query);
    }
    
    public void removeDiscount(Discount discount){
        String query = "DELETE FROM Discounts WHERE id = " + discount.getId();
        handler.executeQuery(query);
    }
    
    public void updateMenuDiscount(Menu menu, String updatedId){
        String query = "UPDATE Menus SET discounts = '" + updatedId + "' WHERE name = '" + menu.getName() + "'";
        handler.executeQuery(query);
    }
    
    public void addOrder(Order order){
        String query = "INSERT INTO Orders (id,tableNo,items,cost,timeSent,request) VALUES('" + Integer.toString(order.getId()) + "','" 
                                                                                      + Integer.toString(order.getTableNo()) + "','"
                                                                                      + order.getItemsCSV() + "','"
                                                                                      + Double.toString(order.getCost()) + "','" 
                                                                                      + order.getTimeStamp() + "','"
                                                                                      + order.getRequest() + "')";
        handler.executeQuery(query);
    }
    
    public void clearOrder(Order order){
        String query = "DELETE FROM Orders WHERE id = " + order.getId();
        handler.executeQuery(query);
    }
    
    public void addOrderToHistory(Order order){
        String query = "INSERT INTO history (tableNo,items,cost,timeSent) VALUES('" + Integer.toString(order.getTableNo()) + "','"
                                                                                      + order.getItemsCSV() + "','"
                                                                                      + Double.toString(order.getCost()) + "','" 
                                                                                      + order.getTimeStamp() + "')";
        handler.executeQuery(query);
    }
    
    public ArrayList<Order> getOrderHistory(){
        String query = "SELECT * FROM history";
        ArrayList<HashMap<String, Object>> resultMap = handler.queryToHashMapList(query);
        ArrayList<Order> orders = new ArrayList<>();
        for (HashMap<String, Object> row : resultMap) {
            int id = (int) row.get("id");
            int tableNo = (int) row.get("tableNo");
            ArrayList<Item> items = getOrderItems(id,"history");
            BigDecimal bd = (BigDecimal) row.get("cost");
            double cost = bd.doubleValue();
            Timestamp ts = (Timestamp) row.get("timeSent");
            String request = (String) row.get("request");
            Order o = new Order(id,tableNo,items,cost,ts,request);
            orders.add(o);
        }
        return orders;
    }
    
    public ArrayList<Order> getOrders(){
        String query = "SELECT * FROM orders";
        ArrayList<HashMap<String, Object>> resultMap = handler.queryToHashMapList(query);
        ArrayList<Order> orders = new ArrayList<>();
        for (HashMap<String, Object> row : resultMap) {
            int id = (int) row.get("id");
            int tableNo = (int) row.get("tableNo");
            ArrayList<Item> items = getOrderItems(id,"orders");
            BigDecimal bd = (BigDecimal) row.get("cost");
            double cost = bd.doubleValue();
            Timestamp ts = (Timestamp) row.get("timeSent");
            String request = (String) row.get("request");
            Order o = new Order(id,tableNo,items,cost,ts,request);
            orders.add(o);
        }
        return orders;
    }
    
    
    
        
    public ArrayList<Item> getOrderItems(int id, String table){
        String[] tokens = null;

        String query = "SELECT * FROM " + table + " WHERE id = '" + id + "'";
        ArrayList<HashMap<String, Object>> resultMap = handler.queryToHashMapList(query);
        
        for (HashMap<String, Object> row : resultMap) {
            String itemString = (String) row.get("items");
            tokens = itemString.split(";");
        }

        ArrayList<Item> allItems = getItems();
        ArrayList<Item> items = new ArrayList<>();
        for(Item i : allItems){
            if(tokens != null){
                for(int n = 0; n < tokens.length; n++){                  
                    if(Integer.toString(i.getId()).equals(tokens[n])){
                       items.add(i);
                    }
                }
            }
        }
        
        return items;
    }

}
