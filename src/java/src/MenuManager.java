package src;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class MenuManager {

    private ArrayList<Menu> menus;
    private DBManager db;
    
    
    
    public MenuManager() {
        this.menus = new ArrayList<>();
        this.db = new DBManager();
    }
    
    public void addMenu(Menu m){
        db.addMenu(m);
    }

    public void removeMenu(Menu m){
        db.removeMenu(m);
    }
    
    public void updateMenu(Menu m){
        db.updateMenu(m);
    }
    
    public Menu getMenu(String name){
        ArrayList<Menu> allMenus = getMenus();
        for(Menu m : allMenus){
            if(m.getName().equals(name)){
                return m;
            }
        }
        return null;
    }
    
    public ArrayList<Menu> getMenus(){
        ArrayList<Menu> m = db.getMenus();
        return m;
    }
    
    public ArrayList<Item> getMenuItems(Menu menu){
        return db.getMenuItems(menu);
    }

    public boolean appendItem(Menu menu, Item item){
        ArrayList<Item> menuItems = db.getMenuItems(menu);
   
        for(Item i : menuItems){
            if(i.getId() == item.getId()){
                return false;
            }
        }
        String itemsCSV = menu.getItemsCSV();
        System.out.println("ItemsCSV : "+itemsCSV);
        itemsCSV += ";" + Integer.toString(item.getId());
        System.out.println("ItemsCSV After : " + itemsCSV);
        db.updateMenuItem(menu, itemsCSV);
        return true;
    }
    
    public void removeItem(Menu menu, Item item){
        String id = Integer.toString(item.getId());
        String str = menu.getItemsCSV();
        String updatedItems = str.replace(id+";", "");

        if(updatedItems.charAt(updatedItems.length()-1) == ';'){
            updatedItems = updatedItems.substring(0, updatedItems.length()-1);
        }
        
        db.updateMenuItem(menu, updatedItems);
    }
    
    public void addDiscount(Menu menu, Discount discount){
         ArrayList<Discount> discounts = db.getMenuDiscounts(menu);
   
        for(Discount d : discounts){
            if(d.getId() == discount.getId()){
                break;
            }
        }
        
        String discountCSV = menu.getDiscountsCSV();
        
        if(!discountCSV.equals("")){
            discountCSV += ";";
        }
        
        discountCSV += Integer.toString(discount.getId());
        
        db.updateMenuDiscount(menu, discountCSV);
    }
    
    public void removeDiscount(Menu menu, Discount discount){
        System.out.println(menu.toString());
        System.out.println(discount.toString());
        String id = Integer.toString(discount.getId());
        String str = menu.getDiscountsCSV();
        String updated = str.replace(id+";", "");
        
        if(updated.charAt(updated.length()-1) == ';'){
            updated = updated.substring(0, updated.length()-1);
        }

        System.out.println(updated);
        db.updateMenuDiscount(menu, updated);
    }
    
    public ArrayList<Discount> getMenuDiscounts(Menu menu){
        ArrayList<Discount> discounts = db.getMenuDiscounts(menu);
        return discounts;
    }
    
    public void activateMenu(Menu m){
        db.activateMenu(m);
    }
    
    public String updateMenuJSON(Menu menu, ArrayList<Item> items){
        String json = "";

        json += "{";
        json += "\"name\":";
        json += "\"" + menu.getName() + "\",";
        json += "\"items\":";
        json += "[";
        
        int n = 0;
        for(Item i : items){
            json += "{";
            json += "\"item\":"; 
            json += i.toJSON();

            if(n >= items.size()-1){
                json += "}";
            } else {
                json += "},";
            }
            
            n++;
        }
        json += "],";
        json += "\"active\":";
        json += Boolean.toString(menu.isActive());
        json += "}";

        
        try{

            File currentDir = new File("");
            String s = currentDir.getAbsolutePath();
            System.out.println(s);
            PrintWriter writer = new PrintWriter(s + "\\web\\menu.json", "UTF-8");
            writer.println(json);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return json;
    }
    
    public String updateRestaurantJSON(String name, String maxTableNo){
        String json = "";
        json += "{";
        json += "\"name\":";
        json += "\"" + name + "\",";
        json += "\"maxTableNo\":";
        json += "\"" + maxTableNo + "\"";
        json += "}";
        
        try{
            File currentDir = new File("");
            String s = currentDir.getAbsolutePath();
            System.out.println(s);
            PrintWriter writer = new PrintWriter(s + "\\web\\info.json", "UTF-8");
            writer.println(json);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return json;
    }
    
}
