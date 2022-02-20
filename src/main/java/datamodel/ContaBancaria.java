package datamodel;

import org.json.JSONObject;

public class ContaBancaria implements JSONTransform{
    private long id;
    private String nome, agencia, num, tipo;
    private boolean status;

    public ContaBancaria(long id, String agencia, String nome, String num, String tipo, boolean status) {
        this.id = id;
        this.nome = nome;
        this.num = num;
        this.tipo = tipo;
        this.agencia = agencia;
        this.status = status;
    }
    
    public ContaBancaria(){}
    
    public ContaBancaria(String json){
        JSONObject j = new JSONObject(json);
        
        this.id = j.getLong("id");
        this.nome = j.getString("nome");
        this.num = j.getString("num");
        this.tipo = j.getString("tipo");
        this.agencia = j.getString("agencia");
        this.status = j.getBoolean("status");
    }
    
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

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public boolean isActive() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ContaBancaria{" + "id=" + id + ", nome=" + nome + ", agencia=" + 
                agencia + ", num=" + num + ", tipo=" + tipo + ", status=" + status + '}';
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        
        json.put("id", id);
        json.put("nome", nome);
        json.put("agencia", agencia);
        json.put("num", num);
        json.put("tipo", tipo);
        json.put("status", status);
        
        return json;
    }
    
}
