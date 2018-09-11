package src;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;

public class Order {

    private int id;
    private int tableNo;
    private ArrayList<Item> items;
    private double cost;
    private Timestamp timeStamp;
    private DBManager db = new DBManager();
    private String request;

    public Order(int id, int tableNo, ArrayList<Item> items, double cost, Timestamp timeStamp, String request) {
        this.id = id;
        this.tableNo = tableNo;
        this.items = items;
        this.cost = applyDiscounts(items);
        this.timeStamp = timeStamp;
        this.request = request;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTableNo() {
        return tableNo;
    }

    public void setTableNo(int tableNo) {
        this.tableNo = tableNo;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Timestamp getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Timestamp timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String toSimpleString() {
        String str = "";
        for (Item i : items) {
            str += i.getName() + ", ";
        }
        
        if(tableNo != 0){
            return "Table " + Integer.toString(tableNo) + ": " + str;
        } else {
            return "Bar : " + str;
        }
    }

    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", tableNo=" + tableNo + ", items=" + items + ", cost=" + cost + ", timeStamp=" + timeStamp + '}';
    }

    public String getItemsPrinted(){
        DecimalFormat df = new DecimalFormat("#.00"); 

        String str = "";
        double sum = 0.00;
        for (int i = 0; i < items.size(); i++) {
            str += items.get(i).getName();
            str += "\t " + df.format(items.get(i).getCost()) + "\n";
            sum += items.get(i).getCost();
        }

        str += "\nTotal: " + df.format(sum);
        return str;
    }
    
    public String getItemsCSV() {
        String str = "";

        for (int i = 0; i < items.size(); i++) {
            if (i > 0) {
                str += ";";
            }
            str += Integer.toString(items.get(i).getId());
        }

        return str;
    }

    public double applyDiscounts(ArrayList<Item> items) {
        double sum = 0.00;
        ArrayList<Discount> discounts = db.getDiscounts();

        for (Item i : items) {
            sum += i.getCost();
            i.isChecked = false;
        }
        
        for(Discount d : discounts){
            for (int i = 0; i < items.size(); i++) {
                if(!items.get(i).isChecked && items.get(i).hasTag(d.getFirstTag())){
                    for (int j = 0; j < items.size(); j++) {
                        if(!items.get(j).isChecked && items.get(j).hasTag(d.getSecondTag())){
                            sum -= d.getReduction();
                        }
                        items.get(j).isChecked = true;
                    }
                }
                items.get(i).isChecked = true;
            }
            
        }
    
        return sum;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    
    
    
}
