package datamodel;

import java.util.Date;

public class Lei {
    private int id, num;
    private Date data;
    private String agrupamento, descricao;

    public Lei(int id, int num, Date data, String agrupamento, String descricao) {
        this.id = id;
        this.num = num;
        this.data = data;
        this.descricao = descricao;
        this.agrupamento = agrupamento;
    }
    
    public Lei(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
    
}
