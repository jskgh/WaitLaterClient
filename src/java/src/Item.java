package src;

public class Item {

    private int id;
    private String name;
    private String desc;
    private double cost;
    private String[] allergens;
    private String[] tags;
    public boolean isChecked;

    public Item(){
        
    }
    
    public Item(int id, String name, String desc, double cost, String[] allergens, String[] tags) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.cost = cost;
        this.allergens = allergens;
        this.tags = tags;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String[] getAllergens() {
        return allergens;
    }
    
    public String getAllergensJSON() {
        String str = "[";
        for (int i = 0; i < allergens.length;i++){
            if(i>0){ str += ";";}
            str += allergens[i];
        }
        str+="]";
        return str;
    }
    
    public String getAllergensCSV() {
        String str = "";
        for (int i = 0; i < allergens.length;i++){
            if(i>0){str += ";";}
            str += allergens[i];
        }
        return str;
    }
    

    public void setAllergens(String[] allergens) {
        this.allergens = allergens;
    }

    public String[] getTags() {
        return tags;
    }
    
    public String getTagsCSV() {
        String str = "";
        for (int i = 0; i < tags.length;i++){
            if(i>0){str += ";";}
            str += tags[i];
        }
        return str;
    }
    
    public String getTagsJSON(){
        String str = "[";
        for (int i = 0; i < tags.length;i++){
            if(i>0){str += ";";}
            str += tags[i];
        }
        str+="]";
        return str;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    
    public String arrayToString(String format, String array){
        boolean isJSON = true;
        if(format.equals("CSV")){
            isJSON = false;
        }
        
        String[] arr = allergens;
        if(array.equals("tags")){
            arr = tags;
        }
        
        String str = "";
        if(isJSON){
            str = "[";
        }
        
        for (int i = 0; i < arr.length;i++){
            if(i>0){str += ";";}
            str += arr[i];
        }
        
        if(isJSON){
            str += "]";
        }
        
        return str;
    }
        
    public String toJSON(){
        String json = "";
        
        
        json += "{";
        json += "\"id\":";
        json += "\"" + this.id + "\",";
        json += "\"name\":";
        json += "\"" + this.name + "\",";
        json += "\"description\":";
        json += "\"" + this.desc + "\",";
        json += "\"cost\":";
        json += this.cost + ",";
        json += "\"allergens\":";
        json += "\"" + getAllergensCSV() + "\",";
        json += "\"tags\":";
        json += "\"" + getTagsCSV() + "\"";
        json += "}";
        
        return json;
        
        
    }

    public boolean hasTag(String tag){
        for (int i = 0; i < tags.length; i++) {
            if(tag.equals(tags[i])){
                return true;
            }
        }
        return false;
    }
    
    @Override
    public String toString() {
        return "Item{" + "name=" + name + '}';
    }

    
   
    
    
}
