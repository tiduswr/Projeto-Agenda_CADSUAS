package datamodel;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.json.JSONObject;

public class Lei implements JSONTransform{
    private long id;
    private int num;
    private Date data;
    private String agrupamento, descricao;

    public Lei(long id, int num, Date data, String agrupamento, String descricao) {
        this.id = id;
        this.num = num;
        this.data = data;
        this.descricao = descricao;
        this.agrupamento = agrupamento;
    }
    
    public Lei(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getAgrupamento() {
        return agrupamento;
    }

    public void setAgrupamento(String agrupamento) {
        this.agrupamento = agrupamento;
    }

    @Override
    public String toString() {
        return "Lei{" + "id=" + id + ", num=" + num + ", data=" + data + 
                ", agrupamento=" + agrupamento + ", descricao=" + descricao + '}';
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        
        json.put("id", id);
        json.put("num", num);
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        json.put("data", sdf.format(data));
        
        json.put("agrupamento", agrupamento);
        json.put("descricao", descricao);
        
        return json;
    }
    
}
