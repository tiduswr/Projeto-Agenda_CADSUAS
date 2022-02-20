package datamodel;

import java.text.SimpleDateFormat;
import org.json.JSONObject;

public class Municipio implements JSONTransform{
    private long id;
    private String nome, uf, iconPath;

    public Municipio(long id, String nome, String uf, String iconPath) {
        this.id = id;
        this.nome = nome;
        this.uf = uf;
        this.iconPath = iconPath;
    }
    
    public Municipio(){}
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    @Override
    public String toString() {
        return "Municipio{" + "id=" + id + ", nome=" + nome + ", uf=" + uf + ", iconPath=" + iconPath + '}';
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        
        json.put("id", id);
        json.put("nome", nome);
        json.put("uf", uf);
        json.put("iconPath", iconPath);
        
        return json;
    }
    
}
