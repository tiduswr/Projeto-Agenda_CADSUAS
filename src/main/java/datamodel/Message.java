package datamodel;

import org.json.JSONObject;

public class Message implements JSONTransform{
    private String title, message, type;

    public Message(String title, String message, String type) {
        this.title = title;
        this.message = message;
        this.type = type;
    }
    
    public Message(String json){
        JSONObject j = new JSONObject(json);
        
        this.title = j.getString("title");
        this.message = j.getString("message");
        this.type = j.getString("type");
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        
        json.put("title", title);
        json.put("message", message);
        json.put("type", type);
        
        return json;
    }
    
}
