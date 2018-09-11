package src;

import java.text.DecimalFormat;

public class Discount {
    private int id;
    private String firstTag;
    private String secondTag;
    private double reduction;

    public Discount(int id, String firstTag, String secondTag, double reduction) {
        this.id = id;
        this.firstTag = firstTag;
        this.secondTag = secondTag;
        this.reduction = reduction;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstTag() {
        return firstTag;
    }

    public void setFirstTag(String firstTag) {
        this.firstTag = firstTag;
    }

    public String getSecondTag() {
        return secondTag;
    }

    public void setSecondTag(String secondTag) {
        this.secondTag = secondTag;
    }

    public double getReduction() {
        return reduction;
    }

    public void setReduction(double reduction) {
        this.reduction = reduction;
    }
    
    @Override
    public String toString(){
        DecimalFormat df = new DecimalFormat("#.00");
        String str = "";
        str += firstTag + " + " + secondTag + ": " + df.format(reduction);
        return str;
    }
    
}
