package datamodel;

import org.json.JSONObject;

public class EmailComSenha implements JSONTransform{
    private long id;
    private String email, senha, tipo;

    public EmailComSenha(long id, String email, String senha, String tipo) {
        this.id = id;
        this.email = email;
        this.senha = senha;
        this.tipo = tipo;
    }
    
    public EmailComSenha(){}
    
    public EmailComSenha(String json){
        JSONObject j = new JSONObject(json);
        
        this.id = j.getLong("id");
        this.email = j.getString("email");
        this.senha = j.getString("senha");
        this.tipo = j.getString("tipo");
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    @Override
    public JSONObject toJson(){
        JSONObject json = new JSONObject();
        
        json.put("id", id);
        json.put("email", email);
        json.put("senha", senha);
        json.put("tipo", tipo);
        
        return json;
    }
}
