package src;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class Menu {

    private String name;
    private ArrayList<Item> items;
    private ArrayList<Discount> discounts;
    private int daysActive;
    private int hoursActive;
    private boolean active;
    
    public Menu(String name, boolean active){
        this.name = name;
        this.items = new ArrayList<>();
        this.discounts = new ArrayList<>();
        this.active = active;
    }
    
    public String toJSON(){
        String json = "";
        
        json += "{";
        json += "\"name\":";
        json += "\"" + this.name + "\",";
        json += "\"items\":";
        json += getItemsCSV() + ",";
        json += "\"active\":";
        json += Boolean.toString(active);
        json += "}";
        
        return json;
    }
    
    private boolean isActive(int hours, int days){
        
        if(isDay(days) && isHour(hours)){
            return true;
        }
        
        return false;
    }
    
    private boolean isHour(int hours){
        String s = Integer.toBinaryString(hours);
        Date date = new Date();   // given date
        Calendar calendar = Calendar.getInstance(); // creates a new calendar instance
        calendar.setTime(date);   // assigns calendar to given date 
        int h = calendar.get(Calendar.HOUR_OF_DAY);
        
        return s.charAt(h) == '1';
    }
    
    private boolean isDay(int days){
        String s = Integer.toBinaryString(days);
        
        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        int currentDay = calendar.get(Calendar.DAY_OF_WEEK);
        
        return s.charAt(currentDay) == '1';
    }


    public void addItem(Item i){
        this.items.add(i);
    }
    
    public void removeItem(Item i){
        this.items.remove(i);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public String getItemsCSV(){
        String str = "";
        
        for(int i = 0; i < items.size(); i++){
            if(i>0){str += ";";}
            str += Integer.toString(items.get(i).getId());
        }
        
        return str;
    }
    
    public String getDiscountsCSV(){
        String str = "";
        
        for(int i = 0; i < discounts.size(); i++){
            if(i>0){str += ";";}
            str += Integer.toString(discounts.get(i).getId());
        }
        
        return str;
    }
    
    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public boolean isActive() {
        return active;
    }
    
    public int isActiveInt(){
        if(active){
            return 1;
        } else {
            return 0;
        }
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public ArrayList<Discount> getDiscounts() {
        return discounts;
    }

    public void setDiscounts(ArrayList<Discount> discounts) {
        this.discounts = discounts;
    }

    public int getDaysActive() {
        return daysActive;
    }

    public void setDaysActive(int daysActive) {
        this.daysActive = daysActive;
    }

    public int getHoursActive() {
        return hoursActive;
    }

    public void setHoursActive(int hoursActive) {
        this.hoursActive = hoursActive;
    }
    
    

    
    
    
    
}
