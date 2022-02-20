package datamodel;

import org.json.JSONObject;

public class Vinculo implements JSONTransform{
    private long id, idEquip;
    private String cpf;
    private Cargo cargo;

    public Vinculo(int id, int idEquip, String cpf, Cargo cargo) {
        this.id = id;
        this.idEquip = idEquip;
        this.cpf = cpf;
        this.cargo = cargo;
    }

    public Vinculo(){}
    
    public Vinculo(String json){
        JSONObject j = new JSONObject(json);
        
        this.id = j.getLong("id");
        this.idEquip = j.getLong("idEquip");
        this.cpf = j.getString("cpf");
        this.cargo = Cargo.getByJson(j.get("cargo").toString());
    
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdEquip() {
        return idEquip;
    }

    public void setIdEquip(long idEquip) {
        this.idEquip = idEquip;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    @Override
    public String toString() {
        return "Vinculo{" + "id=" + id + ", idEquip=" + idEquip + ", cpf=" + cpf + ", cargo=" + cargo + '}';
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        
        json.put("id", id);
        json.put("idEquip", idEquip);
        json.put("cpf", cpf);
        
        JSONObject jc = new JSONObject();
        jc.put("code", cargo.getValue());
        jc.put("nome", cargo.toString());
        
        json.put("cargo", jc);
        
        return json;
    }
    
}
