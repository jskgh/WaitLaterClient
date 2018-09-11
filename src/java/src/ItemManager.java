package src;

import java.util.ArrayList;

public class ItemManager {

    private ArrayList<Item> items;
    private DBManager db;

    public ItemManager() {
        this.items = new ArrayList<>();
        this.db = new DBManager();
    }
    
    
    public ArrayList<Item> getItems(){
        ArrayList<Item> i = db.getItems();
        return i;
    }
    
    public void addItem(Item i){
        db.addItem(i);
    }
    
    public void removeItem(Item i){
        db.removeItem(i);
    }
}
