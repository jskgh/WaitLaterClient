package src;

import java.util.ArrayList;

public class DiscountManager {
    private ArrayList<Discount> discounts;
    private DBManager db;

    public DiscountManager() {
        this.discounts = new ArrayList<>();
        this.db = new DBManager();
    }
    
    
    public ArrayList<Discount> getDiscounts(){
        ArrayList<Discount> d = db.getDiscounts();
        return d;
    }
    
    public void addDiscount(Discount discount){
        db.addDiscount(discount);
    }
    
    public void removeDiscount(Discount discount){
        db.removeDiscount(discount);
    }
}
