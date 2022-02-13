package datamodel;

import java.util.Date;

public class RG {
    private String numero, og, uf;
    private Date dtEmissao;

    public RG(String numero, String og, String uf, Date dtEmissao) {
        this.numero = numero;
        this.og = og;
        this.uf = uf;
        this.dtEmissao = dtEmissao;
    }
    
    public RG(){}
    
    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getOg() {
        return og;
    }

    public void setOg(String og) {
        this.og = og;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public Date getDtEmissao() {
        return dtEmissao;
    }

    public void setDtEmissao(Date dtEmissao) {
        this.dtEmissao = dtEmissao;
    }
    
}
